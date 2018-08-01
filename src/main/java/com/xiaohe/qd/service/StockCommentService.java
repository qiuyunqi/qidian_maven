package com.xiaohe.qd.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.StockCommentApprovalDao;
import com.xiaohe.qd.dao.StockCommentDao;
import com.xiaohe.qd.model.StockComment;
import com.xiaohe.qd.util.CommonUtil;

/**
 * 
 * @description 自动生成 service
 * 
 */
@Service
public class StockCommentService extends BaseService {
	@Resource
	private StockCommentDao stockCommentDao;
	
	@Resource
	private StockCommentApprovalDao stockCommentApprovalDao;

	// ====================== 基本 C R U D 方法 ===========================
	public StockComment get(Long id) {
		return stockCommentDao.get(id);
	}

	public void save(StockComment entity) {
		stockCommentDao.save(entity);
	}

	public void delete(Long id) {
		stockCommentDao.delete(id);
	}

	public List<StockComment> queryStockCommentList(Long stockId, Integer type, String category) {
		return stockCommentDao.queryStockCommentList(stockId, type, category);
	}

	public int countStockDayComments(Long stockId, Integer type) {
		return stockCommentDao.countStockDayComments(stockId, type);
	}

	public int countStockWeekComments(Long stockId, Integer type) {
		return stockCommentDao.countStockWeekComments(stockId, type);
	}

	public int countStockComments(Long stockId, Integer type, Integer isLeaf) {
		return stockCommentDao.countStockComments(stockId, type, isLeaf);
	}

	public Integer getCount(Map<String, Object> map) {
		return stockCommentDao.getCount(map);
	}

	public List<StockComment> findList(int i, int pageSize, Map<String, Object> map) {
		return stockCommentDao.findList(i, pageSize, map);
	}
	
	public JSONArray getStockCommentList(Long stockId, Integer fromIndex, Integer type) {
		JSONArray arr = new JSONArray();
		//分页查询没有下级的记录
		String hql = " select new StockComment(id, name, createTime, content, commentType, commentId, realLikeNum, realNoLikeNum) from StockComment where 1=1 and isLeaf=0 ";
		List<Object> params = new ArrayList<Object>();
		params.add(stockId);
		hql = hql + " and stockInfo.id=? ";
		//3表示全部评论
		if(type != 3){
			params.add(type);
			hql = hql + " and type=? ";		
		}
		hql = hql + " order by createTime desc ";
		List<StockComment> commentList = stockCommentDao.findListByHQL(fromIndex, 5, hql, params);
		if(commentList != null && commentList.size() != 0){
			for(StockComment sc:commentList){
				JSONObject obj = new JSONObject();
				JSONArray parentArr = new JSONArray();
				String content = sc.getContent()==null?"":sc.getContent();
				Long parentId = sc.getCommentId();
				if(parentId != null){
					StockComment parent = this.get(parentId);
					content = "@"+parent.getName()+":"+content;
				}
				obj.put("id", sc.getId());
				obj.put("name", sc.getName()); 
				obj.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sc.getCreateTime()));
				obj.put("content", content);
				obj.put("like", sc.getRealLikeNum());
				obj.put("noLike", sc.getRealNoLikeNum());
				obj.put("commentType", sc.getCommentType());
				obj.put("typeStr", sc.getCommentType()==0?"来自：新浪微博":sc.getCommentType()==1?"来自：用户评论":sc.getCommentType()==2?"来自：股吧评论":"");
				//obj.put("hasApproval", stockCommentApprovalDao.countStockCommentApproval(sc.getId(), userId));
				List<StockComment> parentComments = new ArrayList<StockComment>();
				//调用递归方法,自下往上找
				stockCommentDao.getParentComments(sc, parentComments, type);
				if(parentComments != null && parentComments.size() != 0){
					//将parentComments转成JSONArray
					for(int j=0; j<parentComments.size(); j++){
						JSONObject jsonObj = new JSONObject();
						String parentContent = parentComments.get(j).getContent()==null?"":parentComments.get(j).getContent();
						Long parentCommentId = parentComments.get(j).getCommentId();
						if(parentCommentId != null){
							StockComment parentComment = this.get(parentCommentId);
							parentContent = "@"+parentComment.getName()+":"+parentContent;
						}
						jsonObj.put("id", parentComments.get(j).getId());
						//jsonObj.put("name", parentComments.get(j).getName().length() > 2 ? parentComments.get(j).getName().replace(parentComments.get(j).getName().substring(1, parentComments.get(j).getName().length() - 1), "**") : parentComments.get(j).getName());
						jsonObj.put("name", parentComments.get(j).getName());
						jsonObj.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(parentComments.get(j).getCreateTime()));
						jsonObj.put("content", parentContent);
						jsonObj.put("like", parentComments.get(j).getRealLikeNum());
						jsonObj.put("noLike", parentComments.get(j).getRealNoLikeNum());
						jsonObj.put("commentType", parentComments.get(j).getCommentType());
						jsonObj.put("typeStr", parentComments.get(j).getCommentType()==0?"来自：新浪微博":parentComments.get(j).getCommentType()==1?"来自：用户评论":parentComments.get(j).getCommentType()==2?"来自：股吧评论":"");
						//jsonObj.put("hasApproval", stockCommentApprovalDao.countStockCommentApproval(parentComments.get(j).getId(), userId));
						parentArr.add(jsonObj);
					}
					CommonUtil.jsonArraySort(parentArr, "createTime", true);
					obj.put("parents", parentArr);
				}else{
					obj.put("parents", parentArr);
				}
				arr.add(obj);
			}
		}
		return arr;
	}
	
	public int countStockCommentStructs(Long stockId, Integer type){
		return stockCommentDao.countStockCommentStructs(stockId, type);
	}
	
}
