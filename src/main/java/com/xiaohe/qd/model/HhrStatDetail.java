package com.xiaohe.qd.model;

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
@Table(name = "hhr_stat_detail")
public class HhrStatDetail implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5696417465118727821L;
	private Long id;
	private FuUser fuUser;
	private Date statDate;//统计的日期
	private Long hhrParentID;
	private Integer hhrLevel;//合伙人等级
	private Integer firstlyPartnerNum;//一级会员数目
	private Integer secondaryPartnerNum;//次级会员数目
	private Integer extendPersonNew;//今日新增下级会员
	private BigDecimal accountIncomeBalance;//账户收益余额
	private BigDecimal dailyIncome;//日增收益
	private BigDecimal firstlyStockEndowment;//一级合伙人股票配资额
	private BigDecimal firstlyFuturesEndowment;//一级合伙人期货配资额
	private BigDecimal firstlyStockCommission;//一级合伙人股票返佣
	private BigDecimal firstlyFuturesCommission;//一级合伙人期货返佣
	private BigDecimal firstlyContributionIncome;//一级合伙人贡献收益
	private BigDecimal stockEndowment;//自己或直接合伙人股票配资额
	private BigDecimal futuresEndowment;//自己或直接合伙人期货配资额
	private BigDecimal stockCommission;//自己或直接合伙人股票返佣
	private BigDecimal futuresCommission;//自己或直接合伙人期货返佣
	private BigDecimal contributionIncome;//自己或直接合伙人贡献收益
	private Integer interestReturnCoefficient;//当前利息返还系数%
	private Integer chargesReturnCoefficient;//当前手续费返还系数%
	private Integer bonusCoefficient;//任务达标奖励系数
	private String lastLoginIpToday;//今日最后登录IP地址
	private Date createDate;//创建日期
	private Integer incomeType;//收益类型
	
	public HhrStatDetail() {
		super();
	}

	public HhrStatDetail(Long id, FuUser fuUser, Date statDate, Long hhrParentID,
			Integer hhrLevel, Integer firstlyPartnerNum,
			Integer secondaryPartnerNum, Integer extendPersonNew,
		    BigDecimal accountIncomeBalance,
			BigDecimal dailyIncome, 
			BigDecimal firstlyStockEndowment,
			BigDecimal firstlyFuturesEndowment,
			BigDecimal firstlyStockCommission,
			BigDecimal firstlyFuturesCommission,
			BigDecimal firstlyContributionIncome, BigDecimal stockEndowment,
			BigDecimal futuresEndowment, BigDecimal stockCommission,
			BigDecimal futuresCommission, BigDecimal contributionIncome,
			Integer interestReturnCoefficient,
			Integer chargesReturnCoefficient, 
			Integer bonusCoefficient,
			String lastLoginIpToday,Date createDate,Integer incomeType) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.statDate = statDate;
		this.hhrParentID = hhrParentID;
		this.hhrLevel = hhrLevel;
		this.firstlyPartnerNum = firstlyPartnerNum;
		this.secondaryPartnerNum = secondaryPartnerNum;
		this.extendPersonNew = extendPersonNew;
		this.accountIncomeBalance = accountIncomeBalance;
		this.dailyIncome = dailyIncome;
		this.firstlyStockEndowment = firstlyStockEndowment;
		this.firstlyFuturesEndowment = firstlyFuturesEndowment;
		this.firstlyStockCommission = firstlyStockCommission;
		this.firstlyFuturesCommission = firstlyFuturesCommission;
		this.firstlyContributionIncome = firstlyContributionIncome;
		this.stockEndowment = stockEndowment;
		this.futuresEndowment = futuresEndowment;
		this.stockCommission = stockCommission;
		this.futuresCommission = futuresCommission;
		this.contributionIncome = contributionIncome;
		this.interestReturnCoefficient = interestReturnCoefficient;
		this.chargesReturnCoefficient = chargesReturnCoefficient;
		this.bonusCoefficient = bonusCoefficient;
		this.lastLoginIpToday = lastLoginIpToday;
		this.createDate = createDate;
		this.incomeType = incomeType;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}
	
	@Column(name = "stat_date")
	public Date getStatDate() {
		return statDate;
	}

	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}

	@Column(name = "hhr_parentID")
	public Long getHhrParentID() {
		return hhrParentID;
	}

	public void setHhrParentID(Long hhrParentID) {
		this.hhrParentID = hhrParentID;
	}

	@Column(name = "hhr_level")
	public Integer getHhrLevel() {
		return hhrLevel;
	}

	public void setHhrLevel(Integer hhrLevel) {
		this.hhrLevel = hhrLevel;
	}

	@Column(name = "firstly_partner_num")
	public Integer getFirstlyPartnerNum() {
		return firstlyPartnerNum;
	}

	public void setFirstlyPartnerNum(Integer firstlyPartnerNum) {
		this.firstlyPartnerNum = firstlyPartnerNum;
	}

	@Column(name = "secondary_partner_num")
	public Integer getSecondaryPartnerNum() {
		return secondaryPartnerNum;
	}

	public void setSecondaryPartnerNum(Integer secondaryPartnerNum) {
		this.secondaryPartnerNum = secondaryPartnerNum;
	}

	@Column(name = "extend_person_new")
	public Integer getExtendPersonNew() {
		return extendPersonNew;
	}

	public void setExtendPersonNew(Integer extendPersonNew) {
		this.extendPersonNew = extendPersonNew;
	}

	@Column(name = "account_income_balance")
	public BigDecimal getAccountIncomeBalance() {
		return accountIncomeBalance;
	}

	public void setAccountIncomeBalance(BigDecimal accountIncomeBalance) {
		this.accountIncomeBalance = accountIncomeBalance;
	}

	@Column(name = "daily_income")
	public BigDecimal getDailyIncome() {
		return dailyIncome;
	}

	public void setDailyIncome(BigDecimal dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	@Column(name = "firstly_stock_endowment")
	public BigDecimal getFirstlyStockEndowment() {
		return firstlyStockEndowment;
	}

	public void setFirstlyStockEndowment(BigDecimal firstlyStockEndowment) {
		this.firstlyStockEndowment = firstlyStockEndowment;
	}

	@Column(name = "firstly_futures_endowment")
	public BigDecimal getFirstlyFuturesEndowment() {
		return firstlyFuturesEndowment;
	}

	public void setFirstlyFuturesEndowment(BigDecimal firstlyFuturesEndowment) {
		this.firstlyFuturesEndowment = firstlyFuturesEndowment;
	}

	@Column(name = "firstly_stock_commission")
	public BigDecimal getFirstlyStockCommission() {
		return firstlyStockCommission;
	}

	public void setFirstlyStockCommission(BigDecimal firstlyStockCommission) {
		this.firstlyStockCommission = firstlyStockCommission;
	}

	@Column(name = "firstly_futures_commission")
	public BigDecimal getFirstlyFuturesCommission() {
		return firstlyFuturesCommission;
	}

	public void setFirstlyFuturesCommission(BigDecimal firstlyFuturesCommission) {
		this.firstlyFuturesCommission = firstlyFuturesCommission;
	}

	@Column(name = "firstly_contribution_income")
	public BigDecimal getFirstlyContributionIncome() {
		return firstlyContributionIncome;
	}

	public void setFirstlyContributionIncome(BigDecimal firstlyContributionIncome) {
		this.firstlyContributionIncome = firstlyContributionIncome;
	}

	@Column(name = "stock_endowment")
	public BigDecimal getStockEndowment() {
		return stockEndowment;
	}

	public void setStockEndowment(BigDecimal stockEndowment) {
		this.stockEndowment = stockEndowment;
	}

	@Column(name = "futures_endowment")
	public BigDecimal getFuturesEndowment() {
		return futuresEndowment;
	}

	public void setFuturesEndowment(BigDecimal futuresEndowment) {
		this.futuresEndowment = futuresEndowment;
	}

	@Column(name = "stock_commission")
	public BigDecimal getStockCommission() {
		return stockCommission;
	}

	public void setStockCommission(BigDecimal stockCommission) {
		this.stockCommission = stockCommission;
	}

	@Column(name = "futures_commission")
	public BigDecimal getFuturesCommission() {
		return futuresCommission;
	}

	public void setFuturesCommission(BigDecimal futuresCommission) {
		this.futuresCommission = futuresCommission;
	}

	@Column(name = "contribution_income")
	public BigDecimal getContributionIncome() {
		return contributionIncome;
	}

	public void setContributionIncome(BigDecimal contributionIncome) {
		this.contributionIncome = contributionIncome;
	}

	@Column(name = "interest_return_coefficient")
	public Integer getInterestReturnCoefficient() {
		return interestReturnCoefficient;
	}

	public void setInterestReturnCoefficient(Integer interestReturnCoefficient) {
		this.interestReturnCoefficient = interestReturnCoefficient;
	}

	@Column(name = "charges_return_coefficient")
	public Integer getChargesReturnCoefficient() {
		return chargesReturnCoefficient;
	}

	public void setChargesReturnCoefficient(Integer chargesReturnCoefficient) {
		this.chargesReturnCoefficient = chargesReturnCoefficient;
	}

	@Column(name = "bonus_coefficient")
	public Integer getBonusCoefficient() {
		return bonusCoefficient;
	}

	public void setBonusCoefficient(Integer bonusCoefficient) {
		this.bonusCoefficient = bonusCoefficient;
	}

	@Column(name = "last_login_ip_today")
	public String getLastLoginIpToday() {
		return lastLoginIpToday;
	}

	public void setLastLoginIpToday(String lastLoginIpToday) {
		this.lastLoginIpToday = lastLoginIpToday;
	}
	
	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "income_type")
	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}
	
}
