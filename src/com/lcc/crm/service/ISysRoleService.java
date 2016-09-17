package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.bean.SysRoleSearch;
import com.lcc.crm.domain.SysRole;

public interface ISysRoleService {
	
	public void saveSysRole(SysRole sysRole);

	public List<SysRole> getAllSysRole();

	public List<SysRole> findSysRolesByName(SysRoleSearch sysRoleSearch);

	public void deleteSysUserGroupByIds(String[] ids);

	public SysRole getSysRoleById(String id);

	public void updateSysRole(SysRole sysRole);
}
