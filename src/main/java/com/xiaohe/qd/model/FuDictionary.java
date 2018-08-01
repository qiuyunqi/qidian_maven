package com.xiaohe.qd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuGame entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dictionary")
public class FuDictionary implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3744483774431890923L;
	private Long id;
	private String name;
	private Long pid;
	private Integer isEnabled;
	

	// Constructors

	/** default constructor */
	public FuDictionary() {
	}

	/** full constructor */
	public FuDictionary(Long id, String name, Long pid, Integer isEnabled) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.isEnabled = isEnabled;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "pid")
	public Long getPid() {
		return this.pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "isEnabled")
	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

}