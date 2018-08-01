package com.xiaohe.qd.controller.qidianpc;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.StockInfo;
import com.xiaohe.qd.model.StockType;
import com.xiaohe.qd.service.StockTypeService;
import com.xiaohe.qd.util.ColorUtil;
import com.xiaohe.qd.util.WebUtil;

/**
 * 股票模块查询
 * 
 * @author han
 * @date 2016-09-06
 */
@Controller
@RequestMapping("/ai")
@Scope("prototype")
public class ModuleController extends BaseController {

	@Resource
	private StockTypeService stockTypeService;

	/**
	 * 进入到qdConcept页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qdConcept")
	public String qdConcept(HttpServletRequest request, Long id, Long pid) {
		pid = 1L; // 股票是概念板块的
		if (null == pid || pid != 1) {
			// 参数错误
		} else {
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			if (null != id) {
				// 通过查询板块的id 查询到板块下股票信息集合
				List<StockType> stockList1 = stockTypeService.getStockInfoById(id);
				for (StockType stockType : stockList1) {
					List<StockInfo> stockInfos = stockType.getStockInfos();
					for (StockInfo stockInfo : stockInfos) {
						Map<String, Object> map = new HashMap<String, Object>();
						BigDecimal liftRate = stockInfo.getLiftRate();
						float num = 0;
						if (null != liftRate) {
							num = (liftRate.floatValue());
						} else {
							num = 0;
						}
						map.put("infoId", stockInfo.getId());
						map.put("name", stockInfo.getName());
						map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
						// st 股票
						if (null != stockInfo.getStkType() && "st".equals(stockInfo.getStkType())) {
							map.put("color", ColorUtil.getSpecialColor(num));
						} else { // 一般股票
							map.put("color", ColorUtil.getGeneralColor(num));
						}
						list.add(map);
					}
				}
			} else {
				List<StockType> stockList = stockTypeService.getStockInfoByPid(pid);
				if (null != stockList && stockList.size() > 0) {
					for (StockType stockType : stockList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", stockType.getId());
						map.put("name", stockType.getName());

						BigDecimal liftRate = stockType.getLiftRate();
						if (null != liftRate) {
							float num = liftRate.floatValue();
							map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
							map.put("color", ColorUtil.getGeneralColor(num));
						}

						list.add(map);
					}
				}
			}
			obj.put("stockList", list);
			request.setAttribute("stockList", obj.toString());
			request.setAttribute("id", id);
		}
		return "qdWeb/qdConcept";
	}

	@RequestMapping("/ajaxQdConcept")
	@ResponseBody
	public String ajaxQdConcept(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			if (null != id) {
				// 通过查询板块的id 查询到板块下股票信息集合
				List<StockType> stockList1 = stockTypeService.getStockInfoById(id);
				for (StockType stockType : stockList1) {
					List<StockInfo> stockInfos = stockType.getStockInfos();
					for (StockInfo stockInfo : stockInfos) {
						Map<String, Object> map = new HashMap<String, Object>();
						BigDecimal liftRate = stockInfo.getLiftRate();
						float num = 0;
						if (null != liftRate) {
							num = (liftRate.floatValue());
						} else {
							num = 0;
						}
						map.put("infoId", stockInfo.getId());
						map.put("name", stockInfo.getName());
						map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
						// st 股票
						if (null != stockInfo.getStkType() && "st".equals(stockInfo.getStkType())) {
							map.put("color", ColorUtil.getSpecialColor(num));
						} else { // 一般股票
							map.put("color", ColorUtil.getGeneralColor(num));
						}
						list.add(map);
					}
				}
			} else {
				List<StockType> stockList = stockTypeService.getStockInfoByPid(1L);
				if (null != stockList && stockList.size() > 0) {
					for (StockType stockType : stockList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", stockType.getId());
						map.put("name", stockType.getName());

						BigDecimal liftRate = stockType.getLiftRate();
						if (null != liftRate) {
							float num = liftRate.floatValue();
							map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
							map.put("color", ColorUtil.getGeneralColor(num));
						}

						list.add(map);
					}
				}
			}
			obj.put("stockList", list);
			obj.put("id", id);
			request.setAttribute("stockList", obj.toString());

			writeJson(response, obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 进入到qdArea页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qdArea")
	public String qdArea(HttpServletRequest request, Long id, Long pid) {
		pid = 2L; // 股票是概念板块的
		if (null == pid || pid != 2) {
			// 参数错误
		} else {
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			if (null != id) {
				// 通过查询板块的id 查询到板块下股票信息集合
				List<StockType> stockList1 = stockTypeService.getStockInfoById(id);
				for (StockType stockType : stockList1) {
					List<StockInfo> stockInfos = stockType.getStockInfos();
					for (StockInfo stockInfo : stockInfos) {
						Map<String, Object> map = new HashMap<String, Object>();
						BigDecimal liftRate = stockInfo.getLiftRate();
						float num = 0;
						if (null != liftRate) {
							num = liftRate.floatValue();
						} else {
							num = 0;
						}
						map.put("infoId", stockInfo.getId());
						map.put("name", stockInfo.getName());
						map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
						// st 股票
						if (null != stockInfo.getStkType() && "st".equals(stockInfo.getStkType())) {
							map.put("color", ColorUtil.getSpecialColor(num));
						} else { // 一般股票
							map.put("color", ColorUtil.getGeneralColor(num));
						}
						list.add(map);
					}
				}
			} else {
				List<StockType> stockList = stockTypeService.getStockInfoByPid(pid);
				if (null != stockList && stockList.size() > 0) {
					for (StockType stockType : stockList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", stockType.getId());
						map.put("name", stockType.getName());

						BigDecimal liftRate = stockType.getLiftRate();
						if (null != liftRate) {
							float num = liftRate.floatValue();
							map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
							map.put("color", ColorUtil.getGeneralColor(num));
						}
						list.add(map);
					}
				}
			}
			obj.put("stockList", list);
			request.setAttribute("stockList", obj.toString());
			request.setAttribute("id", id);
		}
		return "qdWeb/qdArea";
	}

	@RequestMapping("/ajaxQdArea")
	@ResponseBody
	public String ajaxQdArea(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			JSONObject obj = new JSONObject();
			List<Object> list = new ArrayList<Object>();
			if (null != id) {
				// 通过查询板块的id 查询到板块下股票信息集合
				List<StockType> stockList1 = stockTypeService.getStockInfoById(id);
				for (StockType stockType : stockList1) {
					List<StockInfo> stockInfos = stockType.getStockInfos();
					for (StockInfo stockInfo : stockInfos) {
						Map<String, Object> map = new HashMap<String, Object>();
						BigDecimal liftRate = stockInfo.getLiftRate();
						float num = 0;
						if (null != liftRate) {
							num = (liftRate.floatValue());
						} else {
							num = 0;
						}
						map.put("infoId", stockInfo.getId());
						map.put("name", stockInfo.getName());
						map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
						// st 股票
						if (null != stockInfo.getStkType() && "st".equals(stockInfo.getStkType())) {
							map.put("color", ColorUtil.getSpecialColor(num));
						} else { // 一般股票
							map.put("color", ColorUtil.getGeneralColor(num));
						}
						list.add(map);
					}
				}
			} else {
				List<StockType> stockList = stockTypeService.getStockInfoByPid(2L);
				if (null != stockList && stockList.size() > 0) {
					for (StockType stockType : stockList) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("id", stockType.getId());
						map.put("name", stockType.getName());

						BigDecimal liftRate = stockType.getLiftRate();
						if (null != liftRate) {
							float num = liftRate.floatValue();
							map.put("liftRate", new DecimalFormat("0.00").format(liftRate) + "%");
							map.put("color", ColorUtil.getGeneralColor(num));
						}

						list.add(map);
					}
				}
			}
			obj.put("stockList", list);
			request.setAttribute("stockList", obj.toString());

			writeJson(response, obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/setCookie", produces = "text/html;charset=UTF-8")
	public String setCookie(HttpServletRequest request, HttpServletResponse response) {
		try {
			Cookie ticket = new Cookie("token_name", request.getParameter("ticket"));
			ticket.setPath("/");
			ticket.setMaxAge(Integer.parseInt(request.getParameter("expiry")));
			response.addCookie(ticket);

			String gotoURL = request.getParameter("gotoURL");
			if (gotoURL != null) {
				response.sendRedirect(gotoURL);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", produces = "text/html;charset=UTF-8")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			WebUtil.addCookie(response, "user_token", "", 1);
			WebUtil.addCookie(response, "token_name", "", 0);
			request.getSession().removeAttribute("fuUser");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/web/ai/qdIndex";
	}

	// 测试
	public static void main(String[] args) {
		String liftRate = "-0.41%";
		String str = liftRate.substring(0, liftRate.length() - 1);
		Integer num = (int) ((Float.parseFloat(str)) * 100);
		System.out.println(num);
	}
}
