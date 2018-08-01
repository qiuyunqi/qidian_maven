package com.xiaohe.qd.dao;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuParameter;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class FuParameterDao extends BaseDAO<FuParameter, Long> {
	public FuParameter findParameter() {
		String hql = " from FuParameter ";
		return this.findUniqueByHQL(hql);
	}

}
