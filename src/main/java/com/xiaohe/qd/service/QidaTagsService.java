package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.QidaTagsDao;
import com.xiaohe.qd.model.QidaTags;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaTagsService extends BaseService {
	@Resource
	private QidaTagsDao qidaTagsDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaTags get(Long id) {
		return qidaTagsDao.get(id);
	}

	public void save(QidaTags entity) {
		qidaTagsDao.save(entity);
	}

	public void delete(Long id) {
		qidaTagsDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map){
		return qidaTagsDao.getCount(map);
	}
	
	public List<QidaTags> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaTagsDao.findList(i, pageSize, map);
	}

	/**
	 * 根据标签查询出问题表的id集合
	 * @param tagList
	 * @return
	 */
	public List<Long> findQuestionByTags(List<Long> tagIds) {
		return qidaTagsDao.findQuestionByTags(tagIds);
	}

	/**
	 * 根绝是否热门的条件查询出对应的标签对象集合
	 * @param isHot 0: 普通标签 1: 热门标签
	 * @return
	 */
	public List<QidaTags> findByIsHot(int isHot) {
		return qidaTagsDao.findByIsHot(isHot);
	}

	/**
	 * 根据标签的名称查询标签
	 * @param tagName
	 * @return
	 */
	public QidaTags findByTagName(String tagName) {
		return qidaTagsDao.findByTagName(tagName);
	}

	/**
	 * 从数据库中删除这个标签
	 * @param qidaTags
	 * @return
	 */
	public int delTag(QidaTags qidaTags) {
		try {
			qidaTagsDao.delete(qidaTags);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	/**
	 * 查询最火前20条标签
	 * @param pageSize 
	 * @param pageData
	 * @return
	 */
	public List<QidaTags> findOrderByHot(int pageData, int pageSize) {
		return qidaTagsDao.findOrderByHot(pageData, pageSize);
	}

}
