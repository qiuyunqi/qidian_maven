package com.xiaohe.qd.model;

import java.io.Serializable;
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
 * FuUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_user")
public class FuUser implements Serializable, Cloneable {
	private static final long serialVersionUID = 1938536313873124732L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "password")
	private String password;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "remark_name")
	private String remarkName;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "is_check_card")
	private Integer isCheckCard;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "is_bind_email")
	private Integer isBindEmail;

	@Column(name = "is_marriage")
	private Integer isMarriage;

	@Column(name = "live_address")
	private String liveAddress;

	@Column(name = "max_degree")
	private Integer maxDegree;

	@Column(name = "business_type")
	private String businessType;

	@Column(name = "position_name")
	private String positionName;

	@Column(name = "salary")
	private BigDecimal salary;

	@Column(name = "draw_password")
	private String drawPassword;

	@Column(name = "card_before_pic")
	private String cardBeforePic;

	@Column(name = "card_behind_pic")
	private String cardBehindPic;

	@Column(name = "card_hand_pic")
	private String cardHandPic;

	@Column(name = "account_balance")
	private BigDecimal accountBalance;

	@Column(name = "account_total_money")
	private BigDecimal accountTotalMoney;

	@Column(name = "match_money")
	private BigDecimal matchMoney;

	@Column(name = "safe_money")
	private BigDecimal safeMoney;

	@Column(name = "freeze_money")
	private BigDecimal freezeMoney;

	@Column(name = "safe_level")
	private Integer safeLevel;

	@Column(name = "register_time", length = 19)
	private Date registerTime;

	@Column(name = "register_ip")
	private String registerIp;

	@Column(name = "extend_person_num")
	private Integer extendPersonNum;

	@Column(name = "borrow_person_num")
	private Integer borrowPersonNum;

	@Column(name = "visit_ip")
	private Integer visitIp;

	@Column(name = "visit_num")
	private Integer visitNum;

	@Column(name = "commission_total")
	private BigDecimal commissionTotal;

	@Column(name = "exchange_money")
	private BigDecimal exchangeMoney;

	@Column(name = "phone_code")
	private String phoneCode;

	@Column(name = "email_code")
	private String emailCode;

	@Column(name = "login_token")
	private String loginToken;

	@Column(name = "state")
	private Integer state;

	@Column(name = "province_id")
	private Long provinceId;

	@Column(name = "city_id")
	private Long cityId;

	@Column(name = "province_name")
	private String provinceName;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "gender")
	private Integer gender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recommend_id")
	private FuUser recommend;

	@Column(name = "login_time")
	private Date loginTime;

	@Column(name = "draw_money")
	private BigDecimal drawMoney;

	@Column(name = "recharge_money")
	private BigDecimal rechargeMoney;

	@Column(name = "fee_total")
	private BigDecimal feeTotal;

	@Column(name = "phone_code_time")
	private Date phoneCodeTime;

	@Column(name = "hhr_level")
	private Integer hhrLevel;

	@Column(name = "hhr_parentID")
	private Long hhrParentID;

	@Column(name = "invitation_code")
	private String invitationCode;

	@Column(name = "user_avatar")
	private String userAvatar;

	@Column(name = "last_login_ip")
	private String lastLoginIp;

	@Column(name = "introduction")
	private String introduction;

	@Column(name = "nick_name")
	private String nickName;

	@Column(name = "isPhone_reg")
	private Integer isPhoneReg;

	@Column(name = "integral")
	private BigDecimal integral;

	@Column(name = "isAcrossCabin")
	private Integer isAcrossCabin;

	@Column(name = "weixin_code")
	private String weixinCode;

	@Column(name = "hhr_type")
	private Integer hhrType;

	@Column(name = "ry_token")
	private String ryToken;

	@Column(name = "is_transaction")
	private Integer isTransaction; // 是否是交易团队 0: 不是 1: 是

	@Column(name = "is_sale")
	private Integer isSale; // 是否是销售 0: 不是 1: 是

	@Column(name = "qida_rank")
	private Integer qidaRank;// 奇答等级

	@Column(name = "qida_integral")
	private BigDecimal qidaIntegral;// 奇答积分
	
	@Column(name = "is_voice")
	private Integer isVoice;// 是否开启声音提醒，0不提醒，1提醒

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuUser")
	private Set<QidaAnswer> qidaAnswers = new HashSet<QidaAnswer>(0);

	// Constructors

	/** default constructor */
	public FuUser() {
	}
	public Object clone() {  
        FuUser o = null;  
        try {  
            o = (FuUser) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
	public FuUser(Long id, String accountName, String password, String userName, String remarkName, String cardNumber, Integer isCheckCard, String phone, String email, Integer isBindEmail,
			Integer isMarriage, String liveAddress, Integer maxDegree, String businessType, String positionName, BigDecimal salary, String drawPassword, String cardBeforePic, String cardBehindPic,
			String cardHandPic, BigDecimal accountBalance, BigDecimal accountTotalMoney, BigDecimal matchMoney, BigDecimal safeMoney, BigDecimal freezeMoney, Integer safeLevel, Date registerTime,
			String registerIp, Integer extendPersonNum, Integer borrowPersonNum, Integer visitIp, Integer visitNum, BigDecimal commissionTotal, BigDecimal exchangeMoney, String phoneCode,
			String emailCode, String loginToken, Integer state, Long provinceId, Long cityId, String provinceName, String cityName, Integer gender, FuUser recommend, Date loginTime,
			BigDecimal drawMoney, BigDecimal rechargeMoney, BigDecimal feeTotal, Date phoneCodeTime, Integer hhrLevel, Long hhrParentID, String invitationCode, String userAvatar, String lastLoginIp,
			String introduction, String nickName, Integer isPhoneReg, BigDecimal integral, Integer isAcrossCabin, String weixinCode, Integer hhrType, String ryToken, Integer isTransaction,
			Integer isSale, Integer qidaRank, BigDecimal qidaIntegral,Integer isVoice, Set<QidaAnswer> qidaAnswers) {
		super();
		this.id = id;
		this.accountName = accountName;
		this.password = password;
		this.userName = userName;
		this.remarkName = remarkName;
		this.cardNumber = cardNumber;
		this.isCheckCard = isCheckCard;
		this.phone = phone;
		this.email = email;
		this.isBindEmail = isBindEmail;
		this.isMarriage = isMarriage;
		this.liveAddress = liveAddress;
		this.maxDegree = maxDegree;
		this.businessType = businessType;
		this.positionName = positionName;
		this.salary = salary;
		this.drawPassword = drawPassword;
		this.cardBeforePic = cardBeforePic;
		this.cardBehindPic = cardBehindPic;
		this.cardHandPic = cardHandPic;
		this.accountBalance = accountBalance;
		this.accountTotalMoney = accountTotalMoney;
		this.matchMoney = matchMoney;
		this.safeMoney = safeMoney;
		this.freezeMoney = freezeMoney;
		this.safeLevel = safeLevel;
		this.registerTime = registerTime;
		this.registerIp = registerIp;
		this.extendPersonNum = extendPersonNum;
		this.borrowPersonNum = borrowPersonNum;
		this.visitIp = visitIp;
		this.visitNum = visitNum;
		this.commissionTotal = commissionTotal;
		this.exchangeMoney = exchangeMoney;
		this.phoneCode = phoneCode;
		this.emailCode = emailCode;
		this.loginToken = loginToken;
		this.state = state;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.provinceName = provinceName;
		this.cityName = cityName;
		this.gender = gender;
		this.recommend = recommend;
		this.loginTime = loginTime;
		this.drawMoney = drawMoney;
		this.rechargeMoney = rechargeMoney;
		this.feeTotal = feeTotal;
		this.phoneCodeTime = phoneCodeTime;
		this.hhrLevel = hhrLevel;
		this.hhrParentID = hhrParentID;
		this.invitationCode = invitationCode;
		this.userAvatar = userAvatar;
		this.lastLoginIp = lastLoginIp;
		this.introduction = introduction;
		this.nickName = nickName;
		this.isPhoneReg = isPhoneReg;
		this.integral = integral;
		this.isAcrossCabin = isAcrossCabin;
		this.weixinCode = weixinCode;
		this.hhrType = hhrType;
		this.ryToken = ryToken;
		this.isTransaction = isTransaction;
		this.isSale = isSale;
		this.qidaRank = qidaRank;
		this.qidaIntegral = qidaIntegral;
		this.isVoice=isVoice;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getIsCheckCard() {
		return this.isCheckCard;
	}

	public void setIsCheckCard(Integer isCheckCard) {
		this.isCheckCard = isCheckCard;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIsBindEmail() {
		return this.isBindEmail;
	}

	public void setIsBindEmail(Integer isBindEmail) {
		this.isBindEmail = isBindEmail;
	}

	public Integer getIsMarriage() {
		return this.isMarriage;
	}

	public void setIsMarriage(Integer isMarriage) {
		this.isMarriage = isMarriage;
	}

	public String getLiveAddress() {
		return this.liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	public Integer getMaxDegree() {
		return this.maxDegree;
	}

	public void setMaxDegree(Integer maxDegree) {
		this.maxDegree = maxDegree;
	}

	public String getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getDrawPassword() {
		return this.drawPassword;
	}

	public void setDrawPassword(String drawPassword) {
		this.drawPassword = drawPassword;
	}

	public String getCardBeforePic() {
		return this.cardBeforePic;
	}

	public void setCardBeforePic(String cardBeforePic) {
		this.cardBeforePic = cardBeforePic;
	}

	public String getCardBehindPic() {
		return this.cardBehindPic;
	}

	public void setCardBehindPic(String cardBehindPic) {
		this.cardBehindPic = cardBehindPic;
	}

	public String getCardHandPic() {
		return this.cardHandPic;
	}

	public void setCardHandPic(String cardHandPic) {
		this.cardHandPic = cardHandPic;
	}

	public BigDecimal getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BigDecimal getAccountTotalMoney() {
		return this.accountTotalMoney;
	}

	public void setAccountTotalMoney(BigDecimal accountTotalMoney) {
		this.accountTotalMoney = accountTotalMoney;
	}

	public BigDecimal getMatchMoney() {
		return this.matchMoney;
	}

	public void setMatchMoney(BigDecimal matchMoney) {
		this.matchMoney = matchMoney;
	}

	public BigDecimal getSafeMoney() {
		return this.safeMoney;
	}

	public void setSafeMoney(BigDecimal safeMoney) {
		this.safeMoney = safeMoney;
	}

	public BigDecimal getFreezeMoney() {
		return this.freezeMoney;
	}

	public void setFreezeMoney(BigDecimal freezeMoney) {
		this.freezeMoney = freezeMoney;
	}

	public Integer getSafeLevel() {
		return this.safeLevel;
	}

	public void setSafeLevel(Integer safeLevel) {
		this.safeLevel = safeLevel;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Integer getExtendPersonNum() {
		return this.extendPersonNum;
	}

	public void setExtendPersonNum(Integer extendPersonNum) {
		this.extendPersonNum = extendPersonNum;
	}

	public BigDecimal getCommissionTotal() {
		return this.commissionTotal;
	}

	public void setCommissionTotal(BigDecimal commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	public BigDecimal getExchangeMoney() {
		return this.exchangeMoney;
	}

	public void setExchangeMoney(BigDecimal exchangeMoney) {
		this.exchangeMoney = exchangeMoney;
	}

	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getEmailCode() {
		return this.emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public String getLoginToken() {
		return this.loginToken;
	}

	public void setLoginToken(String loginToken) {
		this.loginToken = loginToken;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getBorrowPersonNum() {
		return borrowPersonNum;
	}

	public void setBorrowPersonNum(Integer borrowPersonNum) {
		this.borrowPersonNum = borrowPersonNum;
	}

	public Integer getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(Integer visitIp) {
		this.visitIp = visitIp;
	}

	public Integer getVisitNum() {
		return visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public FuUser getRecommend() {
		return recommend;
	}

	public void setRecommend(FuUser recommend) {
		this.recommend = recommend;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public BigDecimal getDrawMoney() {
		return drawMoney;
	}

	public void setDrawMoney(BigDecimal drawMoney) {
		this.drawMoney = drawMoney;
	}

	public BigDecimal getRechargeMoney() {
		return rechargeMoney;
	}

	public void setRechargeMoney(BigDecimal rechargeMoney) {
		this.rechargeMoney = rechargeMoney;
	}

	public BigDecimal getFeeTotal() {
		return feeTotal;
	}

	public void setFeeTotal(BigDecimal feeTotal) {
		this.feeTotal = feeTotal;
	}

	public Date getPhoneCodeTime() {
		return phoneCodeTime;
	}

	public void setPhoneCodeTime(Date phoneCodeTime) {
		this.phoneCodeTime = phoneCodeTime;
	}

	public Integer getHhrLevel() {
		return hhrLevel;
	}

	public void setHhrLevel(Integer hhrLevel) {
		this.hhrLevel = hhrLevel;
	}

	public Long getHhrParentID() {
		return hhrParentID;
	}

	public void setHhrParentID(Long hhrParentID) {
		this.hhrParentID = hhrParentID;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getIsPhoneReg() {
		return isPhoneReg;
	}

	public void setIsPhoneReg(Integer isPhoneReg) {
		this.isPhoneReg = isPhoneReg;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public Integer getIsAcrossCabin() {
		return isAcrossCabin;
	}

	public void setIsAcrossCabin(Integer isAcrossCabin) {
		this.isAcrossCabin = isAcrossCabin;
	}

	public String getWeixinCode() {
		return weixinCode;
	}

	public void setWeixinCode(String weixinCode) {
		this.weixinCode = weixinCode;
	}

	public Integer getHhrType() {
		return hhrType;
	}

	public void setHhrType(Integer hhrType) {
		this.hhrType = hhrType;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getRyToken() {
		return ryToken;
	}

	public void setRyToken(String ryToken) {
		this.ryToken = ryToken;
	}

	public Integer getIsTransaction() {
		return isTransaction;
	}

	public void setIsTransaction(Integer isTransaction) {
		this.isTransaction = isTransaction;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getQidaRank() {
		return qidaRank;
	}

	public void setQidaRank(Integer qidaRank) {
		this.qidaRank = qidaRank;
	}

	public BigDecimal getQidaIntegral() {
		return qidaIntegral;
	}

	public void setQidaIntegral(BigDecimal qidaIntegral) {
		this.qidaIntegral = qidaIntegral;
	}

	public Integer getIsVoice() {
		return isVoice;
	}
	
	public void setIsVoice(Integer isVoice) {
		this.isVoice = isVoice;
	}
	
	public Set<QidaAnswer> getQidaAnswers() {
		return qidaAnswers;
	}

	public void setQidaAnswers(Set<QidaAnswer> qidaAnswers) {
		this.qidaAnswers = qidaAnswers;
	}

}