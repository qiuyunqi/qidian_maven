package com.xiaohe.qd.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuEnrollDao;
import com.xiaohe.qd.model.FuEnroll;

@Service
public class FuEnrollService extends BaseService {

	@Resource
	private FuEnrollDao fuEnrollDao;

	/**
	 * 保存报名对象 0: 保存失败 1: 保存成功
	 * 
	 * @param userName
	 * @param phone
	 * @return
	 */
	public int save(String userName, String phone) {
		try{
			FuEnroll fuEnroll = new FuEnroll();
			fuEnroll.setUserName(userName);
			fuEnroll.setPhone(phone);
			fuEnroll.setCreateTime(new Date());
			fuEnrollDao.save(fuEnroll);
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}

	/**
	 * 查询是否报过名
	 * @param phone  手机号码
	 * @return
	 */
	public int findByPhone(String phone) {
		return fuEnrollDao.findByPhone(phone);
	}

	/**
	 * 查询全部的报名人数
	 * @return
	 */
	public int getCount() {
		return fuEnrollDao.getCount();
	}

	/**
	 * 分页查询报名数据
	 * @param currData   当前数据的条数
	 * @param PageSize   每页数据的大小
	 * @return
	 */
	public List<FuEnroll> findByList(Integer currData, Integer PageSize) {
		return fuEnrollDao.findByList(currData, PageSize);
	}
}
