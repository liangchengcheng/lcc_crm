package com.lcc.crm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lcc.crm.bean.CompanySearch;
import com.lcc.crm.domain.Company;
import com.lcc.crm.domain.SysDictionaryType;
import com.lcc.crm.domain.SysUser;
import com.lcc.crm.service.ICityService;
import com.lcc.crm.service.ICompanyService;
import com.lcc.crm.service.IProvinceService;
import com.lcc.crm.service.ISysDictionaryTypeService;
import com.lcc.crm.service.ISysUserGroupService;
import com.lcc.crm.service.ISysUserService;
import com.lcc.crm.util.Global;
import com.lcc.crm.util.SessionUtils;
import com.opensymphony.xwork2.ActionContext;

import net.sf.morph.transform.copiers.dsl.A;

@Controller("companyAction")
@Scope("prototype")
public class CompanyAction extends BaseAction<Company> {

	@Resource(name = "companyService")
	private ICompanyService companyService;

	@Resource(name = "sysDictionaryTypeService")
	private ISysDictionaryTypeService sysDictionaryTypeService;

	@Resource(name = "provinceService")
	private IProvinceService provinceService;

	@Resource(name = "cityService")
	private ICityService cityService;

	@Resource(name = "sysUserGroupService")
	private ISysUserGroupService sysUserGroupService;

	@Resource(name = "sysUserService")
	private ISysUserService sysUserService;

	public String list(){
		//处理客户登记的下拉选
		List<SysDictionaryType>gradesSelect= this.sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.GRADE);
		ActionContext.getContext().put("gradesSelect", gradesSelect);
		//处理客户来源
		List<SysDictionaryType> sourcesSelect=this.sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.SOURCE);
		ActionContext.getContext().put("sourcesSelect", sourcesSelect);
		//处理客户信息
		List<SysDictionaryType> qualitySelect=this.sysDictionaryTypeService.findSysDictionaryTypeByCode(Global.QUALITY);
		ActionContext.getContext().put("qualitySelect", qualitySelect);
		
		SysUser sysUser = SessionUtils.getSysUserFromSession(ServletActionContext.getRequest());
		if (sysUser!=null) {
			List<Company> companies =this.companyService.findCompanysBySysUser(sysUser);
			ActionContext.getContext().put("companyList", companies);
			return listAction;
		}
		return null;
	}
	
	public String search(){
		//初始化对象
		//设置值
		CompanySearch companySearch = new CompanySearch();
		companySearch.setCode(this.getModel().getCode());
		companySearch.setName(this.getModel().getName());
		companySearch.setPycode(this.getModel().getPycode());
		companySearch.setGrade(this.getModel().getGrade());
		companySearch.setSource(this.getModel().getScale());
		companySearch.setQuality(this.getModel().getQuality());
		
		//从session里去获取用户
		SysUser sysUser= SessionUtils.getSysUserFromSession(ServletActionContext.getRequest());
		
		if (sysUser!=null) {
			//上述可以直接获取到对象的应该
			List<Company> companyList=this.companyService.findCompanysConition(sysUser, companySearch);
			ActionContext.getContext().put("companyList", companyList);
			return listAction;
		}
		return null;
	}
}
