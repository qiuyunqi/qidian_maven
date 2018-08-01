package com.xiaohe.qd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qida_stock_question")
public class QidaStockQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5479696599347783143L;

	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QidaQuestion qidaQuestion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_info_id")
	private StockInfo stockInfo;

	public QidaStockQuestion() {
		super();
	}

	public QidaStockQuestion(Long id, QidaQuestion qidaQuestion, StockInfo stockInfo) {
		super();
		this.id = id;
		this.qidaQuestion = qidaQuestion;
		this.stockInfo = stockInfo;
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

	public StockInfo getStockInfo() {
		return stockInfo;
	}

	public void setStockInfo(StockInfo stockInfo) {
		this.stockInfo = stockInfo;
	}

}
