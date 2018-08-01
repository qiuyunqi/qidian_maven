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
@Table(name = "qd_stock_comment_approval")
public class StockCommentApproval implements Serializable {

	private static final long serialVersionUID = 2721793482945135043L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@Column(name = "create_time")
	private Date createTime; // 操作时间
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_comment_id")
	private StockComment stockComment; // StockComment主键

	@Column(name = "type")
	private Integer type; // 操作类型 0: 顶   1: 踩

	@Column(name = "comment_type")
	private Integer commentType; // 评论类别   0: 微博评论    1: 用户评论

	public StockCommentApproval() {
		super();
	}

	public StockCommentApproval(Long id, FuUser fuUser, Date createTime, StockComment stockComment, Integer type, Integer commentType) {
		super();
		this.id = id;
		this.fuUser = fuUser;
		this.createTime = createTime;
		this.stockComment = stockComment;
		this.type = type;
		this.commentType = commentType;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public StockComment getStockComment() {
		return stockComment;
	}

	public void setStockComment(StockComment stockComment) {
		this.stockComment = stockComment;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

}
