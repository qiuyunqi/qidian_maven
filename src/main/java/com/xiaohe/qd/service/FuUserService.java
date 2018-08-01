package com.xiaohe.qd.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuParameterDao;
import com.xiaohe.qd.dao.FuRateDao;
import com.xiaohe.qd.dao.FuUserDao;
import com.xiaohe.qd.dao.HhrPromoteParameterDao;
import com.xiaohe.qd.dao.HhrStatDao;
import com.xiaohe.qd.dao.HhrStatDetailDao;
import com.xiaohe.qd.model.FuParameter;
import com.xiaohe.qd.model.FuRate;
import com.xiaohe.qd.model.FuSmsLog;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.HhrPromoteParameter;
import com.xiaohe.qd.model.HhrStat;
import com.xiaohe.qd.model.HhrStatDetail;
import com.xiaohe.qd.model.SysAdmin;
import com.xiaohe.qd.util.CommonUtil;
import com.xiaohe.qd.util.IP4;
import com.xiaohe.qd.util.WebUtil;

@Service
public class FuUserService extends BaseService {
	@Resource
	private FuUserDao fuUserDao;
	@Resource
	private FuParameterDao fuParameterDao;
	@Resource
	private FuRateDao fuRateDao;
	@Resource
	private HhrStatDao hhrStatDao;
	@Resource
	private HhrStatDetailDao hhrStatDetailDao;
	@Resource
	private HhrPromoteParameterDao hhrPromoteParameterDao;
	@Resource
	private FuSmsLogService fuSmsLogService;
	@Resource
	private HhrStatService hhrStatService;

	public FuUser get(Long id) {
		return fuUserDao.get(id);
	}

	public void save(FuUser entity) {
		fuUserDao.save(entity);
	}

	public FuUser findUserByWeixinCode(String weixinCode) {
		return fuUserDao.findUserByWeixinCode(weixinCode);
	}

	public FuUser findUserByAccount(String accountName) {
		return fuUserDao.findUserByAccount(accountName);
	}

	public FuUser findFuUserById(Long userId) {
		return fuUserDao.findFuUserById(userId);
	}

	public FuUser findUserByRegInvitationcode(String invitation_code) {
		return fuUserDao.findUserByRegInvitationcode(invitation_code);
	}

	public Integer countInvitationCode(String code) {
		return fuUserDao.countInvitationCode(code);
	}

	public void updatePartnerNum(HhrStat stat) {
		if (stat.getHhrParentID() != null && stat.getHhrParentID() != 0) {
			HhrStat upStat = hhrStatDao.findHhrStatByUser(stat.getHhrParentID());
			if (upStat != null) {
				if (upStat.getSecondaryPartnerNum() == null) {
					upStat.setSecondaryPartnerNum(1);
				} else {
					upStat.setSecondaryPartnerNum(upStat.getSecondaryPartnerNum() + 1);
				}
				hhrStatDao.save(upStat);
			}
			updatePartnerNum(upStat);
		}
	}

	public void saveRate(FuUser user) {
		fuUserDao.save(user);
		// 注册的时候给每个用户设置默认费率
		FuParameter fuParameter = fuParameterDao.findParameter();
		if (null != fuParameter) {
			FuRate fuRate = new FuRate();
			fuRate.setFuUser(user);
			fuRate.setWarnlinePercent(fuParameter.getWarnlinePercent());
			fuRate.setFlatlinePercent(fuParameter.getFlatlinePercent());
			fuRate.setCommissionPercent(fuParameter.getCommissionPercent());
			fuRate.setFeeDay(fuParameter.getFeeDay());
			fuRate.setFeePercent(fuParameter.getFeePercent());
			fuRate.setInterestPercent(fuParameter.getInterestPercent());
			fuRate.setShortNum(fuParameter.getShortNum());
			fuRate.setLongNum(fuParameter.getLongNum());
			SysAdmin fuAdmin = new SysAdmin();
			fuAdmin.setId(1L);
			fuRate.setSysAdmin(fuAdmin);
			fuRate.setCreatetime(new Date());
			fuRate.setUpdateuser(1L);
			fuRate.setUpdatetime(new Date());
			fuRateDao.save(fuRate);
		}
	}

