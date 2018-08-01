package com.xiaohe.qd.model;

import java.io.Serializable;
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

import org.hibernate.annotations.OrderBy;

/**
 * 权限
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "qd_sys_purview")
public class SysPurview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2918388217794166561L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "parentid")
	private Long parentId;

	@Column(name = "url")
	private String url;

	@Column(name = "remark")
	private String remark;

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
	private Set<SysRolePurview> sysRolePurviews = new HashSet<SysRolePurview>(0);

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parentId")
	@OrderBy(clause = "id ASC")
	private Set<SysPurview> children = new HashSet<SysPurview>();

	public SysPurview() {
		super();
	}

	public SysPurview(Long id, String name, Long parentId, String url, String remark, SysAdmin createAdmin, Date createTime, SysAdmin updateAdmin, Date updateTime, Set<SysRolePurview> sysRolePurviews, Set<SysPurview> children) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.url = url;
		this.remark = remark;
		this.createAdmin = createAdmin;
		this.createTime = createTime;
		this.updateAdmin = updateAdmin;
		this.updateTime = updateTime;
		this.sysRolePurviews = sysRolePurviews;
		this.children = children;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Set<SysRolePurview> getSysRolePurviews() {
		return sysRolePurviews;
	}

	public void setSysRolePurviews(Set<SysRolePurview> sysRolePurviews) {
		this.sysRolePurviews = sysRolePurviews;
	}

	public Set<SysPurview> getChildren() {
		return children;
	}

	public void setChildren(Set<SysPurview> children) {
		this.children = children;
	}

}
