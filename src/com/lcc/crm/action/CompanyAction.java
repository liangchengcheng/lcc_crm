package com.lcc.crm.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.datatype.DatatypeAttribute;
import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lcc.crm.bean.CompanySearch;
import com.lcc.crm.domain.Company;
import com.lcc.crm.domain.SysDictionaryType;
import com.lcc.crm.domain.SysUser;
import com.lcc.crm.domain.SysUserGroup;
import com.lcc.crm.service.ICityService;
import com.lcc.crm.service.ICompanyService;
import com.lcc.crm.service.IProvinceService;
import com.lcc.crm.service.ISysDictionaryTypeService;
import com.lcc.crm.service.ISysUserGroupService;
import com.lcc.crm.service.ISysUserService;
import com.lcc.crm.util.DataType;
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
	
	/**
	 * 共享联系人
	 * @return
	 */
	public String showShareSetOne(){
		//获取客户的id
		String sid= ServletActionContext.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(sid)) {
			Integer id= Integer.parseInt(sid);
			Company company =this.companyService.findCompanyById(id);
			ActionContext.getContext().put("company", company);
			//获取部门的信息
			List<SysUserGroup> sysUserGroups=this.sysUserGroupService.findSysUserGroups();
			ActionContext.getContext().put("sysUserGroups", sysUserGroups);
			//获取用户的信息
			List<SysUser> sysUsers=this.sysUserService.findAllSysUsers();
			ActionContext.getContext().put("sysUsers", sysUsers);
		}
		
		return "showShareSetOne";
	}
	
	public String showShareCancelOne(){
		//获取客户的id
		String sid = ServletActionContext.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(sid.trim())) {
			Integer id= Integer.parseInt(sid);
			Company company=this.companyService.findCompanyById(id);
			ActionContext.getContext().put("company", company);
			return "showShareCancelOne";
		}
		return null;
	}
	
	public String updateShareSetOne(){
		//获取id
		String sid= ServletActionContext.getRequest().getParameter("id");
		if (StringUtils.isNotBlank(sid.trim())) {
			Integer id = Integer.parseInt(sid);
			//获取模板的名称
			String s_module = ServletActionContext.getRequest().getParameter("s_module");
			if (StringUtils.isNotBlank(s_module)) {
				//获取用户的id
				String[]suids= ServletActionContext.getRequest().getParameterValues("uid");
				
				Integer[]uids=DataType.convertStringArray2IntegerArray(suids);
				String sharetyoe=ServletActionContext.getRequest().getParameter("sharetype");
				if (StringUtils.isNotBlank(sharetyoe)) {
					if ("add".equals(sharetyoe)) {
						this.companyService.addUpdateShareSetOne(s_module, id, uids);
					}
					if ("minus".equals(sharetyoe)) {
						this.companyService.minusUpdateShareSetOne(s_module, id, uids);
					}
				}
				return "updateShareSetOne";
			}
		}
		return null;
	}
	
	public String showNextTouchTime(){
		return "showNextTouchTime";
	}
	
	/**
	 * 更新下次的联系的时间
	 */
	public String updateNextTouchTime(){
		//获取客户的id
		String idString=ServletActionContext.getRequest().getParameter("ids");
		if (StringUtils.isNotBlank(idString)) {
			String[] sid=idString.split(",");
			Integer[]id=DataType.convertStringArray2IntegerArray(sid);
			//获取下次的联系的时间
			String snext_touch_date=ServletActionContext.getRequest().getParameter("snext_touch_date");
			java.sql.Date next_touch_date=java.sql.Date.valueOf(snext_touch_date);
			this.companyService.updateNextTouchTime(id, next_touch_date);
			return "updateNextTouchTime";
		}
		return null;
	}
}
