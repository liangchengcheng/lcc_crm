package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.domain.Province;

public interface IProvinceService {

	public List<Province> getAllProvinces();

	public Province findProvinceByName(String name);

	/**
	 * 通过省份id来查找省份信息
	 * @param id
	 * @return
	 */
	public Province findProvinceById(int id);

	/**
	 * 更新省份信息
	 * @param province		省份
	 */
	public void updateProvince(Province province);

	/**
	 * 增加省份信息
	 * @param province		省份
	 */
	public void addProvince(Province province);

	/**
	 * 根据条件查询省份信息
	 * @param name		省份条件
	 * @return			对应省份
	 */
	public List<Province> findProvincesByName(String name);

	/**
	 * 删除省份
	 * @param iids			省份id		
	 */
	public void deleteProvinces(Integer[] Iids);
}
