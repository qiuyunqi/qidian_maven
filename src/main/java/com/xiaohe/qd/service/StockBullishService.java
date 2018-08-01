package com.xiaohe.qd.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockBullishDao;
import com.xiaohe.qd.model.StockBullish;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockBullishService extends BaseService {
	@Resource
	private StockBullishDao stockBullishDao;
	

	// ====================== 基本 C R U D 方法 ===========================
	public StockBullish get(Long id) {
		return stockBullishDao.get(id);
	}

	public void save(StockBullish entity) {
		stockBullishDao.save(entity);
	}

	public void delete(Long id) {
		stockBullishDao.delete(id);
	}
	
	public int countStockBullishByParams(Long stockId, Long userId, String timeFlag) {
		return stockBullishDao.countStockBullishByParams(stockId, userId, timeFlag);
	}

}
