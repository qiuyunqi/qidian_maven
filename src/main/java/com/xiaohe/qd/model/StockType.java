package com.xiaohe.qd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "qd_stock_type")
public class StockType implements Serializable {

	private static final long serialVersionUID = -4929199847344818539L;

	private Long id;
	private Long pid; // 父id
	private String name; // 类型名称
	private BigDecimal liftRate; // 升降率

	private List<StockInfo> stockInfos;

	public StockType(Long id, Long pid, String name, BigDecimal liftRate, List<StockInfo> stockInfos) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.liftRate = liftRate;
		this.stockInfos = stockInfos;
	}

	public StockType() {
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

	@Column(name = "pid")
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lift_rate")
	public BigDecimal getLiftRate() {
		return liftRate;
	}

	public void setLiftRate(BigDecimal liftRate) {
		this.liftRate = liftRate;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "qd_info_type", joinColumns = { @JoinColumn(name = "qd_stock_type_id") }, inverseJoinColumns = { @JoinColumn(name = "qd_stock_info_id") })
	public List<StockInfo> getStockInfos() {
		return stockInfos;
	}

	public void setStockInfos(List<StockInfo> stockInfos) {
		this.stockInfos = stockInfos;
	}

}
