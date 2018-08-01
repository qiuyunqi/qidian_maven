package com.xiaohe.qd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "live_lottery_info")
public class LiveLotteryInfo implements Serializable {
	private static final long serialVersionUID = -3113230285807450107L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "draw_time")
	private Date drawTime;
	
	@Column(name = "spend_integral")
	private BigDecimal spendIntegral;
	
	@Column(name = "is_win", nullable = false)
	private Integer isWin;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "is_receive", nullable = false)
	private Integer isReceive;
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name = "lottery_id")
	private LiveJackpot liveJackpot;
	
	@ManyToOne( cascade = {CascadeType.REFRESH, CascadeType.MERGE} )
	@JoinColumn(name = "user_id")
	private FuUser fuUser;
	
	public LiveLotteryInfo() {}
	
	public LiveLotteryInfo(Long id, Date drawTime, BigDecimal spendIntegral,
			Integer isWin, Integer status, Integer isReceive, LiveJackpot liveJackpot, FuUser fuUser) {
		super();
		this.id = id;
		this.drawTime = drawTime;
		this.spendIntegral = spendIntegral;
		this.isWin = isWin;
		this.status = status;
		this.isReceive = isReceive;
		this.liveJackpot = liveJackpot;
		this.fuUser = fuUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}

	public BigDecimal getSpendIntegral() {
		return spendIntegral;
	}

	public void setSpendIntegral(BigDecimal spendIntegral) {
		this.spendIntegral = spendIntegral;
	}
	
	public Integer getIsWin() {
		return isWin;
	}

	public void setIsWin(Integer isWin) {
		this.isWin = isWin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(Integer isReceive) {
		this.isReceive = isReceive;
	}

	public LiveJackpot getLiveJackpot() {
		return liveJackpot;
	}

	public void setLiveJackpot(LiveJackpot liveJackpot) {
		this.liveJackpot = liveJackpot;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}
}
