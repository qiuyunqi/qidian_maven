package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaPayQuestionDao;
import com.xiaohe.qd.model.QidaPayQuestion;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaPayQuestionService extends BaseService {
	@Resource
	private QidaPayQuestionDao qidaPayQuestionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaPayQuestion get(Long id) {
		return qidaPayQuestionDao.get(id);
	}

	public void save(QidaPayQuestion entity) {
		qidaPayQuestionDao.save(entity);
	}

	public void delete(Long id) {
		qidaPayQuestionDao.delete(id);
	}

	/**
	 * 查询某用户是否支付问题
	 */
	public Integer findByMySelf(Long userId, Long questionId) {
		return qidaPayQuestionDao.findByMySelf(userId, questionId);
	}
	
	public List<QidaPayQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaPayQuestionDao.findList(i, pageSize, map);
	}

}
