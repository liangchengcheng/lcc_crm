package com.lcc.crm.domain;

import java.io.Serializable;

public class SysUserGroup implements Serializable {

	private Integer id;										//编号
	private String remark;									//备注
	private String name;									//部门名称
	private String principal;								//部门负责人
	private String incumbent;								//部门职能
	
	//一个部门包含多个用户 
	//private Set<SysUser> users=new HashSet<SysUser>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getIncumbent() {
		return incumbent;
	}
	public void setIncumbent(String incumbent) {
		this.incumbent = incumbent;
	}
}

