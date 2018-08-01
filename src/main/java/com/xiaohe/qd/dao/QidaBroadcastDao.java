package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.xiaohe.qd.model.QidaBroadcast;

@Repository
public class QidaBroadcastDao extends BaseDAO<QidaBroadcast, Long> {
	public Integer getCount(Map<String, Object> map) {
		String hql = "from QidaBroadcast where 1=1 ";
		if (map.get("content") != null) {
			hql = hql + " and content like '%" + map.get("content") + "%'";
		}
		if (map.get("createDay") != null) {
			hql = hql + " and date_format(createDay,'%Y-%m-%d') = '" + map.get("createDay") + "'";
		}
		return this.countQueryResult(hql);
	}

	public List<QidaBroadcast> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaBroadcast where 1=1 ";
		if (map.get("content") != null) {
			hql = hql + " and content like '%" + map.get("content") + "%'";
		}
		if (map.get("createDay") != null) {
			hql = hql + " and date_format(createDay,'%Y-%m-%d') = '" + map.get("createDay") + "'";
		}
		hql = hql + " order by createTime desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
