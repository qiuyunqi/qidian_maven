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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 搜索首页的下面的好评榜
 * 
 * @author han
 * 
 */
@Entity
@Table(name = "qd_stock_praise_list")
public class StockPraiseList implements Serializable {
	private static final long serialVersionUID = 4184446335604904668L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name; // 股票名称

	@Column(name = "code")
	private String code; // 股票代码

	@Column(name = "transaction_price")
	private String transactionPrice; // 成交价格

	@Column(name = "rise_and_fall")
	private String riseAndFall; // 涨跌幅

	@Column(name = "good_num")
	private Integer goodNum; // 好评数

	@Column(name = "py_code")
	private String pyCode; // 股票拼音

	@Column(name = "stock_sort")
	private Integer stockSort; // 股票排序

	@Column(name = "is_lock")
	private Integer isLock;
	
	@Column(name = "is_delete")
	private Integer isDelete;

	@Column(name = "create_time")
	private Date createTime;
	
	@OneToOne(optional = true, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_info_id")
	private StockInfo stockInfo; // stockInfo 主键id

	public StockPraiseList() {
		super();
	}

	public StockPraiseList(Long id, String name, String code, String transactionPrice, String riseAndFall, Integer goodNum, String pyCode, Integer stockSort, 
			Integer isLock, Integer isDelete, StockInfo stockInfo, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.transactionPrice = transactionPrice;
		this.riseAndFall = riseAndFall;
		this.goodNum = goodNum;
		this.pyCode = pyCode;
		this.stockSort = stockSort;
		this.isLock = isLock;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.stockInfo = stockInfo;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTransactionPrice() {
		return transactionPrice;
	}

	public void setTransactionPrice(String transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	public String getRiseAndFall() {
		return riseAndFall;
	}

	public void setRiseAndFall(String riseAndFall) {
		this.riseAndFall = riseAndFall;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public Integer getStockSort() {
		return stockSort;
	}

	public void setStockSort(Integer stockSort) {
		this.stockSort = stockSort;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	public StockInfo getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(StockInfo stockInfo) {
		this.stockInfo = stockInfo;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}