package com.xiaohe.qd.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.StockPraiseList;
import com.xiaohe.qd.util.HttpRemoteUtil;
import com.xiaohe.qd.util.JuheStockUtil;

/**
 * 
 * @description 自动生成 dao
 * 
 * @author 小合
 */
@Repository
public class StockPraiseListDao extends BaseDAO<StockPraiseList, Long> {

	public List<Object[]> queryStockPraiseList(String code) {
		String sql = "select * from qd_stock_praise_list order by stock_sort asc  ";
		if (code != null && !"".equals(code)) {
			sql = "select * from qd_stock_praise_list where code like '%" + code + "%' or name like '%" + code + "%' or py_code like '%" + code + "%' order by stock_sort asc ";
		}
		List<Object[]> praiseList = this.findListBySql(0, 5, sql, null);
		/*for (int i = 0; i < praiseList.size(); i++) {
			Object[] objArr = praiseList.get(i);
			JSONObject obj = this.getStockPriceAndChange(objArr[2].toString());
			String transaction_price = obj.getString("nowPri");
			String rise_and_fall = obj.getString("change");
			objArr[3] = transaction_price;
			objArr[4] = rise_and_fall;
		}*/
		return praiseList;
	}

	/**
	 * 查询成交价和涨跌幅
	 * 
	 * @param stockCode
	 *            股票代码
	 * @return
	 */
	public JSONObject getSinaStockData(String stockCode) {
		Map<String, Object> argsMap = new HashMap<String, Object>();
		argsMap.put("list", stockCode);
		String html = null;
		JSONObject obj = new JSONObject();
		try {
			html = HttpRemoteUtil.GETMethod("http://hq.sinajs.cn/", argsMap);
			String[] stockDataArr = html.split(",");
			// 数据处理,去掉价格末位多余的0
			BigDecimal current = new BigDecimal(stockDataArr[3].substring(0, stockDataArr[3].length() - 1));
			BigDecimal last = new BigDecimal(stockDataArr[2].substring(0, stockDataArr[2].length() - 1));
			BigDecimal change = (current.subtract(last)).multiply(new BigDecimal(100)).divide(last, 2, BigDecimal.ROUND_HALF_UP);
			obj.put("current", current);
			obj.put("change", change.toString() + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	// 查询股票当前价格和涨跌幅
	public JSONObject getStockPriceAndChange(String stockCode) {
		JSONObject obj = new JSONObject();
		JSONObject stockObj = JuheStockUtil.getHsgs(stockCode);
		// 聚合接口出现错误
		if (stockObj == null) {
			obj.put("nowPri", "");
			obj.put("change", "");
		} else {
			JSONObject allData = ((JSONArray) stockObj.get("result")).getJSONObject(0);
			JSONObject data = (JSONObject) allData.get("data");
			BigDecimal nowPri = new BigDecimal(data.get("nowPri").toString().substring(0, data.get("nowPri").toString().length() - 1));
			BigDecimal yestodEndPri = new BigDecimal(data.get("yestodEndPri").toString().substring(0, data.get("yestodEndPri").toString().length() - 1));
			BigDecimal change = (nowPri.subtract(yestodEndPri)).multiply(new BigDecimal(100)).divide(yestodEndPri, 2, BigDecimal.ROUND_HALF_UP);
			obj.put("nowPri", nowPri);
			obj.put("change", change.toString() + "%");
		}
		return obj;
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from StockPraiseList where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		if (map.get("stockInfoId") != null) {
			hql = hql + " and stockInfo.id = " + map.get("stockInfoId");
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		
		return this.countQueryResult(hql);
	}

	public List<StockPraiseList> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from StockPraiseList where 1=1 ";
		if (map.get("name") != null) {
			hql = hql + " and name like '%" + map.get("name") + "%'";
		}
		if (map.get("code") != null) {
			hql = hql + " and code like '%" + map.get("code") + "%'";
		}
		if (map.get("isDelete") != null) {
			hql = hql + " and isDelete = " + map.get("isDelete");
		}
		if (map.get("sort") != null && map.get("order") != null) {
			hql = hql + " order by " + map.get("sort") + " " + map.get("order");
		} else {
			if (map.get("flag") != null) {
				hql = hql + " order by stock_sort asc ";
			}else{
				hql = hql + " order by id desc";
			}
		}
		return this.findListByHQL(i, pageSize, hql, null);
	}

	public List<Object[]> queryWebStockPraiseList() {
		String sql = "select stock_info_id, code, name from qd_stock_praise_list order by stock_sort asc  ";
		List<Object[]> praiseList = this.findListBySql(0, 6, sql, null);
		return praiseList;
	}

}
