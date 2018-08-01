package com.xiaohe.qd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 用户费率
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "fu_rate")
public class FuRate implements Serializable {
	private static final long serialVersionUID = 5355421985978873979L;

	private Long id;
	private FuUser fuUser;
	private BigDecimal rate_one;
	private BigDecimal rate_two;
	private BigDecimal rate_three;
	private BigDecimal rate_four;
	private BigDecimal rate_five;
	private BigDecimal rate_six;
	private BigDecimal rate_seven;
	private BigDecimal rate_eight;
	private BigDecimal rate_nine;
	private BigDecimal rate_ten;
	private BigDecimal rate_day;
	private BigDecimal rate_week;
	private BigDecimal warnlinePercent;
	private BigDecimal flatlinePercent;
	private BigDecimal commissionPercent;
	private BigDecimal feeDay;
	private BigDecimal feePercent;
	private BigDecimal interestPercent;
	private Integer shortNum;
	private Integer longNum;
	private Date createtime;
	private Long updateuser;
	private Date updatetime;
	private SysAdmin sysAdmin;

	public FuRate() {
		super();
	}

	public FuRate(Long id, FuUser fuUser, BigDecimal rate_one, BigDecimal rate_two, BigDecimal rate_three, BigDecimal rate_four, BigDecimal rate_five, BigDecimal rate_six, BigDecimal rate_seven, BigDecimal rate_eight, BigDecimal rate_nine, BigDecimal rate_ten, BigDecimal rate_day, BigDecimal rate_week, BigDecimal warnlinePercent, BigDecimal flatlinePercent, BigDecimal commissionPercent,
			BigDecimal feeDay, BigDecimal feePercent, BigDecimal interestPercent, Integer shortNum, Integer longNum, Date createtime, Long updateuser, Date updatetime, SysAdmin sysAdmin) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.rate_one = rate_one;
		this.rate_two = rate_two;
		this.rate_three = rate_three;
		this.rate_four = rate_four;
		this.rate_five = rate_five;
		this.rate_six = rate_six;
		this.rate_seven = rate_seven;
		this.rate_eight = rate_eight;
		this.rate_nine = rate_nine;
		this.rate_ten = rate_ten;
		this.rate_day = rate_day;
		this.rate_week = rate_week;
		this.warnlinePercent = warnlinePercent;
		this.flatlinePercent = flatlinePercent;
		this.commissionPercent = commissionPercent;
		this.feeDay = feeDay;
		this.feePercent = feePercent;
		this.interestPercent = interestPercent;
		this.shortNum = shortNum;
		this.longNum = longNum;
		this.createtime = createtime;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
		this.sysAdmin = sysAdmin;
	}

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "rate_one")
	public BigDecimal getRate_one() {
		return rate_one;
	}

	public void setRate_one(BigDecimal rate_one) {
		this.rate_one = rate_one;
	}

	@Column(name = "rate_two")
	public BigDecimal getRate_two() {
		return rate_two;
	}

	public void setRate_two(BigDecimal rate_two) {
		this.rate_two = rate_two;
	}

	@Column(name = "rate_three")
	public BigDecimal getRate_three() {
		return rate_three;
	}

	public void setRate_three(BigDecimal rate_three) {
		this.rate_three = rate_three;
	}

	@Column(name = "rate_four")
	public BigDecimal getRate_four() {
		return rate_four;
	}

	public void setRate_four(BigDecimal rate_four) {
		this.rate_four = rate_four;
	}

	@Column(name = "rate_five")
	public BigDecimal getRate_five() {
		return rate_five;
	}

	public void setRate_five(BigDecimal rate_five) {
		this.rate_five = rate_five;
	}

	@Column(name = "rate_six")
	public BigDecimal getRate_six() {
		return rate_six;
	}

	public void setRate_six(BigDecimal rate_six) {
		this.rate_six = rate_six;
	}

	@Column(name = "rate_seven")
	public BigDecimal getRate_seven() {
		return rate_seven;
	}

	public void setRate_seven(BigDecimal rate_seven) {
		this.rate_seven = rate_seven;
	}

	@Column(name = "rate_eight")
	public BigDecimal getRate_eight() {
		return rate_eight;
	}

	public void setRate_eight(BigDecimal rate_eight) {
		this.rate_eight = rate_eight;
	}

	@Column(name = "rate_nine")
	public BigDecimal getRate_nine() {
		return rate_nine;
	}

	public void setRate_nine(BigDecimal rate_nine) {
		this.rate_nine = rate_nine;
	}

	@Column(name = "rate_ten")
	public BigDecimal getRate_ten() {
		return rate_ten;
	}

	public void setRate_ten(BigDecimal rate_ten) {
		this.rate_ten = rate_ten;
	}

	@Column(name = "rate_day")
	public BigDecimal getRate_day() {
		return rate_day;
	}

	public void setRate_day(BigDecimal rate_day) {
		this.rate_day = rate_day;
	}

	@Column(name = "rate_week")
	public BigDecimal getRate_week() {
		return rate_week;
	}

	public void setRate_week(BigDecimal rate_week) {
		this.rate_week = rate_week;
	}

	@Column(name = "warnline_percent")
	public BigDecimal getWarnlinePercent() {
		return warnlinePercent;
	}

	public void setWarnlinePercent(BigDecimal warnlinePercent) {
		this.warnlinePercent = warnlinePercent;
	}

	@Column(name = "flatline_percent")
	public BigDecimal getFlatlinePercent() {
		return flatlinePercent;
	}

	public void setFlatlinePercent(BigDecimal flatlinePercent) {
		this.flatlinePercent = flatlinePercent;
	}

	@Column(name = "commission_percent")
	public BigDecimal getCommissionPercent() {
		return commissionPercent;
	}

	public void setCommissionPercent(BigDecimal commissionPercent) {
		this.commissionPercent = commissionPercent;
	}

	@Column(name = "fee_day")
	public BigDecimal getFeeDay() {
		return feeDay;
	}

	public void setFeeDay(BigDecimal feeDay) {
		this.feeDay = feeDay;
	}

	@Column(name = "fee_percent")
	public BigDecimal getFeePercent() {
		return feePercent;
	}

	public void setFeePercent(BigDecimal feePercent) {
		this.feePercent = feePercent;
	}

	@Column(name = "interest_percent")
	public BigDecimal getInterestPercent() {
		return interestPercent;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createuser")
	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	@Column(name = "createtime")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "updateuser")
	public Long getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(Long updateuser) {
		this.updateuser = updateuser;
	}

	@Column(name = "updatetime")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

}
