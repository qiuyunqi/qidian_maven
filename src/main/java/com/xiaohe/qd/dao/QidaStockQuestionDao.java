package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaStockQuestion;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("unchecked")
@Repository
public class QidaStockQuestionDao extends BaseDAO<QidaStockQuestion, Long> {
	public List<QidaStockQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaStockQuestion where 1=1 ";
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	/**
	 * 通过问题表查询中间表对象集合
	 * @param id   问题表主键
	 * @return
	 */
	public List<QidaStockQuestion> findListByQuestionId(Long id) {
		String hql = "FROM QidaStockQuestion WHERE qidaQuestion.id = :id";
		return this.getSession().createQuery(hql)//
				.setParameter("id", id)//
				.list();
	}

	/**
	 * 通过 股票表查询中间表对象集合
	 * @param stockId   股票表主键
	 * @return
	 */
	public List<QidaStockQuestion> findByStockId(Long stockId) {
		String hql = "FROM QidaStockQuestion WHERE stockInfo.id = :stockId";
		return this.getSession().createQuery(hql)//
				.setParameter("stockId", stockId)//
				.list();
	}
}
