package com.xiaohe.qd.model;

import java.math.BigDecimal;
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
 * FuMoneyDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_money_detail")
public class FuMoneyDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8784043341189291208L;
	private Long id;
	private FuUser fuUser;
	private FuProgram fuProgram;
	private FuBankCard fuBankCard;
	private String rechargeType;
	private FuDictionary fuDictionary;
	private BigDecimal money;
	private BigDecimal accountBalanceBefore;
	private BigDecimal accountBalanceAfter;
	private Integer status;
	private Date time;
	private String comment;
	private Boolean isIncome;
	private Integer isCheck;

	// Constructors

	/** default constructor */
	public FuMoneyDetail() {
	}

	/** full constructor */
	public FuMoneyDetail(Long id, FuUser fuUser, FuProgram fuProgram,
			FuBankCard fuBankCard, String rechargeType,
			FuDictionary fuDictionary, BigDecimal money,
			BigDecimal accountBalanceBefore, BigDecimal accountBalanceAfter,
			Integer status, Date time, String comment, Boolean isIncome,
			Integer isCheck) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.fuProgram = fuProgram;
		this.fuBankCard = fuBankCard;
		this.rechargeType = rechargeType;
		this.fuDictionary = fuDictionary;
		this.money = money;
		this.accountBalanceBefore = accountBalanceBefore;
		this.accountBalanceAfter = accountBalanceAfter;
		this.status = status;
		this.time = time;
		this.comment = comment;
		this.isIncome = isIncome;
		this.isCheck = isCheck;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return this.fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "program_id")
	public FuProgram getFuProgram() {
		return this.fuProgram;
	}

	public void setFuProgram(FuProgram fuProgram) {
		this.fuProgram = fuProgram;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bank_id")
	public FuBankCard getFuBankCard() {
		return this.fuBankCard;
	}

	public void setFuBankCard(FuBankCard fuBankCard) {
		this.fuBankCard = fuBankCard;
	}
	
	
	@Column(name = "recharge_type")
	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
	}

	@Column(name = "money")
	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Column(name = "account_balance_before")
	public BigDecimal getAccountBalanceBefore() {
		return this.accountBalanceBefore;
	}

	public void setAccountBalanceBefore(BigDecimal accountBalanceBefore) {
		this.accountBalanceBefore = accountBalanceBefore;
	}

	@Column(name = "account_balance_after")
	public BigDecimal getAccountBalanceAfter() {
		return this.accountBalanceAfter;
	}

	public void setAccountBalanceAfter(BigDecimal accountBalanceAfter) {
		this.accountBalanceAfter = accountBalanceAfter;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "comment", length = 65535)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionary_id")
	public FuDictionary getFuDictionary() {
		return fuDictionary;
	}

	public void setFuDictionary(FuDictionary fuDictionary) {
		this.fuDictionary = fuDictionary;
	}

	@Column(name = "is_income")
	public Boolean getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Boolean isIncome) {
		this.isIncome = isIncome;
	}

	@Column(name = "is_check")
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
}