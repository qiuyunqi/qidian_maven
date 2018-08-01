package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.xiaohe.qd.model.QidaAgree;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class QidaAgreeDao extends BaseDAO<QidaAgree, Long> {
	/**
	 * 查询某用户赞同反对某回答的记录
	 */
	public Integer findByMySelf(Long userId, Long answerId) {
		String hql=" from QidaAgree where fuUser.id=? and qidaAnswer.id=? ";
		QidaAgree agree = this.findUniqueByHQL(hql, userId, answerId);
		if(agree != null){
			return agree.getType();
		}else{
			return -1;
		}
	}
	
	public List<QidaAgree> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from QidaAgree where 1=1 ";
		if (map.get("answerId") != null) {
			hql = hql + " and qidaAnswer.id = " + map.get("answerId");
		}
		if (map.get("questionId") != null) {
			hql = hql + " and qidaQuestion.id = " + map.get("questionId");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}
}
