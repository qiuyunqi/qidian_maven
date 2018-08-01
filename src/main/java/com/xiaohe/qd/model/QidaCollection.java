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
@Table(name = "qida_collection")
public class QidaCollection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3030219677702720076L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QidaQuestion qidaQuestion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "collection_time")
	private Date collectionTime;

	public QidaCollection() {
		super();
	}

	public QidaCollection(Long id, QidaQuestion qidaQuestion, FuUser fuUser, Date collectionTime) {
		super();
		this.id = id;
		this.qidaQuestion = qidaQuestion;
		this.fuUser = fuUser;
		this.collectionTime = collectionTime;
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

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

}
