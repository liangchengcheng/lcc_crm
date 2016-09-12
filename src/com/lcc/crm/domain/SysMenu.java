package com.lcc.crm.domain;

import java.io.Serializable;

public class SysMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SysMenuId id;

	public SysMenuId getId() {
		return id;
	}

	public void setId(SysMenuId id) {
		this.id = id;
	}

	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String menuName;
	private String title;
	private String url;
	private String target;
	private String icon;
	private String remark;
}
