package com.xiaohe.qd.dao;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.HhrPromoteParameter;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class HhrPromoteParameterDao extends BaseDAO<HhrPromoteParameter, Long> {

	public HhrPromoteParameter findParameter() {
		String hql = " from HhrPromoteParameter ";
		return this.findUniqueByHQL(hql);
	}

}
