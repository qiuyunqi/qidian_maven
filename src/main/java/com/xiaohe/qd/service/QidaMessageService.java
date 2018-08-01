package com.xiaohe.qd.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuUserDao;
import com.xiaohe.qd.dao.QidaMessageDao;
import com.xiaohe.qd.model.QidaMessage;

@Service
public class QidaMessageService {
	@Resource
	private QidaMessageDao messageDao;
	@Resource
	private FuUserDao fuUserDao;

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
		return messageDao.findNew(pageData, pageSize, receiveFuUserId, isRead, isDelete);
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
		List<BigInteger> list = messageDao.getNewDatByGroup(pageData, pageSize, receiveFuUserId, isDelete);
		List<QidaMessage> messsageList = new ArrayList<QidaMessage>();
		for (BigInteger messageId : list) {
			QidaMessage qidaMessage = messageDao.get(messageId.longValue());
			messsageList.add(qidaMessage);
		}
		return messsageList;
	}

	/**
	 * 查询具体的消息集合
	 * @param messageId
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> findViews(QidaMessage qidaMessage, int isDelete) {
		if (isDelete != qidaMessage.getIsDelete()) {
			return null;
		}
		List<QidaMessage> messageList = messageDao.findByHttpCookie(qidaMessage.getHttpCookie(), 0);
		if (null != messageList && messageList.size() > 0) {   // 表示不只是只有一个会话  有回复内容
			return messageList;
		} else {
			List<QidaMessage> list = new ArrayList<QidaMessage>();
			list.add(qidaMessage);
			return list;
		}
	}

	/**
	 * 将这条站内消息isDelete置为1 并且 isRead 置为1 
	 * @param ids  message对象的集合
	 * @return
	 */
	public int delete(String ids) {
		try {
			ids = ids.substring(0, ids.length() - 1);
			String[] idStr = ids.split(",");
			for (String id : idStr) {
				QidaMessage qidaMessage = messageDao.get(Long.parseLong(id));
				qidaMessage.setIsDelete(1);
				qidaMessage.setIsRead(1);
				messageDao.save(qidaMessage);
			}
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 设置站内信消息为已读状态
	 * @param messageIds
	 * @return
	 */
	public int saveIsRead(String messageIds) {
		try {
			String[] messIds = messageIds.split(",");
			List<Long> ids = new ArrayList<Long>();
			for (String sid : messIds) {
				ids.add(Long.parseLong(sid));
			}
			List<QidaMessage> list = messageDao.findByIds(ids);
			if (null == list) {
				return 0;
			} else {
				for (QidaMessage qidaMessage : list) {
					qidaMessage.setIsRead(1);
					messageDao.save(qidaMessage);
				}
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
	}

	/**
	 * 查询对应条件的数据集合数量
	 * @param receiveFuUserId  接收消息的人
	 * @param isDelete    是否删除  0: 未删除  1:删除
	 * @return
	 */
	public int getCount(Long receiveFuUserId, int isDelete) {
		return messageDao.getCount(receiveFuUserId, isDelete);
	}

	/**
	 * 保存消息
	 * @param message
	 */
	public void save(QidaMessage message) {
		messageDao.save(message);
	}

	/**
	 * 保存回复的站内信消息
	 * @param receiveUserId  接收人的id
	 * @param sendUserId	发信人的id
	 * @param content       发送的信息内容
	 * @param uuid			唯一的标识符uuid
	 * @param messageId    回复的站内信的id
	 * @return
	 */
	public int saveRply(Long receiveUserId, Long sendUserId, String content, String uuid, Long messageId) {
		try {
			QidaMessage qidaMessage = messageDao.get(messageId);
			if (null == qidaMessage) {
				return 0;
			}
			QidaMessage qm = new QidaMessage();
			List<QidaMessage> messageList = messageDao.findByHttpCookie(qidaMessage.getHttpCookie(), 0);
			if (null != messageList && messageList.size() > 0) {   // 表示不只是只有一个会话  有回复内容
				qm.setReceiveFuUser(fuUserDao.get(receiveUserId));
				qm.setSendFuUser(fuUserDao.get(sendUserId));
				qm.setMessage(content);
				qm.setIsRead(0);
				qm.setIsDelete(0);
				qm.setCreateTime(new Date());
				qm.setHttpCookie(qidaMessage.getHttpCookie());
			} else {
				qidaMessage.setHttpCookie(uuid);
				messageDao.save(qidaMessage);
			}
			messageDao.save(qm);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 获取message对象
	 * @param messageId
	 * @return
	 */
	public QidaMessage get(Long messageId) {
		return messageDao.get(messageId);
	}

	/**
	 * 如果有null的messageId 就设置成id
	 * @param pageData
	 * @param pageSize
	 * @param receiveFuUserId
	 * @param isDelete
	 */
	public void updateMessageId(int pageData, int pageSize, Long receiveFuUserId, int isDelete) {
		
		List<QidaMessage> msgs = messageDao.finfMessageId(pageData, pageSize, receiveFuUserId, isDelete);
		for (QidaMessage qidaMessage : msgs) {
			qidaMessage.setMessageId(qidaMessage.getId());
			messageDao.save(qidaMessage);
		}
		
	}

	/**
	 * 根据同一会话查询接收者的消息对象集合
	 * @param httpCookie
	 * @param receiveFuUserId  消息的接受者
	 * @param isDelete
	 * @return
	 */
	public List<QidaMessage> findByHttpCookie(String httpCookie, Long receiveFuUserId, int isDelete) {
		return messageDao.findByHttpCookieAndUser(httpCookie, receiveFuUserId, isDelete);
	}

}
