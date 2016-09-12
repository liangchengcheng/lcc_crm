package com.lcc.crm.domain;

import java.io.Serializable;

public class SysPopedomPrivilege implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private SysPopedomPrivilegeId id;

	public SysPopedomPrivilegeId getId() {
		return id;
	}

	public void setId(SysPopedomPrivilegeId id) {
		this.id = id;
	}
}
