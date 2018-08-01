package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.SysPurview;
import com.xiaohe.qd.model.SysRolePurview;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class SysRolePurviewDao extends BaseDAO<SysRolePurview, Long> {
	public List<SysRolePurview> findList(Map<String, Object> map) {
		String hql = " from SysRolePurview where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("roleId")) {
			params.add(map.get("roleId"));
			hql = hql + " and sysRole.id=? ";
		}
		if (map.containsKey("purviewId")) {
			params.add(map.get("purviewId"));
			hql = hql + " and sysPurview.id=? ";
		}
		return this.findAllByHQL(hql, params);
	}

	public String findPurviewList(Long roleId) {
		String sql = "select id, name, parentid as pId from qd_sys_purview where parentid is not null";
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<SysRolePurview> rolePv = this.findList(map);// 根据角色id查询所拥有的权限
		JSONArray jsonArr = new JSONArray();
		for (Map<String, Object> purview : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", purview.get("id").toString());
			obj.put("name", purview.get("name").toString());
			obj.put("pId", purview.get("pId") != null ? purview.get("pId").toString() : "");
			// 如果在中间表有权限对应，则选中
			if (rolePv != null && rolePv.size() > 0) {
				for (SysRolePurview rolePurview : rolePv) {
					String rpid = rolePurview.getSysPurview().getId().toString();
					String pid = purview.get("id").toString();
					// 如果角色权限表的权限id跟权限表的id对应上，就选中
					if (rpid.equals(pid)) {
						obj.put("checked", true);
					}
				}
			}
			jsonArr.add(obj);
		}
		return jsonArr.toString();
	}

	public List<SysPurview> findPurviewListByRoleId(Long roleId) {
		String hql = "FROM SysRolePurview WHERE sysRole.id = " + roleId;
		List<SysRolePurview> list = this.findAllByHQL(hql);
		List<SysPurview> ls = new ArrayList<SysPurview>();
		if (list != null && list.size() > 0) {
			for (SysRolePurview sysRolePurview : list) {
				SysPurview sysPurview = sysRolePurview.getSysPurview();
				ls.add(sysPurview);
			}
		}
		return ls;
	}
}
