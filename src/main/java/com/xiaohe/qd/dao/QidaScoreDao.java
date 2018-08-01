package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaScore;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class QidaScoreDao extends BaseDAO<QidaScore, Long> {

	public List<QidaScore> findList(int i, int pageSize, Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		this.setHQL(hqlB, params, map);
		return this.findListByHQL(i, pageSize, hqlB.toString(), params);
	}

	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		this.setHQL(hqlB, params, map);
		return this.countQueryResult(hqlB.toString(), params);
	}

	public void setHQL(StringBuilder hqlB, List<Object> params, Map<String, Object> map) {
		hqlB = hqlB.append("from QidaScore where 1=1");
		if (map.get("id") != null) {
			hqlB.append(" and id=?");
			params.add(map.get("id"));
		}
		if (map.get("userId") != null) {
			hqlB.append(" and fuUser.id=?");
			params.add(map.get("userId"));
		}
		if (map.get("nickName") != null) {
			hqlB.append(" and fuUser.nickName like '%" + map.get("nickName") + "%'");
		}
		if (map.get("pid") != null) {
			hqlB.append(" and fuDictionary.pid=?");
			params.add(map.get("pid"));
		}
		if (map.get("dictionaryId") != null) {
			hqlB.append(" and fuDictionary.id=?");
			params.add(map.get("dictionaryId"));
		}
		if (map.get("comment") != null) {
			hqlB.append(" and comment like '%" + map.get("comment") + "%'");
		}
		if (map.get("isIncome") != null) {
			hqlB.append(" and isIncome=?");
			params.add(map.get("isIncome"));
		}
		if (map.get("sort") != null && map.get("order") != null) {
			hqlB.append(" order by " + map.get("sort") + " " + map.get("order"));
		} else {
			hqlB.append(" order by id desc");
		}
	}

}
