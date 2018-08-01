package com.xiaohe.qd.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaBroadcastDao;
import com.xiaohe.qd.model.QidaBroadcast;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaBroadcastService extends BaseService {
	@Resource
	private QidaBroadcastDao qidaBroadcastDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaBroadcast get(Long id) {
		return qidaBroadcastDao.get(id);
	}

	public void save(QidaBroadcast entity) {
		qidaBroadcastDao.save(entity);
	}

	public void delete(Long id) {
		qidaBroadcastDao.delete(id);
	}

	public Integer getCount(Map<String, Object> map) {
		return qidaBroadcastDao.getCount(map);
	}
	
	public List<QidaBroadcast> findList(int i, int pageSize, Map<String, Object> map) {
		return qidaBroadcastDao.findList(i, pageSize, map);
	}

}
