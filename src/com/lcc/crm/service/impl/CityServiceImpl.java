package com.lcc.crm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcc.crm.dao.ICityDao;
import com.lcc.crm.domain.City;
import com.lcc.crm.service.ICityService;

@Service("cityService")
public class CityServiceImpl implements ICityService {

	@Resource(name = "cityDao")
	private ICityDao cityDao;

	@Override
	public List<City> findCitysByPid(Integer id) {
		if (id != null) {
			String whereHql = "and o.pid=?";
			Object[] params = { id };
			LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
			orderBy.put("o.id", "asc");
			List<City> list = this.cityDao.findObjectsByConditionWithNoPage(whereHql, params, orderBy);
			return list;
		}
		return null;
	}

	@Override
	public List<City> getAllCities() {
		return this.cityDao.getAllEntry();
	}

	@Transactional(readOnly = false)
	@Override
	public void addCity(City city) {
		this.cityDao.save(city);
	}

}
