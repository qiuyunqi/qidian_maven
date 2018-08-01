package com.xiaohe.qd.controller.qidapc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaCollection;
import com.xiaohe.qd.model.QidaConcern;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaScore;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.MoneyDetailUtil;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaCollectionService;
import com.xiaohe.qd.service.QidaConcernService;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaScoreService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.service.QidaTagsService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.SysConfigService;
import com.xiaohe.qd.util.ReplaceUtil;
import com.xiaohe.qd.util.WebUtil;

/**
 * 奇答关于用户信息的操作
 * 
 * @author han
 * 
 */
@Controller
@RequestMapping("/ai")
@Scope("prototype")
public class UserController extends BaseController {

	public final static String FILE_NAME = "qida/me/";
	public final static String SUFFIX = ".html";
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaAnswerService answerService;
	@Resource
	private QidaQuestionService questionService;
	@Resource
	private QidaConcernService concernService;
	@Resource
	private QidaCollectionService collectionService;
	@Resource
	private QidaScoreService qidaScoreService;
	@Resource
	private StockInfoService stockService;
	@Resource
	private QidaTagsService tagsService;
	@Resource
	private MoneyDetailUtil moneyDetailUtil;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private QidaSwitchService qidaSwitchService;
	@Resource
	private SysConfigService sysConfigService;

	/**
	 * 主页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/meindex" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String index(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		// 用户信息
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 10;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fuUserId", fuUser.getId());
		map.put("state", 1);
		// 我的答案
		List<QidaAnswer> answerList = answerService.findList((pageNo - 1) * pageSize, pageSize, map);

		Integer answerNum = answerService.getCount(map);
		Integer questionNum = questionService.getCount(map);
		Integer collectionNum = collectionService.getCount(fuUser.getId());
		request.setAttribute("answerNum", answerNum);
		request.setAttribute("questionNum", questionNum);
		request.setAttribute("collectionNum", collectionNum);

		request.setAttribute("answerList", answerList);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalCount", answerNum);

		request.setAttribute("fuUser", fuUser);

		leftCommon(request, fuUser);
		return FILE_NAME + "meIndex";
	}

	/**
	 * 兑换积分
	 * 
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exchange" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String exchange(Integer pageNo, HttpServletRequest request) {
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", fuUser.getId());
			Integer totalCount = qidaScoreService.getCount(map);
			List<QidaScore> moneyDetailList = qidaScoreService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map);
			request.setAttribute("moneyDetailList", moneyDetailList);
			request.setAttribute("pageSize", this.getPageSize());
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("totalCount", totalCount);

			request.setAttribute("ConvertScorePercent", sysConfigService.getByCode("001").getValue());
			request.setAttribute("ConvertMoneyPercent", sysConfigService.getByCode("002").getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FILE_NAME + "exchange";
	}

	/**
	 * 兑换积分
	 * 
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exchangeScore" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String exchangeScore(Integer money, HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			// 减少余额，增加积分
			fuUser.setAccountBalance(fuUser.getAccountBalance().subtract(new BigDecimal(money)));
			fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).add(new BigDecimal(money).multiply(sysConfigService.getByCode("001").getValue())));
			fuUserService.save(fuUser);
			// 保存兑换积分产生的明细
			moneyDetailUtil.saveNewFuMoneyDetailByQd(fuUser, 50, new BigDecimal(money), fuUser.getAccountBalance(), false);

			qidaScoreUtil.saveScoreByQd(fuUser, 50, new BigDecimal(money).multiply(sysConfigService.getByCode("001").getValue()), new BigDecimal(money), fuUser.getQidaIntegral(), 1);
			obj.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("success", 0);
		}
		return obj.toString();
	}

	/**
	 * 兑换余额
	 * 
	 * @param score
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/exchangeBlance" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String exchangeBlance(Integer score, HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			// 增加余额，减少积分
			fuUser.setAccountBalance((fuUser.getAccountBalance() == null ? BigDecimal.ZERO : fuUser.getAccountBalance()).add(new BigDecimal(score).multiply(sysConfigService.getByCode("002")
					.getValue())));
			fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).subtract(new BigDecimal(score)));
			fuUserService.save(fuUser);
			// 保存兑换余额产生的明细
			moneyDetailUtil.saveNewFuMoneyDetailByQd(fuUser, 52, new BigDecimal(score).multiply(sysConfigService.getByCode("002").getValue()), fuUser.getAccountBalance(), true);

			qidaScoreUtil.saveScoreByQd(fuUser, 52, new BigDecimal(score), new BigDecimal(score).multiply(sysConfigService.getByCode("002").getValue()), fuUser.getQidaIntegral(), 0);
			obj.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("success", 0);
		}
		return obj.toString();
	}

	/**
	 * 左侧公共部分
	 * 
	 * @param request
	 * @param userId
	 */
	private void leftCommon(HttpServletRequest request, FuUser fuUser) {
		// 粉丝
		List<QidaConcern> fansList = concernService.findByWatchMe(fuUser.getId());
		request.setAttribute("fansList", fansList);
		// 用户关注
		List<QidaConcern> concernList = concernService.findByMySelf(fuUser.getId());
		request.setAttribute("concernNum", null != concernList && concernList.size() > 0 ? concernList.size() : 0);
		request.setAttribute("concernList", concernList);
		request.setAttribute("fuUser", fuUser);
	}

