package com.lcc.crm.dao.impl;

import org.springframework.stereotype.Repository;

import com.lcc.crm.dao.ISysMenuDao;
import com.lcc.crm.domain.SysMenu;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends CommonDaoImpl<SysMenu> implements ISysMenuDao {

}