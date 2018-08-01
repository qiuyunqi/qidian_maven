package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.SysConfigDao;
import com.xiaohe.qd.model.SysConfig;

@Service
public class SysConfigService extends BaseService {
	@Resource
	private SysConfigDao sysConfigDao;

	public SysConfig get(Long id) {
		return sysConfigDao.get(id);
	}

	public void save(SysConfig entity) {
		sysConfigDao.save(entity);
	}

	public void delete(Long id) {
		sysConfigDao.delete(id);
	}

	public SysConfig getByCode(String code) {
		return sysConfigDao.getByCode(code);
	}

	public Integer getCount(Map<String, Object> map) {
		return sysConfigDao.getCount(map);
	}

	public List<SysConfig> findList(int i, int pageSize, Map<String, Object> map) {
		return sysConfigDao.findList(i, pageSize, map);
	}

}
