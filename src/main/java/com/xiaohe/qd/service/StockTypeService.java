package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockTypeDao;
import com.xiaohe.qd.model.StockType;

@Service
public class StockTypeService extends BaseService {

	@Resource
	private StockTypeDao stockTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockType get(Long id) {
		return stockTypeDao.get(id);
	}

	public void save(StockType entity) {
		stockTypeDao.save(entity);
	}

	public void delete(Long id) {
		stockTypeDao.delete(id);
	}

	/**
	 * 根据pid获取类型列表
	 * 
	 * @param pid
	 *            父id
	 * @return
	 */
	public List<StockType> getStockInfoByPid(Long pid) {
		return stockTypeDao.getStockInfoByPid(pid);
	}

	/**
	 * 通过stock_type主键查询到stock_info信息
	 * 
	 * @param id
	 * @return
	 */
	public List<StockType> getStockInfoById(Long id) {
		return stockTypeDao.getStockInfoById(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return stockTypeDao.getCount(map);
	}

	public List<StockType> findList(int i, int pageSize, Map<String, Object> map) {
		return stockTypeDao.findList(i, pageSize, map);
	}

	public List<Object[]> findSumLiftRate(Long stockTypeId) {
		return stockTypeDao.findSumLiftRate(stockTypeId);
	}
}
