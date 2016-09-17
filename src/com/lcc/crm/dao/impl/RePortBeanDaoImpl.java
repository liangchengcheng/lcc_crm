package com.lcc.crm.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.lcc.crm.dao.IReportBeanDao;
import com.lcc.crm.domain.ReportBean;

public class RePortBeanDaoImpl extends CommonDaoImpl<ReportBean> implements IReportBeanDao{

	@Override
	public List<ReportBean> findreportBeans() {
		List<ReportBean> list=(List<ReportBean>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				String hql="select new cn.com.crm.domain.ReportBean(o.grade,count(*)) from Company o group by o.grade";
				Query query=session.createQuery(hql);
				return query.list();
			}
		});
		return list;
	}

}
