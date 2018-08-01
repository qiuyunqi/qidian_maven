package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuSmsLog;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class FuSmsLogDao extends BaseDAO<FuSmsLog, Long> {

	public Integer countLog(HashMap<String, Object> map) {
		String hql = " from FuSmsLog where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("destination")) {
			params.add(map.get("destination"));
			hql = hql + " and destination=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		if (map.containsKey("reason")) {
			hql = hql + " and reason like '%" + map.get("reason") + "%' ";
		}
		if (map.containsKey("content")) {
			hql = hql + " and content like '%" + map.get("content") + "%' ";
		}
		if (map.containsKey("prio")) {
			params.add(map.get("prio"));
			hql = hql + " and prio=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public List<FuSmsLog> findLogList(int i, int j, HashMap<String, Object> map) {
		String hql = " from FuSmsLog where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("destination")) {
			params.add(map.get("destination"));
			hql = hql + " and destination=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		if (map.containsKey("reason")) {
			hql = hql + " and reason like '%" + map.get("reason") + "%' ";
		}
		if (map.containsKey("content")) {
			hql = hql + " and content like '%" + map.get("content") + "%' ";
		}
		if (map.containsKey("prio")) {
			params.add(map.get("prio"));
			hql = hql + " and prio=? ";
		}
		hql = hql + " order by id desc ";
		return this.findListByHQL(i, j, hql, params);
	}

	public FuSmsLog findLogByMap(Map<String, Object> map) {
		String hql = " from FuSmsLog where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("destination")) {
			params.add(map.get("destination"));
			hql = hql + " and destination=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		hql = hql + " order by id desc ";
		if (this.findAllByHQL(hql, params).size() > 0) {
			return this.findAllByHQL(hql, params).get(0);
		} else {
			return null;
		}
	}

	public List<FuSmsLog> findLogListByMap(Map<String, Object> map) {
		String hql = " from FuSmsLog where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("userName")) {
			params.add(map.get("userName"));
			hql = hql + " and fuUser.userName=? ";
		}
		if (map.containsKey("destination")) {
			params.add(map.get("destination"));
			hql = hql + " and destination=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		if (map.containsKey("state")) {
			params.add(map.get("state"));
			hql = hql + " and state=? ";
		}
		if (map.containsKey("reason")) {
			hql = hql + " and reason like '%" + map.get("reason") + "%' ";
		}
		if (map.containsKey("content")) {
			hql = hql + " and content like '%" + map.get("content") + "%' ";
		}
		if (map.containsKey("prio")) {
			params.add(map.get("prio"));
			hql = hql + " and prio=? ";
		}
		if (map.get("state").toString().equals("1")) {
			hql = hql + " order by id asc ";
		} else {
			hql = hql + " order by sendTime asc ";
		}
		return this.findAllByHQL(hql, params);
	}

	// 在规定的时间内查询该电话号码发送了多少条短信
	@SuppressWarnings("unchecked")
	public Integer getNumByPhoneInMin(String phone, Integer regInterval) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -regInterval);
		Date time = calendar.getTime();
		String sql = "from FuSmsLog as f where f.destination = :phone and f.planTime >= :time";
		List<FuSmsLog> list = this.getSession().createQuery(sql)//
				.setParameter("phone", phone)//
				.setParameter("time", time)//
				.list();
		return null == list ? 0 : list.size();
	}

	// 查询该手机号最新的一条记录
	public FuSmsLog findNewMessage(String phone) {
		String hql = "FROM FuSmsLog AS f WHERE f.destination = :phone ORDER BY f.planTime desc";
		return (FuSmsLog) this.getSession().createQuery(hql)//
				.setParameter("phone", phone)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

	public void saveSendServiceEmail(String reason, String content) {
		FuSmsLog log = new FuSmsLog();
		log.setReason(reason);
		log.setPrio(1);
		log.setState(0);
		log.setPlanTime(new Date());
		log.setType(2);
		log.setContent(content);
		this.save(log);
	}

	public List<FuSmsLog> findMailLogList() {
		String hql = " from FuSmsLog where type = 2 and (state = 0 or state = 2)";
		List<Object> params = new ArrayList<Object>();
		hql = hql + " order by planTime asc ";
		return this.findAllByHQL(hql, params);
	}

	// 获取最新的验证码
	public FuSmsLog getNewCode(String phone) {
		String hql = "FROM FuSmsLog AS f WHERE f.destination = :phone ORDER BY f.id DESC";
		return (FuSmsLog) this.getSession().createQuery(hql)//
				.setParameter("phone", phone)//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
	}

}
