package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaAgreeDao;
import com.xiaohe.qd.model.QidaAgree;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaAgreeService extends BaseService {
	@Resource
	private QidaAgreeDao qidaAgreeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaAgree get(Long id) {
		return qidaAgreeDao.get(id);
	}

	public void save(QidaAgree entity) {
		qidaAgreeDao.save(entity);
	}

	public void delete(Long id) {
		qidaAgreeDao.delete(id);
	}

	/**
	 * 查询某用户赞同反对某回答的记录
	 */
	public Integer findByMySelf(Long userId, Long answerId) {
		return qidaAgreeDao.findByMySelf(userId, answerId);
	}
	
	public List<QidaAgree> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaAgreeDao.findList(i, pageSize, map);
	}

}
