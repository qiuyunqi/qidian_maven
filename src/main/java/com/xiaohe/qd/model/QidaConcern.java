package com.xiaohe.qd.model;

import java.io.Serializable;
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
@Table(name = "qida_concern")
public class QidaConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4001866319825352104L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concern_user")
	private FuUser concernUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "be_concern_user")
	private FuUser beConcernUser;

	@Column(name = "concern_time")
	private Date concernTime;

	public QidaConcern() {
		super();
	}

	public QidaConcern(Long id, FuUser concernUser, FuUser beConcernUser, Date concernTime) {
		super();
		this.id = id;
		this.concernUser = concernUser;
		this.beConcernUser = beConcernUser;
		this.concernTime = concernTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuUser getConcernUser() {
		return concernUser;
	}

	public void setConcernUser(FuUser concernUser) {
		this.concernUser = concernUser;
	}

	public FuUser getBeConcernUser() {
		return beConcernUser;
	}

	public void setBeConcernUser(FuUser beConcernUser) {
		this.beConcernUser = beConcernUser;
	}

	public Date getConcernTime() {
		return concernTime;
	}

	public void setConcernTime(Date concernTime) {
		this.concernTime = concernTime;
	}

}
