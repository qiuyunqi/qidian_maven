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
 * 评论系统对象
 * 
 * @author han
 */
@Entity
@Table(name = "qd_stock_comment")
public class StockComment implements Serializable {

	private static final long serialVersionUID = 2721793482945135041L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name; // 评论人的昵称

	@Column(name = "create_time")
	private Date createTime; // 评论时间

	@Column(name = "content")
	private String content; // 评论内容

	@Column(name = "type")
	private Integer type; // 评论类型 0: 好评 1: 中评 2:差评

	@Column(name = "like_num")
	private Integer likeNum; // 点赞数 (原始值)

	@Column(name = "no_like_num")
	private Integer noLikeNum; // 不点赞数 (原始值)

	@Column(name = "real_like_num")
	private Integer realLikeNum; // 点赞数 (真实值)

	@Column(name = "real_no_like_num")
	private Integer realNoLikeNum; // 不点赞数 (真实原始值)

	@Column(name = "wb_url")
	private String wbUrl; // 微博地址

	@Column(name = "wb_mid")
	private String wbMid; // 微博mid

	@Column(name = "wb_reposts")
	private Integer wbReposts; // 微博转发数

	@Column(name = "wb_comments")
	private Integer wbComments; // 微博评论数

	@Column(name = "wb_attitudes")
	private Integer wbAttitudes; // 微博点赞数

	@Column(name = "wb_hdl")
	private Integer wbHdl; // 微博互动量

	@Column(name = "wb_uid")
	private String wbUid; // 微博UID

	@Column(name = "wb_fensi")
	private Integer wbFensi; // 微博用户粉丝数

	@Column(name = "wb_weibo")
	private Integer wbWibo; // 用户微博数

	@Column(name = "wb_guanzhu")
	private Integer wbGuanzhu; // 微博关注数

	@Column(name = "wb_vtype")
	private String wbvtype; // 微博用户类型

	@Column(name = "comment_type")
	private Integer commentType;// 评论类别 0: 微博评论 1: 用户评论

	@Column(name = "comment_id")
	private Long commentId;// 关联的评论id

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private FuUser fuUser;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_info_id")
	private StockInfo stockInfo; // stocnInfo主键
	
	@Column(name = "is_leaf")
	private Integer isLeaf;  // 是否最后一层  0:是  1:不是

	public StockComment() {
		super();
	}

	public StockComment(Long id, String name, Date createTime, String content, Integer type, Integer likeNum, Integer noLikeNum, Integer realLikeNum, Integer realNoLikeNum, String wbUrl, String wbMid, Integer wbReposts, Integer wbComments, Integer wbAttitudes, Integer wbHdl, String wbUid, Integer wbFensi, Integer wbWibo, Integer wbGuanzhu, String wbvtype, StockInfo stockInfo, Integer commentType,
			Long commentId, FuUser fuUser, Integer isLeaf) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.content = content;
		this.type = type;
		this.likeNum = likeNum;
		this.noLikeNum = noLikeNum;
		this.realLikeNum = realLikeNum;
		this.realNoLikeNum = realNoLikeNum;
		this.wbUrl = wbUrl;
		this.wbMid = wbMid;
		this.wbReposts = wbReposts;
		this.wbComments = wbComments;
		this.wbAttitudes = wbAttitudes;
		this.wbHdl = wbHdl;
		this.wbUid = wbUid;
		this.wbFensi = wbFensi;
		this.wbWibo = wbWibo;
		this.wbGuanzhu = wbGuanzhu;
		this.wbvtype = wbvtype;
		this.stockInfo = stockInfo;
		this.commentType = commentType;
		this.commentId = commentId;
		this.fuUser = fuUser;
		this.isLeaf = isLeaf;
	}
	
	public StockComment(Long id, String name, Date createTime, String content, Integer commentType, Long commentId, Integer realLikeNum, Integer realNoLikeNum) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.content = content;
		this.commentType = commentType;
		this.commentId = commentId;
		this.realLikeNum = realLikeNum;
		this.realNoLikeNum = realNoLikeNum;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}

	public Integer getNoLikeNum() {
		return noLikeNum;
	}

	public void setNoLikeNum(Integer noLikeNum) {
		this.noLikeNum = noLikeNum;
	}

	public StockInfo getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(StockInfo stockInfo) {
		this.stockInfo = stockInfo;
	}

	public Integer getRealLikeNum() {
		return realLikeNum;
	}

	public void setRealLikeNum(Integer realLikeNum) {
		this.realLikeNum = realLikeNum;
	}

	public Integer getRealNoLikeNum() {
		return realNoLikeNum;
	}

	public void setRealNoLikeNum(Integer realNoLikeNum) {
		this.realNoLikeNum = realNoLikeNum;
	}

	public String getWbUrl() {
		return wbUrl;
	}

	public void setWbUrl(String wbUrl) {
		this.wbUrl = wbUrl;
	}

	public String getWbMid() {
		return wbMid;
	}

	public void setWbMid(String wbMid) {
		this.wbMid = wbMid;
	}

	public String getWbUid() {
		return wbUid;
	}

	public void setWbUid(String wbUid) {
		this.wbUid = wbUid;
	}

	public Integer getWbReposts() {
		return wbReposts;
	}

	public void setWbReposts(Integer wbReposts) {
		this.wbReposts = wbReposts;
	}

	public Integer getWbComments() {
		return wbComments;
	}

	public void setWbComments(Integer wbComments) {
		this.wbComments = wbComments;
	}

	public Integer getWbAttitudes() {
		return wbAttitudes;
	}

	public void setWbAttitudes(Integer wbAttitudes) {
		this.wbAttitudes = wbAttitudes;
	}

	public Integer getWbHdl() {
		return wbHdl;
	}

	public void setWbHdl(Integer wbHdl) {
		this.wbHdl = wbHdl;
	}

	public Integer getWbFensi() {
		return wbFensi;
	}

	public void setWbFensi(Integer wbFensi) {
		this.wbFensi = wbFensi;
	}

	public Integer getWbWibo() {
		return wbWibo;
	}

	public void setWbWibo(Integer wbWibo) {
		this.wbWibo = wbWibo;
	}

	public Integer getWbGuanzhu() {
		return wbGuanzhu;
	}

	public void setWbGuanzhu(Integer wbGuanzhu) {
		this.wbGuanzhu = wbGuanzhu;
	}

	public String getWbvtype() {
		return wbvtype;
	}

	public void setWbvtype(String wbvtype) {
		this.wbvtype = wbvtype;
	}

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public FuUser getFuUser() {
		return fuUser;
	}

	public void setFuUser(FuUser fuUser) {
		this.fuUser = fuUser;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	
}
