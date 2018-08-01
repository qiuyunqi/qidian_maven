package com.xiaohe.qd.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qida_switch")
public class QidaSwitch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2871035060785000874L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "question_switch")
	private Integer questionSwitch;

	@Column(name = "answer_switch")
	private Integer answerSwitch;
	
	@Column(name = "question_comment_switch")
	private Integer questionCommentSwitch;
	
	@Column(name = "answer_comment_switch")
	private Integer answerCommentSwitch;
	
	public QidaSwitch() {
		super();
	}

	public QidaSwitch(Long id, Integer questionSwitch, Integer answerSwitch,
			Integer questionCommentSwitch, Integer answerCommentSwitch) {
		super();
		this.id = id;
		this.questionSwitch = questionSwitch;
		this.answerSwitch = answerSwitch;
		this.questionCommentSwitch = questionCommentSwitch;
		this.answerCommentSwitch = answerCommentSwitch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuestionSwitch() {
		return questionSwitch;
	}

	public void setQuestionSwitch(Integer questionSwitch) {
		this.questionSwitch = questionSwitch;
	}

	public Integer getAnswerSwitch() {
		return answerSwitch;
	}

	public void setAnswerSwitch(Integer answerSwitch) {
		this.answerSwitch = answerSwitch;
	}

	public Integer getQuestionCommentSwitch() {
		return questionCommentSwitch;
	}

	public void setQuestionCommentSwitch(Integer questionCommentSwitch) {
		this.questionCommentSwitch = questionCommentSwitch;
	}

	public Integer getAnswerCommentSwitch() {
		return answerCommentSwitch;
	}

	public void setAnswerCommentSwitch(Integer answerCommentSwitch) {
		this.answerCommentSwitch = answerCommentSwitch;
	}

	
}
