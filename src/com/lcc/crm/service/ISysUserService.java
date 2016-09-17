package com.lcc.crm.service;

import java.util.List;

import com.lcc.crm.bean.SysUserSearch;
import com.lcc.crm.domain.SysUser;

public interface ISysUserService {
	/*
	 * 通过用户名和密码查询用户信息
	 */
	public SysUser findSysUserByNameAndPassword(String name, String password);

	public void saveSysUser(SysUser sysUser);

	public List<SysUser> findSysUsersByCondition(SysUserSearch sysUserSearch);

	public List<SysUser> getAllSysUsers();

	public void deleteSysUsersByIds(Integer[] Iids);

	public void enable(Integer[] Iids);

	public void disable(Integer[] Iids);

	public SysUser getSysUserById(Integer id);

	public void updateSysUser(SysUser sysUser);

	public List<SysUser> findAllSysUsers();
}
