package com.xiaohe.qd.model;



import org.springframework.stereotype.Component;

@Component(value="pagination")
public class Pagination {
	private Integer rowCount = 0;
	private Integer pageCount = 0;
	private Integer currentPage = 1;
	private Integer pageSize = 10;
	private String actionName;
	private String sql;
	private String hql;
	private String resultpage ;
	private String params ;

	public Pagination() {
		super();
	}

	public Pagination(Integer rowCount, Integer pageCount, Integer currentPage,
			Integer totalPage, Integer pageSize) {
		super();
		this.rowCount = rowCount;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
		this.pageCount = (rowCount + this.pageSize - 1)/this.pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getStartRow() {
		return (getCurrentPage()-1)*getPageSize();
	}
	
	public Integer getEndRow() {
		return getCurrentPage()*getPageSize();
	}

	public Integer getCurrentPage() {
		if(currentPage > pageCount)
			currentPage = pageCount;
		if(currentPage < 1)
			currentPage = 1;
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public String getResultpage() {
		return resultpage;
	}

	public void setResultpage(String resultpage) {
		this.resultpage = resultpage;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
}
