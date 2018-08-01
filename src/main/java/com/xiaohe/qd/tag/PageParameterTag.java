package com.xiaohe.qd.tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * 分页参数类
 * 
 * @author Administrator
 * 
 */
public class PageParameterTag extends TagSupport {
	private static final long serialVersionUID = 2752673127193088553L;
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doEndTag() {
		PaginationTag paginationTag = (PaginationTag) TagSupport.findAncestorWithClass(this, PaginationTag.class);
		TagParameter tempParam = new TagParameter();
		tempParam.setName(name);
		tempParam.setValue(value);
		paginationTag.getTagParams().add(tempParam);
		return EVAL_PAGE;
	}
}
