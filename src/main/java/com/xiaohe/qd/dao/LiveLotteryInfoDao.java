package com.xiaohe.qd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.LiveLotteryInfo;

@Repository
@SuppressWarnings("unchecked")
public class LiveLotteryInfoDao extends BaseDAO<LiveLotteryInfo, Long> {

	/**
	 * 分页查询数据
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List<LiveLotteryInfo> findAll(int currPage, Integer pageSize) {
		String hql = "FROM LiveLotteryInfo ORDER BY drawTime DESC";
		return this.getSession().createQuery(hql).setFirstResult(currPage).setMaxResults(pageSize).list();
	}

	/**
	 * 查询所有的数据的数量
	 * @return
	 */
	public Integer getCount() {
		String hql = "FROM LiveLotteryInfo";
		List<LiveLotteryInfo> list = this.getSession().createQuery(hql).list();
		return null != list && list.size() > 0 ? list.size() : 0;
	}

	/**
	 * 查询被设置的用户是否已经被设置过
	 * @param userId		用户id
	 * @param status		用户是否是被选中的
	 * @param isReceive		领取的奖品是否被领取
	 * @return
	 */
	public LiveLotteryInfo findByStatusAndReceive(Long userId, int status,
			int isReceive) {
		String hql = "FROM LiveLotteryInfo WHERE fuUser.id = :userId AND status = :status AND isReceive = :isReceive";
		return (LiveLotteryInfo) this.getSession().createQuery(hql)//
				.setParameter("userId", userId)//
				.setParameter("status", status)//
				.setParameter("isReceive", isReceive)//
				.uniqueResult();
	}

}
