package com.xiaohe.qd.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaohe.qd.dao.FuDictionaryDao;
import com.xiaohe.qd.dao.FuMoneyDetailDao;
import com.xiaohe.qd.model.FuBankCard;
import com.xiaohe.qd.model.FuMoneyDetail;
import com.xiaohe.qd.model.FuProgram;
import com.xiaohe.qd.model.FuUser;

/**
 * 
 * @description 产生资金明细
 * 
 */

@Service
public class MoneyDetailUtil extends BaseService {
	@Resource
	private FuMoneyDetailDao fuMoneyDetailDao;
	@Resource
	private FuDictionaryDao fuDictionaryDao;

	/**
	 * 合伙人产生新的明细
	 * 
	 * @return
	 */
	public void saveNewFuMoneyDetail(FuUser user, FuProgram program, FuBankCard bankCard, String rechargeType, Integer dictionaryId, BigDecimal money, BigDecimal afterMoney, Boolean isIncome) {
		DecimalFormat sdf = new DecimalFormat("#,###,##0.00");
		FuMoneyDetail detail = new FuMoneyDetail();
		detail.setFuUser(user);
		detail.setFuDictionary(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))));
		detail.setFuProgram(program);
		detail.setFuBankCard(bankCard);
		// 针对充值存入 银行信息和支付宝账号
		if (rechargeType != null) {
			if (rechargeType.contains("银行")) {
				detail.setRechargeType(rechargeType);
			} else {
				detail.setRechargeType("支付宝");
			}
		}
		detail.setMoney(money);
		detail.setAccountBalanceAfter(afterMoney);// 操作后账户余额
		detail.setTime(new Date());
		detail.setIsIncome(isIncome);
		detail.setComment(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))).getName() + sdf.format(money) + "元");
		detail.setIsCheck(0);// 未查看
		fuMoneyDetailDao.save(detail);
	}

	/**
	 * 奇答产生新的积分明细
	 * 
	 * @return
	 */
	public void saveNewFuMoneyDetailByQd(FuUser user, Integer dictionaryId, BigDecimal money, BigDecimal afterMoney, Boolean isIncome) {
		DecimalFormat sdf = new DecimalFormat("#,###,###");
		FuMoneyDetail detail = new FuMoneyDetail();
		detail.setFuUser(user);
		detail.setFuDictionary(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))));
		detail.setFuProgram(null);
		detail.setFuBankCard(null);
		detail.setRechargeType(null);
		detail.setMoney(money);
		detail.setAccountBalanceAfter(afterMoney);// 操作后账户余额
		detail.setTime(new Date());
		detail.setIsIncome(isIncome);
		detail.setComment(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))).getName() + sdf.format(money) + "积分");
		detail.setIsCheck(0);// 未查看
		fuMoneyDetailDao.save(detail);
	}
}
