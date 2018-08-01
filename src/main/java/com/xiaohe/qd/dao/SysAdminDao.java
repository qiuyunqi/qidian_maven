package com.xiaohe.qd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xiaohe.qd.model.SysAdmin;

/**
 * 
 * @description 自动生成 daoImpl
 * 
 * @author 弘威
 */
@Repository
public class SysAdminDao extends BaseDAO<SysAdmin, Long> {

	public SysAdmin findAdminByAccount(String account) {
		String hql = " from SysAdmin where state=1 and account=? ";
		return this.findUniqueByHQL(hql, account);
	}

	public SysAdmin findLoginByToken(String token) {
		String hql = " from SysAdmin where state=1 and loginToken=? ";
		return this.findUniqueByHQL(hql, token);
	}

	public List<Object[]> findList(int i, int pageSize, Map<String, Object> map) {
		String sql = "SELECT a.id, a.account, a.name, a.type, a.job_desc, ";
		sql += "DATE_FORMAT(a.create_time,'%Y-%m-%d %T'), DATE_FORMAT(a.update_login_time,'%Y-%m-%d %T'), ";
		sql += "r.rolename, a.password,  a.email, a.phone ";
		sql += "FROM qd_sys_admin a LEFT JOIN qd_sys_user_role u ON a.id=u.adminid LEFT JOIN qd_sys_role r ON r.id=u.roleid WHERE a.state=1 ";
		if (map.get("account") != null) {
			sql = sql + " and a.account like '%" + map.get("account") + "%'";
		}
		if (map.get("name") != null) {
			sql = sql + " and a.name like '%" + map.get("name") + "%'";
		}
		sql = sql + " order by a.id desc";
		if (this.findListBySql(i, pageSize, sql, null).size() > 0) {
			return this.findListBySql(i, pageSize, sql, null);
		} else {
			return null;
		}
	}

	public Integer getCount(Map<String, Object> map) {
		StringBuilder hqlB = new StringBuilder();
		hqlB.append("from SysAdmin where state=1");
		if (map.get("account") != null) {
			hqlB.append(" and account like '%" + map.get("account") + "%'");
		}
		if (map.get("name") != null) {
			hqlB.append(" and name like '%" + map.get("name") + "%'");
		}
		hqlB.append(" order by id desc");
		return this.countQueryResult(hqlB.toString());
	}

}
