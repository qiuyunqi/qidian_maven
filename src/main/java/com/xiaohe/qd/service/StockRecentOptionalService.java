package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockRecentOptionalDao;
import com.xiaohe.qd.model.StockRecentOptional;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockRecentOptionalService extends BaseService {
	@Resource
	private StockRecentOptionalDao stockRecentOptionalDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockRecentOptional get(Long id) {
		return stockRecentOptionalDao.get(id);
	}

	public void save(StockRecentOptional entity) {
		stockRecentOptionalDao.save(entity);
	}

	public void delete(Long id) {
		stockRecentOptionalDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return stockRecentOptionalDao.getCount(map);
	}

	public List<StockRecentOptional> findList(int i, int pageSize, Map<String, Object> map) {
		return stockRecentOptionalDao.findList(i, pageSize, map);
	}

	public StockRecentOptional findByStockId(Long stockInfoId) {
		return stockRecentOptionalDao.findByStockId(stockInfoId);
	}

	public List<StockRecentOptional> findListByUser(int i, int j, Long userId) {
		return stockRecentOptionalDao.findListByUser(i, j, userId);
	}
}
