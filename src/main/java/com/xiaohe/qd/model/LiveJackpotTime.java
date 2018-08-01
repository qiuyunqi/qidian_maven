package com.xiaohe.qd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "live_jackpot_time")
public class LiveJackpotTime implements Serializable {
	private static final long serialVersionUID = -541112826886702260L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "changes")
	private Integer changes;
	
	@Column(name = "start_time")
	private Date startTime;
	
	@Column(name = "end_time")
	private Date endTime;
	
	@OneToOne(optional=false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "jackpot_id")
	private LiveJackpot livejackpot;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "is_del")
	private Integer isDel;

	public LiveJackpotTime(){}
	
	public LiveJackpotTime(Long id, Integer changes, Date startTime,
			Date endTime, LiveJackpot livejackpot, Date createTime,
			Integer isDel) {
		super();
		this.id = id;
		this.changes = changes;
		this.startTime = startTime;
		this.endTime = endTime;
		this.livejackpot = livejackpot;
		this.createTime = createTime;
		this.isDel = isDel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getChanges() {
		return changes;
	}

	public void setChanges(Integer changes) {
		this.changes = changes;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public LiveJackpot getLivejackpot() {
		return livejackpot;
	}

	public void setLivejackpot(LiveJackpot livejackpot) {
		this.livejackpot = livejackpot;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
}
