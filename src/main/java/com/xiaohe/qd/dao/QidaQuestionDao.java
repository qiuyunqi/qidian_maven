package com.xiaohe.qd.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaQuestion;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("unchecked")
@Repository
public class QidaQuestionDao extends BaseDAO<QidaQuestion, Long> {
	public Integer getCount(Map<String, Object> map) {
		String hql = "from QidaQuestion where state=1 ";
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and title like '%" + map.get("title") + "%'";
		}
		if (map.get("questionNo") != null) {
			hql = hql + " and replyNum = 0";
		}
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("keyword") != null) {
			hql = hql + " and title like '%" + map.get("keyword") + "%' or detail like '%" + map.get("keyword") + "%'";
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		return this.countQueryResult(hql);
	}

	public List<QidaQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaQuestion where state=1 ";
		if (map.get("userId") != null) {
			hql = hql + " and fuUser.id = " + map.get("userId");
		}
		if (map.get("answerUserId") != null) {
			hql = hql + " and answerUser.id = " + map.get("answerUserId");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and title like '%" + map.get("title") + "%'";
		}
		if (map.get("questionNo") != null) {
			hql = hql + " and replyNum = 0";
		}
		if (map.get("keyword") != null) {
			hql = hql + " and title like '%" + map.get("keyword") + "%' or detail like '%" + map.get("keyword") + "%'";
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		if (map.get("sort") != null && map.get("order") != null) {
			hql = hql + " order by " + map.get("sort") + " " + map.get("order");
		} else if (map.get("questionsHot") != null) {
			hql = hql + " order by pageViews desc";
		} else {
			hql = hql + " order by updateTime desc";
		}
		return this.findListByHQL(i, pageSize, hql, null);
	}

	public Integer getCountAfter(Map<String, Object> map) {
		String hql = "from QidaQuestion where 1=1 ";
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and title like '%" + map.get("title") + "%'";
		}
		if (map.get("questionNo") != null) {
			hql = hql + " and replyNum = 0";
		}
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("state") != null) {
			hql = hql + " and state = " + map.get("state");
		}
		if (map.get("keyword") != null) {
			hql = hql + " and title like '%" + map.get("keyword") + "%' or detail like '%" + map.get("keyword") + "%'";
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		return this.countQueryResult(hql);
	}

	public List<QidaQuestion> findListAfter(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaQuestion where 1=1 ";
		if (map.get("userId") != null) {
			hql = hql + " and fuUser.id = " + map.get("userId");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and title like '%" + map.get("title") + "%'";
		}
		if (map.get("questionNo") != null) {
			hql = hql + " and replyNum = 0";
		}
		if (map.get("keyword") != null) {
			hql = hql + " and title like '%" + map.get("keyword") + "%' or detail like '%" + map.get("keyword") + "%'";
		}
		if (map.get("state") != null) {
			hql = hql + " and state = " + map.get("state");
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		if (map.get("sort") != null && map.get("order") != null) {
			hql = hql + " order by " + map.get("sort") + " " + map.get("order");
		} else if (map.get("questionsHot") != null) {
			hql = hql + " order by pageViews desc";
		} else {
			hql = hql + " order by updateTime desc";
		}
		return this.findListByHQL(i, pageSize, hql, null);
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
		String hql = "FROM QidaQuestion WHERE state=1 and fuUser.id = :userId ORDER BY updateTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setFirstResult(currData)//
				.setMaxResults(questionPageSize)//
				.list();
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
		String sql = "SELECT qida_question.* FROM qida_question, qida_tag_question, qida_tags "
				+ " WHERE qida_question.state=1 and qida_question.is_delete=0 and qida_question.id = qida_tag_question.question_id " + " and qida_tags.id = qida_tag_question.tag_id "
				+ " and qida_tags.id = " + tagId + " ORDER BY update_time DESC";
		List<Object[]> objList = this.findListBySql(pageData, pageSize, sql, null);
		List<QidaQuestion> list = new ArrayList<QidaQuestion>();
		for (Object[] obj : objList) {
			if (null != obj) {
				QidaQuestion qs = new QidaQuestion();
				qs.setId(((BigInteger) obj[0]).longValue());
				qs.setFuUser((FuUser) this.getSession().createQuery("FROM FuUser WHERE id = :id").setParameter("id", ((BigInteger) obj[1]).longValue()).uniqueResult());
				qs.setTitle((String) obj[2]);
				qs.setDetail((String) obj[3]);
				qs.setImgUrl((String) obj[4]);
				qs.setReplyNum((Integer) obj[5]);
				qs.setPageViews((Integer) obj[6]);
				qs.setIsMessage((Integer) obj[7]);
				qs.setIsReward((Integer) obj[8]);
				qs.setReward((Integer) obj[9]);
				if (null != obj[10]) {
					FuUser answerFuUser = (FuUser) this.getSession().createQuery("FROM FuUser WHERE id = :id")//
							.setParameter("id", ((BigInteger) obj[10]).longValue())//
							.uniqueResult();//
					qs.setAnswerUser(answerFuUser);
				}
				qs.setIsComment((Integer) obj[11]);
				qs.setUpdateTime((Date) obj[12]);
				if (null != obj[13]) {
					qs.setCreateTime((Date) obj[13]);
				}
				qs.setState((Integer) obj[14]);
				list.add(qs);
			}
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
		String sql = "SELECT qida_question.* FROM qida_question, qida_tags, qida_tag_question "
				+ " WHERE qida_question.state=1 and qida_question.is_delete=0 and qida_question.id = qida_tag_question.question_id " + " and qida_tags.id = qida_tag_question.tag_id "
				+ " and qida_tags.id = " + tagId;
		List<Object[]> list = this.getSession().createSQLQuery(sql).list();
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

	/**
	 * 根据股票id查询问题
	 * 
	 * @param stockId
	 * @return
	 */
	public List<QidaQuestion> findByStockId(Long stockId) {
		String hql = "FROM QidaQuestion WHERE stockQuestions.stockInfo.id = :stockId WHERE state=1 and isDelete=0";
		return this.getSession().createQuery(hql)//
				.setParameter("stockId", stockId)//
				.list();
	}

}
