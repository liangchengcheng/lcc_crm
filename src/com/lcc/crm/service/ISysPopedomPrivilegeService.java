package com.lcc.crm.service;

import java.util.List;

import com.lcc.crm.domain.SysPopedomPrivilege;

public interface ISysPopedomPrivilegeService {

	public void updatePopedom(String roleId, String[] models);

	public List<SysPopedomPrivilege> findSysPopedomPrivilegesByRoleId(String roleId);

	public List<SysPopedomPrivilege> findAllSysPopedomPrivileges();

	/**
	 * 查询所有sys_popedom_privilege中的数据，启用二级缓存
	 * @return
	 */
	public List<SysPopedomPrivilege> findAllSysPopedomPrivilegesCache();

}
