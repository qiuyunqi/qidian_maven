package com.xiaohe.qd.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockComment;
import com.xiaohe.qd.util.DateUtil;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@Repository
public class StockCommentDao extends BaseDAO<StockComment, Long> {

	public List<StockComment> queryStockCommentList(Long stockId, Integer type, String category) {
		List<Object> params=new ArrayList<Object>();
		String hql = " from StockComment where 1=1 ";
		params.add(stockId);
		hql=hql+" and stockInfo.id=? ";
		params.add(type);
		hql=hql+" and type=? ";
		hql=hql+" order by createTime desc ";
		if(category == "all"){
			return this.findAllByHQL(hql, stockId, type);
		}
		return this.findListByHQL(0, 5, hql, params);
	}

	public int countStockDayComments(Long stockId, Integer type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, Object> map = new HashMap<String, Object>();
		if (stockId != null) {
			map.put("stockId", stockId);
		}
		if (type != null) {
			map.put("type", type);
		}
		map.put("timeStr", sdf.format(new Date()));
		String hql = "from StockComment where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("stockId")) {
			params.add(map.get("stockId"));
			hql = hql + " and stockInfo.id=? ";
		}
		if (map.containsKey("timeStr")) {
			params.add(map.get("timeStr"));
			hql = hql + " and DATE_FORMAT(createTime, '%Y-%m-%d')=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public int countStockWeekComments(Long stockId, Integer type) {
		Date date = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		if (stockId != null) {
			map.put("stockId", stockId);
		}
		if (type != null) {
			map.put("type", type);
		}
		map.put("monday", DateUtil.getCurrentMonday(date));
		map.put("sunday", DateUtil.getCurrentSunday(date));
		String hql = "from StockComment where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("stockId")) {
			params.add(map.get("stockId"));
			hql = hql + " and stockInfo.id=? ";
		}
		if (map.containsKey("monday")) {
			params.add(map.get("monday"));
			hql = hql + " and DATE_FORMAT(createTime, '%Y-%m-%d')>=? ";
		}
		if (map.containsKey("sunday")) {
			params.add(map.get("sunday"));
			hql = hql + " and DATE_FORMAT(createTime, '%Y-%m-%d')<=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public int countStockComments(Long stockId, Integer type, Integer isLeaf) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (stockId != null) {
			map.put("stockId", stockId);
		}
		if (type != null) {
			map.put("type", type);
		}
		if (isLeaf != null) {
			map.put("isLeaf", isLeaf);
		}
		String hql = "from StockComment where 1=1 ";
		List<Object> params = new ArrayList<Object>();
		if (map.containsKey("stockId")) {
			params.add(map.get("stockId"));
			hql = hql + " and stockInfo.id=? ";
		}
		if (map.containsKey("type")) {
			params.add(map.get("type"));
			hql = hql + " and type=? ";
		}
		if (map.containsKey("isLeaf")) {
			params.add(map.get("isLeaf"));
			hql = hql + " and isLeaf=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockComment where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("type") != null) {
			hql = hql + " and type = " + map.get("type");
		}
		return this.countQueryResult(hql);
	}

	public List<StockComment> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from StockComment where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("type") != null) {
			hql = hql + " and type = " + map.get("type");
		}
		hql = hql + " order by id desc";
		return this.findListByHQL(i, pageSize, hql, null);
	}

	//递归方法
	public List<StockComment> getParentComments(StockComment sc, List<StockComment> pList, Integer type) {	
		//判断评论是否有parent
		if(sc.getCommentId() != null){
			StockComment parentComment = this.get(sc.getCommentId());
			//type为3表示全部评论,将parent对象加入list
			if(type.equals(3)){
				pList.add(parentComment);
			}else{
				//type不为3表示有具体类别, 只将类型匹配的parent对象加入list,保证好评中全部是好评(中评与差评亦是)
				if(parentComment.getType().equals(type)){
					pList.add(parentComment);
				}
			}
			this.getParentComments(parentComment, pList, type);
		}
		//判断传入的评论记录是否有直接下级
		/*List<StockComment> nextList = this.findStockCommentByParentId(sc.getId());
		if(nextList != null && nextList.size() != 0){
			//先把直接下级list加入
			replyList.addAll(nextList);
			//在直接下级list中进行迭代,继续往下搜索
			for(StockComment s : nextList){
				this.getCommentReplys(s, replyList);
			}
		}*/
		return pList;
	}
	
	//根据id查询某评论是否有直接下级评论
	public List<StockComment> findStockCommentByParentId(Long id) {
		String hql=" from StockComment where commentId=? order by createTime asc ";
		return this.findAllByHQL(hql, id);
	}
	
	//下拉需要查询的总记录数
	public int countStockCommentStructs(Long stockId, Integer type){
		String hql = " from StockComment where 1=1 and isLeaf=0 ";
		List<Object> params = new ArrayList<Object>();
		params.add(stockId);
		hql = hql + " and stockInfo.id=? ";
		//3表示全部评论
		if(type != 3){
			params.add(type);
			hql = hql + " and type=? ";		
		}
		int allCount = this.countQueryResult(hql,params);   
		if(allCount > 5){
			return allCount - 5;
		}else{
			return 0;
		}
	}
	
	
}
