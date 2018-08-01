package com.xiaohe.qd.model;

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
 * FuGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "qd_sys_ip_blacklist")
public class SysIpBlacklist implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3767268867084638742L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ip")
	private String ip;

	@Column(name = "isBlack")
	private Integer isBlack;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_admin")
	private SysAdmin creatAdmin;

	@Column(name = "create_time")
	private Date createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_admin")
	private SysAdmin updateAdmin;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "type")
	private Integer type; // 0:ip地址 1: 手机号码 2:邮箱

	public SysIpBlacklist() {
		super();
	}

	public SysIpBlacklist(Long id, String ip, Integer isBlack, SysAdmin creatAdmin, Date createTime, SysAdmin updateAdmin, Date updateTime, Integer type) {
		super();
		this.id = id;
		this.ip = ip;
		this.isBlack = isBlack;
		this.creatAdmin = creatAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.type = type;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getIsBlack() {
		return this.isBlack;
	}

	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
	}

	public SysAdmin getCreatAdmin() {
		return creatAdmin;
	}

	public void setCreatAdmin(SysAdmin creatAdmin) {
		this.creatAdmin = creatAdmin;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SysAdmin getUpdateAdmin() {
		return updateAdmin;
	}

	public void setUpdateAdmin(SysAdmin updateAdmin) {
		this.updateAdmin = updateAdmin;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}