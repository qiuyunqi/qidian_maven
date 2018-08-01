package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaTags;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("unchecked")
@Repository
public class QidaTagsDao extends BaseDAO<QidaTags, Long> {
	public Integer getCount(Map<String, Object> map){
		String hql = "from QidaTags where 1 = 1 ";
		if (map.get("isHot") != null) {
			hql = hql + " and isHot = " + map.get("isHot");
		}
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		return this.countQueryResult(hql);
	}
	
	public List<QidaTags> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaTags where 1 = 1 ";
		if (map.get("isHot") != null) {
			hql = hql + " and isHot = " + map.get("isHot");
		}
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("parentId") != null) {
			hql = hql + " and parentId = " + map.get("parentId");
		}
		hql = hql + " order by id asc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	/**
	 * 根据标签查询出问题表的id集合
	 * @param tagIds
	 * @return
	 */
	public List<Long> findQuestionByTags(List<Long> tagIds) {
		String sql = "SELECT DISTINCT qida_question.id FROM qida_question, qida_tag_question, qida_tags " +
						" WHERE qida_question.id = qida_tag_question.question_id AND qida_tags.id = qida_tag_question.tag_id " +
						"AND qida_tags.id IN ?";
		return this.getSession().createSQLQuery(sql).setParameter(0, tagIds).list();
	}

	/**
	 * 根绝是否热门的条件查询出对应的标签对象集合
	 * @param isHot 0: 普通标签 1: 热门标签
	 * @return
	 */
	public List<QidaTags> findByIsHot(int isHot) {
		String hql = "FROM QidaTags WHERE isHot = :isHot";
		return this.getSession().createQuery(hql)//
				.setParameter("isHot", isHot)//
				.list();
	}

	/**
	 * 通过标签id集合查询出标签对象集合
	 * @param tagIds  唯一标识符集合
	 * @return
	 */
	public List<QidaTags> findListByTagIds(List<Long> tagIds) {
		String hql = "FROM QidaTags WHERE id in (:tagIds)";
		return this.getSession().createQuery(hql)//
				.setParameterList("tagIds", tagIds)//
				.list();
	}

	/**
	 * 听过标签名称查询标签
	 * @param tagName 标签名称
	 * @return
	 */
	public QidaTags findByTagName(String tagName) {
		String hql = "FROM QidaTags WHERE name = :tagName";
		return (QidaTags) this.getSession().createQuery(hql)//
				.setParameter("tagName", tagName)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	/**
	 * 查询最火前20条标签
	 * @param pageSize 
	 * @param pageData
	 * @return
	 */
	public List<QidaTags> findOrderByHot(int pageData, int pageSize) {
		String hql = "FROM QidaTags ORDER BY isHot DESC";
		return this.getSession().createQuery(hql)//
				.setFirstResult(pageData)//
				.setMaxResults(pageSize)//
				.list();
	}
	
}
