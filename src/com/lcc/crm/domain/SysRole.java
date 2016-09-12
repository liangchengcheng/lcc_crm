package com.lcc.crm.domain;

import java.io.Serializable;

public class SysRole implements Serializable {
	private String id;
	private String name;
	private String remark;
	
	//一个角色包含多个用户
	//private Set<SysUser> users=new HashSet<SysUser>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
