package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaScoreDao;
import com.xiaohe.qd.model.QidaScore;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaScoreService extends BaseService {
	@Resource
	private QidaScoreDao qidaScoreDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaScore get(Long id) {
		return qidaScoreDao.get(id);
	}

	public void save(QidaScore entity) {
		qidaScoreDao.save(entity);
	}

	public void delete(Long id) {
		qidaScoreDao.delete(id);
	}

	public List<QidaScore> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaScoreDao.findList(i, pageSize, map);
	}

	public Integer getCount(Map<String, Object> map) {
		return qidaScoreDao.getCount(map);
	}

}
