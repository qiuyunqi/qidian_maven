package com.xiaohe.qd.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockBullish;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@Repository
public class StockBullishDao extends BaseDAO<StockBullish, Long> {

	public int countStockBullishByParams(Long stockId, Long userId, String timeFlag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		if (stockId != null) {
			map.put("stockId", stockId);
		}
		if (userId != null) {
			map.put("userId", userId);
		}
		if(timeFlag != null && !"".equals(timeFlag)){
			map.put("timeStr", sdf.format(new Date()));
		}
		String hql = "from StockBullish where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("stockId")) {
			params.add(map.get("stockId"));
			hql = hql + " and stockInfo.id=? ";
		}
		if (map.containsKey("timeStr")) {
			params.add(map.get("timeStr"));
			hql = hql + " and DATE_FORMAT(createTime, '%Y-%m-%d')=? ";
		}
		if (map.containsKey("userId")) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		return this.countQueryResult(hql, params);
	}

}
