package com.lcc.crm.dao.impl;

import org.springframework.stereotype.Repository;
import com.lcc.crm.dao.ISysUserDao;
import com.lcc.crm.domain.SysUser;

@Repository("sysUserDao")
public class SysUserDaoImpl extends CommonDaoImpl<SysUser> implements ISysUserDao {

}

