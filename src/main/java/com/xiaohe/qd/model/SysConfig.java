package com.xiaohe.qd.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统全局设置
 * 
 * @author han
 * 
 */
@Entity
@Table(name = "sys_config")
public class SysConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7845213793799871502L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "code", nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "value", nullable = false)
	private BigDecimal value;

	public SysConfig() {
	}

	public SysConfig(Long id, String code, String name, BigDecimal value) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
