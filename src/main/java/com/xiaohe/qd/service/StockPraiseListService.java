package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockPraiseListDao;
import com.xiaohe.qd.model.StockPraiseList;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockPraiseListService extends BaseService {

	@Resource
	private StockPraiseListDao stockPraiseListDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockPraiseList get(Long id) {
		return stockPraiseListDao.get(id);
	}

	public void save(StockPraiseList entity) {
		stockPraiseListDao.save(entity);
	}

	public void delete(Long id) {
		stockPraiseListDao.delete(id);
	}

	public List<Object[]> queryStockPraiseList(String code) {
		return stockPraiseListDao.queryStockPraiseList(code);
	}

	public Integer getCount(Map<String, Object> map) {
		return stockPraiseListDao.getCount(map);
	}

	public List<StockPraiseList> findList(int i, int pageSize, Map<String, Object> map) {
		return stockPraiseListDao.findList(i, pageSize, map);
	}
	
	public JSONObject getStockPriceAndChange(String stockCode) {
		return stockPraiseListDao.getStockPriceAndChange(stockCode);
	} 
	
	public List<Object[]> queryWebStockPraiseList() {
		return stockPraiseListDao.queryWebStockPraiseList();
	}
}
