package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockInfo;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@SuppressWarnings("unchecked")
@Repository
public class StockInfoDao extends BaseDAO<StockInfo, Long> {

	public String queryStockItems() {
		String sql = " SELECT a.id, a.name, CONCAT(a.name,CONCAT(a.stk_type,a.code),a.py_code) as itemname, CONCAT(a.stk_type,a.code) as code, a.py_code FROM qd_stock_info a ";
		List<Map<String, Object>> queryList = this.getJdbcTemplate().queryForList(sql);
		JSONArray dataArr = new JSONArray();
		for (Map<String, Object> map : queryList) {
			JSONObject obj = new JSONObject();
			obj.put("id", map.get("id"));
			obj.put("label", map.get("itemname").toString());
			obj.put("code", map.get("code").toString());
			obj.put("category", map.get("name").toString());
			dataArr.add(obj);
		}
		return dataArr.toString();
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockInfo where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		return this.countQueryResult(hql);
	}

	public List<StockInfo> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from StockInfo where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		if (map.get("sort") != null && map.get("order") != null) {
			hql = hql + " order by " + map.get("sort") + " " + map.get("order");
		} else {
			hql = hql + " order by id desc";
		}
		return this.findListByHQL(i, pageSize, hql, null);
	}

	public StockInfo findByStockCode(String code) {
		String hql=" from StockInfo where code=? ";
		return this.findUniqueByHQL(hql, code);
	}

	/**
	 * 通过股票id集合查询股票对象集合
	 * @param stockIds
	 * @return
	 */
	public List<StockInfo> findListByStockIds(List<Long> stockIds) {
		String hql = "FROM StockInfo WHERE id in (:stockIds)";
		return this.getSession().createQuery(hql)//
				.setParameterList("stockIds", stockIds)//
				.list();
	}

}
