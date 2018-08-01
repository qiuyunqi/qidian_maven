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

@Entity
@Table(name = "qida_broadcast")
public class QidaBroadcast implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 651586689274227890L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "content")
	private String content;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "admin_id")
	private SysAdmin sysAdmin;
	
	@Column(name = "create_day")
	private Date createDay;
	
	@Column(name = "create_time")
	private Date createTime;
	
	public QidaBroadcast() {
		super();
	}

	public QidaBroadcast(Long id, String content, SysAdmin sysAdmin,
			Date createDay, Date createTime) {
		super();
		this.id = id;
		this.content = content;
		this.sysAdmin = sysAdmin;
		this.createDay = createDay;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
