package com.xiaohe.qd.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FuParameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_parameter")
public class FuParameter implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8219981625190467516L;
	private Long id;
	private BigDecimal gameMoney;
	private BigDecimal warnlinePercent;
	private BigDecimal flatlinePercent;
	private BigDecimal commissionPercent;
	private BigDecimal feePercent;
	private BigDecimal interestPercent;
	private Integer shortNum;
	private Integer longNum;
	private String rechargeConfig;
	private BigDecimal feeDay;
	private Integer programDueTime;
	private BigDecimal payPoundage;
	private BigDecimal hhrPercent;
	private BigDecimal stockPercent;
	private Integer regNum;
	private Integer regInterval;
	private Integer messageType;
	private Integer sendNum; // 1分钟发短信的条数
	private String serviceEmail;
	private Integer isMessage;

	// Constructors
	/** default constructor */
	public FuParameter() {
	}

	public FuParameter(Long id, BigDecimal gameMoney, Integer regNum, Integer regInterval, BigDecimal warnlinePercent, BigDecimal flatlinePercent, Integer messageType, BigDecimal commissionPercent,
			BigDecimal feePercent, Integer sendNum, BigDecimal interestPercent, Integer shortNum, Integer longNum, String rechargeConfig, BigDecimal feeDay, Integer programDueTime, Integer isMessage,
			BigDecimal payPoundage, BigDecimal hhrPercent, BigDecimal stockPercent, String serviceEmail) {
		super();
		this.id = id;
		this.gameMoney = gameMoney;
		this.warnlinePercent = warnlinePercent;
		this.flatlinePercent = flatlinePercent;
		this.commissionPercent = commissionPercent;
		this.feePercent = feePercent;
		this.interestPercent = interestPercent;
		this.shortNum = shortNum;
		this.longNum = longNum;
		this.rechargeConfig = rechargeConfig;
		this.feeDay = feeDay;
		this.programDueTime = programDueTime;
		this.payPoundage = payPoundage;
		this.hhrPercent = hhrPercent;
		this.stockPercent = stockPercent;
		this.regNum = regNum;
		this.sendNum = sendNum;
		this.regInterval = regInterval;
		this.messageType = messageType;
		this.serviceEmail = serviceEmail;
		this.isMessage = isMessage;
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

	@Column(name = "game_money")
	public BigDecimal getGameMoney() {
		return this.gameMoney;
	}

	public void setGameMoney(BigDecimal gameMoney) {
		this.gameMoney = gameMoney;
	}

	@Column(name = "warnline_percent")
	public BigDecimal getWarnlinePercent() {
		return this.warnlinePercent;
	}

	public void setWarnlinePercent(BigDecimal warnlinePercent) {
		this.warnlinePercent = warnlinePercent;
	}

	@Column(name = "flatline_percent")
	public BigDecimal getFlatlinePercent() {
		return this.flatlinePercent;
	}

	public void setFlatlinePercent(BigDecimal flatlinePercent) {
		this.flatlinePercent = flatlinePercent;
	}

	@Column(name = "commission_percent")
	public BigDecimal getCommissionPercent() {
		return this.commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	@Column(name = "fee_percent")
	public BigDecimal getFeePercent() {
		return this.feePercent;
	}

	public void setFeePercent(BigDecimal feePercent) {
		this.feePercent = feePercent;
	}

	@Column(name = "interest_percent")
	public BigDecimal getInterestPercent() {
		return this.interestPercent;
	}

	public void setInterestPercent(BigDecimal interestPercent) {
		this.interestPercent = interestPercent;
	}

	@Column(name = "short_num")
	public Integer getShortNum() {
		return shortNum;
	}

	public void setShortNum(Integer shortNum) {
		this.shortNum = shortNum;
	}

	@Column(name = "long_num")
	public Integer getLongNum() {
		return longNum;
	}

	public void setLongNum(Integer longNum) {
		this.longNum = longNum;
	}

	@Column(name = "recharge_config")
	public String getRechargeConfig() {
		return rechargeConfig;
	}

	public void setRechargeConfig(String rechargeConfig) {
		this.rechargeConfig = rechargeConfig;
	}

	@Column(name = "fee_day")
	public BigDecimal getFeeDay() {
		return feeDay;
	}

	public void setFeeDay(BigDecimal feeDay) {
		this.feeDay = feeDay;
	}

	@Column(name = "program_due_time")
	public Integer getProgramDueTime() {
		return programDueTime;
	}

	public void setProgramDueTime(Integer programDueTime) {
		this.programDueTime = programDueTime;
	}

	@Column(name = "pay_poundage")
	public BigDecimal getPayPoundage() {
		return payPoundage;
	}

	public void setPayPoundage(BigDecimal payPoundage) {
		this.payPoundage = payPoundage;
	}

	@Column(name = "hhr_percent")
	public BigDecimal getHhrPercent() {
		return hhrPercent;
	}

	public void setHhrPercent(BigDecimal hhrPercent) {
		this.hhrPercent = hhrPercent;
	}

	@Column(name = "stock_percent")
	public BigDecimal getStockPercent() {
		return stockPercent;
	}

	public void setStockPercent(BigDecimal stockPercent) {
		this.stockPercent = stockPercent;
	}

	@Column(name = "reg_num")
	public Integer getRegNum() {
		return regNum;
	}

	public void setRegNum(Integer regNum) {
		this.regNum = regNum;
	}

	@Column(name = "reg_interval")
	public Integer getRegInterval() {
		return regInterval;
	}

	public void setRegInterval(Integer regInterval) {
		this.regInterval = regInterval;
	}

	@Column(name = "message_type")
	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	@Column(name = "send_num")
	public Integer getSendNum() {
		return sendNum;
	}

	public void setSendNum(Integer sendNum) {
		this.sendNum = sendNum;
	}

	@Column(name = "service_email")
	public String getServiceEmail() {
		return serviceEmail;
	}

	public void setServiceEmail(String serviceEmail) {
		this.serviceEmail = serviceEmail;
	}

	@Column(name = "is_message")
	public Integer getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}

}