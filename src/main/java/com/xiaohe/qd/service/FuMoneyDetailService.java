package com.xiaohe.qd.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuMoneyDetailDao;
import com.xiaohe.qd.model.FuMoneyDetail;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuMoneyDetailService extends BaseService {
	@Resource
	private FuMoneyDetailDao fuMoneyDetailDao;

	// ====================== 基本 C R U D 方法 ===========================
	public FuMoneyDetail get(Long id) {
		return fuMoneyDetailDao.get(id);
	}

	public void save(FuMoneyDetail entity) {
		fuMoneyDetailDao.save(entity);
	}

	public void delete(Long id) {
		fuMoneyDetailDao.delete(id);
	}

	public List<FuMoneyDetail> findListBy(int i, int pageSize, Map<String, Object> map) {
		return fuMoneyDetailDao.findListBy(i, pageSize, map);
	}

	public Integer getCount(Map<String, Object> map) {
		return fuMoneyDetailDao.getCount(map);
	}

	public List<FuMoneyDetail> findFuMoneyDetailByParams(Map<String, Object> map) {
		return fuMoneyDetailDao.findFuMoneyDetailByParams(map);
	}

	/**
	 * 根据用户查询用户的收入详情列表
	 * 
	 * @param userId
	 * @param isInCome
	 *            0: 支出 1: 收入
	 * @param curPage
	 *            当前页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	public List<FuMoneyDetail> findInComeByUserId(long userId, int isInCome, Integer curPage, Integer pageSize) {
		return fuMoneyDetailDao.findInComeByUserId(userId, isInCome, curPage, pageSize);
	}

	// 根据条件查询累计的收益
	public BigDecimal getCountMoneyByDictionaryIdAndUserId(Long userId, Long dictionaryId, int isEnabled) {
		return fuMoneyDetailDao.getCountMoneyByDictionaryIdAndUserId(userId, dictionaryId, isEnabled);
	}

	// 查找某个用户的资金明细
	public List<FuMoneyDetail> findListByDetailId(Long userId, long dictionaryId, Long detailId) {
		return fuMoneyDetailDao.findListByDetailId(userId, dictionaryId, detailId);
	}

	public List<FuMoneyDetail> findListByUserIdCount(Long userId, Integer i) {
		return fuMoneyDetailDao.findListByUserIdCount(userId, i);
	}

	public List<FuMoneyDetail> findListByUserId(Long userId) {
		return fuMoneyDetailDao.findListByUserId(userId);
	}

}
