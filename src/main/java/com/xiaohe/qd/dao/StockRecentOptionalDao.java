package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockRecentOptional;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@Repository
public class StockRecentOptionalDao extends BaseDAO<StockRecentOptional, Long> {

	public StockRecentOptional findByStockId(Long stockInfoId) {
		String hql = " from StockRecentOptional where stockInfo.id=? ";
		return this.findUniqueByHQL(hql, stockInfoId);
	}

	public List<StockRecentOptional> findListByUser(int i, int j, Long userId) {
		String hql = " from StockRecentOptional where 1=1  ";
		List<Object> params = new ArrayList<Object>();
		params.add(userId);
		hql = hql + " and fuUser.id=? ";
		hql = hql + " order by createTime desc";
		return this.findListByHQL(i, j, hql, params);
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockRecentOptional where 1=1 ";
		if (map.get("stockName") != null) {
			hql = hql + " and stockInfo.name like '%" + map.get("stockName") + "%'";
		}
		if (map.get("userName") != null) {
			hql = hql + " and fuUser.userName like '%" + map.get("userName") + "%'";
		}
		return this.countQueryResult(hql);
	}

	public List<StockRecentOptional> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from StockRecentOptional where 1=1 ";
		if (map.get("stockName") != null) {
			hql = hql + " and stockInfo.name like '%" + map.get("stockName") + "%'";
		}
		if (map.get("userName") != null) {
			hql = hql + " and fuUser.userName like '%" + map.get("userName") + "%'";
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

}
