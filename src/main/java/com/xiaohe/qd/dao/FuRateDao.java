package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuRate;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class FuRateDao extends BaseDAO<FuRate, Long> {
	public FuRate findRateByUserID(Long userid) {
		String hql = " from FuRate where userid=? ";
		return this.findUniqueByHQL(hql, userid);
	}

	public List<FuRate> findRateList(int i, int pageSize, Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = " from FuRate where 1=1 ";
		if (map.containsKey("userId")) {
			hql = hql + " and fuUser.id=? ";
			params.add(map.get("userId"));
		}
		if (map.containsKey("userName")) {
			hql = hql + " and fuUser.userName=? ";
			params.add(map.get("userName"));
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	public Integer countRate(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = " from FuRate where 1=1 ";
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public void setHQL(StringBuilder hqlB, List<Object> params, Map<String, Object> map) {
		hqlB = hqlB.append("from FuRate where 1=1");
		if (map.get("id") != null) {
			hqlB.append(" and id=?");
			params.add(map.get("id"));
		}
		if (map.get("userId") != null) {
			hqlB.append(" and fuUser.id=?");
			params.add(map.get("userId"));
		}
		if (map.get("accountName") != null) {
			hqlB.append(" and fuUser.accountName like '%" + map.get("accountName") + "%'");
		}
		hqlB.append(" order by id desc");
	}
}
