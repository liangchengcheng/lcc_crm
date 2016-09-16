package com.lcc.crm.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/*
 * 自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {
	String module();					//模块名称
	String privilege();					//操作名称
}
