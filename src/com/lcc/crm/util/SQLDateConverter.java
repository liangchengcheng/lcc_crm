package com.lcc.crm.util;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

public class SQLDateConverter implements Converter {

	@Override
	public Object convert(Class type, Object value) {
		if (value == null) {
			return null;
		}
		if (type == null) {
			return null;
		}

		if (type != java.sql.Date.class) {
			return null;
		}
		if (value instanceof java.lang.String) {
			String string = (String) value;
			if (StringUtils.isNotBlank(string)) {
				return java.sql.Date.valueOf(string);
			}
		}
		return null;
	}

}