	/**
	 * 我的问题
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/questionAll" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String questionAll(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 10;
		}
		// 左侧公共部分
		leftCommon(request, fuUser);
		// 用户问过的问题
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fuUserId", fuUser.getId());
		map.put("state", 1);
		List<QidaQuestion> questionList = questionService.findByMySelf((pageNo - 1) * pageSize, pageSize, fuUser.getId());
		Integer answerNum = answerService.getCount(map);
		Integer questionNum = questionService.getCount(map);
		Integer collectionNum = collectionService.getCount(fuUser.getId());
		request.setAttribute("answerNum", answerNum);
		request.setAttribute("questionNum", questionNum);
		request.setAttribute("collectionNum", collectionNum);

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalCount", questionNum);
		request.setAttribute("questionList", questionList);
		return FILE_NAME + "myQs";
	}

	/**
	 * 我的关注
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/concernAll" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String concernAll(HttpServletRequest request) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fuUserId", fuUser.getId());
		map.put("state", 1);
		Integer answerNum = answerService.getCount(map);
		Integer questionNum = questionService.getCount(map);
		Integer collectionNum = collectionService.getCount(fuUser.getId());
		request.setAttribute("answerNum", answerNum);
		request.setAttribute("questionNum", questionNum);
		request.setAttribute("collectionNum", collectionNum);
		// 左侧公共部分
		leftCommon(request, fuUser);
		return FILE_NAME + "myFollow";
	}

	/**
	 * 我的收藏
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/collectionAll" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String collectionAll(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fuUserId", fuUser.getId());
		map.put("state", 1);
		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 10;
		}
		// 左侧公共部分
		leftCommon(request, fuUser);

		// 用户收藏
		List<QidaCollection> collectionList = collectionService.findByMySelf((pageNo - 1) * pageSize, pageSize, fuUser.getId());
		Integer answerNum = answerService.getCount(map);
		Integer questionNum = questionService.getCount(map);
		Integer collectionNum = collectionService.getCount(fuUser.getId());
		request.setAttribute("answerNum", answerNum);
		request.setAttribute("questionNum", questionNum);
		request.setAttribute("collectionNum", collectionNum);

		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("totalCount", collectionNum);
		request.setAttribute("collectionList", collectionList);
		return FILE_NAME + "bookMark";
	}

	/**
	 * 编辑个人图像
	 * 
	 * @param userAvatar
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editUserAvatar" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editUserAvatar(String userAvatar, HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			fuUser.setUserAvatar(userAvatar);
			fuUserService.save(fuUser);
			obj.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("success", 0);
		}
		return obj.toString();
	}

	/**
	 * 关注或取消关注
	 * 
	 * @param request
	 * @param userId
	 *            作者
	 * @param falg
	 *            1: 代表关注 2: 取消关注
	 * @return
	 */
	@RequestMapping(value = "/watch/{falg}" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String watch(HttpServletRequest request, Long userId, @PathVariable Integer falg) {
		JSONObject obj = new JSONObject();
		if (null == userId || 0 == userId) {
			obj.put("is_message", -1); // 失败
		} else {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (userId.equals(fuUser.getId())) {
				obj.put("is_message", -2); // 失败 自己不能关注自己
				return obj.toString();
			} else if (falg == 1) {
				QidaConcern concern = new QidaConcern();
				concern.setConcernUser(fuUser);
				concern.setBeConcernUser(fuUserService.get(userId));
				concern.setConcernTime(new Date());
				concernService.save(concern);
				obj.put("is_message", 1);
			} else if (falg == 2) {
				concernService.saveUnWatch(fuUser, fuUserService.get(userId));
				obj.put("is_message", 2);
			}
		}
		return obj.toString();
	}

	/**
	 * 更新用户的签名
	 * 
	 * @param request
	 * @param introduction
	 *            签名
	 * @return
	 */
	@RequestMapping(value = "/upIntroduction" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String upIntroduction(HttpServletRequest request, String introduction) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		JSONObject obj = new JSONObject();
		if (null == introduction || "".equals(introduction)) {
			obj.put("num", 1); // 失败
		} else if (null != fuUser.getIntroduction() && fuUser.getIntroduction().trim().equals(introduction.trim())) {
			obj.put("num", 1);
		} else {
			fuUser.setIntroduction(introduction);
			fuUserService.save(fuUser);
			obj.put("num", 2);
			obj.put("content", introduction);
		}
		return obj.toString();
	}

	/**
	 * 奇答支出记录
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/payrd" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String payrecord(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 10;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isIncome", 0);// 支出
		map.put("userId", fuUser.getId());
		map.put("pid", 7L); // 7: 字典表的奇答
		List<QidaScore> findListBy = qidaScoreService.findList((pageNo - 1) * pageSize, pageSize, map);
		Integer count = qidaScoreService.getCount(map);
		request.setAttribute("totalCount", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("findListBy", findListBy);
		return FILE_NAME + "payrd";
	}

	/**
	 * 奇答收款记录
	 * 
	 * @param request
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/incomerd" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String incomerd(HttpServletRequest request, Integer pageNo, Integer pageSize) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		if (null == pageNo || null == pageSize) {
			pageNo = 1;
			pageSize = 10;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isIncome", 1);// 收入
		map.put("userId", fuUser.getId());
		map.put("pid", 7L); // 7: 字典表的奇答
		List<QidaScore> findListBy = qidaScoreService.findList((pageNo - 1) * pageSize, pageSize, map);
		Integer count = qidaScoreService.getCount(map);
		request.setAttribute("totalCount", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("findListBy", findListBy);
		return FILE_NAME + "incomerd";
	}

	/**
	 * 用户提问
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/questionAsk" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String questionAsk(HttpServletRequest request) {
		FuUser fu = (FuUser) request.getSession().getAttribute("fuUser");
		fu = fuUserService.get(fu.getId());
		// 克隆一个新的对象
		FuUser fuUser = (FuUser) fu.clone();
		String phone = fuUser.getPhone();
		if (null != phone && !"".equals(phone)) {
			phone = phone.replace(phone.substring(3, 7), "****");
			fuUser.setPhone(phone);
		}
		// 热门标签
		// List<QidaTags> tagList = tagsService.findByIsHot(1);
		List<QidaTags> tagList = tagsService.findOrderByHot(0, 20);
		request.setAttribute("tagList", tagList);
		request.setAttribute("fuUser", fuUser);
		return "qida/qidaHome/questionAsk";
	}

	/**
	 * 检测短信通知是否有足够的积分
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkMsg" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkMsg(HttpServletRequest request) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		BigDecimal integral = fuUser.getQidaIntegral();
		BigDecimal subtractValue = (null == integral ? BigDecimal.ZERO : integral).subtract(new BigDecimal(0.1));
		JSONObject obj = new JSONObject();
		if ((subtractValue).compareTo(BigDecimal.ZERO) == 0 || (subtractValue).compareTo(BigDecimal.ZERO) == 1) {
			obj.put("success", 1); // 积分足够
		} else {
			obj.put("success", 0); // 积分不足
		}
		return obj.toString();
	}

	/**
	 * 检测答案悬赏的积分是否足够
	 * 
	 * @param isMsg
	 *            1: 选中了发送短信通知 其他表示没有
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkReward" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String checkReward(HttpServletRequest request, Integer isMsg, BigDecimal rewardValue) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		BigDecimal integral = fuUser.getQidaIntegral();
		if (isMsg == 1) {
			rewardValue = rewardValue.add(new BigDecimal(0.1));
		}
		BigDecimal subtractValue = (null == integral ? BigDecimal.ZERO : integral).subtract(rewardValue);
		JSONObject obj = new JSONObject();
		if ((subtractValue).compareTo(BigDecimal.ZERO) == 0 || (subtractValue).compareTo(BigDecimal.ZERO) == 1) {
			obj.put("success", 1); // 积分足够
		} else {
			obj.put("success", 0); // 积分不足
		}
		return obj.toString();
	}

	@RequestMapping(value = "saveTag" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveTag(HttpServletRequest request, String tagName) {
		JSONObject obj = new JSONObject();
		try {
			if (null == tagName || "".equals(tagName)) {
				obj.put("success", 0);
				obj.put("message", "请输入标签名称");
				return obj.toString();
			}

			if (tagName.length() > 8) {
				obj.put("success", 0);
				obj.put("message", "标签长度最大是8个字");
				return obj.toString();
			}
			QidaTags tags = tagsService.findByTagName(tagName.trim());
			if (null == tags) {
				QidaTags tag = new QidaTags();
				tag.setName(tagName);
				tag.setParentId(0L);
				tag.setIsHot(0);
				tagsService.save(tag);
				obj.put("success", 1);
				obj.put("flag", 1);
				obj.put("id", tag.getId());
				obj.put("message", "创建标签成功");
			} else {
				obj.put("success", 1);
				obj.put("id", tags.getId());
				obj.put("flag", 0);
				obj.put("message", "创建标签成功");

			}
			return obj.toString();
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("success", 0);
			obj.put("message", "创建标签失败, 请联系管理员");
			return obj.toString();

		}
	}

	/**
	 * 保存问题
	 * 
	 * @param request
	 * @param title
	 *            标题
	 * @param code
	 *            股票代码
	 * @param content
	 *            问题详细
	 * @param isMsg
	 *            是否发送短信 1: 发送 0: 不发送
	 * @param rewardValue
	 *            悬赏值
	 * @param tags
	 *            标签
	 * @return
	 */
	@RequestMapping(value = "/saveQuestion" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveQuestion(HttpServletRequest request, String title, String stockIds, String content, String imgSrc, Integer isMsg, BigDecimal rewardValue, String tagIds) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		JSONObject obj = new JSONObject();
		if (null == title || "".equals(title)) {
			obj.put("success", 0);
			obj.put("message", "请输入问题标题");
			return obj.toString();
		}
		if (title.length() > 45) {
			obj.put("success", 0);
			obj.put("message", "问题标题过长, 最多45字");
			return obj.toString();
		}
		BigDecimal integral = fuUser.getQidaIntegral();
		BigDecimal subtractValue = (null == integral ? BigDecimal.ZERO : integral).subtract(rewardValue);
		if ((subtractValue).compareTo(BigDecimal.ZERO) == 0 || (subtractValue).compareTo(BigDecimal.ZERO) == -1) {
			obj.put("success", 0); // 积分不足
			obj.put("message", "您的积分不足");
			return obj.toString();
		}
		List<StockInfo> infoList = new ArrayList<StockInfo>();
		if (null != stockIds && !"".equals(stockIds)) {
			stockIds = stockIds.substring(0, stockIds.length() - 1);
			String[] c = stockIds.split(",");
			if (c.length > 5) {
				obj.put("success", 0);
				obj.put("message", "最多可以添加五个股票");
				return obj.toString();

			} else {
				for (String stockId : c) {
					StockInfo stockInfo = stockService.get(Long.parseLong(stockId));
					if (null != stockInfo) {
						infoList.add(stockInfo);
					}
				}
				if (null == infoList || infoList.size() <= 0) {
					obj.put("success", 0);
					obj.put("message", "您添加的股票代码不存在");
					return obj.toString();
				}
			}
		} else {
			infoList = null;
		}

		List<QidaTags> tagList = new ArrayList<QidaTags>();
		if (null != tagIds && !"".equals(tagIds)) {
			tagIds = tagIds.substring(0, tagIds.length() - 1);
			String[] tags = tagIds.split(",");
			if (tags.length > 5) {
				obj.put("success", 0);
				obj.put("message", "最多可以添加五个标签");
				return obj.toString();
			} else {
				for (String tagId : tags) {
					QidaTags qidaTags = tagsService.get(Long.parseLong(tagId));
					if (null != qidaTags) {
						tagList.add(qidaTags);
					}
				}
				if (null == tagList || tagList.size() <= 0) {
					obj.put("success", 0);
					obj.put("message", "您添加的标签不存在");
					return obj.toString();
				}
			}
		} else {
			tagList = null;
		}
		// 过滤关键词
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");
		title = ReplaceUtil.replaceCheck(keys, title);
		content = ReplaceUtil.replaceCheck(keys, content);

		int result = questionService.saveQuestion(fuUser, title.trim(), infoList, content, imgSrc, isMsg, rewardValue, tagList);
		if (result == 1) {
			obj.put("success", 1);
			obj.put("message", "提交问题成功");
		} else {
			obj.put("success", 0);
			obj.put("message", "提交问题失败, 请链接管理员");
		}
		return obj.toString();
	}

	/**
	 * 查看回答
	 */
	@RequestMapping(value = "/answerInfo/{id}" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String answerInfo(@PathVariable Long id, ModelMap model, HttpServletRequest request, Integer pageNo) {
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			QidaAnswer answer = answerService.get(id);
			model.put("answer", answer);

			QidaQuestion question = questionService.get(answer.getQidaQuestion().getId());
			question.setPageViews((question.getPageViews() == null ? 0 : question.getPageViews()) + 1);
			questionService.save(question);
			model.put("question", question);
			model.put("nowDate", new Date());

			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			model.put("fuUser", fuUser);
			if (fuUser != null) {
				// 用户关注
				List<QidaConcern> concernList = concernService.findByMySelf(fuUser.getId());
				model.put("concernList", concernList);
				// 用户收藏
				List<QidaCollection> collectionList = collectionService.findByMySelf(1, 100, fuUser.getId());
				model.put("collectionList", collectionList);
				// 是否关注
				int res = concernService.findIsWatch(fuUser, question.getFuUser());
				model.put("res", res);
				// 是否收藏
				QidaCollection collection = collectionService.findByQuestion(fuUser.getId(), question.getId());
				if (collection == null) {
					model.put("isCollection", 0);
				} else {
					model.put("isCollection", 1);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", question.getId());
			map.put("state", 1);
			Integer totalCount = answerService.getCount(map);
			List<QidaAnswer> qidaAnswers = answerService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map);
			model.put("totalCount", totalCount);
			model.put("pageNo", pageNo);
			model.put("pageSize", this.getPageSize());
			model.put("qidaAnswers", qidaAnswers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return FILE_NAME + "answerInfo";
	}

	/**
	 * 保存编辑后的答案
	 * 
	 * @param id
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/saveEditAnswer/{id}" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveEditAnswer(@PathVariable Long id, String content) {
		try {
			QidaAnswer answer = answerService.get(id);
			answer.setContent(content);
			answerService.save(answer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 编辑问题
	 * 
	 * @param request
	 * @param questionId
	 *            问题id
	 * @return
	 */
	@RequestMapping(value = "/editQuestion/{questionId}" + SUFFIX, produces = "text/html;charset=UTF-8")
	public String editQuestion(HttpServletRequest request, @PathVariable Long questionId) {
		QidaQuestion qidaQuestion = questionService.get(questionId);
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		if (null != qidaQuestion && (qidaQuestion.getFuUser().getId().longValue() == fuUser.getId().longValue())) {
			List<QidaTags> tagList = tagsService.findByIsHot(1);

			request.setAttribute("tagList", tagList);
			request.setAttribute("question", qidaQuestion);
			request.setAttribute("fuUser", fuUser);
		} else {
			if (qidaQuestion.getIsReward() == 0) {
				return "redirect:/web/qida/question/{questionId}.html";
			} else if (qidaQuestion.getIsReward() == 1) {
				return "redirect:/web/qida/questionPay/{questionId}.html";
			}
		}
		return FILE_NAME + "editQuestion";
	}

	@RequestMapping(value = "/updateQuestion" + SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateQuestion(HttpServletRequest request, Long id, String title, String stockIds, String content, String imgSrc, Integer isMsg, BigDecimal rewardValue, String tagIds) {
		FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
		fuUser = fuUserService.get(fuUser.getId());
		JSONObject obj = new JSONObject();
		if (null == id || 0 == id.longValue()) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请链接管理员");
			return obj.toString();
		}
		if (null == title || "".equals(title)) {
			obj.put("success", 0);
			obj.put("message", "请输入问题标题");
			return obj.toString();
		}
		if (title.length() > 45) {
			obj.put("success", 0);
			obj.put("message", "问题标题过长, 最多45字");
			return obj.toString();
		}
		BigDecimal integral = fuUser.getQidaIntegral();
		BigDecimal subtractValue = (null == integral ? BigDecimal.ZERO : integral).subtract(rewardValue);
		if ((subtractValue).compareTo(BigDecimal.ZERO) == 0 || (subtractValue).compareTo(BigDecimal.ZERO) == -1) {
			obj.put("success", 0); // 积分不足
			obj.put("message", "您的积分不足");
			return obj.toString();
		}
		List<StockInfo> infoList = new ArrayList<StockInfo>();
		if (null != stockIds && !"".equals(stockIds)) {
			stockIds = stockIds.substring(0, stockIds.length() - 1);
			String[] c = stockIds.split(",");
			if (c.length > 5) {
				obj.put("success", 0);
				obj.put("message", "最多可以添加五个股票");
				return obj.toString();

			} else {
				for (String stockId : c) {
					StockInfo stockInfo = stockService.get(Long.parseLong(stockId));
					if (null != stockInfo) {
						infoList.add(stockInfo);
					}
				}
				if (null == infoList || infoList.size() <= 0) {
					obj.put("success", 0);
					obj.put("message", "您添加的股票代码不存在");
					return obj.toString();
				}
			}
		} else {
			infoList = null;
		}
		tagIds = tagIds.substring(0, tagIds.length() - 1);
		QidaQuestion question = questionService.getId(id);
		if (null == question) {
			obj.put("success", 0);
			obj.put("message", "非法请求, 请链接管理员");
			return obj.toString();
		}

		// 过滤关键词
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");
		title = ReplaceUtil.replaceCheck(keys, title);
		content = ReplaceUtil.replaceCheck(keys, content);

		question.setTitle(title.trim());
		question.setDetail(content);
		question.setImgUrl(imgSrc);
		question.setState(qidaSwitchService.get(1L).getQuestionSwitch());
		question.setIsDelete(0);
		if (isMsg == 1) {
			question.setIsMessage(1);
		} else {
			question.setIsMessage(0);
		}
		/*
		 * if (null != rewardValue && rewardValue.compareTo(BigDecimal.ZERO) == 1) {
		 * 
		 * } else { question.setIsReward(0); }
		 */
		question.setIsReward(1);
		question.setReward(rewardValue.intValue());
		question.setUpdateTime(new Date());

		int result = questionService.updateQuestion(question, infoList, tagIds.trim());
		if (result == 1) {
			obj.put("success", 1);
			obj.put("message", "修改问题成功");
		} else {
			obj.put("success", 0);
			obj.put("message", "修改问题失败, 请联系管理员");
		}
		return obj.toString();
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout.html", produces = "text/html;charset=UTF-8")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			WebUtil.addCookie(response, "user_token", "", 1);
			WebUtil.addCookie(response, "token_name", "", 0);
			request.getSession().removeAttribute("fuUser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/qida/index.html";
	}

	/**
	 * 关注
	 */
	@RequestMapping(value = "/concern.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String concern(Long id, HttpServletRequest request) {
		JSONObject obj = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			QidaConcern concern = new QidaConcern();
			concern.setConcernUser(fuUser);
			concern.setBeConcernUser(fuUserService.get(id));
			concern.setConcernTime(new Date());
			concernService.save(concern);
			obj.put("message", "1");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("message", "0");
		}
		return obj.toString();
	}

	/**
	 * 设置播报声音提醒
	 */
	@RequestMapping(value = "/setVoice.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setVoice(Integer isVoice, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			fuUser.setIsVoice(isVoice);
			fuUserService.save(fuUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
