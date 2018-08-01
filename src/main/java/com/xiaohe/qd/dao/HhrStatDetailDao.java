package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.HhrStatDetail;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class HhrStatDetailDao extends BaseDAO<HhrStatDetail, Long> {

	public HhrStatDetail findHhrStatDetailByUser(Long userId) {
		String hql = " from HhrStatDetail where user_id = ? ";
		return this.findUniqueByHQL(hql, userId);
	}

	public HhrStatDetail findHhrStatDetailByUserAndDate(Long userId, String date) {
		String hql = " from HhrStatDetail where user_id = ? and stat_date = ?";
		return this.findUniqueByHQL(hql, userId, date);
	}

	public List<Object[]> findStatDataByMap(Map<String, Object> map) {
		List<Object[]> list = new ArrayList<Object[]>();
		String sql1 = "select sum(daily_income),id from hhr_stat_detail where 1=1 ";
		String sql2 = "select sum(daily_income),id from hhr_stat_detail where 1=1 ";
		List<Object> params1 = new ArrayList<Object>();
		List<Object> params2 = new ArrayList<Object>();
		if (map.containsKey("user_id")) {
			params1.add(map.get("user_id"));
			params2.add(map.get("user_id"));
			sql1 = sql1 + " and user_id=? ";
			sql2 = sql2 + " and user_id=? ";
		}
		if (map.containsKey("stat_date")) {
			params1.add(map.get("stat_date"));
			sql1 = sql1 + " and stat_date=? ";
		}
		if (map.containsKey("yyyy_mm")) {
			params2.add(map.get("yyyy_mm"));
			sql2 = sql2 + " and date_format(stat_date ,'%Y-%m')=?";
		}
		if (this.findBySqlGetArray(sql1, params1).size() > 0) {
			Object[] a = this.findBySqlGetArray(sql1, params1).get(0);
			list.add(a);
		} else {
			list.add(null);
		}
		if (this.findBySqlGetArray(sql2, params2).size() > 0) {
			Object[] b = this.findBySqlGetArray(sql2, params2).get(0);
			list.add(b);
		} else {
			list.add(null);
		}
		return list;
	}

	public HhrStatDetail findHhrStatDetailByIncomeType(Long userId, Integer incomeType) {
		String hql = " from HhrStatDetail where user_id = ? and income_type = ? ";
		return this.findUniqueByHQL(hql, userId, incomeType);
	}

}
