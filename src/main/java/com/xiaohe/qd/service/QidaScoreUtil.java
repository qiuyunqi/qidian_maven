package com.xiaohe.qd.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.xiaohe.qd.dao.FuDictionaryDao;
import com.xiaohe.qd.dao.QidaScoreDao;
import com.xiaohe.qd.model.FuUser;
import com.xiaohe.qd.model.QidaScore;

/**
 * 
 * @description 产生资金明细
 * 
 */

@Service
public class QidaScoreUtil extends BaseService {
	@Resource
	private QidaScoreDao qidaScoreDao;
	@Resource
	private FuDictionaryDao fuDictionaryDao;

	/**
	 * 奇答产生新的积分明细
	 * 
	 * @return
	 */
	public void saveScoreByQd(FuUser user, Integer dictionaryId, BigDecimal scoreNew, BigDecimal money, BigDecimal totalScore, Integer isIncome) {
		DecimalFormat sdf = new DecimalFormat("#,###,##0.00");
		QidaScore score=new QidaScore();
		score.setFuUser(user);
		score.setFuDictionary(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))));
		score.setScore(scoreNew);
		score.setMoney(money);
		score.setTotalScore(totalScore);// 操作后账户总积分
		score.setOrderNum(String.valueOf((int)((Math.random()*1000000+100000))));// 随机产生订单号
		score.setCreateTime(new Date());
		score.setIsIncome(isIncome);//0支出，1收入
		score.setComment(fuDictionaryDao.get(Long.valueOf(String.valueOf(dictionaryId))).getName() + sdf.format(scoreNew) + "积分");
		qidaScoreDao.save(score);
	}
}
