package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.QidaCollectionDao;
import com.xiaohe.qd.model.QidaCollection;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaCollectionService extends BaseService {
	@Resource
	private QidaCollectionDao qidaCollectionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaCollection get(Long id) {
		return qidaCollectionDao.get(id);
	}

	public void save(QidaCollection entity) {
		qidaCollectionDao.save(entity);
	}

	public void delete(Long id) {
		qidaCollectionDao.delete(id);
	}

	/**
	 * 查询我收藏过的问题
	 * @param collectionPageSize 每页大小
	 * @param currData   从某条数据开始
	 * @param userId     用户id
	 * @return collection 集合
	 */
	public List<QidaCollection> findByMySelf(Integer currData, Integer collectionPageSize, Long userId) {
		return qidaCollectionDao.findByMySelf(currData, collectionPageSize, userId);
	}
	
	public List<QidaCollection> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaCollectionDao.findList(i, pageSize, map);
	}
	
	public QidaCollection findByQuestion(Long userId, Long questionId){
		return qidaCollectionDao.findByQuestion(userId, questionId);
	}

	/**
	 * 查询我收藏了问题的数量
	 * @param userId
	 * @return
	 */
	public Integer getCount(Long userId) {
		return qidaCollectionDao.getCount(userId);
	}

}