	// 微信存储用户
	public void saveWeiXinUser(HttpServletRequest request, HttpServletResponse response, String phone, String password, String nickName, String phoneCode, String invitationCode, int regType, String avatar, String openId) {
		FuUser user = new FuUser();
		user.setAccountName(phone);
		user.setPhone(phone);
		user.setPassword(password);
		user.setNickName(nickName);
		user.setUserAvatar(avatar); // 设置用户头像
		user.setIntegral(BigDecimal.ZERO);// 积分
		user.setPhoneCode(phoneCode);// 注册验证码
		user.setIsAcrossCabin(0);// 是否穿仓
		user.setIsPhoneReg(regType);// 网站注册
		user.setSafeMoney(new BigDecimal(0.00));// 总风险保证金
		user.setMatchMoney(new BigDecimal(0.00));// 总配资金额
		user.setFreezeMoney(new BigDecimal(0.00));// 冻结金额
		user.setExtendPersonNum(0);// 推广人数
		user.setBorrowPersonNum(0);// 配资用户
		user.setExchangeMoney(new BigDecimal(0.00));// 已兑佣金
		user.setCommissionTotal(new BigDecimal(0.00));// 佣金总额
		user.setAccountBalance(new BigDecimal(0.00));// 账户余额
		user.setAccountTotalMoney(new BigDecimal(0.00));// 总资产
		user.setIsBindEmail(0);// 邮箱未绑定
		user.setIsCheckCard(0);// 未实名认证
		user.setVisitIp(1);// 访问IP数
		user.setVisitNum(1);// 访问次数
		user.setSafeLevel(1);// 安全等级
		user.setRegisterTime(new Date());
		user.setIntegral(new BigDecimal(0.00));
		if (null != openId && !"".equals(openId)) {
			user.setWeixinCode(openId);
		}
		saveRate(user); // 新注册用户同时创建一条个人费率
		// 默认登录
		String token = UUID.randomUUID().toString();
		WebUtil.addCookie(response, "user_token", token, 8640000);
		user.setLoginToken(token);
		user.setLoginTime(new Date());
		user.setState(1);
		user.setRegisterIp(IP4.getIP4(request));// 访问IP

		// 产生随机邀请码
		while (true) {
			double rand = new Random().nextDouble();
			String invitcode = new String(rand + "").substring(2, 14);
			Integer count = countInvitationCode(invitcode);
			if (count < 1) {
				user.setInvitationCode(invitcode);
				break;
			}
		}
		// 确定上级用户
		user.setHhrParentID(findUserByRegInvitationcode(invitationCode).getId());
		user.setRecommend(findUserByRegInvitationcode(invitationCode));// 权属人
		user.setLastLoginIp(IP4.getIP4(request));
		user.setHhrLevel(findUserByRegInvitationcode(invitationCode).getHhrLevel() + 1);// 合伙人等级为上级等级+1
		save(user);
		// 统计表信息写入
		HhrStat hhrStat = new HhrStat();
		hhrStat.setFuUser(user);
		hhrStat.setStatDate(new Date());
		hhrStat.setHhrParentID(user.getHhrParentID());
		hhrStatDao.save(hhrStat);

		FuUser parentUser = findFuUserById(user.getHhrParentID());
		if (null != parentUser) {
			// 上级合伙人信息
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", user.getHhrParentID());
			HhrStat parentStat = hhrStatDao.findStatDataByMap2(map);
			if (null != parentStat) {
				if (parentStat.getFirstlyPartnerNum() == null) {
					parentStat.setFirstlyPartnerNum(1);
				} else {
					parentStat.setFirstlyPartnerNum(parentStat.getFirstlyPartnerNum() + 1);
				}
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);

				// 上级合伙人明细
				HhrStatDetail parentStatDetail = hhrStatDetailDao.findHhrStatDetailByUserAndDate(parentUser.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				if (parentStatDetail != null) {
					parentStatDetail.setExtendPersonNew(parentStatDetail.getExtendPersonNew() == null ? 1 : (parentStatDetail.getExtendPersonNew() + 1));
				} else {
					parentStatDetail = new HhrStatDetail();
					parentStatDetail.setFuUser(parentUser);
					if (parentUser.getHhrParentID() == Long.parseLong("0") || parentUser.getHhrParentID() == null) {
						parentStatDetail.setHhrParentID(0L);
					}
					parentStatDetail.setExtendPersonNew(1);
					parentStatDetail.setCreateDate(new Date());
				}
				parentStatDetail.setStatDate(new Date());
				hhrStatDetailDao.save(parentStatDetail);

				// 上级的上级合伙人信息
				HhrStat ppStat = hhrStatDao.findHhrStatByUser(parentStat.getHhrParentID());
				if (ppStat != null) {
					if (ppStat.getSecondaryPartnerNum() == null) {
						ppStat.setSecondaryPartnerNum(1);
					} else {
						ppStat.setSecondaryPartnerNum(ppStat.getSecondaryPartnerNum() + 1);
					}
					ppStat.setStatDate(new Date());
					hhrStatDao.save(ppStat);
					// 从这一层用户开始往上回溯, 每个用户的次级合伙人都+1
					updatePartnerNum(ppStat);
				}
			} else {
				parentStat = new HhrStat();
				parentStat.setFuUser(parentUser);
				parentStat.setFirstlyPartnerNum(1); // 一级会员数目
				parentStat.setExtendPersonNew(1); // 今日新增会员
				parentStat.setStatDate(new Date());
				hhrStatDao.save(parentStat);
			}

			// 判断活动是否已开启,并且活动费用余额是否足够, 暂定50
			HhrPromoteParameter param = hhrPromoteParameterDao.findParameter();
			if (null != param) {
				if (param.getIsOpen() == 1 && param.getTotalMoney().compareTo(new BigDecimal(50)) == 1) {
					String[] moneyArr = param.getLineMoney().split(",");
					// 从数组中随机抽取一个值,作为初始发钱金额
					int rand = new Random().nextInt(moneyArr.length);
					BigDecimal initialMoney = new BigDecimal(moneyArr[rand]);
					hhrStatService.updateHhrIncome(user.getId(), initialMoney, 1, null);
					param.setTotalMoney(param.getTotalMoney().subtract(initialMoney));
					hhrPromoteParameterDao.save(param);
				}
			}
		}

		DecimalFormat format = new DecimalFormat("0000");
		String code = format.format(Math.random() * 9999);
		String message;
		try {
			message = URLDecoder.decode("您的合伙人APP密码是:" + code + "，请妥善保管。", "UTF-8");
			FuSmsLog log = new FuSmsLog();
			log.setContent(message);
			log.setPrio(1);
			log.setReason("发送APP密码");
			log.setDestination(phone);
			log.setPlanTime(new Date());
			log.setType(1);
			log.setRegCode(code);
			log.setState(0);
			fuSmsLogService.save(log);
			code = CommonUtil.getMd5(code);
			user.setPassword(code);
			save(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public FuUser findLoginByToken(String token) {
		return fuUserDao.findLoginByToken(token);
	}

	public Integer getCount(Map<String, Object> map){
		return fuUserDao.getCount(map);
	}
	
	public List<FuUser> findList(int i, int pageSize, Map<String, Object> map) {
		return fuUserDao.findList(i, pageSize, map);
	}
	
}
