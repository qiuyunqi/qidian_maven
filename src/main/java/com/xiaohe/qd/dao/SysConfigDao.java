package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.SysConfig;

@Repository
public class SysConfigDao extends BaseDAO<SysConfig, Long> {
	public SysConfig getByCode(String code) {
		String hql = "from SysConfig where code=?";
		return this.findUniqueByHQL(hql, code);
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from SysConfig where 1=1 ";
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		return this.countQueryResult(hql);
	}

	public List<SysConfig> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from SysConfig where 1=1 ";
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
