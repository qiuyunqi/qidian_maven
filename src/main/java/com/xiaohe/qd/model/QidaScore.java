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
@Table(name = "qida_score")
public class QidaScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805467086285031636L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dictionary_id")
	private FuDictionary fuDictionary;

	@Column(name = "score")
	private BigDecimal score;
	
	@Column(name = "money")
	private BigDecimal money;
	
	@Column(name = "total_score")
	private BigDecimal totalScore;
	
	@Column(name = "order_num")
	private String orderNum;
	
	@Column(name = "comment")
	private String comment;

	@Column(name = "is_income")
	private Integer isIncome;

	@Column(name = "create_time")
	private Date createTime;
	
	public QidaScore() {
		super();
	}

	public QidaScore(Long id, FuUser fuUser, FuDictionary fuDictionary,
			BigDecimal score, BigDecimal money, BigDecimal totalScore,String orderNum,
			String comment, Integer isIncome, Date createTime) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.fuDictionary = fuDictionary;
		this.score = score;
		this.money = money;
		this.totalScore = totalScore;
		this.orderNum = orderNum;
		this.comment = comment;
		this.isIncome = isIncome;
		this.createTime = createTime;
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

	public FuDictionary getFuDictionary() {
		return fuDictionary;
	}

	public void setFuDictionary(FuDictionary fuDictionary) {
		this.fuDictionary = fuDictionary;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIsIncome() {
		return isIncome;
	}

	public void setIsIncome(Integer isIncome) {
		this.isIncome = isIncome;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
