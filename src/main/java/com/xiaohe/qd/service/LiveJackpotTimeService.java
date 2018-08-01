package com.xiaohe.qd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.LiveJackpotTimeDao;
import com.xiaohe.qd.model.LiveJackpotTime;


@Service
public class LiveJackpotTimeService {
	
	@Autowired
	private LiveJackpotTimeDao timeDao;

	/**
	 * 分页查询特殊时段列表
	 * @param currPage	当前页
	 * @param rows		每页大小
	 * @return
	 */
	public List<LiveJackpotTime> findAll(Integer currPage, Integer rows) {
		return timeDao.findAll(currPage, rows);
	}

	/**
	 * 查询记录条数
	 * @return
	 */
	public Integer getCount() {
		return timeDao.getCount();
	}

	/**
	 * 保存特殊时段对象
	 * @param jackpotTime
	 * @return
	 */
	public int save(LiveJackpotTime jackpotTime) {
		try {
			timeDao.save(jackpotTime);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 根据这个两个条件查询对象集合
	 * @param jackpotId  livejackpot对象的主键
	 * @param isDel   0: 不删除 1: 删除
	 * @return
	 */
	public List<LiveJackpotTime> findByJackIdAndIsDel(Long jackpotId, int isDel) {
		return timeDao.findByJackIdAndIsDel(jackpotId, isDel);
	}

	/**
	 * 通过id查询对象
	 * @param id
	 * @return
	 */
	public LiveJackpotTime getById(Long id) {
		return timeDao.get(id);
	}

	/**
	 * 批量修改启用状态
	 * @param ids
	 * @param isDel 0: 不删除 1:删除
	 */
	public void updateDel(List<Long> ids, Integer isDel) {
		for (Long id : ids) {
			LiveJackpotTime liveJackpotTime = timeDao.get(id);
			liveJackpotTime.setIsDel(isDel);
			timeDao.save(liveJackpotTime);
		}
	}

}
