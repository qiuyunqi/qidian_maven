package com.xiaohe.qd.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FuBankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_bank_card")
public class FuBankCard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8698107376410262518L;
	private Long id;
	private Long userId;
	private String accountName;
	private Long bankNameId;
	private String bankName;
	private String bankAddress;
	private String cardNumber;
	private Long bankProvince;
	private Long bankCity;
	private Long bankBranch;
	private String bankBranchName;
	private Integer state;
	private Set<FuMoneyDetail> fuMoneyDetails = new HashSet<FuMoneyDetail>(0);

	// Constructors

	/** default constructor */
	public FuBankCard() {
	}

	/** full constructor */

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public FuBankCard(Long id, Long userId, String accountName, Long bankNameId, String bankName, String bankAddress, String cardNumber, Long bankProvince, Long bankCity, Long bankBranch, String bankBranchName, Integer state, Set<FuMoneyDetail> fuMoneyDetails) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountName = accountName;
		this.bankNameId = bankNameId;
		this.bankName = bankName;
		this.bankAddress = bankAddress;
		this.cardNumber = cardNumber;
		this.bankProvince = bankProvince;
		this.bankCity = bankCity;
		this.bankBranch = bankBranch;
		this.bankBranchName = bankBranchName;
		this.state = state;
		this.fuMoneyDetails = fuMoneyDetails;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "account_name")
	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Column(name = "bank_name_id")
	public Long getBankNameId() {
		return bankNameId;
	}

	public void setBankNameId(Long bankNameId) {
		this.bankNameId = bankNameId;
	}

	@Column(name = "bank_name")
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "bank_address")
	public String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	@Column(name = "card_number")
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "bank_province")
	public Long getBankProvince() {
		return this.bankProvince;
	}

	public void setBankProvince(Long bankProvince) {
		this.bankProvince = bankProvince;
	}

	@Column(name = "bank_city")
	public Long getBankCity() {
		return this.bankCity;
	}

	public void setBankCity(Long bankCity) {
		this.bankCity = bankCity;
	}

	@Column(name = "bank_branch")
	public Long getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(Long bankBranch) {
		this.bankBranch = bankBranch;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fuBankCard")
	public Set<FuMoneyDetail> getFuMoneyDetails() {
		return this.fuMoneyDetails;
	}

	public void setFuMoneyDetails(Set<FuMoneyDetail> fuMoneyDetails) {
		this.fuMoneyDetails = fuMoneyDetails;
	}

	@Column(name = "bank_branch_name")
	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

}