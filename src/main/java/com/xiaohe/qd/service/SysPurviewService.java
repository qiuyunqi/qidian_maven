package com.xiaohe.qd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysPurviewDao;
import com.xiaohe.qd.dao.SysRolePurviewDao;
import com.xiaohe.qd.model.SysPurview;
import com.xiaohe.qd.model.SysRolePurview;

@Service
public class SysPurviewService extends BaseService {
	@Resource
	private SysPurviewDao sysPurviewDao;
	@Resource
	private SysRolePurviewDao sysRolePurviewDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysPurview get(Long id) {
		return sysPurviewDao.get(id);
	}

	public void save(SysPurview entity) {
		sysPurviewDao.save(entity);
	}

	public void delete(Long id) {
		sysPurviewDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return sysPurviewDao.getCount(map);
	}

	/* 查询出所有的顶级权限的URL */
	public List<SysPurview> findTopPrivilege() {
		return sysPurviewDao.findTopPrivilege();
	}

	/* 查询所有的权限URL */
	public List<String> getAllPrivilegeUrls() {
		return sysPurviewDao.getAllPrivilegeUrls();
	}

	public List<SysPurview> findPurviewList(int i, int pageSize, Map<String, Object> map) {
		return sysPurviewDao.findPurviewList(i, pageSize, map);
	}

	public void deletePurview(Long id) {
		// 先删除对应角色菜单中间表的内容
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("purviewId", id);
		List<SysRolePurview> rolePurviews = sysRolePurviewDao.findList(map);
		if (rolePurviews != null && rolePurviews.size() > 0) {
			for (SysRolePurview sysRolePurview : rolePurviews) {
				sysRolePurviewDao.delete(sysRolePurview.getId());
			}
		}
		sysPurviewDao.delete(id);
	}
}
