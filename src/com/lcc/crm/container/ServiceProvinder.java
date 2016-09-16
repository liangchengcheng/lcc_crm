package com.lcc.crm.container;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang.xwork.StringUtils;

public class ServiceProvinder {

	private static ServiceProvinderCore sc;
	
	//加载执行spring
	static {
		sc = new ServiceProvinderCore();
		sc.load("spring/applicationContext.xml");
	}
	
	public static Object getService(String beanName){
		Object bean= null;
		if (StringUtils.isBlank(beanName)) {
			throw new RuntimeException("您要访问的服务的名称不能为空啊");
		}
		//要是在sprin 的容器里面包含beanName
		if (sc.context.containsBean(beanName)) {
			bean= sc.context.getBean(beanName);
		}
		//要是不是在spring里面的话
		if (bean==null) {
			throw new RuntimeException("您要访问的服务不存在啊");
		}
		
		return bean;
	}
	
	
}
