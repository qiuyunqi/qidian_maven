package com.xiaohe.qd.model;

import java.io.Serializable;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "qida_answer")
public class QidaAnswer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6999716038351436599L;

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

	@Column(name = "content")
	private String content;

	@Column(name = "ticket_num")
	private Integer ticketNum;

	@Column(name = "type")
	private Integer type;

	@Column(name = "support_num")
	private Integer supportNum;

	@Column(name = "no_support_num")
	private Integer noSupportNum;

	@Column(name = "real_support_num")
	private Integer realSupportNum;

	@Column(name = "real_no_support_num")
	private Integer realNoSupportNum;

	@Column(name = "state")
	private Integer state;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "is_delete")
	private Integer isDelete;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaAnswer")
	private Set<QidaAgree> qidaAgrees = new HashSet<QidaAgree>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaAnswer")
	@OrderBy("createTime ASC")
	private Set<QidaAnswerComment> answerComments = new HashSet<QidaAnswerComment>();

	public QidaAnswer() {
		super();
	}

	public QidaAnswer(Long id, QidaQuestion qidaQuestion, FuUser fuUser, String content, Integer ticketNum, Integer type, Integer supportNum, Integer noSupportNum, Integer realSupportNum,
			Integer realNoSupportNum, Integer state, Date createTime, Integer isDelete, Set<QidaAgree> qidaAgrees, Set<QidaAnswerComment> answerComments) {
		super();
		this.id = id;
		this.qidaQuestion = qidaQuestion;
		this.fuUser = fuUser;
		this.content = content;
		this.ticketNum = ticketNum;
		this.type = type;
		this.supportNum = supportNum;
		this.noSupportNum = noSupportNum;
		this.realSupportNum = realSupportNum;
		this.realNoSupportNum = realNoSupportNum;
		this.state = state;
		this.isDelete = isDelete;
		this.createTime = createTime;
		this.qidaAgrees = qidaAgrees;
		this.answerComments = answerComments;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTicketNum() {
		return ticketNum;
	}

	public void setTicketNum(Integer ticketNum) {
		this.ticketNum = ticketNum;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(Integer supportNum) {
		this.supportNum = supportNum;
	}

	public Integer getNoSupportNum() {
		return noSupportNum;
	}

	public void setNoSupportNum(Integer noSupportNum) {
		this.noSupportNum = noSupportNum;
	}

	public Integer getRealSupportNum() {
		return realSupportNum;
	}

	public void setRealSupportNum(Integer realSupportNum) {
		this.realSupportNum = realSupportNum;
	}

	public Integer getRealNoSupportNum() {
		return realNoSupportNum;
	}

	public void setRealNoSupportNum(Integer realNoSupportNum) {
		this.realNoSupportNum = realNoSupportNum;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<QidaAgree> getQidaAgrees() {
		return qidaAgrees;
	}

	public void setQidaAgrees(Set<QidaAgree> qidaAgrees) {
		this.qidaAgrees = qidaAgrees;
	}

	public Set<QidaAnswerComment> getAnswerComments() {
		return answerComments;
	}

	public void setAnswerComments(Set<QidaAnswerComment> answerComments) {
		this.answerComments = answerComments;
	}

}
