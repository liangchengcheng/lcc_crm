package com.lcc.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.lcc.crm.dao.IProvinceDao;
import com.lcc.crm.domain.Province;

@Repository("provinceDao")
public class ProvinceDaoImpl extends CommonDaoImpl<Province> implements IProvinceDao {

}