package com.lcc.crm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public interface ICommonDao<T> {

	public void save(T entity);

	public void update(T entity);

	public T findObjectById(Serializable id);

	public void deleteByIds(Serializable... ids);

	public void deleteCollections(Collection<T> entities);

	public List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderBy);

	public List<T> getAllEntry();

	public List<T> findObjectsByConditionWithNoPage(String whereHql, Object[] params);

	/**
	 * 使用缓存
	 * 
	 * @param whereHql
	 * @param params
	 * @param orderBy
	 * @return
	 */
	public List<T> findObjectsByConditionWithNoPageCache(String whereHql, Object[] params,
			LinkedHashMap<String, String> orderBy);
}
