package com.xiaohe.qd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuParameterDao;
import com.xiaohe.qd.model.FuParameter;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuParameterService extends BaseService {
	@Resource
	private FuParameterDao fuParameterDao;

	// ====================== 基本 C R U D 方法 ===========================
	public FuParameter get(Long id) {
		return fuParameterDao.get(id);
	}

	public void save(FuParameter entity) {
		fuParameterDao.save(entity);
	}

	public void delete(Long id) {
		fuParameterDao.delete(id);
	}

	public FuParameter findParameter() {
		return fuParameterDao.findParameter();
	}

}
