package com.xiaohe.qd.model;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

public class DialectForInkfish extends MySQL5Dialect {
	public DialectForInkfish() {
		super();
		registerHibernateType(Types.LONGVARCHAR, "text");
	}
}
