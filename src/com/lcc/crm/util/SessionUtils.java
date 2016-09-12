package com.lcc.crm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.lcc.crm.domain.SysUser;

public class SessionUtils {

	/**
	 * 处理验证码
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isCheckNum(HttpServletRequest request) {
		// 获取存在的session
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}

		String CHECK_NUMBER_KEY = (String) session.getAttribute("CHECK_NUMBER_KEY");
		if (StringUtils.isBlank(CHECK_NUMBER_KEY)) {
			return false;
		}

		// 获取jsp页面文本框的值
		String saved = request.getParameter("checkNum");
		if (StringUtils.isAllLowerCase(saved)) {
			return false;
		}
		return CHECK_NUMBER_KEY.equalsIgnoreCase(saved);
	}

	/**
	 * 设置session
	 * @param request
	 * @param sysUser
	 */
	public static void setSysIserToSession(HttpServletRequest request, SysUser sysUser) {
		request.getSession().setAttribute("sysUser", sysUser);
	}

	/**
	 * 获取session
	 * @param request
	 * @return
	 */
	public static SysUser getSysUserFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");
		return sysUser;
	}
}
