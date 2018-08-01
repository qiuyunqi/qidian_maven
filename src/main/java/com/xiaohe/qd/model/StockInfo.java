package com.xiaohe.qd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 股票详细信息
 * 
 * @author hhr
 * 
 */
@Entity
@Table(name = "qd_stock_info")
public class StockInfo implements Serializable {

	private static final long serialVersionUID = -6925814362399878944L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name; // 股票名称

	@Column(name = "good_day")
	private String goodDay; // 今日好评

	@Column(name = "good_week")
	private String goodWeek; // 本周好评

	@Column(name = "watch_num")
	private String watchNum; // 关注指数

	@Column(name = "good_comment_num")
	private Integer goodCommentNum; // 该股票的好评数量

	@Column(name = "medium_comment_num")
	private Integer mediumCommentNum; // 该股票的中评数量

	@Column(name = "bad_comment_num")
	private Integer badCommentNum; // 该股票的差评数量

	@Column(name = "code")
	private String code; // 股票代码

	@Column(name = "py_code")
	private String pyCode; // 股票拼音

	@Column(name = "comment_num")
	private Integer commentNum; // 评论数量 每条评论的总和
	
	@Column(name = "comment_num_today")
	private Integer commentNumToday; // 评论数量 每条评论的总和

	@Column(name = "stk_type")
	private String stkType; // 股票所属交易所

	@Column(name = "lift_rate")
	private BigDecimal liftRate; // 股票的涨跌幅度

	@Column(name = "curr_price")
	private BigDecimal currPrice; // 股票的当前价
	
	@Column(name = "tra_number")
	private BigDecimal traNumber;	// 成交量
	
	@Column(name = "today_start_pri")
	private BigDecimal todayStartPri; 	// 今日开盘价格
	
	@Column(name = "yestoday_end_pri")
	private BigDecimal yestodayEndPri; 	// 昨日收盘价
	
	@Column(name = "today_max")
	private BigDecimal todayMax;	// 今日最高价
	
	@Column(name = "today_min")
	private BigDecimal todayMin;	// 今日最低价

	@Column(name = "difference")
	private BigDecimal difference;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stockInfo")
	private Set<StockComment> stockComments = new HashSet<StockComment>(0);

	@ManyToMany(mappedBy = "stockInfos", cascade = CascadeType.ALL)
	private List<StockType> stockTypes;

	public StockInfo() {
		super();
	}

	public StockInfo(Long id, String name, String goodDay, String goodWeek,
			String watchNum, Integer goodCommentNum, Integer mediumCommentNum,
			Integer badCommentNum, String code, String pyCode,
			Integer commentNum, Integer commentNumToday, String stkType,
			BigDecimal liftRate, BigDecimal currPrice, BigDecimal traNumber,
			BigDecimal todayStartPri, BigDecimal yestodayEndPri,
			BigDecimal todayMax, BigDecimal todayMin, BigDecimal difference,
			Set<StockComment> stockComments, List<StockType> stockTypes) {
		super();
		this.id = id;
		this.name = name;
		this.goodDay = goodDay;
		this.goodWeek = goodWeek;
		this.watchNum = watchNum;
		this.goodCommentNum = goodCommentNum;
		this.mediumCommentNum = mediumCommentNum;
		this.badCommentNum = badCommentNum;
		this.code = code;
		this.pyCode = pyCode;
		this.commentNum = commentNum;
		this.commentNumToday = commentNumToday;
		this.stkType = stkType;
		this.liftRate = liftRate;
		this.currPrice = currPrice;
		this.traNumber = traNumber;
		this.todayStartPri = todayStartPri;
		this.yestodayEndPri = yestodayEndPri;
		this.todayMax = todayMax;
		this.todayMin = todayMin;
		this.difference = difference;
		this.stockComments = stockComments;
		this.stockTypes = stockTypes;
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

	public String getGoodDay() {
		return goodDay;
	}

	public void setGoodDay(String goodDay) {
		this.goodDay = goodDay;
	}

	public String getGoodWeek() {
		return goodWeek;
	}

	public void setGoodWeek(String goodWeek) {
		this.goodWeek = goodWeek;
	}

	public String getWatchNum() {
		return watchNum;
	}

	public void setWatchNum(String watchNum) {
		this.watchNum = watchNum;
	}

	public Integer getGoodCommentNum() {
		return goodCommentNum;
	}

	public void setGoodCommentNum(Integer goodCommentNum) {
		this.goodCommentNum = goodCommentNum;
	}

	public Integer getMediumCommentNum() {
		return mediumCommentNum;
	}

	public void setMediumCommentNum(Integer mediumCommentNum) {
		this.mediumCommentNum = mediumCommentNum;
	}

	public Integer getBadCommentNum() {
		return badCommentNum;
	}

	public void setBadCommentNum(Integer badCommentNum) {
		this.badCommentNum = badCommentNum;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPyCode() {
		return pyCode;
	}

	public void setPyCode(String pyCode) {
		this.pyCode = pyCode;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getCommentNumToday() {
		return commentNumToday;
	}

	public void setCommentNumToday(Integer commentNumToday) {
		this.commentNumToday = commentNumToday;
	}

	public String getStkType() {
		return stkType;
	}

	public void setStkType(String stkType) {
		this.stkType = stkType;
	}

	public BigDecimal getLiftRate() {
		return liftRate;
	}

	public void setLiftRate(BigDecimal liftRate) {
		this.liftRate = liftRate;
	}

	public BigDecimal getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(BigDecimal currPrice) {
		this.currPrice = currPrice;
	}

	public Set<StockComment> getStockComments() {
		return stockComments;
	}

	public void setStockComments(Set<StockComment> stockComments) {
		this.stockComments = stockComments;
	}

	public List<StockType> getStockTypes() {
		return stockTypes;
	}

	public void setStockTypes(List<StockType> stockTypes) {
		this.stockTypes = stockTypes;
	}

	public BigDecimal getTraNumber() {
		return traNumber;
	}

	public void setTraNumber(BigDecimal traNumber) {
		this.traNumber = traNumber;
	}

	public BigDecimal getTodayStartPri() {
		return todayStartPri;
	}

	public void setTodayStartPri(BigDecimal todayStartPri) {
		this.todayStartPri = todayStartPri;
	}

	public BigDecimal getTodayMax() {
		return todayMax;
	}

	public void setTodayMax(BigDecimal todayMax) {
		this.todayMax = todayMax;
	}

	public BigDecimal getTodayMin() {
		return todayMin;
	}

	public void setTodayMin(BigDecimal todayMin) {
		this.todayMin = todayMin;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}

	public BigDecimal getYestodayEndPri() {
		return yestodayEndPri;
	}

	public void setYestodayEndPri(BigDecimal yestodayEndPri) {
		this.yestodayEndPri = yestodayEndPri;
	}
}
