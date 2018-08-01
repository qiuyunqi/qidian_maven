package com.xiaohe.qd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.LiveLotteryInfoDao;
import com.xiaohe.qd.model.LiveLotteryInfo;

@Service
public class LiveLotteryInfoService {

	@Resource
	private LiveLotteryInfoDao lotteryInfoDao;

	/**
	 * 分页查询数据
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List<LiveLotteryInfo> findAll(int currPage, Integer pageSize) {
		return lotteryInfoDao.findAll(currPage, pageSize);
	}

	/**
	 * 查询所有的数据的数量
	 * @return
	 */
	public Integer getCount() {
		return lotteryInfoDao.getCount();
	}

	public LiveLotteryInfo getById(Long id) {
		return lotteryInfoDao.get(id);
	}

	public void save(LiveLotteryInfo lottery) {
		lotteryInfoDao.save(lottery);
	}

	/**
	 * 查询被设置的用户是否已经被设置过
	 * @param userId		用户id
	 * @param status		用户是否是被选中的
	 * @param isReceive		领取的奖品是否被领取
	 * @return
	 */
	public LiveLotteryInfo findByStatusAndReceive(Long userId, int status, int isReceive) {
		return lotteryInfoDao.findByStatusAndReceive(userId, status, isReceive);
	}
}
