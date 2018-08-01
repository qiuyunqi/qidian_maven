package com.xiaohe.qd.dao;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockCommentApproval;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@Repository
public class StockCommentApprovalDao extends BaseDAO<StockCommentApproval, Long> {

	public int countStockCommentApproval(Long commentId, Long userId) {
		String hql=" from StockCommentApproval where stockComment.id=? and fuUser.id=?";
		StockCommentApproval commentApproval = this.findUniqueByHQL(hql, commentId, userId);
		if(commentApproval != null){
			return commentApproval.getType();
		}else{
			return -1;
		}
	}
}
