package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.domain.SysMenuPrivilege;

public interface ISysMenuPrivilegeService {

	public void updateMenu(String roleId, String[] menuModules);

	public List<SysMenuPrivilege> findsysMenuPrivilegesById(String roleId);

	public List<SysMenuPrivilege> findAllSysMenuPrivileges();

	public List<SysMenuPrivilege> findAllSysMenuPrivilegesCache();
}
