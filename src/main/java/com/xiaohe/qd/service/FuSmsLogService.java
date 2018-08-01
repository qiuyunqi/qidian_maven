package com.xiaohe.qd.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuSmsLogDao;
import com.xiaohe.qd.model.FuParameter;
import com.xiaohe.qd.model.FuSmsLog;
import com.xiaohe.qd.model.SysIpBlacklist;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class FuSmsLogService extends BaseService {
	@Resource
	private FuSmsLogDao fuSmsLogDao;
	@Resource
	private SysIpBlacklistService sysIpBlacklistService;
	@Resource
	private FuParameterService fuParameterService;

	// ====================== 基本 C R U D 方法 ===========================
	public FuSmsLog get(Long id) {
		return fuSmsLogDao.get(id);
	}

	public void save(FuSmsLog entity) {
		fuSmsLogDao.save(entity);
	}

	public void delete(Long id) {
		fuSmsLogDao.delete(id);
	}

	public Integer countLog(HashMap<String, Object> map) {
		return fuSmsLogDao.countLog(map);
	}

	public List<FuSmsLog> findLogList(int i, int j, HashMap<String, Object> map) {
		return fuSmsLogDao.findLogList(i, j, map);
	}

	public FuSmsLog findLogByMap(Map<String, Object> map) {
		return fuSmsLogDao.findLogByMap(map);
	}

	public List<FuSmsLog> findLogListByMap(Map<String, Object> map) {
		return fuSmsLogDao.findLogListByMap(map);
	}

	public int examin(String phone, String ip) {
		// 查询手机号和ip是否在黑名单中
		List<SysIpBlacklist> backList = sysIpBlacklistService.getBlackListByPhoneOrIp(phone, ip);
		if (null != backList && backList.size() > 0) { // 在黑名单中
			return 0;
		}
		// 查询手机号在一个时段发送了多少条
		FuParameter fuParameter = fuParameterService.findParameter();
		Integer num = fuSmsLogDao.getNumByPhoneInMin(phone, fuParameter.getRegInterval());
		if (num > (null == fuParameter.getSendNum() ? 10 : fuParameter.getSendNum())) { // 加入黑名单
			SysIpBlacklist fb = new SysIpBlacklist();
			fb.setIp(phone);
			fb.setType(1);
			fb.setCreateTime(new Date());
			fb.setIsBlack(1);
			sysIpBlacklistService.save(fb);
			return 0;
		}
		// 间隔上一个短信时间是 30/S
		// FuIpBlacklist findBlackByRegIp =
		// fuIpBlacklistService.findBlackByRegIp(ip);
		FuSmsLog fsl = fuSmsLogDao.findNewMessage(phone);
		if (null != fsl) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.SECOND, -30);
			Long d = cal.getTime().getTime();
			Long s = d - (fsl.getPlanTime().getTime());

			// Date date = new Date(s);
			// String strFromDate = DateUtil.getStrFromDate(date, "ss");
			if (s.intValue() >= 0) {
				return 1;
			} else {
				return 2;
			}
		}
		return 1;
	}

	public void saveSendServiceEmail(String reason, String content) {
		fuSmsLogDao.saveSendServiceEmail(reason, content);

	}

	public List<FuSmsLog> findMailLogList() {
		return fuSmsLogDao.findMailLogList();
	}

	// 获取最新的验证码
	public FuSmsLog getNewCode(String phone) {
		return fuSmsLogDao.getNewCode(phone);
	}

}
