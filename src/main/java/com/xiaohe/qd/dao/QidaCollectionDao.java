package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaCollection;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("all")
@Repository
public class QidaCollectionDao extends BaseDAO<QidaCollection, Long> {

	/**
	 * 查询我收藏过的问题
	 * @param collectionPageSize 
	 * @param currData 
	 * @param userId
	 * @return collection 集合
	 */
	public List<QidaCollection> findByMySelf(Integer currData, Integer collectionPageSize, Long userId) {
		String hql = "FROM QidaCollection WHERE fuUser.id = :userId ORDER BY collectionTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setFirstResult(currData)//
				.setMaxResults(collectionPageSize)//
				.list();
	}
	
	public List<QidaCollection> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaCollection where 1=1 ";
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
	
	public QidaCollection findByQuestion(Long userId, Long questionId){
		String hql = "from QidaCollection where fuUser.id="+userId+" and qidaQuestion.id="+questionId;
		return this.findUniqueByHQL(hql, null);
	}

	/**
	 * 查询我收藏了问题的数量
	 * @param userId
	 * @return
	 */
	public Integer getCount(Long userId) {
		String hql = "FROM QidaCollection WHERE fuUser.id = :userId";
		List<QidaCollection> list = this.getSession().createQuery(hql)//
		.setParameter("userId", userId)//
		.list();
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

}
