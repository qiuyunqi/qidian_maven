package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.GuessMarket;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class GuessMarketDao extends BaseDAO<GuessMarket, Long> {

	public Integer getCount(Map<String, Object> map) {
		String hql = "from GuessMarket where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("type") != null) {
			hql = hql + " and type = " + map.get("type");
		}
		return this.countQueryResult(hql);
	}

	public List<GuessMarket> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from GuessMarket where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("type") != null) {
			hql = hql + " and type = " + map.get("type");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
