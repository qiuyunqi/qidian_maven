package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.HhrStat;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class HhrStatDao extends BaseDAO<HhrStat, Long> {

	public Object[] findStatDataByMap(Map<String, Object> map) {
		String sql = "select hhr_level,firstly_partner_num,secondary_partner_num," + "extend_person_new,interest_return_coefficient,charges_return_coefficient,daily_income,monthly_income,total_income from hhr_stat h where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("user_id")) {
			params.add(map.get("user_id"));
			sql = sql + " and user_id=? ";
		}
		if (map.containsKey("stat_date")) {
			params.add(map.get("stat_date"));
			sql = sql + " and stat_date=? ";
		}
		if (this.findBySqlGetArray(sql, params).size() > 0) {
			return this.findBySqlGetArray(sql, params).get(0);
		} else {
			return null;
		}
	}

	public HhrStat findHhrStatByUser(Long userId) {
		String hql = " from HhrStat where user_id = ? ";
		if (this.findAllByHQL(hql, userId).size() > 0) {
			return this.findAllByHQL(hql, userId).get(0);
		} else {
			return null;
		}
	}

	public HhrStat findStatDataByMap2(Map<String, Object> map) {
		String hql = "from HhrStat where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("user_id")) {
			params.add(map.get("user_id"));
			hql = hql + " and user_id=? ";
		}
		if (this.findAllByHQL(hql, params) != null && this.findAllByHQL(hql, params).size() > 0) {
			return this.findAllByHQL(hql, params).get(0);
		} else {
			return null;
		}
	}

	public List<HhrStat> findUserByParentId(Long parentId) {
		String hql = "from HhrStat where hhrParentID=?";
		return this.findAllByHQL(hql, parentId);
	}

	public List<HhrStat> queryHhrStatList(int i, int j, Map<String, Object> map) {
		String hql = "from HhrStat where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("accountName")) {
			params.add(map.get("accountName"));
			hql = hql + " and fuUser.accountName=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and statDate >=? ";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and statDate <=? ";
		}
		return this.findListByHQL(i, j, hql, params);
	}

	public Integer countHhrStat(Map<String, Object> map) {
		String hql = "from HhrStat where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("accountName")) {
			params.add(map.get("accountName"));
			hql = hql + " and fuUser.accountName=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("time1")) {
			params.add(map.get("time1"));
			hql = hql + " and statDate >=? ";
		}
		if (map.containsKey("time2")) {
			params.add(map.get("time2"));
			hql = hql + " and statDate <=? ";
		}
		return this.countQueryResult(hql, params);
	}

	// 根据 hhrParentID 查询用户信息
	@SuppressWarnings("unchecked")
	public List<HhrStat> findUserByParentId(long userId, int curPage, int pageSize) {
		String hql = "from HhrStat where hhrParentID = :userId";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setFirstResult(curPage)//
				.setMaxResults(pageSize)//
				.list();
	}
}
