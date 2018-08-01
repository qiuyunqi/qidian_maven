package com.xiaohe.qd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
 * 最近自选股票
 */
@Entity
@Table(name = "qd_stock_recent_optional")
public class StockRecentOptional implements Serializable {
	private static final long serialVersionUID = 4184446335604904669L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@OneToOne(optional = true, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_info_id")
	private StockInfo stockInfo; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "create_time")
	private Date createTime; 
	
	public StockRecentOptional() {
		super();
	}

	public StockRecentOptional(Long id, StockInfo stockInfo, FuUser fuUser, Date createTime) {
		super();
		this.id = id;
		this.stockInfo = stockInfo;
		this.fuUser = fuUser;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StockInfo getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(StockInfo stockInfo) {
		this.stockInfo = stockInfo;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}