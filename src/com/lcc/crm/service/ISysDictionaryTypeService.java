package com.lcc.crm.service;

import java.util.List;
import com.lcc.crm.domain.SysDictionaryType;

public interface ISysDictionaryTypeService {
	public List<SysDictionaryType> findSysDictionaryTypeByCode(String code);
}
