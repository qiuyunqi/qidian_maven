package com.xiaohe.qd.controller.qidapc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
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
import com.xiaohe.qd.model.FuSmsLog;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaAgree;
import com.xiaohe.qd.model.QidaAnswer;
import com.xiaohe.qd.model.QidaAnswerComment;
import com.xiaohe.qd.model.QidaCollection;
import com.xiaohe.qd.model.QidaConcern;
import com.xiaohe.qd.model.QidaMessage;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaQuestionComment;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.service.FuSmsLogService;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.QidaAgreeService;
import com.xiaohe.qd.service.QidaAnswerService;
import com.xiaohe.qd.service.QidaCollectionService;
import com.xiaohe.qd.service.QidaCommentService;
import com.xiaohe.qd.service.QidaConcernService;
import com.xiaohe.qd.service.QidaMessageService;
import com.xiaohe.qd.service.QidaPayQuestionService;
import com.xiaohe.qd.service.QidaQuestionService;
import com.xiaohe.qd.service.QidaScoreUtil;
import com.xiaohe.qd.service.QidaStockQuestionService;
import com.xiaohe.qd.service.QidaSwitchService;
import com.xiaohe.qd.service.QidaTagQuestionService;
import com.xiaohe.qd.service.QidaTagsService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.util.ReplaceUtil;

