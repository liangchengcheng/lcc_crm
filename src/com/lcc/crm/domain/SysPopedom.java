package com.lcc.crm.domain;

import java.io.Serializable;

public class SysPopedom implements Serializable {
	/*
	 * popedomModule VARCHAR(30), #模块名称 popedomPrivilege VARCHAR(30), #操作名称 sort
	 * INTEGER(11), #排序 title VARCHAR(200), #提示 popedomName VARCHAR(200), #标题
	 * remark TEXT, #说明
	 */
	private SysPopedomId id; // 主键

	public SysPopedomId getId() {
		return id;
	}

	public void setId(SysPopedomId id) {
		this.id = id;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPopedomName() {
		return popedomName;
	}

	public void setPopedomName(String popedomName) {
		this.popedomName = popedomName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private Integer sort;
	private String title;
	private String popedomName;
	private String remark;
}
