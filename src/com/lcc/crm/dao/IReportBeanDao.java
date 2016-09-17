package com.lcc.crm.dao;

import java.util.List;

import com.lcc.crm.domain.ReportBean;

public interface IReportBeanDao extends ICommonDao<ReportBean> {
	/**
	 * 查询报表中的数据
	 * @return
	 */
	public List<ReportBean> findreportBeans();
}
