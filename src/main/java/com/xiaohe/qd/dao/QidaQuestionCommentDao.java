package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaQuestionComment;

@Repository
public class QidaQuestionCommentDao extends BaseDAO<QidaQuestionComment, Long> {

	public Integer getCountQuestionComment(Map<String, Object> map) {
		String hql = "from QidaQuestionComment where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
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

	public List<QidaQuestionComment> findListQuestionComment(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaQuestionComment where 1=1 ";
		if (map.get("fuUserId") != null) {
			hql = hql + " and fuUser.id = " + map.get("fuUserId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
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
