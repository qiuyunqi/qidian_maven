package com.xiaohe.qd.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuMoneyDetail;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class FuMoneyDetailDao extends BaseDAO<FuMoneyDetail, Long> {

	public List<FuMoneyDetail> findListBy(int i, int pageSize, Map<String, Object> map) {
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

	public List<FuMoneyDetail> findFuMoneyDetailByParams(Map<String, Object> map) {
		List<Object> params = new ArrayList<Object>();
		String hql = " from FuMoneyDetail where 1=1 ";
		if (map.containsKey("id")) {
			params.add(map.get("id"));
			hql = hql + " and id=? ";
		}
		if (map.get("userId") != null) {
			params.add(map.get("userId"));
			hql = hql + " and fuUser.id=? ";
		}
		if (map.get("isCheck") != null) {
			params.add(map.get("isCheck"));
			hql = hql + " and isCheck=? ";
		}
		if (map.containsKey("programId")) {
			params.add(map.get("programId"));
			hql = hql + " and fuProgram.id=? ";
		}
		if (map.containsKey("dictionaryId")) {
			params.add(map.get("dictionaryId"));
			hql = hql + " and fuDictionary.id=? ";
		}
		if (map.containsKey("accountName")) {
			params.add(map.get("accountName"));
			hql = hql + " and fuUser.accountName=? ";
		}
		if (map.containsKey("userName")) {
			hql = hql + " and fuUser.userName like '%" + map.get("userName") + "%' ";
		}
		if (map.containsKey("status")) {
			params.add(map.get("status"));
			hql = hql + " and status = ? ";
		}
		if (map.containsKey("money1")) {
			params.add(map.get("money1"));
			hql = hql + " and money >= ? ";
		}
		if (map.containsKey("money2")) {
			params.add(map.get("money2"));
			hql = hql + " and money <= ? ";
		}
		if (map.containsKey("date1")) {
			params.add(map.get("date1"));
			hql = hql + " and time >= ? ";
		}
		if (map.containsKey("date2")) {
			params.add(map.get("date2"));
			hql = hql + " and time <= ? ";
		}
		if (map.containsKey("comment")) {
			params.add(map.get("comment"));
			hql = hql + " and comment = ? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and fuUser.state = ? ";
		}
		hql = hql + " order by id desc ";
		return this.findAllByHQL(hql, params);
	}

	public void setHQL(StringBuilder hqlB, List<Object> params, Map<String, Object> map) {
		hqlB = hqlB.append("from FuMoneyDetail where 1=1");
		if (map.get("id") != null) {
			hqlB.append(" and id=?");
			params.add(map.get("id"));
		}
		if (map.get("userId") != null) {
			hqlB.append(" and fuUser.id=?");
			params.add(map.get("userId"));
		}
		if (map.get("programId") != null) {
			hqlB.append(" and fuProgram.id=?");
			params.add(map.get("programId"));
		}
		if (map.containsKey("accountName")) {
			hqlB.append(" and fuUser.accountName=? ");
			params.add(map.get("accountName"));
		}
		if (map.get("userName") != null) {
			hqlB.append(" and fuUser.userName like '%" + map.get("userName") + "%'");
		}
		if (map.get("pid") != null) {
			hqlB.append(" and fuDictionary.pid=?");
			params.add(map.get("pid"));
		}
		if (map.get("dictionaryId") != null) {
			hqlB.append(" and fuDictionary.id=?");
			params.add(map.get("dictionaryId"));
		}
		if (map.get("money1") != null) {
			hqlB.append(" and money>=?");
			params.add(map.get("money1"));
		}
		if (map.get("money2") != null) {
			hqlB.append(" and money<=?");
			params.add(map.get("money2"));
		}
		if (map.get("money3") != null) {
			hqlB.append(" and money <> 0");
		}
		if (map.get("date1") != null) {
			hqlB.append(" and time>=?");
			params.add(map.get("date1"));
		}
		if (map.get("date2") != null) {
			hqlB.append(" and time<=?");
			params.add(map.get("date2"));
		}
		if (map.get("rechargeType") != null) {
			hqlB.append(" and rechargeType like '%" + map.get("rechareType") + "%'");
		}
		if (map.get("comment") != null) {
			hqlB.append(" and comment like '%" + map.get("comment") + "%'");
		}
		if (map.get("isCheck") != null) {
			hqlB.append(" and isCheck=?");
			params.add(map.get("isCheck"));
		}
		if (map.get("queryUserId") != null) {
			hqlB.append(" and fuUser.id = ?");
			params.add(map.get("queryUserId"));
		}
		if (map.get("time1") != null && map.get("time2") != null) {
			hqlB.append(" and date_format(time,'%Y-%m-%d') >= ? and date_format(time,'%Y-%m-%d') <= ? ");
			params.add(map.get("time1"));
			params.add(map.get("time2"));
		}
		if (map.get("time1") != null && map.get("time2") == null) {
			hqlB.append(" and date_format(time,'%Y-%m-%d') >= ?");
			params.add(map.get("time1"));
		}
		if (map.get("time1") == null && map.get("time2") != null) {
			hqlB.append(" and date_format(time,'%Y-%m-%d') <= ? ");
			params.add(map.get("time2"));
		}
		if (map.get("isIncome") != null) {
			hqlB.append(" and isIncome=?");
			params.add(map.get("isIncome"));
		}
		if (map.get("state") != null) {
			hqlB.append(" and fuUser.state=? ");
			params.add(map.get("state"));
		}
		hqlB.append(" order by id desc");
	}

	/**
	 * 根据用户查询用户的收入详情列表
	 * 
	 * @param userId
	 * @param isInCome
	 *            0: 支出 1: 收入
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FuMoneyDetail> findInComeByUserId(long userId, int isInCome, Integer curPage, Integer pageSize) {
		boolean isIncome = true;
		if (isInCome != 1) {
			isIncome = false;
		}
		String hql = "FROM FuMoneyDetail as f WHERE f.fuUser.id = :userId and f.isIncome = :isInCome and f.money <> 0 ORDER BY f.id desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("isInCome", isIncome)//
				.setFirstResult(curPage)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 根据条件查询累计的收益
	 * 
	 * @param userId
	 *            用户id
	 * @param dictionaryId
	 *            字典id
	 * @param isEnabled
	 *            字典的可用值 1: 可用 0: 不可用
	 * @return
	 */
	public BigDecimal getCountMoneyByDictionaryIdAndUserId(Long userId, Long dictionaryId, int isEnabled) {
		String sql = "SELECT SUM(money) FROM fu_money_detail, sys_dictionary  " + "WHERE user_id = :userId AND fu_money_detail.dictionary_id = :dictionaryId  and "
				+ "fu_money_detail. dictionary_id = sys_dictionary.id and sys_dictionary.isEnabled = :isEnable";
		return (BigDecimal) this.getSession().createSQLQuery(sql)//
				.setParameter("userId", userId)//
				.setParameter("dictionaryId", dictionaryId)//
				.setParameter("isEnable", isEnabled)//
				.uniqueResult();
	}

	// 查找某个用户的资金明细
	@SuppressWarnings("unchecked")
	public List<FuMoneyDetail> findListByDetailId(Long userId, long dictionaryId, Long detailId) {
		String hql = "FROM FuMoneyDetail AS f WHERE f.fuUser.id = :userId AND f.fuDictionary.id = :dictionaryId ";

		if (null != detailId) {
			hql += " AND f.id < " + detailId;
		}
		hql += " ORDER BY f.time DESC";

		return this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("dictionaryId", dictionaryId)//
				.setFirstResult(0)//
				.setMaxResults(10)//
				.list();

	}

	// 根据指定记录数查询用户的资金明细
	public List<FuMoneyDetail> findListByUserIdCount(Long userId, Integer i) {
		List<Object> params = new ArrayList<Object>();
		String hql = " from FuMoneyDetail where 1=1 ";
		params.add(userId);
		hql = hql + " and fuUser.id=? ";
		hql = hql + " order by time desc ";
		return this.findListByHQL(0, i, hql, params);
	}

	public List<FuMoneyDetail> findListByUserId(Long userId) {
		String hql = " from FuMoneyDetail where fuUser.id=? ";
		hql = hql + " order by time desc ";
		return this.findAllByHQL(hql, userId);
	}
}
