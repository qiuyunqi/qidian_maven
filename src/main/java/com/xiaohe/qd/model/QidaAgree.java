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
@Table(name = "qida_agree")
public class QidaAgree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 801598981391172169L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "answer_id")
	private QidaAnswer qidaAnswer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "type")
	private Integer type;//类型：0赞同，1反对

	public QidaAgree() {
		super();
	}

	public QidaAgree(Long id, QidaAnswer qidaAnswer, FuUser fuUser, Date createTime, Integer type) {
		super();
		this.id = id;
		this.qidaAnswer = qidaAnswer;
		this.fuUser = fuUser;
		this.createTime = createTime;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QidaAnswer getQidaAnswer() {
		return qidaAnswer;
	}

	public void setQidaAnswer(QidaAnswer qidaAnswer) {
		this.qidaAnswer = qidaAnswer;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
