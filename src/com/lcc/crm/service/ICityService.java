package com.lcc.crm.service;

import java.util.List;

import com.lcc.crm.domain.City;

public interface ICityService {

	/**
	 * 通过城市的id的返回城市信息
	 * 
	 * @param id
	 * @return
	 */
	public List<City> findCitysByPid(Integer id);

	/**
	 * 得到所有的城市信息
	 * 
	 * @return
	 */
	public List<City> getAllCities();

	/**
	 * 新增城市
	 * 
	 * @param city
	 */
	public void addCity(City city);
}
