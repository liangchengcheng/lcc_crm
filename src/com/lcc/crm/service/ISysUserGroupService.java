package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.domain.SysUserGroup;

public interface ISysUserGroupService {
	public void saveSysUserGroup(SysUserGroup sysUserGroup);

	public List<SysUserGroup> findSysUserGroups(String name, String principal);

	public List<SysUserGroup> findSysUserGroups();

	/*
	 * 按条件进行查询，这里封装的是条件
	 */
	public List<SysUserGroup> findSysUserGroupsWithName(String name);

	public SysUserGroup findSysUserGroupById(int id);

	/*
	 * 修改部门信息
	 */
	public void updateSysUserGroup(SysUserGroup sysUserGroup);

	public void deleteSysUserGroupByIds(Integer[] Iids);
}
