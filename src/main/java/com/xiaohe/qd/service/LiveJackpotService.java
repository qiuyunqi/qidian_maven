package com.xiaohe.qd.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.LiveJackpotDao;
import com.xiaohe.qd.model.LiveJackpot;

@Service
public class LiveJackpotService {

	@Resource
	private LiveJackpotDao jackpotDao;

	/**
	 * 分页查询奖品池
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public List<LiveJackpot> findAll(int currPage, Integer pageSize) {
		return jackpotDao.findAll(currPage, pageSize);
	}

	/**
	 * 查询全部的奖品池数量
	 * @return
	 */
	public Integer getCount() {
		return jackpotDao.getCount();
	}

	/**
	 * 根据唯一标识符查询出数据
	 * @param id
	 * @return
	 */
	public LiveJackpot getById(Long id) {
		return jackpotDao.get(id);
	}

	/**
	 * 保存实体类对象
	 * @param lj
	 */
	public void save(LiveJackpot lj) {
		jackpotDao.save(lj);
	}

	/**
	 * 删除数据
	 * @param id
	 */
	public void delete(Long id) {
		jackpotDao.delete(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void dels(List<Long> ids) {
		for (Long id : ids) {
			jackpotDao.delete(id);
		}
	}

	/**
	 * 逻辑删除
	 * @param ids
	 */
	public void updateDel(List<Long> ids) {
		for (Long id : ids) {
			LiveJackpot liveJackpot = jackpotDao.get(id);
			if (liveJackpot != null) {
				liveJackpot.setIsDel(1);
				jackpotDao.update(liveJackpot);
			}
		}

	}

	/**
	 * 查询当前期数的奖品池
	 * @return
	 */
	public List<LiveJackpot> findNowPeriods() {
		return jackpotDao.findNowPeriods();
	}
}
