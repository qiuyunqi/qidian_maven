package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.QidaAnswerDao;
import com.xiaohe.qd.model.QidaAnswer;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaAnswerService extends BaseService {
	@Resource
	private QidaAnswerDao qidaAnswerDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaAnswer get(Long id) {
		return qidaAnswerDao.get(id);
	}

	public void save(QidaAnswer entity) {
		qidaAnswerDao.save(entity);
	}

	public void delete(Long id) {
		qidaAnswerDao.delete(id);
	}
	
	public QidaAnswer findNewAnswer(Long userId){
		return qidaAnswerDao.findNewAnswer(userId);
	}

	public Integer getCount(Map<String, Object> map) {
		return qidaAnswerDao.getCount(map);
	}

	public List<QidaAnswer> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaAnswerDao.findList(i, pageSize, map);
	}

	/**
	 * 查询我自己回答过的问题
	 * @param userId  用户的id
	 * @param pageSize 每页数据的大小
	 * @param currPage 当前页的页数
	 * @return 答案集合
	 */
	public List<QidaAnswer> findByMySelf(Long userId, int currPage, int pageSize) {
		return qidaAnswerDao.findByMySelf(userId, currPage, pageSize);
	}

	public List<Object[]> findJcwd() {
		return qidaAnswerDao.findJcwd();
	}

}
