package com.lcc.crm.util;

import org.apache.commons.lang.StringUtils;

public class DataType {

	/**
	 * 这个方法完成的功能是转换字符串的数组为整形数组
	 * 
	 * @param ids
	 * @return
	 */
	public static Integer[] convertStringArray2IntegerArray(String[] ids) {
		if (ids != null && ids.length > 0) {
			Integer[] Iids = new Integer[ids.length];
			for (int i = 0; i < ids.length; i++) {
				if (StringUtils.isNotBlank(ids[i])) {
					Iids[i] = Integer.parseInt(ids[i]);
				}
			}
			return Iids;
		}
		return null;
	}

	/**
	 * 利用给定的流水位生成第一个流水号 流水位 4 第一个流水号003
	 * 
	 * @param gLideBit
	 * @return
	 */
	public static String geneFirstNumber(Integer gLideBit) {
		if (gLideBit == null || gLideBit < 3) {
			gLideBit = 3;
		}
		String gLideNumber = "";
		for (int i = 0; i < gLideBit - 1; i++) {
			gLideNumber = gLideNumber + "0";
		}
		gLideNumber = gLideNumber + "1";
		return gLideNumber;
	}

	/**
	 * 根据当前流水号生成下一个流水号
	 * 
	 * @param firstNumber:当前流水号
	 * @return:下一个流水号
	 */
	public static String geneNextNumber(String firstNumber) {
		if (StringUtils.isBlank(firstNumber)) {
			throw new RuntimeException("不能生成下一个流水号!");
		}
		firstNumber = "1" + firstNumber;
		Integer iCurrentNumber = Integer.parseInt(firstNumber);
		iCurrentNumber++;
		firstNumber = iCurrentNumber + "";
		firstNumber = firstNumber.substring(1);
		return firstNumber;
	}
}
