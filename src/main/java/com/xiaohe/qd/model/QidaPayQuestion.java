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
@Table(name = "qida_pay_question")
public class QidaPayQuestion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1661342318901084168L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QidaQuestion qidaQuestion;

	@Column(name = "create_time")
	private Date createTime;

	public QidaPayQuestion() {
		super();
	}

	public QidaPayQuestion(Long id, QidaQuestion qidaQuestion, FuUser fuUser, Date createTime) {
		super();
		this.id = id;
		this.qidaQuestion = qidaQuestion;
		this.fuUser = fuUser;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QidaQuestion getQidaQuestion() {
		return qidaQuestion;
	}

	public void setQidaQuestion(QidaQuestion qidaQuestion) {
		this.qidaQuestion = qidaQuestion;
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

}
