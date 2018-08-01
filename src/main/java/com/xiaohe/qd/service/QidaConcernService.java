package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.QidaConcernDao;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaConcern;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaConcernService extends BaseService {
	@Resource
	private QidaConcernDao qidaConcernDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaConcern get(Long id) {
		return qidaConcernDao.get(id);
	}

	public void save(QidaConcern entity) {
		qidaConcernDao.save(entity);
	}

	public void delete(Long id) {
		qidaConcernDao.delete(id);
	}

	/**
	 * 查询被我关注的人
	 * @param userId  用户id
	 * @return 关注集合
	 */
	public List<QidaConcern> findByMySelf(Long userId) {
		return qidaConcernDao.findByMySelf(userId);
	}

	/**
	 * 查询出我的粉丝
	 * 
	 * @param userId
	 *            用户id
	 * @return 关注集合
	 */
	public List<QidaConcern> findByWatchMe(Long userId) {
		return qidaConcernDao.findByWatchMe(userId);
	}

	/**
	 * 查询是否关注 0: 没有关注 1: 关注 默认0
	 * 
	 * @param concernUser
	 *            关注人
	 * @param beConcernUser
	 *            被关注
	 * @return
	 */
	public int findIsWatch(FuUser concernUser, FuUser beConcernUser) {
		QidaConcern concern = qidaConcernDao.findConcern(concernUser, beConcernUser);
		return null != concern ? 1 : 0;
	}

	/**
	 * 两个人取消关注
	 * 
	 * @param concernUser
	 *            关注人
	 * @param beConcernUser
	 *            被关注
	 */
	public void saveUnWatch(FuUser concernUser, FuUser beConcernUser) {
		QidaConcern concern = qidaConcernDao.findConcern(concernUser, beConcernUser);
		qidaConcernDao.delete(concern);
	}
	
	public Integer getCount(Map<String, Object> map) {
		return qidaConcernDao.getCount(map);
	}
	
	public List<QidaConcern> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaConcernDao.findList(i, pageSize, map);
	}

}
