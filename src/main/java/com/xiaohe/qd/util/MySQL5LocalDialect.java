package com.xiaohe.qd.util;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class MySQL5LocalDialect extends MySQL5InnoDBDialect{   //继承链接mysql的类
 public MySQL5LocalDialect() {
  super();
  registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING,"convert(?1 using ?2)"));
 }
}
