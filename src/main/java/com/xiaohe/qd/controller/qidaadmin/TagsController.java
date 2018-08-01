package com.xiaohe.qd.controller.qidaadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.jsoup.helper.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.QidaTagQuestion;
import com.xiaohe.qd.model.QidaTags;
import com.xiaohe.qd.service.QidaTagQuestionService;
import com.xiaohe.qd.service.QidaTagsService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class TagsController extends BaseController {
	@Resource
	private QidaTagsService qidaTagsService;
	@Resource
	private QidaTagQuestionService qidaTagQuestionService;

	/**
	 * 标签列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tagsList", produces = "text/html;charset=UTF-8")
	public String tagsList() {
		return "qida/qidaManage/tagsList";
	}
	
	/**
	 * 查询所有的顶级标签
	 */
	@RequestMapping(value = "/topTagData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String topTagData() {
		JSONArray jsonArray = new JSONArray();
		try {
			JSONObject object = new JSONObject();
			object.put("tagId", 0L);
			object.put("tagName", "顶级标签");
			jsonArray.add(object);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", 0L);
			List<QidaTags> list = qidaTagsService.findList(0, Integer.MAX_VALUE, map);
			if (list != null && list.size() > 0) {
				for (QidaTags tag : list) {
					JSONObject obj = new JSONObject();
					obj.put("tagId", tag.getId());
					obj.put("tagName", tag.getName());
					jsonArray.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(jsonArray);
	}

	/**
	 * 标签数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tagsData", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String tagsData(String name, Integer isHot, Integer page, Integer rows) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isBlank(name)) {
				map.put("name", name);
			}
			if (isHot!=null) {
				map.put("isHot", isHot);
			}
			Integer total = qidaTagsService.getCount(map);
			List<QidaTags> list = qidaTagsService.findList((page - 1) * rows, rows, map);
			JSONArray jsonArray = new JSONArray();
			if (list != null && list.size() > 0) {
				for (QidaTags qidaTags : list) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", qidaTags.getId());
					jsonObject.put("name", qidaTags.getName());
					jsonObject.put("parentId", qidaTags.getParentId());
					jsonObject.put("isHot", qidaTags.getIsHot()==1?"热门标签":"普通标签");
					jsonArray.add(jsonObject);
				}
			}
			result.put("total", total);
			result.put("rows", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 添加或修改标签
	 * 
	 */
	@RequestMapping(value = "/addTag", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addTag(QidaTags tag) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			QidaTags qidaTags = new QidaTags();
			if (tag.getId() != null) {
				qidaTags = qidaTagsService.get(tag.getId());
			}
			qidaTags.setName(tag.getName());
			qidaTags.setParentId(tag.getParentId());
			qidaTags.setIsHot(tag.getIsHot());
			qidaTagsService.save(qidaTags);

			result.put("success", true);
			result.put("msg", "保存标签成功！");
			return JSONObject.toJSONString(result);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	/**
	 * 删除标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delTag", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delTag(Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tagId", id);
			List<QidaTagQuestion> list = qidaTagQuestionService.findList(0, Integer.MAX_VALUE, map);
			if (list != null && list.size() > 0) {
				for (QidaTagQuestion qidaTagQuestion : list) {
					qidaTagQuestionService.delete(qidaTagQuestion.getId());//删除中间表数据
				}
			}
			map.put("parentId", id);
			List<QidaTags> list2=qidaTagsService.findList(0, Integer.MAX_VALUE, map);
			if (list2 != null && list2.size() > 0) {
				for (QidaTags tags : list2) {
					qidaTagsService.delete(tags.getId());//删除子标签
				}
			}
			qidaTagsService.delete(id);
			result.put("success", true);
			result.put("msg", "删除成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

}
