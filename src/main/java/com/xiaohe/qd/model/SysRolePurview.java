package com.xiaohe.qd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 角色权限
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "qd_sys_role_purview")
public class SysRolePurview implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1912869126834154247L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleid")
	private SysRole sysRole;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "purviewid")
	private SysPurview sysPurview;

	public SysRolePurview() {
		super();
	}

	public SysRolePurview(Long id, SysRole sysRole, SysPurview sysPurview) {
		super();
		this.id = id;
		this.sysRole = sysRole;
		this.sysPurview = sysPurview;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysPurview getSysPurview() {
		return sysPurview;
	}

	public void setSysPurview(SysPurview sysPurview) {
		this.sysPurview = sysPurview;
	}

}
