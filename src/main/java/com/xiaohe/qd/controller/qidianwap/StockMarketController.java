package com.xiaohe.qd.controller.qidianwap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.StockComment;
import com.xiaohe.qd.model.StockCommentApproval;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.model.StockPraiseList;
import com.xiaohe.qd.model.StockRecentOptional;
import com.xiaohe.qd.service.StockCommentApprovalService;
import com.xiaohe.qd.service.StockCommentService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.StockPraiseListService;
import com.xiaohe.qd.service.StockRecentOptionalService;
import com.xiaohe.qd.util.Property;

@Controller
@RequestMapping("/stock")
@Scope("prototype")
public class StockMarketController extends BaseController {
	@Resource
	private StockPraiseListService stockPraiseListService;
	@Resource
	private StockInfoService stockInfoService;
	@Resource
	private StockCommentService stockCommentService;
	@Resource
	private StockCommentApprovalService stockCommentApprovalService;
	@Resource
	private StockRecentOptionalService stockRecentOptionalService;

	@RequestMapping(value = "/kLine")
	public String kLine(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam(value = "id", required = false) Long id) {
		try{
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if(fuUser != null){
				StockRecentOptional optional = stockRecentOptionalService.findByStockId(id);
				if(optional == null){
					optional = new StockRecentOptional();
				}
				optional.setStockInfo(stockInfoService.get(id));
				optional.setFuUser(fuUser);
				optional.setCreateTime(new Date());
				stockRecentOptionalService.save(optional);
			}
			request.setAttribute("appid", Property.getProperty("WEIXIN_APPID"));
			request.setAttribute("url", Property.getProperty("REDIRECT_URL"));
			StockInfo stock = stockInfoService.get(id);
			String stockCode = stock.getStkType() + stock.getCode();
			String items = stockInfoService.queryStockItems();
			/*int dayGoodCommentsCount = stockCommentService.countStockDayComments(stock.getId(), 0);
			int dayCommentsCount = stockCommentService.countStockDayComments(stock.getId(), null);
			int weekGoodCommentsCount = stockCommentService.countStockWeekComments(stock.getId(), 0);
			int weekCommentsCount = stockCommentService.countStockWeekComments(stock.getId(), null);
			BigDecimal dayGoodRatio = dayCommentsCount == 0 ? BigDecimal.ZERO : ((new BigDecimal(dayGoodCommentsCount)).multiply(new BigDecimal(100)).divide((new BigDecimal(dayCommentsCount)), 2, BigDecimal.ROUND_HALF_UP));
			BigDecimal weekGoodRatio = weekCommentsCount == 0 ? BigDecimal.ZERO : ((new BigDecimal(weekGoodCommentsCount)).multiply(new BigDecimal(100)).divide((new BigDecimal(weekCommentsCount)), 2, BigDecimal.ROUND_HALF_UP));*/

			int allSize = stockCommentService.countStockCommentStructs(id, 3);
			int goodSize = stockCommentService.countStockCommentStructs(id, 0);
			int midSize = stockCommentService.countStockCommentStructs(id, 1);
			int badSize = stockCommentService.countStockCommentStructs(id, 2);

			// 聚合股票数据
			/*JSONObject stockObj = JuheStockUtil.getHsgs(stockCode);
			JSONObject allData = ((JSONArray) stockObj.get("result")).getJSONObject(0);
			JSONObject data = (JSONObject) allData.get("data");
			BigDecimal nowPri = new BigDecimal(data.get("nowPri").toString().substring(0, data.get("nowPri").toString().length() - 1));
			BigDecimal yestodEndPri = new BigDecimal(data.get("yestodEndPri").toString().substring(0, data.get("yestodEndPri").toString().length() - 1));
			BigDecimal traNumber = new BigDecimal(data.get("traNumber").toString());
			BigDecimal change = (nowPri.subtract(yestodEndPri)).multiply(new BigDecimal(100)).divide(yestodEndPri, 2, BigDecimal.ROUND_HALF_UP);
			BigDecimal todayStartPri = new BigDecimal(data.get("todayStartPri").toString().substring(0, data.get("todayStartPri").toString().length() - 1));
			BigDecimal todayMax = new BigDecimal(data.get("todayMax").toString().substring(0, data.get("todayMax").toString().length() - 1));
			BigDecimal todayMin = new BigDecimal(data.get("todayMin").toString().substring(0, data.get("todayMin").toString().length() - 1));*/
			JSONArray allCommentList = stockCommentService.getStockCommentList(stock.getId(), 0, 3);
			JSONArray goodCommentList = stockCommentService.getStockCommentList(stock.getId(), 0, 0);
			JSONArray middleCommentList = stockCommentService.getStockCommentList(stock.getId(), 0, 1);
			JSONArray badCommentList = stockCommentService.getStockCommentList(stock.getId(), 0, 2);

			model.addAttribute("items", items);
			model.addAttribute("name", stock.getName());
			model.addAttribute("code", stockCode);
			model.addAttribute("baseCode", stock.getCode());
			model.addAttribute("goodCount", stockCommentService.countStockComments(stock.getId(), 0, null));
			model.addAttribute("middleCount", stockCommentService.countStockComments(stock.getId(), 1, null));
			model.addAttribute("badCount", stockCommentService.countStockComments(stock.getId(), 2, null));
			model.addAttribute("totalCount", stockCommentService.countStockComments(stock.getId(), null, null));
			model.addAttribute("goodCommentList", goodCommentList);
			model.addAttribute("middleCommentList", middleCommentList);
			model.addAttribute("badCommentList", badCommentList);
			model.addAttribute("allCommentList", allCommentList);
			model.addAttribute("allSize", allSize);
			model.addAttribute("goodSize", goodSize);
			model.addAttribute("midSize", midSize);
			model.addAttribute("badSize", badSize);
			model.addAttribute("id", id);
			model.put("id", id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "userLine/kLine";
	}

	@RequestMapping(value = "/stockIndex")
	public String stockIndex(ModelMap model, @RequestParam(required = false) String code) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flag", "flag");
			List<StockPraiseList> praiseList = stockPraiseListService.findList(0, 5, map);
			String items = stockInfoService.queryStockItems();
			model.addAttribute("praiseList", praiseList);
			model.addAttribute("items", items);
			//model.addAttribute("codeArr", codeArr);
			model.addAttribute("code", code == null ? "" : code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userLine/stockIndex";
	}

	@RequestMapping("/saveStockCommentApproval")
	@ResponseBody
	public String saveStockCommentApproval(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "type", required = false) Integer type) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if(fuUser == null){
				return "-1";
			}
			//先查询用户是否顶过或踩过某评论
			int approvalType = stockCommentApprovalService.countStockCommentApproval(id, fuUser.getId());
			if(approvalType == -1){
				//更新评论记录的顶或踩数量
				StockComment comment = stockCommentService.get(id);
				if(type.equals(0)){
					comment.setRealLikeNum(comment.getRealLikeNum()+1);
				}else{
					comment.setRealNoLikeNum(comment.getRealNoLikeNum()+1);
				}
				stockCommentService.save(comment);
				//新增一条approval
				StockCommentApproval approval = new StockCommentApproval();
				approval.setFuUser(fuUser);
				approval.setCreateTime(new Date());
				approval.setStockComment(comment);
				approval.setType(type);
				approval.setCommentType(comment.getCommentType());
				stockCommentApprovalService.save(approval);
			}
			//顶过
			if(approvalType == 0){
				return "hasUp";
			}
			//踩过
			if(approvalType == 1){
				return "hasDown";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 下拉查询评论
	@RequestMapping(value = "/moreStockCommentList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String moreStockCommentList(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "type", required = false) Integer type) {
		JSONObject obj = new JSONObject();
		JSONArray jsonArr = stockCommentService.getStockCommentList(id, page * 5, type);
		obj.put("lists", jsonArr);
		return obj.toString();
	}

	//保存评论
	@RequestMapping(value = "/saveCreateStockComment", method = RequestMethod.POST)
	@ResponseBody
	public String saveCreateStockComment(HttpServletRequest request,HttpServletResponse response, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "stockId", required = false) Long stockId, @RequestParam(value = "type", required = false) Integer type, @RequestParam(value = "content", required = false) String content) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if(fuUser == null){
				return "-1";
			}
			StockInfo stock = stockInfoService.get(stockId);
			StockComment parent = stockCommentService.get(id);
			parent.setIsLeaf(1);
			stockCommentService.save(parent);
			StockComment stockComment = new StockComment();
			stockComment.setName(fuUser.getNickName() == null ? "匿名用户" : fuUser.getNickName());
			stockComment.setCreateTime(new Date());
			stockComment.setContent(content);
			stockComment.setType(type);
			stockComment.setStockInfo(stock);
			stockComment.setCommentType(1);
			stockComment.setCommentId(id);
			stockComment.setFuUser(fuUser);
			stockComment.setIsLeaf(0);
			stockComment.setRealLikeNum(0);
			stockComment.setRealNoLikeNum(0);
			stockCommentService.save(stockComment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//聚合接口
	/**
	 * 2016-12-06 17:00 
	 * 将聚合的数据修改成qd_stock_info中的数据 : 
	 * 	因为每分钟都在从聚合取数据 然后填充在qd_stock_info中  
	 * 这个表的数据已经是最新的了 不需要再从聚合里面重复拿数据
	 * 
	 * @param id	qdStockInfoId
	 */
	@RequestMapping(value = "/getJuheStockInfo", method = RequestMethod.GET)
	@ResponseBody
	public String getJuheStockInfo(@RequestParam(value = "id", required = false) Long id) {
		JSONObject obj = new JSONObject();
		StockInfo stock = stockInfoService.get(id);
		obj.put("nowPri",stock.getCurrPrice().toString());
		obj.put("yestodEndPri", stock.getYestodayEndPri().toString());
		obj.put("traNumber", stock.getTraNumber().toString());
		obj.put("change", stock.getLiftRate().toString());
		obj.put("todayStartPri", stock.getTodayStartPri().toString());
		obj.put("todayMax", stock.getTodayMax().toString());
		obj.put("todayMin", stock.getTodayMin().toString());
		obj.put("difference", stock.getDifference().toString());
		return obj.toString();
	}
	
	@RequestMapping(value = "/getPriceAndRange", method = RequestMethod.GET)
	@ResponseBody
	public String getPriceAndRange(@RequestParam(value = "codeArr", required = false) String codeArr) {
		JSONArray allObj = new JSONArray();
		if(!"".equals(codeArr)){
			String[] codeStrArr = codeArr.split(",");
			for(int i=0; i<codeStrArr.length; i++){
				JSONObject obj = stockPraiseListService.getStockPriceAndChange(codeStrArr[i]);
				obj.put("code", codeStrArr[i]);
				obj.put("id", stockInfoService.findByStockCode(codeStrArr[i].substring(2,codeStrArr[i].length())).getId());
				allObj.add(obj);
			}
		}
		return allObj.toString();
	}

}
