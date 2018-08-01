package com.xiaohe.qd.controller.qidianpc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.StockBullish;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.model.StockRecentOptional;
import com.xiaohe.qd.service.StockBullishService;
import com.xiaohe.qd.service.StockCommentService;
import com.xiaohe.qd.service.StockInfoService;
import com.xiaohe.qd.service.StockPraiseListService;
import com.xiaohe.qd.service.StockRecentOptionalService;

@Controller
@RequestMapping("/ai")
@Scope("prototype")
public class QdStockController extends BaseController {
	@Resource
	private StockPraiseListService stockPraiseListService;
	@Resource
	private StockInfoService stockInfoService;
	@Resource
	private StockCommentService stockCommentService;
	@Resource
	private StockRecentOptionalService stockRecentOptionalService;
	@Resource
	private StockBullishService stockBullishService;

	@RequestMapping(value = "/qdIndex")
	public String qdIndex(HttpServletRequest request, ModelMap model) {
		String items = stockInfoService.queryStockItems();
		List<Object[]> arrList = stockPraiseListService.queryWebStockPraiseList();
		List<String[]> codeList = new ArrayList<String[]>();
		for (int i = 0; i < 6; i = i + 2) {
			String[] strArr = new String[6];
			// id
			strArr[0] = arrList.get(i)[0].toString();
			// 股票代码
			String code0 = arrList.get(i)[1].toString();
			/**
			JSONObject stockObj0 = JuheStockUtil.getHsgs(code0);
			if (null != stockObj0) {
				strArr[1] = "http://image.sinajs.cn/newchart/min/n/"+code0+".gif";
			} else {
				strArr[1] = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/web/images_line/default.png";
			}
			**/
			strArr[1] = "http://image.sinajs.cn/newchart/min/n/"+code0+".gif";
			
			// id
			strArr[2] = arrList.get(i + 1)[0].toString();
			// 股票代码
			String code2 = arrList.get(i + 1)[1].toString();
			/**
			JSONObject stockObj2 = JuheStockUtil.getHsgs(code0);
			if (null != stockObj2) {
				strArr[3] = "http://image.sinajs.cn/newchart/min/n/"+code2+".gif";
			} else {
				strArr[3] = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+"/web/images_line/default.png";
			}
			**/
			strArr[3] = "http://image.sinajs.cn/newchart/min/n/"+code2+".gif";
			codeList.add(strArr);
		}
		model.addAttribute("items", items);
		model.addAttribute("codeList", codeList);
		model.addAttribute("top", "top");
		return "qdWeb/qdIndex";
	}

	@RequestMapping(value = "/qdHot")
	public String qdHot(HttpServletRequest request, ModelMap model) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			String items = stockInfoService.queryStockItems();
			List<StockRecentOptional> optionalList = stockRecentOptionalService.findListByUser(0, 11, fuUser.getId());
			String codeArr = "";
			for (StockRecentOptional op : optionalList) {
				codeArr = codeArr + (op.getStockInfo().getStkType() + op.getStockInfo().getCode()) + ",";
			}
			if (!"".equals(codeArr)) {
				codeArr = codeArr.substring(0, codeArr.length() - 1);
			}
			List<Object[]> arrList = stockPraiseListService.queryWebStockPraiseList();
			List<String[]> codeList = new ArrayList<String[]>();
			for (int i = 0; i < 6; i = i + 2) {
				String[] strArr = new String[6];
				strArr[0] = arrList.get(i)[0].toString();
				strArr[1] = arrList.get(i)[1].toString();
				strArr[2] = arrList.get(i)[2].toString();
				strArr[3] = arrList.get(i + 1)[0].toString();
				strArr[4] = arrList.get(i + 1)[1].toString();
				strArr[5] = arrList.get(i + 1)[2].toString();
				codeList.add(strArr);
			}
			model.addAttribute("items", items);
			model.addAttribute("fuUser", fuUser);
			model.addAttribute("codeArr", codeArr);
			model.addAttribute("optionalList", optionalList);
			model.addAttribute("codeList", codeList);
			return "qdWeb/qdHot";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/saveStockBullish")
	@ResponseBody
	public String saveStockBullish(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false) Long id) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser == null) {
				return "-1";
			}
			// 查询用户当天内是否看涨过某股票
			int paramCount = stockBullishService.countStockBullishByParams(id, fuUser.getId(), "timeFlag");
			if (paramCount == 0) {
				StockBullish bullish = new StockBullish();
				bullish.setFuUser(fuUser);
				bullish.setStockInfo(stockInfoService.get(id));
				bullish.setCreateTime(new Date());
				stockBullishService.save(bullish);
			}
			// 计算
			BigDecimal stockBullishCount = new BigDecimal(stockBullishService.countStockBullishByParams(id, null, null));
			BigDecimal allBullishCount = new BigDecimal(stockBullishService.countStockBullishByParams(null, null, null));
			BigDecimal result = stockBullishCount.multiply(new BigDecimal(100)).divide(allBullishCount, 2, BigDecimal.ROUND_HALF_UP);
			return result.toString() + "%";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/qdKline")
	public String qdKline(HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam(value = "id", required = false) Long id) {
		try {
			FuUser fuUser = (FuUser) request.getSession().getAttribute("fuUser");
			if (fuUser != null) {
				StockRecentOptional optional = stockRecentOptionalService.findByStockId(id);
				if (optional == null) {
					optional = new StockRecentOptional();
				}
				optional.setStockInfo(stockInfoService.get(id));
				optional.setFuUser(fuUser);
				optional.setCreateTime(new Date());
				stockRecentOptionalService.save(optional);
			}
			StockInfo stockInfo = stockInfoService.get(id);
			JSONArray allCommentList = stockCommentService.getStockCommentList(id, 0, 3);
			JSONArray goodCommentList = stockCommentService.getStockCommentList(id, 0, 0);
			JSONArray middleCommentList = stockCommentService.getStockCommentList(id, 0, 1);
			JSONArray badCommentList = stockCommentService.getStockCommentList(id, 0, 2);
			model.addAttribute("stockInfo", stockInfo);
			model.addAttribute("code", stockInfo.getStkType() + stockInfo.getCode());
			model.addAttribute("goodCount", stockCommentService.countStockComments(id, 0, null));
			model.addAttribute("middleCount", stockCommentService.countStockComments(id, 1, null));
			model.addAttribute("badCount", stockCommentService.countStockComments(id, 2, null));
			model.addAttribute("totalCount", stockCommentService.countStockComments(id, null, null));
			model.addAttribute("allSize", stockCommentService.countStockComments(id, null, 0));
			model.addAttribute("goodSize", stockCommentService.countStockComments(id, 0, 0));
			model.addAttribute("midSize", stockCommentService.countStockComments(id, 1, 0));
			model.addAttribute("badSize", stockCommentService.countStockComments(id, 2, 0));
			model.addAttribute("goodCommentList", goodCommentList);
			model.addAttribute("middleCommentList", middleCommentList);
			model.addAttribute("badCommentList", badCommentList);
			model.addAttribute("allCommentList", allCommentList);
			model.addAttribute("id", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "qdWeb/qdKline";
	}
}
