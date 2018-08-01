package com.xiaohe.qd.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FuProgram entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_program")
public class FuProgram implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1619265106673507791L;
	private Long id;
	private FuUser fuUser;
	private BigDecimal matchMoney;
	private BigDecimal safeMoney;
	private BigDecimal warnLine;
	private BigDecimal closeLine;
	private Date createTime;
	private Date tradeTime;
	private Date endManageMoneyTime;// 最后一次交管理费的时间
	private Date nextManageMoneyTime;// 下一次交管理费的时间
	private BigDecimal manageMoney;
	private Integer status;
	private Integer tradeAccount;
	private String tradePassword;
	private String tradeIp;
	private String tradeServiceName;
	private Integer programType;
	private Integer programWay;
	private Integer cycleNum;
	private Integer doubleNum;
	private Date checkTime;
	private Date clearTime;
	private Date closeTime;
	private BigDecimal totalMatchMoney;
	private BigDecimal moneyPercent;
	private BigDecimal addSafeMoney;
	private BigDecimal commisionPercent;
	private String comment; // 备注
	private Integer groupId;
	private Integer roleId;
	private BigDecimal gameIncomeWeek;
	private BigDecimal gameIncomeMonth;
	private BigDecimal income;
	private Date agreeTime;
	private String agreeIp;
	private String tradePort;
	private String goodsFee; // 商品期货手续费
	private String stockIndexFee; // 股指期货手续费
	private String returnCommission; // 返佣
	private String mediator; // 居间人（上线）
	private String safeMoneyRate; // 保证金率
	private BigDecimal overnightGoodRate; // 隔夜商品保证金占用
	private BigDecimal overnightStockIndexRate; // 隔夜沪指保证金占用
	private String longHolidayRate; // 小长假持仓标准
	private String remark; // 备注
	private BigDecimal expenseScore;// 消耗积分
	private Long addMatchId;// 增配主方案ID
	private Long subMatchId;// 减配主方案ID
	private Integer closeMatchId;
	private Integer closeBlanceId;
	private Integer openUserId;
	private Integer openPaymentId;
	private Integer openTriggerId;

	private Set<FuMoneyDetail> fuMoneyDetails = new HashSet<FuMoneyDetail>(0);

	// Constructors

	/** default constructor */
	public FuProgram() {
	}

	/** full constructor */

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public FuProgram(Long id, FuUser fuUser, BigDecimal matchMoney, BigDecimal safeMoney, BigDecimal warnLine, BigDecimal closeLine, Date createTime, Date tradeTime, Date endManageMoneyTime, Date nextManageMoneyTime, BigDecimal manageMoney, Integer status, Integer tradeAccount, String tradePassword, String tradeIp, String tradeServiceName, Integer programType, Integer programWay,
			Integer cycleNum, Integer doubleNum, Date checkTime, Date clearTime, Date closeTime, BigDecimal totalMatchMoney, BigDecimal moneyPercent, BigDecimal addSafeMoney, BigDecimal commisionPercent, String comment, Integer groupId, Integer roleId, BigDecimal gameIncomeWeek, BigDecimal gameIncomeMonth, BigDecimal income, Date agreeTime, String agreeIp, String tradePort, String goodsFee,
			String stockIndexFee, String returnCommission, String mediator, String safeMoneyRate, BigDecimal overnightGoodRate, BigDecimal overnightStockIndexRate, String longHolidayRate, String remark, BigDecimal expenseScore, Long addMatchId, Long subMatchId, Integer closeMatchId, Integer closeBlanceId, Integer openUserId, Integer openPaymentId, Integer openTriggerId,
			Set<FuMoneyDetail> fuMoneyDetails) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.matchMoney = matchMoney;
		this.safeMoney = safeMoney;
		this.warnLine = warnLine;
		this.closeLine = closeLine;
		this.createTime = createTime;
		this.tradeTime = tradeTime;
		this.endManageMoneyTime = endManageMoneyTime;
		this.nextManageMoneyTime = nextManageMoneyTime;
		this.manageMoney = manageMoney;
		this.status = status;
		this.tradeAccount = tradeAccount;
		this.tradePassword = tradePassword;
		this.tradeIp = tradeIp;
		this.tradeServiceName = tradeServiceName;
		this.programType = programType;
		this.programWay = programWay;
		this.cycleNum = cycleNum;
		this.doubleNum = doubleNum;
		this.checkTime = checkTime;
		this.clearTime = clearTime;
		this.closeTime = closeTime;
		this.totalMatchMoney = totalMatchMoney;
		this.moneyPercent = moneyPercent;
		this.addSafeMoney = addSafeMoney;
		this.commisionPercent = commisionPercent;
		this.comment = comment;
		this.groupId = groupId;
		this.roleId = roleId;
		this.gameIncomeWeek = gameIncomeWeek;
		this.gameIncomeMonth = gameIncomeMonth;
		this.income = income;
		this.agreeTime = agreeTime;
		this.agreeIp = agreeIp;
		this.tradePort = tradePort;
		this.goodsFee = goodsFee;
		this.stockIndexFee = stockIndexFee;
		this.returnCommission = returnCommission;
		this.mediator = mediator;
		this.safeMoneyRate = safeMoneyRate;
		this.overnightGoodRate = overnightGoodRate;
		this.overnightStockIndexRate = overnightStockIndexRate;
		this.longHolidayRate = longHolidayRate;
		this.remark = remark;
		this.expenseScore = expenseScore;
		this.addMatchId = addMatchId;
		this.subMatchId = subMatchId;
		this.closeMatchId = closeMatchId;
		this.closeBlanceId = closeBlanceId;
		this.openUserId = openUserId;
		this.openPaymentId = openPaymentId;
		this.openTriggerId = openTriggerId;
		this.fuMoneyDetails = fuMoneyDetails;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return this.fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "clear_time", length = 19)
	public Date getClearTime() {
		return clearTime;
	}

	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}

	@Column(name = "match_money")
	public BigDecimal getMatchMoney() {
		return this.matchMoney;
	}

	public void setMatchMoney(BigDecimal matchMoney) {
		this.matchMoney = matchMoney;
	}

	@Column(name = "safe_money")
	public BigDecimal getSafeMoney() {
		return this.safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	@Column(name = "warn_line")
	public BigDecimal getWarnLine() {
		return this.warnLine;
	}

	public void setWarnLine(BigDecimal warnLine) {
		this.warnLine = warnLine;
	}

	@Column(name = "close_line")
	public BigDecimal getCloseLine() {
		return this.closeLine;
	}

	public void setCloseLine(BigDecimal closeLine) {
		this.closeLine = closeLine;
	}

	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "trade_time", length = 19)
	public Date getTradeTime() {
		return this.tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	@Column(name = "end_manage_money_time", length = 19)
	public Date getEndManageMoneyTime() {
		return endManageMoneyTime;
	}

	public void setEndManageMoneyTime(Date endManageMoneyTime) {
		this.endManageMoneyTime = endManageMoneyTime;
	}

	@Column(name = "next_manage_money_time", length = 19)
	public Date getNextManageMoneyTime() {
		return nextManageMoneyTime;
	}

	public void setNextManageMoneyTime(Date nextManageMoneyTime) {
		this.nextManageMoneyTime = nextManageMoneyTime;
	}

	@Column(name = "manage_money")
	public BigDecimal getManageMoney() {
		return this.manageMoney;
	}

	public void setManageMoney(BigDecimal manageMoney) {
		this.manageMoney = manageMoney;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "trade_account")
	public Integer getTradeAccount() {
		return this.tradeAccount;
	}

	public void setTradeAccount(Integer tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	@Column(name = "trade_password")
	public String getTradePassword() {
		return this.tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	@Column(name = "trade_ip")
	public String getTradeIp() {
		return this.tradeIp;
	}

	public void setTradeIp(String tradeIp) {
		this.tradeIp = tradeIp;
	}

	@Column(name = "trade_service_name")
	public String getTradeServiceName() {
		return this.tradeServiceName;
	}

	public void setTradeServiceName(String tradeServiceName) {
		this.tradeServiceName = tradeServiceName;
	}

	@Column(name = "program_type")
	public Integer getProgramType() {
		return this.programType;
	}

	public void setProgramType(Integer programType) {
		this.programType = programType;
	}

	@Column(name = "program_way")
	public Integer getProgramWay() {
		return this.programWay;
	}

	public void setProgramWay(Integer programWay) {
		this.programWay = programWay;
	}

	@Column(name = "cycle_num")
	public Integer getCycleNum() {
		return this.cycleNum;
	}

	public void setCycleNum(Integer cycleNum) {
		this.cycleNum = cycleNum;
	}

	@Column(name = "double_num")
	public Integer getDoubleNum() {
		return this.doubleNum;
	}

	public void setDoubleNum(Integer doubleNum) {
		this.doubleNum = doubleNum;
	}

	@Column(name = "check_time", length = 19)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "close_time", length = 19)
	public Date getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "total_match_money")
	public BigDecimal getTotalMatchMoney() {
		return totalMatchMoney;
	}

	public void setTotalMatchMoney(BigDecimal totalMatchMoney) {
		this.totalMatchMoney = totalMatchMoney;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuProgram")
	public Set<FuMoneyDetail> getFuMoneyDetails() {
		return this.fuMoneyDetails;
	}

	public void setFuMoneyDetails(Set<FuMoneyDetail> fuMoneyDetails) {
		this.fuMoneyDetails = fuMoneyDetails;
	}

	@Column(name = "money_percent")
	public BigDecimal getMoneyPercent() {
		return moneyPercent;
	}

	public void setMoneyPercent(BigDecimal moneyPercent) {
		this.moneyPercent = moneyPercent;
	}

	@Column(name = "add_safe_money")
	public BigDecimal getAddSafeMoney() {
		return addSafeMoney;
	}

	public void setAddSafeMoney(BigDecimal addSafeMoney) {
		this.addSafeMoney = addSafeMoney;
	}

	@Column(name = "commision_percent")
	public BigDecimal getCommisionPercent() {
		return commisionPercent;
	}

	public void setCommisionPercent(BigDecimal commisionPercent) {
		this.commisionPercent = commisionPercent;
	}

	@Column(name = "comment")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return groupId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "role_id")
	public Integer getRoleId() {
		return roleId;
	}

	@Column(name = "game_income_week")
	public BigDecimal getGameIncomeWeek() {
		return gameIncomeWeek;
	}

	public void setGameIncomeWeek(BigDecimal gameIncomeWeek) {
		this.gameIncomeWeek = gameIncomeWeek;
	}

	@Column(name = "game_income_month")
	public BigDecimal getGameIncomeMonth() {
		return gameIncomeMonth;
	}

	public void setGameIncomeMonth(BigDecimal gameIncomeMonth) {
		this.gameIncomeMonth = gameIncomeMonth;
	}

	@Column(name = "income")
	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "agree_time")
	public Date getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Date agreeTime) {
		this.agreeTime = agreeTime;
	}

	@Column(name = "agree_ip")
	public String getAgreeIp() {
		return agreeIp;
	}

	public void setAgreeIp(String agreeIp) {
		this.agreeIp = agreeIp;
	}

	@Column(name = "trade_port")
	public String getTradePort() {
		return tradePort;
	}

	public void setTradePort(String tradePort) {
		this.tradePort = tradePort;
	}

	@Column(name = "goods_fee")
	public String getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(String goodsFee) {
		this.goodsFee = goodsFee;
	}

	@Column(name = "stock_index_fee")
	public String getStockIndexFee() {
		return stockIndexFee;
	}

	public void setStockIndexFee(String stockIndexFee) {
		this.stockIndexFee = stockIndexFee;
	}

	@Column(name = "return_commission")
	public String getReturnCommission() {
		return returnCommission;
	}

	public void setReturnCommission(String returnCommission) {
		this.returnCommission = returnCommission;
	}

	@Column(name = "mediator")
	public String getMediator() {
		return mediator;
	}

	public void setMediator(String mediator) {
		this.mediator = mediator;
	}

	@Column(name = "safe_money_rate")
	public String getSafeMoneyRate() {
		return safeMoneyRate;
	}

	public void setSafeMoneyRate(String safeMoneyRate) {
		this.safeMoneyRate = safeMoneyRate;
	}

	@Column(name = "overnight_good_rate")
	public BigDecimal getOvernightGoodRate() {
		return overnightGoodRate;
	}

	public void setOvernightGoodRate(BigDecimal overnightGoodRate) {
		this.overnightGoodRate = overnightGoodRate;
	}

	@Column(name = "overnight_stock_index_rate")
	public BigDecimal getOvernightStockIndexRate() {
		return overnightStockIndexRate;
	}

	public void setOvernightStockIndexRate(BigDecimal overnightStockIndexRate) {
		this.overnightStockIndexRate = overnightStockIndexRate;
	}

	@Column(name = "long_holiday_rate")
	public String getLongHolidayRate() {
		return longHolidayRate;
	}

	public void setLongHolidayRate(String longHolidayRate) {
		this.longHolidayRate = longHolidayRate;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "expense_score")
	public BigDecimal getExpenseScore() {
		return expenseScore;
	}

	public void setExpenseScore(BigDecimal expenseScore) {
		this.expenseScore = expenseScore;
	}

	@Column(name = "addMatchId")
	public Long getAddMatchId() {
		return addMatchId;
	}

	public void setAddMatchId(Long addMatchId) {
		this.addMatchId = addMatchId;
	}

	@Column(name = "subMatchId")
	public Long getSubMatchId() {
		return subMatchId;
	}

	public void setSubMatchId(Long subMatchId) {
		this.subMatchId = subMatchId;
	}

	@Column(name = "closeMatchId")
	public Integer getCloseMatchId() {
		return closeMatchId;
	}

	public void setCloseMatchId(Integer closeMatchId) {
		this.closeMatchId = closeMatchId;
	}

	@Column(name = "closeBlanceId")
	public Integer getCloseBlanceId() {
		return closeBlanceId;
	}

	public void setCloseBlanceId(Integer closeBlanceId) {
		this.closeBlanceId = closeBlanceId;
	}

	@Column(name = "openUserId")
	public Integer getOpenUserId() {
		return openUserId;
	}

	public void setOpenUserId(Integer openUserId) {
		this.openUserId = openUserId;
	}

	@Column(name = "openPaymentId")
	public Integer getOpenPaymentId() {
		return openPaymentId;
	}

	public void setOpenPaymentId(Integer openPaymentId) {
		this.openPaymentId = openPaymentId;
	}

	@Column(name = "openTriggerId")
	public Integer getOpenTriggerId() {
		return openTriggerId;
	}

	public void setOpenTriggerId(Integer openTriggerId) {
		this.openTriggerId = openTriggerId;
	}

}