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
@Table(name = "guess_market")
public class GuessMarket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1610280881252141325L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "guess_score")
	private BigDecimal guessScore;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "accept_factor")
	private BigDecimal acceptFactor;

	@Column(name = "accept_result")
	private Integer acceptResult;

	@Column(name = "state")
	private Integer state;

	@Column(name = "is_close")
	private Integer isClose;

	@Column(name = "type")
	private Integer type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accept_admin")
	private SysAdmin acceptAdmin;

	@Column(name = "accept_time")
	private Date acceptTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionary_id")
	private FuDictionary fuDictionary;

	public GuessMarket() {
		super();
	}

	public GuessMarket(Long id, String name, BigDecimal guessScore, Date createTime, BigDecimal acceptFactor, Integer acceptResult, Integer state, Integer isClose, Integer type, SysAdmin acceptAdmin,
			Date acceptTime, FuDictionary fuDictionary) {
		super();
		this.id = id;
		this.name = name;
		this.guessScore = guessScore;
		this.createTime = createTime;
		this.acceptFactor = acceptFactor;
		this.acceptResult = acceptResult;
		this.state = state;
		this.isClose = isClose;
		this.type = type;
		this.acceptAdmin = acceptAdmin;
		this.acceptTime = acceptTime;
		this.fuDictionary = fuDictionary;
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

	public BigDecimal getGuessScore() {
		return guessScore;
	}

	public void setGuessScore(BigDecimal guessScore) {
		this.guessScore = guessScore;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getAcceptFactor() {
		return acceptFactor;
	}

	public void setAcceptFactor(BigDecimal acceptFactor) {
		this.acceptFactor = acceptFactor;
	}

	public Integer getAcceptResult() {
		return acceptResult;
	}

	public void setAcceptResult(Integer acceptResult) {
		this.acceptResult = acceptResult;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsClose() {
		return isClose;
	}

	public void setIsClose(Integer isClose) {
		this.isClose = isClose;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public SysAdmin getAcceptAdmin() {
		return acceptAdmin;
	}

	public void setAcceptAdmin(SysAdmin acceptAdmin) {
		this.acceptAdmin = acceptAdmin;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public FuDictionary getFuDictionary() {
		return fuDictionary;
	}

	public void setFuDictionary(FuDictionary fuDictionary) {
		this.fuDictionary = fuDictionary;
	}

}
