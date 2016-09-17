package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.bean.CompanySearch;
import com.lcc.crm.domain.Company;
import com.lcc.crm.domain.SysUser;

public interface ICompanyService {
	
	public String getCompanyCodeByTabName(String tabName);

	public void saveCompany(SysUser user, Company company);

	public List<Company> findCompanysConition(SysUser sysUser,CompanySearch companySearch);

	public List<Company> findCompanysBySysUser(SysUser sysUser);

	public Company findCompanyById(Integer id);

	public void updateCompany(SysUser user, Company company);

	public void deleteCompanyByIds(Integer[] ids);
	
	/**
	 * 增加共享
	 * @param s_module
	 * @param id
	 * @param uids
	 */
	public void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids);

	/**
	 * 减少共享
	 * @param s_module
	 * @param id
	 * @param uids
	 */
	public void minusUpdateShareSetOne(String s_module, Integer id,Integer[] uids);

	/**
	 * 取消共享
	 * @param id	客户id
	 * @param s_module  模块名称
	 */
	public void updateshareCancelOne(Integer id, String s_module);

	/**
	 * 获取客户对应的共享人
	 * @param id	客户id
	 * @return
	 */
	public List<SysUser> findSysUserBySharedIds(Integer id);

	/**
	 * 修改客户下次联系时间
	 * @param id	客户id
	 * @param next_touch_date		下次联系时间		
	 */
	public void updateNextTouchTime(Integer[] id, java.sql.Date next_touch_date);

	/**
	 * 变更客户的所有人
	 * @param id			客户id
	 * @param new_owner		新持有人
	 */
	public void changeHandler(Integer[] id, Integer new_owner);
}
