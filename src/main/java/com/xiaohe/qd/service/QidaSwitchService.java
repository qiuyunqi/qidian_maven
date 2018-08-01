package com.xiaohe.qd.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.QidaSwitchDao;
import com.xiaohe.qd.model.QidaSwitch;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class QidaSwitchService extends BaseService {
	@Resource
	private QidaSwitchDao qidaSwitchDao;

	// ====================== 基本 C R U D 方法 ===========================
	public QidaSwitch get(Long id) {
		return qidaSwitchDao.get(id);
	}

	public void save(QidaSwitch entity) {
		qidaSwitchDao.save(entity);
	}

	public void delete(Long id) {
		qidaSwitchDao.delete(id);
	}

}
