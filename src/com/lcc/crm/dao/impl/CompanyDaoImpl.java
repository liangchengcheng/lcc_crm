package com.lcc.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.lcc.crm.dao.ICompanyDao;
import com.lcc.crm.domain.Company;

@Repository("companyDao")
public class CompanyDaoImpl  extends CommonDaoImpl<Company> implements ICompanyDao{

}
