package com.lcc.crm.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.orm.jpa.EntityManagerHolder;

import com.lcc.crm.anotation.Limit;
import com.lcc.crm.domain.SysUser;
import com.lcc.crm.util.SessionUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LimitInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// 获取的请求的action对象
		Object action = invocation.getAction();
		// 获取请求的方法名称
		String methodName = invocation.getProxy().getMethod();
		// 获取action中的方法的封装的类。action中方法没有参数
		Method method = action.getClass().getMethod(methodName, null);
		//获取request对象
		HttpServletRequest request =ServletActionContext.getRequest();
		//检测注解
		boolean flag= 
		return null;
	}
	
	private boolean isCheckLimit(HttpServletRequest request,Method method){
		//要是用户登录的权限组的id+注解上信息存在表中flog就是true
		boolean flag = false;
		if (method == null) {
			return false;
		}
		//获取当前登录的用户
		SysUser sysUser = SessionUtils.getSysUserFromSession(request);
		if (sysUser==null) {
			return false;
		}
		
		//获取当前的登录的用户的权限的组的id
		String roleId = sysUser.getSysRole().getId();
		//处理注解
		boolean isAnnotation = method.isAnnotationPresent(Limit.class);
		if (!isAnnotation) {
			return false;
		}
		//存在注解的话
		Limit limit= method.getAnnotation(Limit.class);
		//获取注解上的值
		String module=limit.module();
		//获取操作的名称
		String privilege = limit.privilege();
		//检车ysys_popedom_privilege上所有的数据
		
		
		
		
		
		return false;
	}

}
