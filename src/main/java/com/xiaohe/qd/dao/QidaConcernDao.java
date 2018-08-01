package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaConcern;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@SuppressWarnings("unchecked")
@Repository
public class QidaConcernDao extends BaseDAO<QidaConcern, Long> {

	/**
	 * 查询被我关注的人
	 * @param userId 用户id
	 * @return 关注集合
	 */
	public List<QidaConcern> findByMySelf(Long userId) {
		String hql = "FROM QidaConcern WHERE concernUser.id = :userId";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.list();
	}

	/**
	 * 查询出我的粉丝
	 * @param userId 用户id
	 * @return 关注集合
	 */
	public List<QidaConcern> findByWatchMe(Long userId) {
		String hql = "FROM QidaConcern WHERE beConcernUser.id = :userId";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.list();
	}

	/**
	 * 根据关注和被关注查询关注对象
	 * @param concernUser
	 * @param beConcernUser
	 * @return
	 */
	public QidaConcern findConcern(FuUser concernUser, FuUser beConcernUser) {
		String hql = "FROM QidaConcern WHERE beConcernUser.id = :beConcernUser AND concernUser.id = :concernUser";
		return (QidaConcern) this.getSession().createQuery(hql)//
				.setParameter("beConcernUser", beConcernUser.getId())//
				.setParameter("concernUser", concernUser.getId())//
				.uniqueResult();
	}
	
	public Integer getCount(Map<String, Object> map) {
		String hql = "from QidaConcern where 1=1 ";
		if (map.get("concernUser") != null) {
			hql = hql + " and concernUser.nickName like '%" + map.get("concernUser") + "%'";
		}
		if (map.get("beConcernUser") != null) {
			hql = hql + " and beConcernUser.nickName like '%" + map.get("beConcernUser") + "%'";
		}
		return this.countQueryResult(hql);
	}
	
	public List<QidaConcern> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaConcern where 1=1 ";
		if (map.get("concernUser") != null) {
			hql = hql + " and concernUser.nickName like '%" + map.get("concernUser") + "%'";
		}
		if (map.get("beConcernUser") != null) {
			hql = hql + " and beConcernUser.nickName like '%" + map.get("beConcernUser") + "%'";
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

}
