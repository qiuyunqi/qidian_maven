package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaStockQuestionDao;
import com.xiaohe.qd.model.QidaStockQuestion;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaStockQuestionService extends BaseService {
	@Resource
	private QidaStockQuestionDao qidaStockQuestionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaStockQuestion get(Long id) {
		return qidaStockQuestionDao.get(id);
	}

	public void save(QidaStockQuestion entity) {
		qidaStockQuestionDao.save(entity);
	}

	public void delete(Long id) {
		qidaStockQuestionDao.delete(id);
	}
	
	public List<QidaStockQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaStockQuestionDao.findList(i, pageSize, map);
	}

}
