package com.xiaohe.qd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuUserDao;
import com.xiaohe.qd.dao.QidaQuestionDao;
import com.xiaohe.qd.dao.QidaStockQuestionDao;
import com.xiaohe.qd.dao.QidaSwitchDao;
import com.xiaohe.qd.dao.QidaTagQuestionDao;
import com.xiaohe.qd.dao.QidaTagsDao;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaQuestion;
import com.xiaohe.qd.model.QidaStockQuestion;
import com.xiaohe.qd.model.QidaTagQuestion;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.model.StockInfo;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaQuestionService extends BaseService {
	@Resource
	private QidaQuestionDao qidaQuestionDao;
	@Resource
	private QidaTagsDao qidaTagsDao;
	@Resource
	private QidaTagQuestionDao qidaTagQuestionDao;
	@Resource
	private QidaStockQuestionDao qidaStockQuestionDao;
	@Resource
	private QidaScoreUtil qidaScoreUtil;
	@Resource
	private FuUserDao fuUserDao;
	@Resource
	private QidaStockQuestionDao stockQuestionDao;
	@Resource
	private QidaSwitchDao qidaSwitchDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaQuestion get(Long id) {
		return qidaQuestionDao.get(id);
	}

	public void save(QidaQuestion entity) {
		qidaQuestionDao.save(entity);
	}

	public void delete(Long id) {
		qidaQuestionDao.delete(id);
	}

	/**
	 * 查询我自己提过的问题
	 * 
	 * @param questionPageSize
	 *            每页大小
	 * @param currData
	 *            从某条数据开始
	 * @param userId
	 *            用户的id
	 * @return 问题集合
	 */
	public List<QidaQuestion> findByMySelf(Integer currData, Integer questionPageSize, Long userId) {
		return qidaQuestionDao.findByMySelf(currData, questionPageSize, userId);
	}

	public Integer getCount(Map<String, Object> map) {
		return qidaQuestionDao.getCount(map);
	}

	public List<QidaQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaQuestionDao.findList(i, pageSize, map);
	}

	public Integer getCountAfter(Map<String, Object> map) {
		return qidaQuestionDao.getCountAfter(map);
	}

	public List<QidaQuestion> findListAfter(int i, int pageSize, Map<String, Object> map) {
		return qidaQuestionDao.findListAfter(i, pageSize, map);
	}

	/**
	 * 保存问题
	 * 
	 * @param fuUser
	 * @param title
	 * @param stockInfo
	 * @param content
	 * @param imgSrc
	 * @param isMsg
	 * @param rewardValue
	 * @param tagList
	 * @return
	 */
	public int saveQuestion(FuUser fuUser, String title, List<StockInfo> stockInfoList, String content, String imgSrc, Integer isMsg, BigDecimal rewardValue, List<QidaTags> tagList) {

		try {
			QidaQuestion question = new QidaQuestion();
			question.setTitle(title);
			question.setDetail(content);
			question.setFuUser(fuUser);
			question.setImgUrl(imgSrc);
			question.setReplyNum(0);
			question.setPageViews(0);
			if (isMsg == 1) {
				question.setIsMessage(1);
			} else {
				question.setIsMessage(0);
			}
			question.setIsReward(1);
			/*
			 * if(null != rewardValue && rewardValue.compareTo(BigDecimal.ZERO) == 1) { } else { question.setIsReward(0); }
			 */
			question.setIsComment(0);
			question.setReward(rewardValue.intValue());
			question.setCreateTime(new Date());
			question.setUpdateTime(new Date());
			question.setState(qidaSwitchDao.get(1L).getQuestionSwitch());
			question.setIsDelete(0);
			qidaQuestionDao.save(question);
			if (null != stockInfoList && stockInfoList.size() > 0) {
				for (StockInfo stockInfo : stockInfoList) {
					QidaStockQuestion qsq = new QidaStockQuestion();
					qsq.setQidaQuestion(question);
					qsq.setStockInfo(stockInfo);
					qidaStockQuestionDao.save(qsq);
				}
			}

			if (null != tagList && tagList.size() > 0) {
				for (QidaTags qidaTag : tagList) {
					qidaTag.setIsHot((null == qidaTag.getIsHot() ? 0 : qidaTag.getIsHot()) + 1);

					QidaTagQuestion qtq = new QidaTagQuestion();
					qtq.setQidaQuestion(question);
					qtq.setQidaTags(qidaTag);
					qtq.setCreateTime(new Date());
					qidaTagQuestionDao.save(qtq);
				}
			} else {
				QidaTags qidaTags = qidaTagsDao.findByTagName("股票".trim());
				if (null == qidaTags) {
					qidaTags = new QidaTags();
					qidaTags.setParentId(0L);
					qidaTags.setName("股票");
					qidaTags.setIsHot(1);
					qidaTagsDao.save(qidaTags);

					QidaTagQuestion qtq = new QidaTagQuestion();
					qtq.setQidaQuestion(question);
					qtq.setQidaTags(qidaTags);
					qtq.setCreateTime(new Date());
					qidaTagQuestionDao.save(qtq);
				} else {
					qidaTags.setIsHot((null == qidaTags.getIsHot() ? 0 : qidaTags.getIsHot()) + 1);
					QidaTagQuestion qtq = new QidaTagQuestion();
					qtq.setQidaQuestion(question);
					qtq.setQidaTags(qidaTags);
					qtq.setCreateTime(new Date());
					qidaTagQuestionDao.save(qtq);
				}
			}

			// 扣除积分
			fuUser.setQidaIntegral(fuUser.getQidaIntegral().subtract(rewardValue));
			fuUserDao.save(fuUser);
			qidaScoreUtil.saveScoreByQd(fuUser, 46, rewardValue, BigDecimal.ZERO, fuUser.getQidaIntegral(), 0);
			return 1;
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 获取question对象
	 * 
	 * @param id
	 *            问题对象的唯一标识符
	 * @return
	 */
	public QidaQuestion getId(Long id) {
		return qidaQuestionDao.get(id);
	}

	/**
	 * 更新问题
	 * 
	 * @param question
	 *            问题对象
	 * @param infoList
	 * @param tagsId
	 * @return 0: 失败 1:成功
	 */
	public int updateQuestion(QidaQuestion question, List<StockInfo> infoList, String tagIds) {
		try {
			qidaQuestionDao.save(question);
			
			if (null != infoList) {
				// 删除 原来问题和股票的关系
				List<QidaStockQuestion> sqList = qidaStockQuestionDao.findListByQuestionId(question.getId());
				for (QidaStockQuestion qidaStockQuestion : sqList) {
					qidaStockQuestionDao.delete(qidaStockQuestion);
				}
				// 建立现在的关系
				for (StockInfo stockInfo : infoList) {
					QidaStockQuestion sq = new QidaStockQuestion();
					sq.setQidaQuestion(question);
					sq.setStockInfo(stockInfo);
					qidaStockQuestionDao.save(sq);
				}
			}

			// 删除 问题和标签的关系
			List<QidaTagQuestion> tqList = qidaTagQuestionDao.findListByQuestionId(question.getId());
			for (QidaTagQuestion qidaTagQuestion : tqList) {
				qidaTagQuestionDao.delete(qidaTagQuestion);
			}
			// 建立现在的关系
			String[] tIds = tagIds.split(",");
			List<Long> sts = new ArrayList<Long>();
			for (String sid : tIds) {
				sts.add(Long.parseLong(sid));
			}
			List<QidaTags> tagList = qidaTagsDao.findListByTagIds(sts);
			for (QidaTags qidaTags : tagList) {
				QidaTagQuestion tq = new QidaTagQuestion();
				tq.setQidaQuestion(question);
				tq.setQidaTags(qidaTags);
				tq.setCreateTime(new Date());
				qidaTags.setIsHot((null == qidaTags.getIsHot() ? 0 : qidaTags.getIsHot()) + 1);
				qidaTagQuestionDao.save(tq);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 通过标签分页查询出问题集合
	 * 
	 * @param pageData
	 *            从某条开始
	 * @param pageSize
	 *            每页条数
	 * @param tagId
	 *            标签Id
	 * @return
	 */
	public List<QidaQuestion> findByTagid(int pageData, int pageSize, Long tagId) {
		List<QidaQuestion> questionList = qidaQuestionDao.findByTagid(pageData, pageSize, tagId);
		List<QidaQuestion> list = new ArrayList<QidaQuestion>();
		for (QidaQuestion qidaQuestion : questionList) {
			QidaQuestion qidaQuestion2 = qidaQuestionDao.get(qidaQuestion.getId());
			list.add(qidaQuestion2);
		}
		return list;
	}

	/**
	 * 根据标签查询出该标签下的数量
	 * 
	 * @param tagId
	 * @return
	 */
	public Integer getTagCount(Long tagId) {
		return qidaQuestionDao.getTagCount(tagId);
	}

	/**
	 * 根据股票id查询问题
	 * 
	 * @param stockId
	 * @return
	 */
	public List<QidaQuestion> findByStockId(Long stockId) {
		// 提通过中间表查询出所有的questionid
		List<QidaStockQuestion> list = stockQuestionDao.findByStockId(stockId);
		List<QidaQuestion> questionList = new ArrayList<QidaQuestion>();
		for (QidaStockQuestion qidaStockQuestion : list) {
			QidaQuestion qidaQuestion = qidaStockQuestion.getQidaQuestion();
			if (qidaQuestion.getState()==1 && qidaQuestion.getIsDelete() == 0) {
				questionList.add(qidaQuestion);
			}
		}
		return questionList;
	}

}
