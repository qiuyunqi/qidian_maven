package com.xiaohe.qd.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "qida_tags")
public class QidaTags implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6563169171280333296L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "parent_id")
	private Long parentId;
	
	@Column(name = "is_hot")
	private Integer isHot; // 0:普通标签 1:热门标签 默认0

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "qidaTags")
	private Set<QidaTagQuestion> tagQuestions = new HashSet<QidaTagQuestion>(0);
	public QidaTags() {
		super();
	}
	
	public QidaTags(Long id, String name, Long parentId, Integer isHot,
			Set<QidaTagQuestion> tagQuestions) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.isHot = isHot;
		this.tagQuestions = tagQuestions;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Set<QidaTagQuestion> getTagQuestions() {
		return tagQuestions;
	}

	public void setTagQuestions(Set<QidaTagQuestion> tagQuestions) {
		this.tagQuestions = tagQuestions;
	}
	
}
