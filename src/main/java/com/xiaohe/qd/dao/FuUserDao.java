package com.xiaohe.qd.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.FuUser;

@Repository
public class FuUserDao extends BaseDAO<FuUser, Long> {
	public FuUser findUserByWeixinCode(String weixinCode) {
		String hql = " from FuUser where state=1 and weixinCode=?";
		return this.findUniqueByHQL(hql, weixinCode);
	}

	public FuUser findUserByAccount(String accountName) {
		String hql = " from FuUser where state=1 and accountName=? or phone=?";
		return this.findUniqueByHQL(hql, accountName, accountName);
	}

	public FuUser findFuUserById(Long userId) {
		String hql = " from FuUser where id = ? ";
		return this.findUniqueByHQL(hql, userId);
	}

	public FuUser findUserByRegInvitationcode(String invitation_code) {
		String hql = " from FuUser where  invitationCode=? ";
		return this.findUniqueByHQL(hql, invitation_code);
	}

	public Integer countInvitationCode(String code) {
		List<Object> params = new ArrayList<Object>();
		String hql = " from FuUser where state=1 ";
		if (code != null) {
			params.add(code);
			hql = hql + " and invitationCode=? ";
		}
		return this.countQueryResult(hql, params);
	}

	public FuUser findLoginByToken(String token) {
		String hql = " from FuUser where state=1 and loginToken=? ";
		return this.findUniqueByHQL(hql, token);
	}

	public Integer getCount(Map<String, Object> map) {
		String hql = "from FuUser where 1 = 1 ";
		if (map.get("qidaRank") != null) {
			hql = hql + " and qidaRank = " + map.get("qidaRank");
		}
		return this.countQueryResult(hql);
	}

	public List<FuUser> findList(int i, int pageSize, Map<String, Object> map) {
		String hql = "from FuUser where 1 = 1 ";
		if (map.get("qidaRank") != null) {
			hql = hql + " and qidaRank = " + map.get("qidaRank");
		}
		if (map.get("answerUserId") != null){
			hql = hql + " and id != " + map.get("answerUserId");
		}
		if (map.get("qidaIntegral") != null) {
			hql = hql + " order by qidaIntegral desc ";
		} else {
			hql = hql + " order by id desc ";
		}
		return this.findListByHQL(i, pageSize, hql, null);
	}

}
