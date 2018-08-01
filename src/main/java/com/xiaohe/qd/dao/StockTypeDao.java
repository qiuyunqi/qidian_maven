package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockType;

@SuppressWarnings("unchecked")
@Repository
public class StockTypeDao extends BaseDAO<StockType, Long> {

	/**
	 * 根据pid获取类型列表
	 * 
	 * @param pid
	 *            父id
	 * @return
	 */
	public List<StockType> getStockInfoByPid(Long pid) {
		String hql = "FROM StockType AS s WHERE s.pid = :pid ORDER BY id desc";
		return this.getSession().createQuery(hql)//
				.setParameter("pid", pid)//
				.list();
	}

	/**
	 * 通过stock_type主键查询到stock_info信息
	 * 
	 * @param id
	 * @return
	 */
	public List<StockType> getStockInfoById(Long id) {
		String hql = "FROM StockType AS s WHERE s.id = :id";
		return this.getSession().createQuery(hql)//
				.setParameter("id", id)//
				.list();
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockType where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("liftRate") != null) {
			hql = hql + " and liftRate = " + map.get("liftRate");
		}
		return this.countQueryResult(hql);
	}

	public List<StockType> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from StockType where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("liftRate") != null) {
			hql = hql + " and liftRate = " + map.get("liftRate");
		}
		if (map.get("pid") != null) {
			hql = hql + " and pid > " + map.get("pid");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	public List<Object[]> findSumLiftRate(Long stockTypeId) {
		String sql = "SELECT SUM(c.lift_rate) FROM qd_stock_type a ";
		sql = sql + " LEFT JOIN qd_info_type b ON a.id=b.qd_stock_type_id ";
		sql = sql + " LEFT JOIN qd_stock_info c ON c.id=b.qd_stock_info_id ";
		sql = sql + " WHERE a.id = " + stockTypeId;
		return this.findBySqlGetArray(sql, null);
	}

}
