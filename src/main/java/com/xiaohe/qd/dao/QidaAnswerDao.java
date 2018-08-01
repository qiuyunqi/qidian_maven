package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaAnswer;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("all")
@Repository
public class QidaAnswerDao extends BaseDAO<QidaAnswer, Long> {
	public Integer getCount(Map<String, Object> map) {
		String hql = "from QidaAnswer where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and qidaQuestion.title like '%" + map.get("title") + "%'";
		}
		if (map.get("content") != null) {
			hql = hql + " and content like '%" + map.get("content") + "%'";
		}
		if (map.get("state") != null) {
			hql = hql + " and state = " + map.get("state");
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		return this.countQueryResult(hql);
	}

	public List<QidaAnswer> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaAnswer where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		if (map.get("qidaRank") != null) {
			hql = hql + " and fuUser.qidaRank = " + map.get("qidaRank");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
		}
		if (map.get("title") != null) {
			hql = hql + " and qidaQuestion.title like '%" + map.get("title") + "%'";
		}
		if (map.get("content") != null) {
			hql = hql + " and content like '%" + map.get("content") + "%'";
		}
		if (map.get("state") != null) {
			hql = hql + " and state = " + map.get("state");
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		if (map.get("concernUserId") != null) {
			hql = hql + " and fuUser.id in (select beConcernUser.id from QidaConcern where concernUser.id = " + map.get("concernUserId") + ") or fuUser.id = " + map.get("concernUserId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	/**
	 * 查询我自己回答过的问题
	 * 
	 * @param userId
	 *            用户的id
	 * @param pageSize
	 * @param currPage
	 * @return 答案集合
	 */
	public List<QidaAnswer> findByMySelf(Long userId, int currPage, int pageSize) {
		String hql = "FROM QidaAnswer WHERE state=1 and isDelete=0 AND fuUser.id = :userId ORDER BY createTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setFirstResult(currPage - 1)//
				.setMaxResults(pageSize)//
				.list();
	}

	public List<Object[]> findJcwd() {
		String sql = "SELECT COUNT(*),b.id,b.title FROM qida_answer AS a INNER JOIN qida_question AS b ON a.question_id=b.id where a.is_delete=0 and a.state=1 GROUP BY b.id,b.title ORDER BY COUNT(*) DESC";
		return this.findListBySql(0, 6, sql, null);
	}

	public QidaAnswer findNewAnswer(Long userId) {
		String hql = "from QidaAnswer where fuUser.id=" + userId + " order by createTime desc";
		if (this.findAllByHQL(hql) != null) {
			return this.findAllByHQL(hql).get(0);
		}
		return null;
	}
}
