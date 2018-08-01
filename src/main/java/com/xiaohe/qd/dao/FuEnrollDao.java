package com.xiaohe.qd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuEnroll;

@SuppressWarnings("unchecked")
@Repository
public class FuEnrollDao extends BaseDAO<FuEnroll, Long>{

	/**
	 * 检测手机号是否报名过
	 * @param phone
	 * @return
	 */
	public int findByPhone(String phone) {
		String hql = "FROM FuEnroll WHERE phone = :phone";
		List<FuEnroll> list = this.getSession().createQuery(hql)//
		.setParameter("phone", phone)//
		.list();
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

	/**
	 * 查询全部的报名人数
	 * @return
	 */
	public int getCount() {
		String hql = "FROM FuEnroll";
		List<FuEnroll> list = this.getSession().createQuery(hql).list();
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

	/**
	 * 分页查询报名数据
	 * @param currData   当前数据的条数
	 * @param PageSize   每页数据的大小
	 * @return
	 */
	public List<FuEnroll> findByList(Integer currData, Integer pageSize) {
		String hql = "FROM FuEnroll";
		return this.getSession().createQuery(hql)//
				.setFirstResult(currData)//
				.setMaxResults(pageSize)
				.list();
	}

}
