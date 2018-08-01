package com.xiaohe.qd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysUserRoleDao;
import com.xiaohe.qd.model.SysUserRole;

@Service
public class SysUserRoleService extends BaseService {

	@Resource
	private SysUserRoleDao sysUserRoleDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysUserRole get(Long id) {
		return sysUserRoleDao.get(id);
	}

	public void save(SysUserRole entity) {
		sysUserRoleDao.save(entity);
	}

	public void delete(Long id) {
		sysUserRoleDao.delete(id);
	}

	public List<SysUserRole> findUserRoleByRoleId(Long roleId) {
		return sysUserRoleDao.findUserRoleByRoleId(roleId);
	}

	public List<SysUserRole> findUserRole(Long adminId) {
		return sysUserRoleDao.findUserRole(adminId);
	}

}
