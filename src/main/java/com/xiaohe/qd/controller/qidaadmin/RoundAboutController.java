package com.xiaohe.qd.controller.qidaadmin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaohe.qd.controller.BaseController;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.LiveJackpot;
import com.xiaohe.qd.model.LiveJackpotTime;
import com.xiaohe.qd.model.LiveLotteryInfo;
import com.xiaohe.qd.service.FuUserService;
import com.xiaohe.qd.service.LiveJackpotService;
import com.xiaohe.qd.service.LiveJackpotTimeService;
import com.xiaohe.qd.service.LiveLotteryInfoService;
import com.xiaohe.qd.util.DateUtil;

/**
 * 大转盘管理
 * @author han
 */
@Controller
@RequestMapping("/admin")
public class RoundAboutController extends BaseController {

	@Resource
	private LiveJackpotService jackpotService;
	@Resource
	private LiveJackpotTimeService timeService;
	@Resource
	private LiveLotteryInfoService lotteryService;
	@Resource
	private FuUserService fuUserService;

	/**
	 * 进入奖品池列表页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findJackpot" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	public String findJackpot(HttpServletRequest request) {
		return "roundabout/findJackpot";
	}

	/**
	 * ajax 获取奖品池数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/jackpotData" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String jackpotData(Integer page, Integer rows) {
		List<LiveJackpot> kackpotList = jackpotService.findAll((page - 1) * rows, rows);
		Integer totalCount = jackpotService.getCount();
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		for (LiveJackpot liveJackpot : kackpotList) {
			JSONObject obj = new JSONObject();
			obj.put("id", liveJackpot.getId());
			obj.put("name", liveJackpot.getName());
			obj.put("changes", liveJackpot.getChanges());
			obj.put("minAngle", liveJackpot.getMinAngle());
			obj.put("maxAngle", liveJackpot.getMaxAngle());
			obj.put("grade", liveJackpot.getGrade());
			obj.put("type", liveJackpot.getType() == 0 ? "虚拟" : "实物");
			obj.put("score", liveJackpot.getScore());
			obj.put("isDel", liveJackpot.getIsDel() == 0 ? "正在启用" : "不启用");
			obj.put("periods", liveJackpot.getPeriods());
			obj.put("createTime", DateUtil.showDateTime(liveJackpot.getCreateTime()));
			jsonArray.add(obj);
		}
		result.put("total", totalCount);
		result.put("rows", jsonArray);
		return JSONObject.toJSONString(result);
	}

	/**
	 * 编辑或者添加奖品
	 * 
	 * @param jackpot
	 * @return
	 */
	@RequestMapping(value = "/addjackpot" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addjackpot(LiveJackpot jackpot) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LiveJackpot lj = new LiveJackpot();
			if (null != jackpot.getId()) { // 修改
				lj = jackpotService.getById(jackpot.getId());
			} else {
				lj.setCreateTime(new Date());
				lj.setIsDel(0);
				lj.setPeriods(jackpot.getPeriods());
			}
			lj.setName(jackpot.getName());
			lj.setChanges(jackpot.getChanges());
			lj.setMinAngle(jackpot.getMinAngle());
			lj.setMaxAngle(jackpot.getMaxAngle());
			lj.setGrade(jackpot.getGrade());
			lj.setType(jackpot.getType());
			lj.setScore(jackpot.getScore());
			jackpotService.save(lj);
			result.put("success", true);
			result.put("msg", "保存成功！");
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

	@RequestMapping(value = "/delJackpot" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delJackpot(@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			jackpotService.updateDel(ids);
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

	/**
	 * 进入中奖人列表页
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/winInfo" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	public String winInfo(HttpServletRequest request) {
		List<LiveJackpot> jackpotList = jackpotService.findAll(0, 100);
		request.setAttribute("jackpotList", jackpotList);
		return "roundabout/winInfo";
	}

	/**
	 * 分页获取中奖数据
	 * 
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/winInfoData" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String winInfoData(Integer page, Integer rows) {
		List<LiveLotteryInfo> lotteryList = lotteryService.findAll((page - 1) * rows, rows);
		Integer totalCount = lotteryService.getCount();
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		for (LiveLotteryInfo lottery : lotteryList) {
			JSONObject obj = new JSONObject();
			obj.put("id", lottery.getId());
			obj.put("userName", lottery.getFuUser().getNickName());
			obj.put("phone", lottery.getFuUser().getPhone());
			obj.put("lotteryName", lottery.getLiveJackpot().getName());
			obj.put("isWin", lottery.getIsWin() == 0 ? "未中奖" : "已中奖");
			obj.put("spendIntegral", lottery.getSpendIntegral());
			obj.put("status", lottery.getStatus() == 0 ? "非选中用户" : "选中用户");
			obj.put("isReceive", lottery.getIsReceive() == 0 ? "未领取奖品" : "领取奖品");
			obj.put("drawTime", null != lottery.getDrawTime() ? DateUtil.showDateTime(lottery.getDrawTime()) : "");
			jsonArray.add(obj);
		}
		result.put("total", totalCount);
		result.put("rows", jsonArray);
		return JSONObject.toJSONString(result);
	}

	/**
	 * 编辑是否领取奖品
	 * 
	 * @param id
	 * @param isReceive
	 * @return
	 */
	@RequestMapping(value = "/editLottery" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editLottery(Long id, Integer isReceive) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			LiveLotteryInfo lottery = lotteryService.getById(id);
			lottery.setIsReceive(isReceive);
			lotteryService.save(lottery);
			result.put("success", true);
			result.put("msg", "领取成功！");
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("处理失败", e);
			}
			result.put("success", false);
			result.put("msg", "系统错误，请稍候再试！");
		}
		return JSONObject.toJSONString(result);
	}

	@RequestMapping(value = "/searchUser" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String searchUser(String userPhone) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (null == userPhone) {
				result.put("success", false);
				result.put("msg", "请输入手机号码");
				return JSONObject.toJSONString(result);
			}

			if (userPhone.toString().length() != 11) {
				result.put("success", false);
				result.put("msg", "请输入正确的手机号码");
				return JSONObject.toJSONString(result);
			}

			FuUser fuUser = fuUserService.findUserByAccount(userPhone.toString().trim());
			if (null == fuUser || "".equals(fuUser)) {
				result.put("success", false);
				result.put("msg", "您查询的用户不存在");
				return JSONObject.toJSONString(result);
			} else {
				result.put("success", true);
				result.put("userName", fuUser.getNickName());
				result.put("userId", fuUser.getId());
				return JSONObject.toJSONString(result);
			}
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
	 * 设置选中用户
	 * 
	 * @param userId
	 * @param lotteryId
	 * @return
	 */
	@RequestMapping(value = "/saveLottery" + URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveLottery(Long userId, Long lotteryId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (null == userId) {
				result.put("success", false);
				result.put("msg", "请选中用户");
				return JSONObject.toJSONString(result);
			}

			if (null == lotteryId) {
				result.put("success", false);
				result.put("msg", "请选择奖品");
				return JSONObject.toJSONString(result);
			}

			LiveLotteryInfo lottery = lotteryService.findByStatusAndReceive(userId, 1, 0);
			if (null != lottery) {
				result.put("success", false);
				result.put("msg", "该用户已经被设置");
				return JSONObject.toJSONString(result);
			}

			LiveLotteryInfo info = new LiveLotteryInfo();
			info.setFuUser(fuUserService.get(userId));
			info.setLiveJackpot(jackpotService.getById(lotteryId));
			info.setStatus(1);
			info.setIsReceive(0);
			info.setIsWin(1);
			lotteryService.save(info);

			result.put("success", true);
			result.put("msg", "设置成功!");
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
	 * 进入特殊时段页面
	 * @return
	 */ 
	@RequestMapping(value = "/timePage"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	public String timePage() {
		return "roundabout/timelist";
	}

	/**
	 * ajax 获取特殊时段数据列表
	 * @return
	 */
	@RequestMapping(value = "/timePageData"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String timePageData(Integer page, Integer rows) {
		List<LiveJackpotTime> timeList = timeService.findAll((page-1) * rows, rows);
		Integer totalCount = timeService.getCount();
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		for (LiveJackpotTime jackpotList : timeList) {
			JSONObject obj = new JSONObject();
			obj.put("id", jackpotList.getId());
			obj.put("name", jackpotList.getLivejackpot().getName());
			obj.put("changes", jackpotList.getChanges());
			obj.put("startTime", DateUtil.showDateTime(jackpotList.getStartTime()));
			obj.put("endTime", DateUtil.showDateTime(jackpotList.getEndTime()));
			obj.put("createTime", DateUtil.showDate(jackpotList.getCreateTime()));
			obj.put("isDel", jackpotList.getIsDel() == 0 ? "启用" : "不启用");
			jsonArray.add(obj);
		}
		result.put("total", totalCount);
		result.put("rows", jsonArray);
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 获取当前的奖品池列表
	 * @return
	 */
	@RequestMapping(value = "/getJackpotData"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getJackpotData() {
		List<LiveJackpot> kackpotList = jackpotService.findNowPeriods();
		Map<String, Object> result = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		for (LiveJackpot liveJackpot : kackpotList) {
			JSONObject obj = new JSONObject();
			obj.put("jackpotId", liveJackpot.getId());
			obj.put("name", liveJackpot.getName());
			jsonArray.add(obj);
		}
		result.put("rows", jsonArray);
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 添加特殊时段的的奖品池
	 * @return
	 */
	@RequestMapping(value = "/addJackpotTime"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addJackpotTime(Long jackpotId, Integer changes, String startTime, String endTime) {
		JSONObject obj = new JSONObject();
		if (null == changes) {
			obj.put("success", 0);
			obj.put("msg", "请输入概率");
			return obj.toString();
		}
		if (null == jackpotId) {
			obj.put("success", 0);
			obj.put("msg", "请选择奖品");
			return obj.toString();
		}
		if (null == startTime) {
			obj.put("success", 0);
			obj.put("msg", "请输入开始时间");
			return obj.toString();
		}
		Date st = DateUtil.getDateFromString(startTime, "yyyy-MM-dd HH:mm:ss");
		if (null == endTime) {
			obj.put("success", 0);
			obj.put("msg", "请输入结束时间");
			return obj.toString();
		}
		Date et = DateUtil.getDateFromString(endTime, "yyyy-MM-dd HH:mm:ss");
		LiveJackpot liveJackpot = jackpotService.getById(jackpotId);
		if (null == liveJackpot) {
			obj.put("success", 0);
			obj.put("msg", "请重新选择奖品");
			return obj.toString();
		}
		// 查询这个奖品是否已经被添加并且没有在删除状态
		List<LiveJackpotTime> timeList = timeService.findByJackIdAndIsDel(jackpotId, 0);
		if (null != timeList && timeList.size() > 0) {
			obj.put("success", 0);
			obj.put("msg", "不要重复添加奖品, 请重新选择奖品");
			return obj.toString();
		}
		LiveJackpotTime jackpotTime = new LiveJackpotTime();
		jackpotTime.setChanges(changes);
		jackpotTime.setStartTime(st);
		jackpotTime.setEndTime(et);
		jackpotTime.setLivejackpot(liveJackpot);
		jackpotTime.setCreateTime(new Date());
		jackpotTime.setIsDel(0);
		int result = timeService.save(jackpotTime);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("msg", "添加失败, 请联系管理员");
			return obj.toString();
		} else if (1 == result) {
			obj.put("success", 1);
			obj.put("msg", "添加成功");
			return obj.toString();
		}
		return null;
	}
	
	/**
	 * 编辑特殊时段的的奖品池
	 * @return
	 */
	@RequestMapping(value = "/editJackpotTime"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editJackpotTime(Long id, Long jackpotId, Integer changes, String startTime, String endTime) {
		JSONObject obj = new JSONObject();
		if (null == id) {
			obj.put("success", 0);
			obj.put("msg", "非法请求");
			return obj.toString();
		}
		if (null == changes) {
			obj.put("success", 0);
			obj.put("msg", "请输入概率");
			return obj.toString();
		}
		if (null == jackpotId) {
			obj.put("success", 0);
			obj.put("msg", "请选择奖品");
			return obj.toString();
		}
		if (null == startTime) {
			obj.put("success", 0);
			obj.put("msg", "请输入开始时间");
			return obj.toString();
		}
		Date st = DateUtil.getDateFromString(startTime, "yyyy-MM-dd HH:mm:ss");
		if (null == endTime) {
			obj.put("success", 0);
			obj.put("msg", "请输入结束时间");
			return obj.toString();
		}
		Date et = DateUtil.getDateFromString(endTime, "yyyy-MM-dd HH:mm:ss");
		LiveJackpot liveJackpot = jackpotService.getById(jackpotId);
		if (null == liveJackpot) {
			obj.put("success", 0);
			obj.put("msg", "请重新选择奖品");
			return obj.toString();
		}
		// 查询这个奖品是否已经被添加并且没有在删除状态
		List<LiveJackpotTime> timeList = timeService.findByJackIdAndIsDel(jackpotId, 0);
		if (null != timeList && timeList.size() > 0) {
			for (LiveJackpotTime liveJackpotTime : timeList) {
				if (liveJackpotTime.getId() != id) {
					obj.put("success", 0);
					obj.put("msg", "不要重复添加奖品, 请重新选择奖品");
					return obj.toString();
				}
			}
		}
		
		LiveJackpotTime potTime = timeService.getById(id);
		potTime.setChanges(changes);
		potTime.setStartTime(st);
		potTime.setEndTime(et);
		potTime.setLivejackpot(liveJackpot);
		int result = timeService.save(potTime);
		if (0 == result) {
			obj.put("success", 0);
			obj.put("msg", "添加失败, 请联系管理员");
			return obj.toString();
		} else if (1 == result) {
			obj.put("success", 1);
			obj.put("msg", "添加成功");
			return obj.toString();
		}
		return null;
	}
	
	/**
	 * 设置特殊时段的的奖品池是否启用
	 * @return
	 */
	@RequestMapping(value = "/delJackpotTime"+URL_SUFFIX, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delJackpotTime(@RequestParam("ids[]") List<Long> ids, Integer isDel) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			timeService.updateDel(ids, isDel);
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
