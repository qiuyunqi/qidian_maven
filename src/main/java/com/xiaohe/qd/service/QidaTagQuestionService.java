package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaTagQuestionDao;
import com.xiaohe.qd.model.QidaTagQuestion;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaTagQuestionService extends BaseService {
	@Resource
	private QidaTagQuestionDao qidaTagQuestionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaTagQuestion get(Long id) {
		return qidaTagQuestionDao.get(id);
	}

	public void save(QidaTagQuestion entity) {
		qidaTagQuestionDao.save(entity);
	}

	public void delete(Long id) {
		qidaTagQuestionDao.delete(id);
	}
	
	public List<QidaTagQuestion> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaTagQuestionDao.findList(i, pageSize, map);
	}

}
