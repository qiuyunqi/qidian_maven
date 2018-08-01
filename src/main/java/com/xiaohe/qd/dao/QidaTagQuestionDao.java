package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaTagQuestion;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("unchecked")
@Repository
public class QidaTagQuestionDao extends BaseDAO<QidaTagQuestion, Long> {
	public List<QidaTagQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaTagQuestion where 1=1 ";
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		if (map.get("tagId") != null) {
			hql = hql + " and qidaTags.id = " + map.get("tagId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	/**
	 * 查询出与这个问题id相关的所有的中间关系对象
	 * @param questionId  问题id
	 * @return
	 */
	public List<QidaTagQuestion> findListByQuestionId(Long questionId) {
		String hql = "FROM QidaTagQuestion WHERE qidaQuestion.id = :questionId";
		return this.getSession().createQuery(hql)//
				.setParameter("questionId", questionId)//
				.list();
	}
}
