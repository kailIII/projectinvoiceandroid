/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

/**
 * 
 * handle event of string
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Feb 28, 2013
 */
public class StringUtil {

	/**
	 * 
	 * get string resource from
	 * 
	 * @param @param id
	 * @param @return
	 * @return: String
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	public static String getString(int id) {
		return InvoiceInfo.getInstance().getAppContext().getResources()
				.getString(id);
	}

	/**
	 * 
	 * check string is null or emty
	 * 
	 * @param aString
	 * @return
	 * @return: boolean
	 * @throws:
	 * @author: HaiTC3
	 * @date: Oct 28, 2012
	 */
	public static boolean isNullOrEmpty(String aString) {
		return (aString == null) || ("".equals(aString.trim()));
	}
}
