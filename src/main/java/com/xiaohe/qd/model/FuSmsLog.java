package com.xiaohe.qd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * FuSmsLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fu_sms_log")
public class FuSmsLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6951618783938209934L;
	private Long id;
	private FuUser fuUser;
	private String destination;
	private String regCode;
	private String reason;
	private Integer prio;
	private String content;
	private Integer state;
	private Date planTime;
	private Date sendTime;
	private Integer type;

	// Constructors

	/** default constructor */
	public FuSmsLog() {
	}

	/** full constructor */
	public FuSmsLog(FuUser fuUser, String destination, String regCode, String reason, Integer prio, String content, Integer state, Date planTime, Date sendTime, Integer type) {
		this.fuUser = fuUser;
		this.destination = destination;
		this.regCode = regCode;
		this.reason = reason;
		this.prio = prio;
		this.content = content;
		this.state = state;
		this.planTime = planTime;
		this.sendTime = sendTime;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public FuUser getFuUser() {
		return this.fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	@Column(name = "destination")
	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Column(name = "reason")
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "prio")
	public Integer getPrio() {
		return this.prio;
	}

	public void setPrio(Integer prio) {
		this.prio = prio;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "plan_time", length = 19)
	public Date getPlanTime() {
		return this.planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	@Column(name = "send_time", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "reg_code")
	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

}