package com.lcc.crm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lcc.crm.bean.CompanySearch;
import com.lcc.crm.dao.ICompanyDao;
import com.lcc.crm.dao.ISysCodeRuleDao;
import com.lcc.crm.dao.ISysOperateLogDao;
import com.lcc.crm.dao.ISysUserDao;
import com.lcc.crm.domain.Company;
import com.lcc.crm.domain.SysCodeRule;
import com.lcc.crm.domain.SysOperateLog;
import com.lcc.crm.domain.SysUser;
import com.lcc.crm.service.ICompanyService;
import com.lcc.crm.util.DataType;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Resource(name = "companyDao")
	private ICompanyDao companyDao;

	@Resource(name = "sysCodeRuleDao")
	private ISysCodeRuleDao sysCodeRuleDao;

	@Resource(name = "sysUserDao")
	private ISysUserDao sysUserDao;

	@Resource(name = "sysOperateLogDao")
	private ISysOperateLogDao sysOperateLogDao;

	@Override
	public String getCompanyCodeByTabName(String tabName) {
		// 获取代码的规则sys_code_rule
		String whereHql = "and 0.tabName=?";
		Object[] params = { tabName };
		List<SysCodeRule> list = this.sysCodeRuleDao.findObjectsByConditionWithNoPage(whereHql, params);

		if (list == null || list.size() != 1) {
			throw new RuntimeException("不能生成客户的编码");
		}

		SysCodeRule sysCodeRule = list.get(0);
		if (sysCodeRule.getAvailable().equals("Y")) {
			// 获取流水位
			Integer gLideBit = sysCodeRule.getGlideBit();
			// 生成第一个流水号001
			String firstNumber = DataType.geneFirstNumber(gLideBit);
			// 计算下一个流水号
			String nextGlideNumber = DataType.geneNextNumber(firstNumber);
			// 获取系统的当前时间
			String curDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
			// 生成客户端编码
			String code = sysCodeRule.getAreaPrefix() + "-"
					+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + firstNumber;
			// 修改编码规则表
			// 下一个序列号
			sysCodeRule.setNextseq(nextGlideNumber);
			// 当前日期
			sysCodeRule.setCurDate(curDate);
			// 是否被修改过
			sysCodeRule.setAvailable("N");
			this.sysCodeRuleDao.update(sysCodeRule);
			return code;
		} else {
			// 是否被修改过或新添加的=='N'
			// * 获取代码规则表中的当前日期字段的值
			String curDate = sysCodeRule.getCurDate();

			// * 获取系统的当前日期、
			String sysCurDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
			// * 如果代码规则表中的当前日期字段的值==系统的当前日期、
			if (curDate.equals(sysCurDate)) {
				// * 获取下一个序列号 ="002"
				String nextseq = sysCodeRule.getNextseq();

				// * 计算新的流水号 003
				String nextGlideNumber = DataType.geneNextNumber(nextseq);

				// * 生成客户编码
				// * 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001
				String code = sysCodeRule.getAreaPrefix() + "-"
						+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + nextseq;
				// * 修改代码规则表
				// * 下一个序列号="003"
				sysCodeRule.setNextseq(nextGlideNumber);
				// * 当前日期 20110914
				// * 是否被修改过='N'
				sysCodeRuleDao.update(sysCodeRule);
				return code;
			} else { // 如果代码规则表中的当前日期字段的值!=系统的当前日期、

				// * 获取 流水位=3
				Integer glideBit = sysCodeRule.getGlideBit();
				// * 生成第一个流水号 001
				String firstGlideNumber = DataType.geneFirstNumber(glideBit);
				// * 计算下一个流水号 002
				String nextGlideNumber = DataType.geneNextNumber(firstGlideNumber);
				// * 生成客户编码
				// * 编码前缀+"-"+利用日期位格式生成当前的日期[yyyy-MM-dd ]+"-"+001

				String code = sysCodeRule.getAreaPrefix() + "-"
						+ DateFormatUtils.format(new Date(), sysCodeRule.getAreaTime()) + "-" + firstGlideNumber;

				// * 修改代码规则表
				// * 下一个序列号="002"
				sysCodeRule.setNextseq(nextGlideNumber);
				// * 当前日期 20110915
				sysCodeRule.setCurDate(sysCurDate);
				// * 是否被修改过='N'
				sysCodeRule.setAvailable("N");
				sysCodeRuleDao.update(sysCodeRule);
				return code;
			}
		}
	}

	@Transactional(readOnly = false)
	public void saveCompany(SysUser user, Company company) {
		if (user != null) {
			this.companyDao.save(company);
			// 处理日志，写日志到数据库中
			SysOperateLog log = new SysOperateLog();
			log.setUserName(user.getName());
			log.setCnname(user.getCnname());
			log.setActionType("添加");
			String actionContent = "添加一个客户信息[ID:" + company.getId() + ",客户名称:" + company.getName() + ",客户编码:"
					+ company.getCode() + "]";
			log.setActionContent(actionContent);
			log.setActionDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
			this.sysOperateLogDao.save(log);
		}
	}

	public List<Company> findCompanysConition(SysUser sysUser, CompanySearch companySearch) {
		if (sysUser != null && companySearch != null) {
			String whereHql = "";
			List paramList = new ArrayList();
			if (sysUser.getId() != null) {
				whereHql = whereHql + " and o.sysUser.id=? ";
				paramList.add(sysUser.getId());
			}
			if (StringUtils.isNotBlank(companySearch.getCode())) {
				whereHql = whereHql + " and o.code like ? ";
				paramList.add("%" + companySearch.getCode() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getName())) {
				whereHql = whereHql + " and o.name like ? ";
				paramList.add("%" + companySearch.getName() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getPycode())) {
				whereHql = whereHql + " and o.pycode  like ? ";
				paramList.add("%" + companySearch.getPycode() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getTell())) {
				whereHql = whereHql + " and o.tell like ? ";
				paramList.add("%" + companySearch.getTell() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getSource())) {
				whereHql = whereHql + " and o.source like ? ";
				paramList.add("%" + companySearch.getSource() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getGrade())) {
				whereHql = whereHql + " and o.Grade like ? ";
				paramList.add("%" + companySearch.getGrade() + "%");
			}
			if (StringUtils.isNotBlank(companySearch.getQuality())) {
				whereHql = whereHql + " and o.quality like ? ";
				paramList.add("%" + companySearch.getQuality() + "%");
			}
			LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
			orderBy.put("o.id", "asc");
			return this.companyDao.findObjectsByConditionWithNoPage(whereHql, paramList.toArray(), orderBy);
		}
		return null;
	}

	public List<Company> findCompanysBySysUser(SysUser sysUser) {
		if (sysUser.getId() != null) {
			String whereHql = " and o.sysUser.id=?";
			Object[] params = { sysUser.getId() };
			LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
			orderBy.put("o.id", "asc");
			return this.companyDao.findObjectsByConditionWithNoPage(whereHql, params, orderBy);
		}
		return null;
	}

	public Company findCompanyById(Integer id) {
		return this.companyDao.findObjectById(id);
	}

	@Transactional(readOnly = false)
	public void updateCompany(SysUser user, Company company) {
		this.companyDao.update(company);
	}

	@Transactional(readOnly = false)
	public void deleteCompanyByIds(Integer[] ids) {
		this.companyDao.deleteByIds(ids);
	}

	@Transactional(readOnly = false)
	public void addUpdateShareSetOne(String s_module, Integer id, Integer[] uids) {
		if (StringUtils.isNotBlank(s_module) && id != null && uids != null && uids.length > 0) {
			if ("c_company".equals(s_module)) {
				Company company = this.companyDao.findObjectById(id);
				if (company != null) {
					StringBuffer buf = new StringBuffer();
					for (int i = 0; i < uids.length; i++) {
						// 处理用户
						buf.append(uids[i] + "#");
					}
					if ('N' == company.getShareFlag()) {
						company.setShareFlag('Y');
						company.setShareIds("#" + buf.toString());
						this.companyDao.update(company);
					}
					company.setShareFlag('Y');
					company.setShareIds(company.getShareIds() + buf.toString());
					this.companyDao.update(company);
				}
			}
		}
	}

	@Transactional(readOnly = false)
	public void minusUpdateShareSetOne(String s_module, Integer id, Integer[] uids) {
		if (StringUtils.isNotBlank(s_module) && id != null && uids != null && uids.length > 0) {
			if ("c_company".equals(s_module)) {
				Company company = this.companyDao.findObjectById(id);
				if (company != null) {
					if ('Y' == company.getShareFlag()) { // 只有Y才处理
						String shareIds = company.getShareIds();
						if (StringUtils.isNotBlank(shareIds)) {
							for (int i = 0; i < uids.length; i++) {
								String uid = "#" + uids[i] + "#";
								while (true) {
									if (shareIds.contains(uid)) {
										shareIds = shareIds.replaceAll(uid, "#");
									} else {
										break;
									}
								}
							}
							if ("#".equals(shareIds)) {
								company.setShareFlag('N');
								company.setShareIds(null);
								this.companyDao.update(company);
							} else {
								company.setShareIds(shareIds);
								this.companyDao.update(company);
							}
						}
					}
				}
			}
		}
	}

	@Transactional(readOnly = false)
	public void updateshareCancelOne(Integer id, String s_module) {
		if (id != null && StringUtils.isNotBlank(s_module)) {
			Company company = this.companyDao.findObjectById(id);
			if (company != null) {
				company.setShareFlag('N');
				company.setShareIds(null);
				this.companyDao.update(company);
			}
		}
	}

	public List<SysUser> findSysUserBySharedIds(Integer id) {
		if (id != null) {
			Company company = this.companyDao.findObjectById(id);
			if (company != null) {
				String shareIds = company.getShareIds();
				if (shareIds != null) {
					String[] shareId = shareIds.split("#");
					Integer[] uids = DataType.convertStringArray2IntegerArray(shareId);
					StringBuffer whereBuff = new StringBuffer();
					List paramList = new ArrayList<>();
					whereBuff.append(" and o.id in (");
					for (int i = 0; i < uids.length; i++) {
						whereBuff.append("?,");
						paramList.add(uids[i]);
					}
					whereBuff.deleteCharAt(whereBuff.length() - 1);
					whereBuff.append(")");
					return this.sysUserDao.findObjectsByConditionWithNoPage(whereBuff.toString(), paramList.toArray());
				}
			}
		}
		return null;
	}

	@Transactional(readOnly = false)
	public void updateNextTouchTime(Integer[] id, java.sql.Date next_touch_date) {
		if (id != null && id.length > 0 && next_touch_date != null) {
			for (int i = 0; i < id.length; i++) {
				Company company = this.companyDao.findObjectById(id[i]);
				if (company != null) {
					company.setNextTouchDate(next_touch_date);
					this.companyDao.update(company);
				}
			}
		}
	}

	@Transactional(readOnly = false)
	public void changeHandler(Integer[] id, Integer new_owner) {
		if (id != null && id.length > 0 && new_owner != null) {
			// 通过用户id查询该用户信息
			SysUser sysUser = this.sysUserDao.findObjectById(new_owner);
			for (int i = 0; i < id.length; i++) {
				Company company = this.companyDao.findObjectById(id[i]);
				if (company != null && sysUser != null) {
					company.setSysUser(sysUser);
					company.setDispensePerson(sysUser.getCnname());
					company.setDispenseDate(DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:ss"));
					this.companyDao.update(company);
				}
			}
		}
	}
}
