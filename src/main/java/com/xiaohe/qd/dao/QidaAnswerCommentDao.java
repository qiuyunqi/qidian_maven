package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaAnswerComment;

@Repository
public class QidaAnswerCommentDao extends BaseDAO<QidaAnswerComment, Long> {
	public Integer getCountAnswerComment(Map<String, Object> map) {
		String hql = "from QidaAnswerComment where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("answerId") != null) {
			hql = hql + " and qidaAnswer.id = " + map.get("answerId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaAnswer.qidaQuestion.id = " + map.get("questionId");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
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

	public List<QidaAnswerComment> findListAnswerComment(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaAnswerComment where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("answerId") != null) {
			hql = hql + " and qidaAnswer.id = " + map.get("answerId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaAnswer.qidaQuestion.id = " + map.get("questionId");
		}
		if (map.get("nickName") != null) {
			hql = hql + " and fuUser.nickName like '%" + map.get("nickName") + "%'";
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
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
