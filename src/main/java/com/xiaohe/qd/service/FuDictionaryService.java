package com.xiaohe.qd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuDictionaryDao;
import com.xiaohe.qd.model.FuDictionary;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuDictionaryService extends BaseService {
	@Resource
	private FuDictionaryDao fuDictionaryDao;

	// ====================== 基本 C R U D 方法 ===========================
	public FuDictionary get(Long id) {
		return fuDictionaryDao.get(id);
	}

	public void save(FuDictionary entity) {
		fuDictionaryDao.save(entity);
	}

	public void delete(Long id) {
		fuDictionaryDao.delete(id);
	}

}
