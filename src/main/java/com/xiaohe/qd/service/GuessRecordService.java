package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.GuessRecordDao;
import com.xiaohe.qd.model.GuessRecord;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class GuessRecordService extends BaseService {
	@Resource
	private GuessRecordDao guessRecordDao;

	// ====================== 基本 C R U D 方法 ===========================
	public GuessRecord get(Long id) {
		return guessRecordDao.get(id);
	}

	public void save(GuessRecord entity) {
		guessRecordDao.save(entity);
	}

	public void delete(Long id) {
		guessRecordDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return guessRecordDao.getCount(map);
	}

	public List<GuessRecord> findList(int i, int pageSize, Map<String, Object> map) {
		return guessRecordDao.findList(i, pageSize, map);
	}

}
