package com.xiaohe.qd.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * HhrPromoteParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "hhr_promote_parameter")
public class HhrPromoteParameter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5777835889745642608L;
	private Long id;
	private BigDecimal totalMoney;
	private String lineMoney;
	private Integer isOpen;

	// Constructors

	/** default constructor */
	public HhrPromoteParameter() {
	}

	public HhrPromoteParameter(Long id, BigDecimal totalMoney, String lineMoney, Integer isOpen) {
		super();
		this.id = id;
		this.totalMoney = totalMoney;
		this.lineMoney = lineMoney;
		this.isOpen = isOpen;
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
	
	@Column(name="total_money")
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name="line_money")
	public String getLineMoney() {
		return lineMoney;
	}

	public void setLineMoney(String lineMoney) {
		this.lineMoney = lineMoney;
	}

	@Column(name="is_open")
	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

}