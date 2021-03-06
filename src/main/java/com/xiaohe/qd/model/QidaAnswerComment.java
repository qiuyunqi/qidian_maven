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

/**
 * 奇答的答案评论表
 * @author han
 *
 */
@Entity
@Table(name = "qida_answer_comment")
public class QidaAnswerComment implements Serializable {

	private static final long serialVersionUID = -7329221172177080774L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "answer_id")
	private QidaAnswer qidaAnswer;

	@Column(name = "content")
	private String content;
	

	@Column(name = "create_time")
	private Date createTime;


	@Column(name = "state")
	private Integer state;
	
	@Column(name = "is_delete")
	private Integer isDelete;
	

	public QidaAnswerComment() {
		super();
	}


	public QidaAnswerComment(Long id, FuUser fuUser,
			QidaAnswer qidaAnswer, String content, Date createTime,
			Integer state, Integer isDelete) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.qidaAnswer = qidaAnswer;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
		this.isDelete = isDelete;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public FuUser getFuUser() {
		return fuUser;
	}


	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public QidaAnswer getQidaAnswer() {
		return qidaAnswer;
	}


	public void setQidaAnswer(QidaAnswer qidaAnswer) {
		this.qidaAnswer = qidaAnswer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
