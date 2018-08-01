package com.xiaohe.qd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockCommentApprovalDao;
import com.xiaohe.qd.model.StockCommentApproval;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockCommentApprovalService extends BaseService {
	@Resource
	private StockCommentApprovalDao stockCommentApprovalDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockCommentApproval get(Long id) {
		return stockCommentApprovalDao.get(id);
	}

	public void save(StockCommentApproval entity) {
		stockCommentApprovalDao.save(entity);
	}

	public void delete(Long id) {
		stockCommentApprovalDao.delete(id);
	}

	public int countStockCommentApproval(Long commentId, Long userId) {
		return stockCommentApprovalDao.countStockCommentApproval(commentId, userId);
	}
}
