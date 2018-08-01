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
import javax.persistence.Table;

@Entity
@Table(name = "guess_record")
public class GuessRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 892669856373009120L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "trade_no")
	private String tradeNo;

	@Column(name = "score")
	private BigDecimal score;

	@Column(name = "guess_time")
	private Date guessTime;

	@Column(name = "order_income")
	private BigDecimal orderIncome;

	@Column(name = "guess_type")
	private Integer guessType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "market_id")
	private GuessMarket guessMarket;

	public GuessRecord() {
		super();
	}

	public GuessRecord(Long id, FuUser fuUser, String tradeNo, BigDecimal score, Date guessTime, BigDecimal orderIncome, Integer guessType, GuessMarket guessMarket) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.tradeNo = tradeNo;
		this.score = score;
		this.guessTime = guessTime;
		this.orderIncome = orderIncome;
		this.guessType = guessType;
		this.guessMarket = guessMarket;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public Date getGuessTime() {
		return guessTime;
	}

	public void setGuessTime(Date guessTime) {
		this.guessTime = guessTime;
	}

	public BigDecimal getOrderIncome() {
		return orderIncome;
	}

	public void setOrderIncome(BigDecimal orderIncome) {
		this.orderIncome = orderIncome;
	}

	public Integer getGuessType() {
		return guessType;
	}

	public void setGuessType(Integer guessType) {
		this.guessType = guessType;
	}

	public GuessMarket getGuessMarket() {
		return guessMarket;
	}

	public void setGuessMarket(GuessMarket guessMarket) {
		this.guessMarket = guessMarket;
	}

}
