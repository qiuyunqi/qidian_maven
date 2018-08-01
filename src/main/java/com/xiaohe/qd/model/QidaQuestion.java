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
import javax.persistence.Transient;

@Entity
@Table(name = "qida_question")
public class QidaQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7329221172177080774L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "title")
	private String title;

	@Column(name = "detail")
	private String detail;

	@Column(name = "img_url")
	private String imgUrl;

	@Column(name = "reply_num")
	private Integer replyNum;

	@Column(name = "page_views")
	private Integer pageViews;

	@Column(name = "is_message")
	private Integer isMessage;

	@Column(name = "is_reward")
	private Integer isReward;

	@Column(name = "is_comment")
	private Integer isComment;

	@Column(name = "reward")
	private Integer reward;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "update_time")
	private Date updateTime;

	@Column(name = "state")
	private Integer state;

	@Column(name = "is_delete")
	private Integer isDelete;

	@Transient
	private String timeStr; // 不在数据中存储 只是为了前台页面显示的 多少小时之前

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "answer_id")
	private FuUser answerUser;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaQuestion")
	private Set<QidaTagQuestion> tagQuestions = new HashSet<QidaTagQuestion>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaQuestion")
	private Set<QidaStockQuestion> stockQuestions = new HashSet<QidaStockQuestion>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaQuestion")
	private Set<QidaAnswer> qidaAnswers = new HashSet<QidaAnswer>(0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaQuestion")
	@OrderBy("createTime ASC")
	private Set<QidaQuestionComment> questionComments = new HashSet<QidaQuestionComment>();

	public QidaQuestion() {
		super();
	}

	public QidaQuestion(Long id, FuUser fuUser, String title, String detail, String imgUrl, Integer replyNum, Integer pageViews, Integer isMessage, Integer isReward, Integer isComment,
			Integer reward, Date createTime, Date updateTime, Integer state, Integer isDelete, String timeStr, FuUser answerUser, Set<QidaTagQuestion> tagQuestions,
			Set<QidaStockQuestion> stockQuestions, Set<QidaAnswer> qidaAnswers, Set<QidaQuestionComment> questionComments) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.title = title;
		this.detail = detail;
		this.imgUrl = imgUrl;
		this.replyNum = replyNum;
		this.pageViews = pageViews;
		this.isMessage = isMessage;
		this.isReward = isReward;
		this.isComment = isComment;
		this.reward = reward;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.state = state;
		this.isDelete = isDelete;
		this.timeStr = timeStr;
		this.answerUser = answerUser;
		this.tagQuestions = tagQuestions;
		this.stockQuestions = stockQuestions;
		this.qidaAnswers = qidaAnswers;
		this.questionComments = questionComments;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Integer getPageViews() {
		return pageViews;
	}

	public void setPageViews(Integer pageViews) {
		this.pageViews = pageViews;
	}

	public Integer getIsMessage() {
		return isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
	}

	public Integer getIsReward() {
		return isReward;
	}

	public void setIsReward(Integer isReward) {
		this.isReward = isReward;
	}

	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}

	public Integer getReward() {
		return reward;
	}

	public void setReward(Integer reward) {
		this.reward = reward;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public FuUser getAnswerUser() {
		return answerUser;
	}

	public void setAnswerUser(FuUser answerUser) {
		this.answerUser = answerUser;
	}

	public Set<QidaTagQuestion> getTagQuestions() {
		return tagQuestions;
	}

	public void setTagQuestions(Set<QidaTagQuestion> tagQuestions) {
		this.tagQuestions = tagQuestions;
	}

	public Set<QidaStockQuestion> getStockQuestions() {
		return stockQuestions;
	}

	public void setStockQuestions(Set<QidaStockQuestion> stockQuestions) {
		this.stockQuestions = stockQuestions;
	}

	public Set<QidaAnswer> getQidaAnswers() {
		return qidaAnswers;
	}

	public void setQidaAnswers(Set<QidaAnswer> qidaAnswers) {
		this.qidaAnswers = qidaAnswers;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public Set<QidaQuestionComment> getQuestionComments() {
		return questionComments;
	}

	public void setQuestionComments(Set<QidaQuestionComment> questionComments) {
		this.questionComments = questionComments;
	}
}
