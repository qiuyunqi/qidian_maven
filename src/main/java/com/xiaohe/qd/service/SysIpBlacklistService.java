package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysIpBlacklistDao;
import com.xiaohe.qd.model.SysIpBlacklist;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class SysIpBlacklistService extends BaseService {

	@Resource
	private SysIpBlacklistDao sIpBlacklistDao;

	// ====================== 基本 C R U D 方法 ===========================
	public SysIpBlacklist get(Long id) {
		return sIpBlacklistDao.get(id);
	}

	public void save(SysIpBlacklist entity) {
		sIpBlacklistDao.save(entity);
	}

	public void delete(Long id) {
		sIpBlacklistDao.delete(id);
	}

	public SysIpBlacklist findBlackByRegIp(String registerIp) {
		return sIpBlacklistDao.findBlackByRegIp(registerIp);
	}

	public Integer countIpBlack(Map<String, Object> map) {
		return sIpBlacklistDao.countIpBlack(map);
	}

	public List<SysIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map) {
		return sIpBlacklistDao.findIpBlackList(i, pageSize, map);
	}

	// 根据手机号码或者ip查询 fuIpBlackList对象
	public List<SysIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip) {
		return sIpBlacklistDao.getBlackListByPhoneOrIp(phone, ip);
	}

}
