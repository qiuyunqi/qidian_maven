package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysAdminDao;
import com.xiaohe.qd.model.SysAdmin;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class SysAdminService extends BaseService {

	@Resource
	private SysAdminDao sysAdminDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysAdmin get(Long id) {
		return sysAdminDao.get(id);
	}

	public void save(SysAdmin entity) {
		sysAdminDao.save(entity);
	}

	public void delete(Long id) {
		sysAdminDao.delete(id);
	}

	public SysAdmin findAdminByAccount(String account) {
		return sysAdminDao.findAdminByAccount(account);
	}

	public SysAdmin findLoginByToken(String token) {
		return sysAdminDao.findLoginByToken(token);
	}

	public List<Object[]> findList(int i, int pageSize, Map<String, Object> map) {
		return sysAdminDao.findList(i, pageSize, map);
	}

	public Integer getCount(Map<String, Object> map) {
		return sysAdminDao.getCount(map);
	}

}
