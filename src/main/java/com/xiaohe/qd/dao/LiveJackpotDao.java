package com.xiaohe.qd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.LiveJackpot;

@Repository
@SuppressWarnings("unchecked")
public class LiveJackpotDao extends BaseDAO<LiveJackpot, Long>{

	public List<LiveJackpot> findAll(int currPage, Integer pageSize) {
		String hql = "FROM LiveJackpot ORDER BY isDel ASC, id ASC";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currPage)//
				.setMaxResults(pageSize)//
				.list();
	}

	public Integer getCount() {
		String hql = "FROM LiveJackpot";
		List<LiveJackpot> list = this.getSession().createQuery(hql).list();
		return null != list && list.size() > 0 ? list.size() : 0;
	}

	/**
	 * 查询当前期数的奖品池
	 * @return
	 */
	public List<LiveJackpot> findNowPeriods() {
		String hql = "FROM LiveJackpot WHERE isDel = 0";
		return this.getSession().createQuery(hql).list();
	}

}
