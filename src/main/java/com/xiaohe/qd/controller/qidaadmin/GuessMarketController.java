package com.xiaohe.qd.controller.qidaadmin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.GuessMarket;
import com.xiaohe.qd.model.GuessRecord;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.service.FuDictionaryService;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.GuessMarketService;
import com.xiaohe.qd.service.GuessRecordService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.util.DateUtil;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class GuessMarketController extends BaseController {
	@Resource
	private GuessMarketService guessMarketService;
	@Resource
	private FuDictionaryService fuDictionaryService;
	@Resource
	private GuessRecordService guessRecordService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaScoreUtil qidaScoreUtil;

	/**
	 * 猜猜乐列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/guessMarketList", produces = "text/html;charset=UTF-8")
	public String guessMarketList() {
		return "qida/qidaManage/guessMarketList";
	}

	/**
	 * 猜猜乐数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/guessMarketData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String guessMarketData(String name, Integer type, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (type != null) {
				map.put("type", type);
			}
			Integer total = guessMarketService.getCount(map);
			List<GuessMarket> list = guessMarketService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (GuessMarket market : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", market.getId());
					jsonObject.put("name", market.getName());
					jsonObject.put("guessScore", market.getGuessScore() == null ? "0.00" : market.getGuessScore());
					jsonObject.put("acceptFactor", market.getAcceptFactor());
					jsonObject.put("acceptResult", market.getAcceptResult() == null ? "" : market.getAcceptResult() == 1 ? "涨（奇数）" : market.getAcceptResult() == 2 ? "涨（偶数）"
							: market.getAcceptResult() == 3 ? "跌（奇数）" : "跌（偶数）");
					jsonObject.put("state", market.getState() == 1 ? "已结算" : "未结算");
					jsonObject.put("isClose", market.getIsClose() == 1 ? "已封盘" : "未封盘");
					jsonObject.put("type", market.getType() == 1 ? "下午盘" : "上午盘");
					jsonObject.put("acceptAdmin", market.getAcceptAdmin() == null ? "" : market.getAcceptAdmin().getName());
					jsonObject.put("acceptTime", market.getAcceptTime() == null ? "" : DateUtil.getStrFromDate(market.getAcceptTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonObject.put("createTime", market.getCreateTime() == null ? "" : DateUtil.getStrFromDate(market.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
					jsonArray.add(jsonObject);
				}
			}
			result.put("total", total);
			result.put("rows", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 添加或修改猜猜乐
	 * 
	 */
	@RequestMapping(value = "/addGuessMarket", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addGuessMarket(Long id, String name, String guessScore, String createTime, Integer state, Integer isClose, Integer type, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			GuessMarket market = new GuessMarket();
			if (id != null) {
				market = guessMarketService.get(id);
			}
			market.setCreateTime(DateUtil.getDateFromString(createTime, "yyyy-MM-dd HH:mm:ss"));
			market.setGuessScore(new BigDecimal(guessScore));
			market.setName(name);
			market.setState(state);
			market.setIsClose(isClose);
			market.setType(type);
			market.setFuDictionary(fuDictionaryService.get(54L));
			guessMarketService.save(market);

			result.put("success", true);
			result.put("msg", "保存成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 删除猜猜乐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delGuessMarket", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delGuessMarket(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			GuessMarket market = guessMarketService.get(id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("marketId", market.getId());
			List<GuessRecord> list = guessRecordService.findList(0, Integer.MAX_VALUE, map);
			if (list.size() > 0) {
				for (GuessRecord guessRecord : list) {
					guessRecordService.delete(guessRecord.getId());
				}
			}
			guessMarketService.delete(id);

			result.put("success", true);
			result.put("msg", "删除成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 结算猜猜乐
	 * 
	 * @return
	 */
	@RequestMapping(value = "/balanceMarket", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String balanceMarket(Long id, String acceptFactor, Integer acceptResult, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			SysAdmin admin = (SysAdmin) session.getAttribute("admin");
			GuessMarket market = guessMarketService.get(id);
			if (market.getState() == 1) {
				result.put("success", false);
				result.put("msg", "请勿重复结算！");
				return JSONObject.toJSONString(result);
			}
			market.setAcceptFactor(new BigDecimal(acceptFactor));
			market.setAcceptResult(acceptResult);
			market.setAcceptTime(new Date());
			market.setState(1);
			market.setIsClose(1);
			market.setAcceptAdmin(admin);
			guessMarketService.save(market);

			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("marketId", market.getId());
			List<GuessRecord> list2 = guessRecordService.findList(0, Integer.MAX_VALUE, map2);// 本期全部竞猜

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("guessType", acceptResult);
			map1.put("marketId", market.getId());
			List<GuessRecord> list1 = guessRecordService.findList(0, Integer.MAX_VALUE, map1);// 本期猜对的
			if (list2.size() > 0 && list1.size() > 0) {
				for (GuessRecord guessRecord : list1) {
					// 猜对的人获得的积分=竞猜积分*猜错人数/猜对人数
					BigDecimal score = market.getGuessScore().multiply(new BigDecimal(list2.size() - list1.size())).divide(new BigDecimal(list1.size()), 2, BigDecimal.ROUND_HALF_UP);
					FuUser user = guessRecord.getFuUser();
					user.setQidaIntegral((user.getQidaIntegral() == null ? BigDecimal.ZERO : user.getQidaIntegral()).add(score));
					fuUserService.save(user);
					// 积分明细
					qidaScoreUtil.saveScoreByQd(user, 57, score, BigDecimal.ZERO, user.getQidaIntegral(), 1);
					// 订单收益
					guessRecord.setOrderIncome(score);
					guessRecordService.save(guessRecord);
				}
			}

			result.put("success", true);
			result.put("msg", "结算成功！");
		} catch (Exception e) {
			e.printStackTrace();
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

}
