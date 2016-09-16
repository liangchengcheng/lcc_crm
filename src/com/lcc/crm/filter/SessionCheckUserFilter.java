package com.lcc.crm.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lcc.crm.domain.SysUser;
import com.lcc.crm.util.SessionUtils;

public class SessionCheckUserFilter implements Filter{

	private List list;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain china)
			throws IOException, ServletException {
		HttpServletRequest req =(HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path=req.getServletPath();
		if (list!=null&&list.contains(path)) {
			china.doFilter(req, resp);
			return;
		}
		
		//过滤用户的登录状态，且在开始的地方排除那些地址
		SysUser sysUser = SessionUtils.getSysUserFromSession(req);
		if (sysUser!=null) {
			china.doFilter(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath());
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		list = new ArrayList<>();
		list.add("/image.jsp");
		list.add("/index.jsp");
		list.add("/WEB-INF/page/login.jsp");
		list.add("/user/sysUserAction_isLogin");
		
	}

}
