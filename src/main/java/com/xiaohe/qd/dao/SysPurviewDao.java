package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.SysPurview;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class SysPurviewDao extends BaseDAO<SysPurview, Long> {
	public List<SysPurview> findPurviewList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from SysPurview where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("url") != null) {
			hql = hql + " and url like '%" + map.get("url") + "%'";
		}
		if (map.get("parentId") != null) {
			params.add(map.get("parentId"));
			hql = hql + " and parentId=?";
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	/* 查询出所有的顶级权限的URL */
	public List<SysPurview> findTopPrivilege() {
		String hql = "FROM SysPurview p WHERE p.parentId = 0";
		return this.findAllByHQL(hql);
	}

	/* 查询所有的权限URL */
	public List<String> getAllPrivilegeUrls() {
		String hql = "SELECT DISTINCT p.url FROM SysPurview p WHERE p.url IS NOT NULL";
		return this.findCollectionByHql(hql);
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from SysPurview where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("url") != null) {
			hql = hql + " and url like '%" + map.get("url") + "%'";
		}
		if (map.get("parentId") != null) {
			params.add(map.get("parentId"));
			hql = hql + " and parentId=?";
		}
		hql = hql + " order by id desc ";
		return this.countQueryResult(hql, params);
	}

}
