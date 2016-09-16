package com.lcc.crm.container;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 该类的主要目的就是加载spring的文件，因为这个采取的
 * 配置的方式，不是注解的方式
 * @author lcc
 *
 */
public class ServiceProvinderCore {
	protected ApplicationContext context;
	
	public void load(String filename){
		context= new ClassPathXmlApplicationContext(filename);
	}

}
