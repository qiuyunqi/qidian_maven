package com.xiaohe.qd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysRoleDao;
import com.xiaohe.qd.dao.SysRolePurviewDao;
import com.xiaohe.qd.dao.SysUserRoleDao;
import com.xiaohe.qd.model.SysRole;
import com.xiaohe.qd.model.SysRolePurview;
import com.xiaohe.qd.model.SysUserRole;

@Service
public class SysRoleService extends BaseService {
	@Resource
	private SysUserRoleDao sysUserRoleDao;
	@Resource
	private SysRolePurviewDao sysRolePurviewDao;
	@Resource
	private SysRoleDao sysRoleDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysRole get(Long id) {
		return sysRoleDao.get(id);
	}

	public void save(SysRole entity) {
		sysRoleDao.save(entity);
	}

	public void delete(Long id) {
		sysRoleDao.delete(id);
	}

	public Integer countRole(Map<String, Object> map) {
		return sysRoleDao.countRole(map);
	}

	public List<SysRole> findRoleListByMap(int i, int pageSize, Map<String, Object> map) {
		return sysRoleDao.findRoleListByMap(i, pageSize, map);
	}

	// 根据用户id查询用户对应的角色
	public List<SysRole> findRoleListByRoleId(Long userId) {
		return sysRoleDao.findRoleListByRoleId(userId);
	}

	public void deleteRole(Long roleId) {
		// 先删除对应用户角色中间表内容
		List<SysUserRole> list = sysUserRoleDao.findUserRoleByRoleId(roleId);
		if (list != null && list.size() > 0) {
			for (SysUserRole sysUserRole : list) {
				sysUserRoleDao.delete(sysUserRole);
			}
		}
		// 删除对应角色菜单中间表的内容
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		List<SysRolePurview> rolePurviews = sysRolePurviewDao.findList(map);
		if (rolePurviews != null && rolePurviews.size() > 0) {
			for (SysRolePurview sysRolePurview : rolePurviews) {
				sysRolePurviewDao.delete(sysRolePurview);
			}
		}
		// 再删除角色表的
		sysRoleDao.delete(roleId);
	}

}
