package com.xiaohe.qd.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 分页标签类
 * 
 * @author Administrator
 * 
 */
public class PaginationTag extends BodyTagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9050132313072588410L;
	private int pageVolume;
	private int totalNum;
	private int curPageNum;
	private String divId;
	private String formId = "pageForm";
	private String url;
	private BodyContent bodyContent;
	private Map<String, Object> paramMap;
	private String ctx;
	private static String templateFile = "pagination.ftl";

	private List<TagParameter> tagParams;

	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public BodyContent getBodyContent() {
		return bodyContent;
	}

	@Override
	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	@Override
	public int doStartTag() throws JspException {
		tagParams = new ArrayList<TagParameter>();
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		ApplicationContext content = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		try {

			paramMap = new HashMap<String, Object>();

			paramMap.put("pageVolume", pageVolume);
			if (totalNum == 0) {
				totalNum = 1;
			}
			if (pageVolume == 0) {
				pageVolume = 1;
			}
			paramMap.put("totalNum", totalNum);
			paramMap.put("url", url);
			paramMap.put("ctx", ctx);
			paramMap.put("param", tagParams);
			paramMap.put("formId", formId);
			paramMap.put("divId", getDivId());
			int totalPageNum = ((totalNum % pageVolume) == 0 ? (totalNum / pageVolume) : (totalNum / pageVolume + 1));
			List<Integer> pageNums = new ArrayList<Integer>();
			if (curPageNum > totalPageNum) {
				paramMap.put("curPageNum", totalPageNum);
			} else if (curPageNum < 1) {
				paramMap.put("curPageNum", 1);
			} else {
				paramMap.put("curPageNum", curPageNum);
			}

			// 判断页面显示页数编号
			int minPageNum = 1;
			int maxPageNum = 1;

			if (totalPageNum <= 5) {
				minPageNum = 1;
				maxPageNum = totalPageNum;
			} else {
				if (curPageNum + 2 >= totalPageNum) {
					minPageNum = totalPageNum - 4;
					maxPageNum = totalPageNum;
				} else if (curPageNum - 2 <= 1) {
					minPageNum = 1;
					maxPageNum = 5;
				} else {
					minPageNum = curPageNum - 2;
					maxPageNum = curPageNum + 2;
				}
			}

			for (int i = minPageNum; i <= maxPageNum; i++) {
				pageNums.add(i);
			}

			paramMap.put("totalPageNum", totalPageNum);
			paramMap.put("pageNums", pageNums);
			freeMarkerConfigurer = (FreeMarkerConfigurer) content.getBean("freeMarkerConfigurer");
			Template temp = freeMarkerConfigurer.getConfiguration().getTemplate(templateFile);
			Writer out = pageContext.getOut();
			temp.process(paramMap, out);
			out.flush();

		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getCtx() {
		return ctx;
	}

	public void setCtx(String ctx) {
		this.ctx = ctx;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<TagParameter> getTagParams() {
		return tagParams;
	}

	public void setTagParams(List<TagParameter> tagParams) {
		this.tagParams = tagParams;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public int getPageVolume() {
		return pageVolume;
	}

	public void setPageVolume(int pageVolume) {
		this.pageVolume = pageVolume;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}

	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getDivId() {
		return divId;
	}

}
