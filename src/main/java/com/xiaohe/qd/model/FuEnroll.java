package com.xiaohe.qd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报名
 * @author han
 * @date 2016-12-13
 */
@Entity
@Table(name = "fu_enroll")
public class FuEnroll implements Serializable {

	private static final long serialVersionUID = -8607929401152449554L;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;
	
	@Column(name = "phone", nullable = false, unique = true)
	private String phone;
	
	@Column(name = "create_time", nullable = false, unique = true)
	private Date createTime;

	public FuEnroll(){}
	
	public FuEnroll(Long id, String userName, String phone, Date createTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.phone = phone;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
