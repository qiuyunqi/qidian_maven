package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.GuessRecord;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class GuessRecordDao extends BaseDAO<GuessRecord, Long> {

	public Integer getCount(Map<String, Object> map) {
		String hql = "from GuessRecord where 1=1 ";
		if (map.get("userName") != null) {
			hql = hql + " and fuUser.userName like '%" + map.get("userName") + "%'";
		}
		if (map.get("marketId") != null) {
			hql = hql + " and guessMarket.id = " + map.get("marketId");
		}
		if (map.get("marketName") != null) {
			hql = hql + " and guessMarket.name like '%" + map.get("marketName") + "%'";
		}
		if (map.get("guessType") != null) {
			hql = hql + " and guessType = " + map.get("guessType");
		}

		return this.countQueryResult(hql);
	}

	public List<GuessRecord> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from GuessRecord where 1=1 ";
		if (map.get("userName") != null) {
			hql = hql + " and fuUser.userName like '%" + map.get("userName") + "%'";
		}
		if (map.get("marketId") != null) {
			hql = hql + " and guessMarket.id = " + map.get("marketId");
		}
		if (map.get("marketName") != null) {
			hql = hql + " and guessMarket.name like '%" + map.get("marketName") + "%'";
		}
		if (map.get("guessType") != null) {
			hql = hql + " and guessType = " + map.get("guessType");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
