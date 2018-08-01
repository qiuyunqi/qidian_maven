package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.SysIpBlacklist;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 小合
 */
@Repository
public class SysIpBlacklistDao extends BaseDAO<SysIpBlacklist, Long> {
	public SysIpBlacklist findBlackByRegIp(String registerIp) {
		String hql = "from SysIpBlacklist where ip=?";
		return this.findUniqueByHQL(hql, registerIp);
	}

	public Integer countIpBlack(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from SysIpBlacklist where 1=1";
		if (map.containsKey("ip")) {
			params.add(map.get("ip"));
			hql = hql + " and ip=? ";
		}
		if (map.containsKey("isBlack")) {
			params.add(map.get("isBlack"));
			hql = hql + " and isBlack=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<SysIpBlacklist> findIpBlackList(int i, int pageSize, Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from SysIpBlacklist where 1=1";
		if (map.containsKey("ip")) {
			params.add(map.get("ip"));
			hql = hql + " and ip=? ";
		}
		if (map.containsKey("isBlack")) {
			params.add(map.get("isBlack"));
			hql = hql + " and isBlack=? ";
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, pageSize, hql, params);
	}

	// 根据手机号码或者ip查询 fuIpBlackList对象
	public List<SysIpBlacklist> getBlackListByPhoneOrIp(String phone, String ip) {
		List<Object> list = new ArrayList<Object>();
		list.add(phone);
		list.add(ip);
		String hql = "FROM SysIpBlacklist AS f WHERE f.ip = ? OR f.ip = ? AND f.isBlack=1";
		return this.findAllByHQL(hql, list);
	}
}
