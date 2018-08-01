package com.xiaohe.qd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockInfoDao;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.util.HttpRemoteUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockInfoService extends BaseService {

	@Resource
	private StockInfoDao stockInfoDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockInfo get(Long id) {
		return stockInfoDao.get(id);
	}

	public void save(StockInfo entity) {
		stockInfoDao.save(entity);
	}

	public void delete(Long id) {
		stockInfoDao.delete(id);
	}

	/**
	 * 访问新浪股票接口
	 * 
	 * @param stockCode
	 *            股票代码
	 * @return
	 */
	public String getSinaStockInfo(String stockCode) {
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("list", stockCode);
		String html = null;
		try {
			html = HttpRemoteUtil.GETMethod("http://hq.sinajs.cn/", argsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return html;
	}

	public String queryStockItems() {
		return stockInfoDao.queryStockItems();
	}

	public Integer getCount(Map<String, Object> map) {
		return stockInfoDao.getCount(map);
	}

	public List<StockInfo> findList(int i, int pageSize, Map<String, Object> map) {
		return stockInfoDao.findList(i, pageSize, map);
	}
	
	public StockInfo findByStockCode(String code) {
		return stockInfoDao.findByStockCode(code);
	}

	public List<StockInfo> getStockInfoById(Long id) {
		return null;
	}
}
