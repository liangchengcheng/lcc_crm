package com.lcc.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.lcc.crm.dao.ISysOperateLogDao;
import com.lcc.crm.domain.SysOperateLog;

@Repository("sysOperateLogDao")
public class SysOperateLogDaoImpl extends CommonDaoImpl<SysOperateLog> implements ISysOperateLogDao {

}
