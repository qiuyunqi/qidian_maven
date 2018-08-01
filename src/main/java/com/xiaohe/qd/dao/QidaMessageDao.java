package com.xiaohe.qd.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.QidaMessage;

@SuppressWarnings("unchecked")
@Repository
public class QidaMessageDao extends BaseDAO<QidaMessage, Long>{

	/**
	 * 查询我所有没有删除的消息 默认只是给5个  其他的 只能到更多中去获取
	 * @param receiveFuUserId 接收消息人的id
	 * @param isDelete 0: 未删除 1: 删除 默认0
	 * @param isMore 0: 查询前5个 1: 查询全部 默认0
	 * @return
	 */
	public List<QidaMessage> findByMe(Long receiveFuUserId, int isDelete, int isMore) {
		String hql = "FROM QidaMessage WHERE receiveFuUser.id = :userId AND isDelete = :isDelete ORDER BY createTime DESC";
		if (isMore == 0) {
			return this.getSession().createQuery(hql)//
					.setParameter("userId", receiveFuUserId)//
					.setParameter("isDelete", isDelete)//
					.setFirstResult(0)//
					.setMaxResults(5)//
					.list();
		} else {
			return this.getSession().createQuery(hql)//
					.setParameter("userId", receiveFuUserId)//
					.setParameter("isDelete", isDelete)//
					.list();
		}
	}

	/**
	 * 查询我最新的站内信消息
	 * @param pageSize 
	 * @param pageData 
	 * @param receiveFuUserId
	 * @param isRead
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> findNew(int pageData, int pageSize, Long receiveFuUserId, int isRead, int isDelete) {
		String hql = "FROM QidaMessage WHERE receiveFuUser.id = :userId AND isDelete = :isDelete AND isRead = :isRead ORDER BY createTime DESC";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.setParameter("isRead", isRead)//
				.setFirstResult(pageData)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 分页查询我所有的站内消息
	 * @param pageData	从那条数据开始
	 * @param pageSize  每页数据的大小
	 * @param receiveFuUserId  接收消息的人
	 * @param isDelete    是否删除  0: 未删除  1:删除
	 * @return
	 */
	public List<QidaMessage> findByMe(int pageData, int pageSize, Long receiveFuUserId, int isDelete) {
		String hql = "FROM QidaMessage WHERE receiveFuUser.id = :userId AND isDelete = :isDelete GROUP BY httpCookie ORDER BY isRead ASC, createTime Desc";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.setFirstResult(pageData)//
				.setMaxResults(pageSize)//
				.list();
	}
	
	/**
	 * 分页查询我所有的站内消息的最新时间
	 * @param pageData	从那条数据开始
	 * @param pageSize  每页数据的大小
	 * @param receiveFuUserId  接收消息的人
	 * @param isDelete    是否删除  0: 未删除  1:删除
	 * @return
	 */
	public List<BigInteger> getNewDatByGroup(int pageData, int pageSize, Long receiveFuUserId, int isDelete) {
		String sql = "SELECT MAX(message_id) FROM qida_message WHERE receive_user_id = :userId AND is_delete = :isDelete  GROUP BY http_cookie ORDER BY is_read, create_time DESC";
		return this.getSession().createSQLQuery(sql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.setFirstResult(pageData)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 查询同一个会话的消息
	 * @param httpCookie
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> findByHttpCookie(String httpCookie, int isDelete) {
		String hql = "FROM QidaMessage WHERE httpCookie = :httpCookie AND isDelete = :isDelete ORDER BY createTime ASC";
		return this.getSession().createQuery(hql)//
				.setParameter("isDelete", isDelete)//
				.setParameter("httpCookie", httpCookie)//
				.list();
	}

	/**
	 * 根据ids查询出对象集合
	 * @param ids
	 * @return
	 */
	public List<QidaMessage> findByIds(List<Long> ids) {
		String hql = "FROM QidaMessage WHERE id in (:ids)";
		List<QidaMessage> list = this.getSession().createQuery(hql)//
		.setParameterList("ids", ids)//
		.list();
		return (null == list || list.size() <= 0) ? null : list;
	}

	/**
	 * 查询对应条件的数据集合数量
	 * @param receiveFuUserId  接收消息的人
	 * @param isDelete    是否删除  0: 未删除  1:删除
	 * @return
	 */
	public int getCount(Long receiveFuUserId, int isDelete) {
		String hql = "FROM QidaMessage WHERE receiveFuUser.id = :userId AND isDelete = :isDelete GROUP BY httpCookie";
		List<QidaMessage> list = this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.list();
		return (null == list || list.size() <= 0) ?  0 : list.size();
	}

	/**
	 * 通过创建时间查询记录
	 * @param receiveFuUserId 
	 * @param createTime
	 * @return
	 */
	public List<QidaMessage> findByTime(Long receiveFuUserId, Date createTime) {
		String hql = "FROM QidaMessage WHERE receiveFuUser.id = :userId AND  createTime = :createTime";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("createTime", createTime)//
				.list();
	}

	/**
	 * 查询出messageId == null的message
	 * @param pageData
	 * @param pageSize
	 * @param receiveFuUserId
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> finfMessageId(int pageData, int pageSize,
			Long receiveFuUserId, int isDelete) {
		String hql = "FROM QidaMessage WHERE messageId = null AND receiveFuUser.id = :userId AND isDelete = :isDelete";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.setFirstResult(pageData)//
				.setMaxResults(pageSize)//
				.list();
	}

	/**
	 * 根据同一会话查询接收者的消息对象集合
	 * @param httpCookie
	 * @param receiveFuUserId  消息的接受者
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> findByHttpCookieAndUser(String httpCookie,
			Long receiveFuUserId, int isDelete) {
		String hql = "FROM QidaMessage WHERE httpCookie = :httpCookie AND isDelete = :isDelete AND receiveFuUser.id = :userId";
		return this.getSession().createQuery(hql)//
				.setParameter("userId", receiveFuUserId)//
				.setParameter("isDelete", isDelete)//
				.setParameter("httpCookie", httpCookie)//
				.list();
	}

}
