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
@Table(name = "qida_tag_question")
public class QidaTagQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1305826035626083485L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private QidaTags qidaTags;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QidaQuestion qidaQuestion;

	@Column(name = "create_time")
	private Date createTime;

	public QidaTagQuestion() {
		super();
	}

	public QidaTagQuestion(Long id, QidaTags qidaTags, QidaQuestion qidaQuestion, Date createTime) {
		super();
		this.id = id;
		this.qidaTags = qidaTags;
		this.qidaQuestion = qidaQuestion;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public QidaTags getQidaTags() {
		return qidaTags;
	}

	public void setQidaTags(QidaTags qidaTags) {
		this.qidaTags = qidaTags;
	}

	public QidaQuestion getQidaQuestion() {
		return qidaQuestion;
	}

	public void setQidaQuestion(QidaQuestion qidaQuestion) {
		this.qidaQuestion = qidaQuestion;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
