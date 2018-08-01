package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.xiaohe.qd.model.QidaPayQuestion;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class QidaPayQuestionDao extends BaseDAO<QidaPayQuestion, Long> {
	/**
	 * 查询某用户是否支付问题
	 */
	public Integer findByMySelf(Long userId, Long questionId) {
		String hql=" from QidaPayQuestion where fuUser.id=? and qidaQuestion.id=? ";
		QidaPayQuestion payQuestion = this.findUniqueByHQL(hql, userId, questionId);
		if(payQuestion != null){
			return 1;
		}else{
			return 0;
		}
	}
	
	public List<QidaPayQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaPayQuestion where 1=1 ";
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
