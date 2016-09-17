package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.domain.SysMenu;

public interface ISysMenuService {

	public List<SysMenu> findAllSysMenus();

	/*
	 * 带缓存的方法
	 */
	public List<SysMenu> findAllSysMenusCache();
}
