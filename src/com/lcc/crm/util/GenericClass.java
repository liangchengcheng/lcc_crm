package com.lcc.crm.util;

import java.lang.reflect.ParameterizedType;

public class GenericClass {
	public static Class getGenericClass(Class clazz) {
		ParameterizedType type= (ParameterizedType) clazz.getGenericSuperclass();
		Class entityClass=(Class) type.getActualTypeArguments()[0];
		return entityClass;
	}
}
