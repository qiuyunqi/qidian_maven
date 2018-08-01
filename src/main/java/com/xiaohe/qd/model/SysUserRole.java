package com.xiaohe.qd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qd_sys_user_role")
public class SysUserRole implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7818943282091570395L;
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "adminid")
	private SysAdmin sysAdmin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	private SysRole sysRole;

	public SysUserRole() {
		super();
	}

	public SysUserRole(Long id, SysAdmin sysAdmin, SysRole sysRole) {
		super();
		this.id = id;
		this.sysAdmin = sysAdmin;
		this.sysRole = sysRole;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}
