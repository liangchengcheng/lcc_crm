package com.lcc.crm.domain;

public class SysCodeRule implements java.io.Serializable {

	private Integer id;
	private String module;
	private String areaPrefix;
	private String areaTime;
	private Integer glideBit;
	private String currentCode;
	private String tabName;
	private String available;
	private String nextseq;
	private String curDate;

	public SysCodeRule() {
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getAreaPrefix() {
		return this.areaPrefix;
	}

	public void setAreaPrefix(String areaPrefix) {
		this.areaPrefix = areaPrefix;
	}

	public String getAreaTime() {
		return this.areaTime;
	}

	public void setAreaTime(String areaTime) {
		this.areaTime = areaTime;
	}

	public Integer getGlideBit() {
		return this.glideBit;
	}

	public void setGlideBit(Integer glideBit) {
		this.glideBit = glideBit;
	}

	public String getCurrentCode() {
		return this.currentCode;
	}

	public void setCurrentCode(String currentCode) {
		this.currentCode = currentCode;
	}

	public String getTabName() {
		return this.tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	public String getAvailable() {
		return this.available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getNextseq() {
		return this.nextseq;
	}

	public void setNextseq(String nextseq) {
		this.nextseq = nextseq;
	}

	public String getCurDate() {
		return this.curDate;
	}

	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}

}