/**
 * 奇答社区（我）
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qida")
@Scope("prototype")
public class QidaQuestionContorller extends BaseController {
	@Resource
	private QidaQuestionService qidaQuestionService;
	@Resource
	private QidaTagsService qidaTagsService;
	@Resource
	private QidaAnswerService qidaAnswerService;
	@Resource
	private QidaCollectionService qidaCollectionService;
	@Resource
	private QidaAgreeService qidaAgreeService;
	@Resource
	private FuUserService fuUserService;
	@Resource
	private QidaConcernService qidaConcernService;
	@Resource
	private QidaTagQuestionService qidaTagQuestionService;
	@Resource
	private QidaStockQuestionService qidaStockQuestionService;
	@Resource
	private StockInfoService stockService;
	@Resource
	private QidaPayQuestionService qidaPayQuestionService;
	@Resource
	private FuSmsLogService fuSmsLogService;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private QidaCommentService qidaCommentService;
	@Resource
	private QidaMessageService messageService;
	@Resource
	private QidaSwitchService qidaSwitchService;

	/**
	 * 全部问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionAll.html", produces = "text/html;charset=UTF-8")
	public String questionAll(ModelMap model, String keyword, Integer pageNo) {
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			List<QidaTags> qidaTags = qidaTagsService.findList(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			model.put("qidaTags", qidaTags);
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("keyword", keyword);
			map1.put("isDelete", 0);
			Integer totalCount = qidaQuestionService.getCount(map1);
			List<QidaQuestion> questions = qidaQuestionService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map1);
			model.put("questions", questions);
			model.put("keyword", keyword);
			model.put("totalCount", totalCount);
			model.put("pageNo", pageNo);
			model.put("pageSize", this.getPageSize());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionsHot", "questionsHot");
			map.put("isDelete", 0);
			List<QidaQuestion> questionsHot = qidaQuestionService.findList(0, 10, map);
			model.put("questionsHot", questionsHot);
			model.put("nowDate", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/qidaHome/questionAll";
	}

	/**
	 * 待回答问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionNo.html", produces = "text/html;charset=UTF-8")
	public String questionNo(ModelMap model, Integer pageNo) {
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			List<QidaTags> qidaTags = qidaTagsService.findList(0, Integer.MAX_VALUE, new HashMap<String, Object>());
			model.put("qidaTags", qidaTags);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionNo", "questionNo");
			map.put("isDelete", 0);
			Integer totalCount = qidaQuestionService.getCount(map);
			List<QidaQuestion> questions = qidaQuestionService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map);
			model.put("questions", questions);
			model.put("totalCount", totalCount);
			model.put("pageNo", pageNo);
			model.put("pageSize", this.getPageSize());
			model.put("nowDate", new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/qidaHome/questionNo";
	}

	/**
	 * 免费问题详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/question/{id}.html", produces = "text/html;charset=UTF-8")
	public String question(@PathVariable Long id, Integer pageNo, ModelMap model, HttpServletRequest request) {
		try {
			if (pageNo == null) {
				pageNo = 1;
			}
			QidaQuestion question = qidaQuestionService.get(id);
			question.setPageViews((question.getPageViews() == null ? 0 : question.getPageViews()) + 1);
			qidaQuestionService.save(question);
			model.put("question", question);
			model.put("nowDate", new Date());
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			model.put("fuUser", fuUser);
			if (fuUser != null) {
				// 用户关注
				List<QidaConcern> concernList = qidaConcernService.findByMySelf(fuUser.getId());
				model.put("concernList", concernList);
				// 用户收藏
				List<QidaCollection> collectionList = qidaCollectionService.findByMySelf(1, 100, fuUser.getId());
				model.put("collectionList", collectionList);
				// 是否关注
				int res = qidaConcernService.findIsWatch(fuUser, question.getFuUser());
				model.put("res", res);
				// 是否收藏
				QidaCollection collection = qidaCollectionService.findByQuestion(fuUser.getId(), id);
				if (collection == null) {
					model.put("isCollection", 0);
				} else {
					model.put("isCollection", 1);
				}
				// 是否回答过
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("questionId", id);
				map1.put("fuUserId", fuUser.getId());
				Integer isAnswer = qidaAnswerService.getCount(map1);
				model.put("isAnswer", isAnswer);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", id);
			map.put("state", 1);
			map.put("isDelete", 0);
			Integer totalCount = qidaAnswerService.getCount(map);
			List<QidaAnswer> qidaAnswers = qidaAnswerService.findList((pageNo - 1) * this.getPageSize(), this.getPageSize(), map);
			model.put("totalCount", totalCount);
			model.put("pageNo", pageNo);
			model.put("pageSize", this.getPageSize());
			model.put("qidaAnswers", qidaAnswers);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/questionDetail/question";
	}

	/**
	 * 付费问题详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionPay/{id}.html", produces = "text/html;charset=UTF-8")
	public String questionPay(@PathVariable Long id, ModelMap model, HttpServletRequest request) {
		try {
			QidaQuestion question = qidaQuestionService.get(id);
			question.setPageViews((question.getPageViews() == null ? 0 : question.getPageViews()) + 1);
			qidaQuestionService.save(question);
			model.put("question", question);
			model.put("nowDate", new Date());
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			model.put("fuUser", fuUser);
			if (fuUser != null) {
				// 用户关注
				List<QidaConcern> concernList = qidaConcernService.findByMySelf(fuUser.getId());
				model.put("concernList", concernList);
				// 用户收藏
				List<QidaCollection> collectionList = qidaCollectionService.findByMySelf(1, 100, fuUser.getId());
				model.put("collectionList", collectionList);
				// 是否关注
				int res = qidaConcernService.findIsWatch(fuUser, question.getFuUser());
				model.put("res", res);
				// 是否收藏
				QidaCollection collection = qidaCollectionService.findByQuestion(fuUser.getId(), id);
				if (collection == null) {
					model.put("isCollection", 0);
				} else {
					model.put("isCollection", 1);
				}
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qidaRank", 1);
			Integer totalCount = fuUserService.getCount(map);
			model.put("totalCount", totalCount);
			long nowDate = new Date().getTime();
			long questionDate = question.getUpdateTime().getTime();
			int timeDiffer = (int) ((nowDate - questionDate) / 1000 / 60 / 60);// 访问时间和问题更新时间差
			model.put("timeDiffer", timeDiffer);
			model.put("size", question.getQuestionComments().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qida/questionDetail/questionPay";
	}

	/**
	 * 分页查询专家列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/questionExpert.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String questionExpert(Integer pageNo, Integer pageSize, ModelMap model,HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qidaRank", 1);
			map.put("answerUserId", fuUser.getId());
			List<FuUser> expertList = fuUserService.findList((pageNo - 1) * pageSize, pageSize, map);
			JSONArray array = new JSONArray();
			if (expertList != null && expertList.size() > 0) {
				for (FuUser user : expertList) {
					JSONObject obj = new JSONObject();
					obj.put("id", user.getId());
					obj.put("userAvatar", user.getUserAvatar() == null ? "../../images_qiDa/tx.png" : user.getUserAvatar());
					obj.put("nickName", user.getNickName() == null ? "佚名" : user.getNickName());
					array.add(obj);
				}
				json.put("array", array);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * 邀请专家解答付费问题
	 */
	@RequestMapping(value = "/InvitationAnswer/{expertId}/{questionId}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String InvitationAnswer(HttpServletRequest request, @PathVariable Long expertId, @PathVariable Long questionId) {
		try {
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			QidaQuestion question = qidaQuestionService.get(questionId);
			question.setAnswerUser(fuUserService.get(expertId));
			question.setUpdateTime(new Date());
			qidaQuestionService.save(question);

			QidaMessage message = new QidaMessage();
			message.setSendFuUser(question.getFuUser()); // 是谁发送
			message.setHttpCookie(UUID.randomUUID().toString());
			message.setIsRead(0);
			message.setIsDelete(0);
			message.setCreateTime(new Date());
			message.setMessage(null == question.getFuUser().getNickName() ? "佚名" : question.getFuUser().getNickName() + "邀请您回答<a href=" + url + "/web/qida/questionPay/" + question.getId() + ".html>"
					+ question.getTitle() + "</a>问题");
			message.setReceiveFuUser(fuUserService.get(expertId)); // 接受者
			messageService.save(message);
			message.setMessageId(message.getId());
			messageService.save(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存赞同或反对回答
	 */
	@RequestMapping(value = "/savaAgree/{id}/{type}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String savaAgree(@PathVariable Long id, @PathVariable Integer type, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				return "-1";
			}
			// 先查询用户是否顶过或踩过某评论
			Integer agreeType = qidaAgreeService.findByMySelf(fuUser.getId(), id);
			// 顶过
			if (agreeType == 0) {
				return "hasUp";
			}
			// 踩过
			if (agreeType == 1) {
				return "hasDown";
			}
			if (agreeType == -1) {
				// 更新回答的顶或踩数目
				QidaAnswer answer = qidaAnswerService.get(id);
				if (type.equals(0)) {
					answer.setRealSupportNum((answer.getRealSupportNum() == null ? 0 : answer.getRealSupportNum()) + 1);
				} else {
					answer.setRealNoSupportNum((answer.getRealNoSupportNum() == null ? 0 : answer.getRealNoSupportNum()) + 1);
				}
				qidaAnswerService.save(answer);

				QidaAgree agree = new QidaAgree();
				agree.setFuUser(fuUser);
				agree.setQidaAnswer(answer);
				agree.setType(type);
				agree.setCreateTime(new Date());
				qidaAgreeService.save(agree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 收藏问题
	 */
	@RequestMapping(value = "/collectQuestion/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String collectQuestion(@PathVariable Long id, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				return "-1";
			}
			QidaCollection collection = new QidaCollection();
			collection.setFuUser(fuUser);
			collection.setQidaQuestion(qidaQuestionService.get(id));
			collection.setCollectionTime(new Date());
			qidaCollectionService.save(collection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取消收藏问题
	 */
	@RequestMapping(value = "/cancelCollectQuestion/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelCollectQuestion(@PathVariable Long id, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				return "-1";
			}
			QidaCollection collection = qidaCollectionService.findByQuestion(fuUser.getId(), id);
			if (collection != null) {
				qidaCollectionService.delete(collection.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存免费问题回复的答案
	 * 
	 * @param id
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveAnswer.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveAnswer(Long id, String content, HttpServletRequest request) {
		try {
			// 过滤关键词
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
	        String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");  
			content = ReplaceUtil.replaceCheck(keys, content);
			
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			QidaQuestion question = qidaQuestionService.get(id);

			QidaAnswer answer = new QidaAnswer();
			answer.setFuUser(fuUser);
			answer.setQidaQuestion(question);
			answer.setState(qidaSwitchService.get(1L).getAnswerSwitch());
			answer.setIsDelete(0);
			answer.setContent(content);
			answer.setType(0);
			answer.setTicketNum(0);
			answer.setRealSupportNum(0);
			answer.setRealNoSupportNum(0);
			answer.setCreateTime(new Date());
			qidaAnswerService.save(answer);

			question.setReplyNum((question.getReplyNum() == null ? 0 : question.getReplyNum()) + 1);
			question.setUpdateTime(new Date());
			qidaQuestionService.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存付费问题回复的答案
	 * 
	 * @param id
	 * @param content
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveAnswerByPayQuestion.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public synchronized String saveAnswerByPayQuestion(Long id, String content, HttpServletRequest request) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			QidaQuestion question = qidaQuestionService.get(id);
			if (fuUser.getQidaRank() == null || fuUser.getQidaRank() == 0) {
				return "-3";// 不是专家
			}
			if (question.getIsComment() == 1) {
				return "-1";// 不能评论
			}
			if (question.getReplyNum() > 0) {
				return "-2";// 已经有人回答
			}
			long nowDate = new Date().getTime();
			long questionDate = question.getUpdateTime().getTime();
			int timeDiffer = (int) ((nowDate - questionDate) / 1000 / 60 / 60);// 访问时间和问题更新时间差
			if (timeDiffer <= 2 && question.getAnswerUser() != null && !question.getAnswerUser().getId().equals(fuUser.getId())) {
				return "-4";// 如果邀请了专家不是当前用户，并且时间差还是2小时之内，就不能回答
			}
			// 过滤关键词
			WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext(); 
	        String[] keys = (String[]) webApplicationContext.getServletContext().getAttribute("keys");  
			content = ReplaceUtil.replaceCheck(keys, content);
			
			QidaAnswer answer = new QidaAnswer();
			answer.setFuUser(fuUser);
			answer.setQidaQuestion(question);
			answer.setState(qidaSwitchService.get(1L).getAnswerSwitch());
			answer.setIsDelete(0);
			answer.setContent(content);
			answer.setType(0);
			answer.setTicketNum(0);
			answer.setRealSupportNum(0);
			answer.setRealNoSupportNum(0);
			answer.setCreateTime(new Date());
			
			if (qidaSwitchService.get(1L).getAnswerSwitch() == 1) {
				question.setReplyNum(1);
				qidaQuestionService.save(question);
				// 如果为付费问题，回答者就要获得积分
				if (question.getReward() > 0) {
					fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral().add(new BigDecimal(question.getReward()))));
					fuUserService.save(fuUser);
					// 保存回答问题得到的积分收益明细
					qidaScoreUtil.saveScoreByQd(fuUser, 47, new BigDecimal(question.getReward()), BigDecimal.ZERO, fuUser.getQidaIntegral(), 1);
				}
				
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				QidaMessage message = new QidaMessage();
				message.setMessage((null == answer.getFuUser().getNickName()) ? "佚名" : answer.getFuUser().getNickName() + "评论了您关于<a href=" + url + "/web/qida/questionPay/"
						+ answer.getQidaQuestion().getId() + ".html>" + answer.getQidaQuestion().getTitle() + "</a>问题");
				message.setSendFuUser(answer.getFuUser());
				message.setReceiveFuUser(answer.getQidaQuestion().getFuUser());
				message.setHttpCookie(UUID.randomUUID().toString());
				message.setIsRead(0);
				message.setIsDelete(0);
				message.setCreateTime(new Date());
				messageService.save(message);
			}
			
			qidaAnswerService.save(answer);

			question.setUpdateTime(new Date());
			qidaQuestionService.save(question);

			// 如果需要短信提醒
			// if(question.getIsMessage()==1){
			FuSmsLog log = new FuSmsLog();
			log.setPlanTime(new Date());
			log.setContent("您的提问:" + question.getTitle() + "，有了新的回复，请注意查看。");
			log.setPrio(1);
			log.setReason("回答问题");
			log.setDestination(question.getFuUser().getPhone());
			log.setType(1);// 短信
			log.setState(0);
			fuSmsLogService.save(log);

			// FuUser fuUser2=question.getFuUser();//提问者
			// fuUser2.setQidaIntegral((fuUser2.getQidaIntegral()==null?BigDecimal.ZERO:fuUser2.getQidaIntegral()).subtract(new BigDecimal(0.1)));//每条短信扣除0.1积分
			// fuUserService.save(fuUser2);

			// 保存短信通知支付积分的明细
			// qidaScoreUtil.saveScoreByQd(fuUser2, 51, new BigDecimal(0.1), BigDecimal.ZERO, fuUser2.getAccountBalance(), 0);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 删除问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delQuestion/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delQuestion(@PathVariable Long id) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("questionId", id);
			List<QidaAnswer> list1 = qidaAnswerService.findList(0, Integer.MAX_VALUE, map);
			if (list1 != null && list1.size() > 0) {
				return "-1";
			}
			// List<QidaTagQuestion> list2 = qidaTagQuestionService.findList(0, Integer.MAX_VALUE, map);
			// if (list2 != null && list2.size() > 0) {
			// for (QidaTagQuestion qidaTagQuestion : list2) {
			// qidaTagQuestionService.delete(qidaTagQuestion.getId());
			// }
			// }
			// List<QidaStockQuestion> list3 = qidaStockQuestionService.findList(0, Integer.MAX_VALUE, map);
			// if (list3 != null && list3.size() > 0) {
			// for (QidaStockQuestion qidaStockQuestion : list3) {
			// qidaStockQuestionService.delete(qidaStockQuestion.getId());
			// }
			// }
			// List<QidaCollection> list4 = qidaCollectionService.findList(0, Integer.MAX_VALUE, map);
			// if (list4 != null && list4.size() > 0) {
			// for (QidaCollection qidaCollection : list4) {
			// qidaCollectionService.delete(qidaCollection.getId());
			// }
			// }
			// List<QidaPayQuestion> list5 = qidaPayQuestionService.findList(0, Integer.MAX_VALUE, map);
			// if (list5 != null && list5.size() > 0) {
			// for (QidaPayQuestion qidaPayQuestion : list5) {
			// qidaPayQuestionService.delete(qidaPayQuestion.getId());
			// }
			// }
			List<QidaQuestionComment> list6 = qidaCommentService.findListQuestionComment(0, Integer.MAX_VALUE, map);
			if (list6 != null && list6.size() > 0) {
				for (QidaQuestionComment questionComment : list6) {
					questionComment.setIsDelete(1);
					qidaCommentService.saveQc(questionComment);
				}
			}
			List<QidaAnswerComment> list7 = qidaCommentService.findListAnswerComment(0, Integer.MAX_VALUE, map);
			if (list7 != null && list7.size() > 0) {
				for (QidaAnswerComment answerComment : list7) {
					answerComment.setIsDelete(1);
					qidaCommentService.saveAc(answerComment);
				}
			}
			QidaQuestion question = qidaQuestionService.get(id);
			FuUser fuUser = question.getFuUser();
			fuUser.setQidaIntegral((fuUser.getQidaIntegral() == null ? BigDecimal.ZERO : fuUser.getQidaIntegral()).add(new BigDecimal(question.getReward())));
			fuUserService.save(fuUser);
			// 删除问题返还积分
			qidaScoreUtil.saveScoreByQd(fuUser, 53, new BigDecimal(question.getReward()), BigDecimal.ZERO, fuUser.getQidaIntegral(), 1);

			question.setIsDelete(1);
			qidaQuestionService.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭此问题的评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/closeComment/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String closeComment(@PathVariable Long id) {
		try {
			QidaQuestion question = qidaQuestionService.get(id);
			question.setIsComment(1);
			qidaQuestionService.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 开启此问题的评论
	 * 
	 * @return
	 */
	@RequestMapping(value = "/openComment/{id}.html", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String openComment(@PathVariable Long id) {
		try {
			QidaQuestion question = qidaQuestionService.get(id);
			question.setIsComment(0);
			qidaQuestionService.save(question);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/checkStockInfo.html")
	@ResponseBody
	public String checkStockInfo(HttpServletRequest request, String code) {
		StockInfo stockInfo = stockService.findByStockCode(code);
		JSONObject obj = new JSONObject();
		if (null != stockInfo) {
			obj.put("success", 1); // 显示存在
		} else {
			obj.put("success", 0); // 显示不存在
		}
		return obj.toString();
	}

	/**
	 * 根据股票查询这支股票的相关问题
	 * 
	 * @return
	 */
	@RequestMapping(value = "/find/{stockId}.html")
	public String findByStockId(HttpServletRequest request, @PathVariable Long stockId) {
		List<QidaTags> qidaTags = qidaTagsService.findList(0, Integer.MAX_VALUE, new HashMap<String, Object>());

		List<QidaQuestion> list = qidaQuestionService.findByStockId(stockId);

		request.setAttribute("questions", list);
		request.setAttribute("qidaTags", qidaTags);
		request.setAttribute("nowDate", new Date());
		return "qida/qidaHome/questionWithStock";
	}
}
