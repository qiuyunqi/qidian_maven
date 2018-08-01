package com.xiaohe.qd.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 角色
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "qd_sys_role")
public class SysRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024820125036518702L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "rolecode")
	private String roleCode;

	@Column(name = "rolename")
	private String roleName;

	@Column(name = "roledesc")
	private String roleDesc;

	@Column(name = "parentid")
	private Long parentId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createadmin")
	private SysAdmin createAdmin;

	@Column(name = "createtime")
	private Date createTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updateadmin")
	private SysAdmin updateAdmin;

	@Column(name = "updatetime")
	private Date updateTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	private Set<SysUserRole> sysUserRoles = new HashSet<SysUserRole>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysRole")
	private Set<SysRolePurview> sysRolePurviews = new HashSet<SysRolePurview>(0);

	public SysRole() {
		super();
	}

	public SysRole(Long id, String roleCode, String roleName, String roleDesc, Long parentId, SysAdmin createAdmin, Date createTime, SysAdmin updateAdmin, Date updateTime, Set<SysUserRole> sysUserRoles, Set<SysRolePurview> sysRolePurviews) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.parentId = parentId;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.sysUserRoles = sysUserRoles;
		this.sysRolePurviews = sysRolePurviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public SysAdmin getCreateAdmin() {
		return createAdmin;
	}

	public void setCreateAdmin(SysAdmin createAdmin) {
		this.createAdmin = createAdmin;
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

	public Set<SysUserRole> getSysUserRoles() {
		return sysUserRoles;
	}

	public void setSysUserRoles(Set<SysUserRole> sysUserRoles) {
		this.sysUserRoles = sysUserRoles;
	}

	public Set<SysRolePurview> getSysRolePurviews() {
		return sysRolePurviews;
	}

	public void setSysRolePurviews(Set<SysRolePurview> sysRolePurviews) {
		this.sysRolePurviews = sysRolePurviews;
	}

}
