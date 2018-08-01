package com.xiaohe.qd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.LiveJackpotTime;

@Repository
public class LiveJackpotTimeDao extends BaseDAO<LiveJackpotTime, Long>{

	/**
	 * 分页查询全部的特殊时间段列表
	 * @param currPage
	 * @param rows
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LiveJackpotTime> findAll(Integer currPage, Integer rows) {
		String hql = "FROM LiveJackpotTime ORDER BY isDel ASC, id DESC";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currPage)//
				.setMaxResults(rows)//
				.list();
	}

	/**
	 * 查询特殊时间段记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getCount() {
		String hql = "FROM LiveJackpotTime";
		List<LiveJackpotTime> list = this.getSession().createQuery(hql).list();
		return (null != list && list.size() > 0) ? list.size() : 0;
	}

	/**
	 * 根据这个两个条件查询对象集合
	 * @param jackpotId  livejackpot对象的主键
	 * @param isDel   0: 不删除 1: 删除
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<LiveJackpotTime> findByJackIdAndIsDel(Long jackpotId, int isDel) {
		String hql = "FROM LiveJackpotTime WHERE livejackpot.id = :jackpotId AND isDel = :isDel";
		return this.getSession().createQuery(hql)//
				.setParameter("jackpotId", jackpotId)//
				.setParameter("isDel", isDel)//
				.list();
	}

}
