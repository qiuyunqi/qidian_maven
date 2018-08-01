package com.xiaohe.qd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 站内信实体类
 * @author han
 * @date 2016-12-13
 */
@Entity
@Table(name = "qida_message")
public class QidaMessage implements Serializable {

	private static final long serialVersionUID = -8607929401152449554L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "message_id", unique = true)
	private Long messageId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "send_user_id")
	private FuUser sendFuUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receive_user_id")
	private FuUser receiveFuUser;
	
	@Column(name="message", nullable = false)
	private String message;
	
	@Column(name="is_read", nullable = false)
	private Integer isRead;
	
	@Column(name="is_delete", nullable = false)
	private Integer isDelete;
	
	@Column(name="create_time", nullable = false)
	private Date createTime;
	
	@Column(name="http_cookie", nullable = false)
	private String httpCookie;

	public QidaMessage(){}
	
	public QidaMessage(Long id, Long messageId, FuUser sendFuUser,
			FuUser receiveFuUser, String message, Integer isRead,
			Integer isDelete, Date createTime, String httpCookie) {
		super();
		this.id = id;
		this.messageId = messageId;
		this.sendFuUser = sendFuUser;
		this.receiveFuUser = receiveFuUser;
		this.message = message;
		this.isRead = isRead;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.httpCookie = httpCookie;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuUser getSendFuUser() {
		return sendFuUser;
	}

	public void setSendFuUser(FuUser sendFuUser) {
		this.sendFuUser = sendFuUser;
	}

	public FuUser getReceiveFuUser() {
		return receiveFuUser;
	}

	public void setReceiveFuUser(FuUser receiveFuUser) {
		this.receiveFuUser = receiveFuUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHttpCookie() {
		return httpCookie;
	}

	public void setHttpCookie(String httpCookie) {
		this.httpCookie = httpCookie;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
}
