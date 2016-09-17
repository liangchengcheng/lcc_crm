package com.lcc.crm.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lcc.crm.dao.ICommonDao;
import com.lcc.crm.util.GenericClass;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T>{

	private Class clazz = GenericClass.getGenericClass(this.getClass());
	
	@Resource(name="sessionFactory")
	public  void setSessionFactoryDI(SessionFactory sessionFactory) {
	    //调用父类的setSessionFactory方法,注入sessionFactory
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findObjectById(Serializable id) {
		if (id==null) {
			throw new RuntimeException("你要查找的id不能是空");
		}
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public void deleteByIds(Serializable... ids) {
		// 批量的删除，通过id删除多条记录
		if (ids!=null&&ids.length>0) {
			for (Serializable id : ids) {
				Object object = this.getHibernateTemplate().get(clazz, id);
				if (object==null) {
					throw new RuntimeException("你要查找的id不存在");
				}
				this.getHibernateTemplate().delete(object);
			}
		}
		
	}

	@Override
	public void deleteCollections(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
	}

	@Override
	public List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		// 组织sql
		String hql="from "+clazz.getSimpleName()+"o where 1=1 ";
		//在select的语句后面组合查询的条件
		if (StringUtils.isNotBlank(whereHql)) {
			hql=hql+whereHql;
		}
		//处理排序
		String orderbystr=buildOrderBy(orderBy);
		hql = hql+orderbystr;
		final String fhql = hql;
		@SuppressWarnings("unchecked")
		List<T> list=this.getHibernateTemplate().executeFind(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(fhql);
				setParam(query, params);
				return query.list();
			}
			
		});
		return list;
	}

	@Override
	public List<T> getAllEntry() {
		String whereHql="from"+clazz.getName();
		List<T> list = this.getHibernateTemplate().find(whereHql);
		return list;
	}

	@Override
	public List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params) {
		return this.findObjectsByConditionWithNoPage(whereHql, params,null);
	}

	@Override
	public List<T> findObjectsByConditionWithNoPageCache(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		//组织sql的语句
		String sql="from"+clazz.getSimpleName()+"o where 1=1";
		//在select的语句的后面组合查询条件
		if (StringUtils.isNotBlank(whereHql)) {
			sql=sql+whereHql;
		}
		//处理排序
		String order = buildOrderBy(orderBy);
		sql= sql=order;
		final String fhql= sql;
		@SuppressWarnings("unchecked")
		List<T> list=(List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				Query query=session.createQuery(fhql);
				//使用查询缓存
				query.setCacheable(true);
				setParam(query,params);
				return query.list();
			}
		});
		return list;
	}
	
	//组织排序的条件
	public String buildOrderBy(LinkedHashMap<String, String> orderBy){
		StringBuffer sb= new StringBuffer();
		if (orderBy!=null && orderBy.isEmpty()) {
			sb.append("order by");
			for(Map.Entry<String, String> em:orderBy.entrySet()){
				sb.append(em.getKey()+" "+em.getValue()+",");
			}
			//去掉最后的那个逗号
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	/**
	 * 设置hql语句需要的参数
	 * @param query
	 * @param params
	 */
	public void setParam(Query query,Object[]params){
		if (params!=null&&params.length>0) {
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
	}

}
