/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.Constants;

/**
 * Column table info
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public class ColumnTable {
	public static String DATA_TYPE_TEXT = "TEXT"; // type text
	public static String DATA_TYPE_NUMERIC = "NUM"; // type number
	public static String DATA_TYPE_INTEGER = "INTEGER"; // type int
	public static String DATA_TYPE_REAL = "REAL"; // type real
	public static String DATA_TYPE_NONE = ""; // type none
	public static String DATA_TYPE_BLOB = "BLOB"; // type none

	// current date "YYYY-MM-DD"
	public static String DEFAULT_VALUE_CURRENT_DATE = "CURRENT_DATE";
	// current time "HH:MM:SS"
	public static String DEFAULT_VALUE_CURRENT_TIME = "CURRENT_TIME";
	// current timestamp "YYYY-MM-DD HH:MM:SS"
	public static String DEFAULT_VALUE_CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
	// default value none
	public static String DEFAULT_VALUE_CURRENT_NONE = "";

	// order ASC
	public static String ORDER_TYPE_ASC = "ASC";
	// order DESC
	public static String ORDER_TYPE_DESC = "DESC";

	public String culumnName; // culumn name
	public String dataType; // type data for culumn
	public boolean primaryKey; // is primary for table
	public boolean autoinc; // autoincrement
	public boolean allowNull; // allow null
	public boolean unique; // unique table
	public String defaultValue; // default value (CURRENT_DATE, CURRENT_TIME,
								// CURRENT_TIMESTAMP)
	public String typeOrderPrimaryKey;

	public ColumnTable() {
		culumnName = Constants.STR_BLANK;
		dataType = DATA_TYPE_TEXT;
		primaryKey = false;
		autoinc = false;
		allowNull = true;
		unique = true;
		defaultValue = Constants.STR_BLANK;
		typeOrderPrimaryKey = ORDER_TYPE_ASC;
	}

	/**
	 * constructor for object
	 * 
	 * @param columnName
	 * @param dataType
	 * @param prikey
	 * @param autoinc
	 * @param allowNull
	 * @param unique
	 * @param defaultValue
	 * @param typeOrder
	 */
	public ColumnTable(String columnName, String dataType, boolean prikey,
			boolean autoinc, boolean allowNull, boolean unique,
			String defaultValue, String typeOrder) {
		this.culumnName = columnName;
		this.dataType = dataType;
		this.primaryKey = prikey;
		this.autoinc = autoinc;
		this.allowNull = allowNull;
		this.unique = unique;
		this.defaultValue = defaultValue;
		this.typeOrderPrimaryKey = typeOrder;

	}

	/**
	 * 
	 * genera sql createate column in table
	 * 
	 * @return
	 * @return: String
	 * @throws:
	 * @author: HaiTC3
	 * @date: Mar 3, 2012
	 */
	public String generaSQLCreateColumn() {
		StringBuffer strSQL = new StringBuffer();
		// column name
		strSQL.append(" " + culumnName);
		// data type
		strSQL.append(" " + dataType);
		// primary key
		if (primaryKey) {
			if (typeOrderPrimaryKey.equals(ORDER_TYPE_ASC)) {
				strSQL.append(" " + "PRIMARY KEY " + ORDER_TYPE_ASC);
			} else {
				strSQL.append(" " + "PRIMARY KEY " + ORDER_TYPE_DESC);
			}
		}
		// autoinc
		if (autoinc) {
			strSQL.append(" AUTOINCREMENT");
		}
		if (unique) {
			strSQL.append(" UNIQUE");
		}
		// allow null
		if (!allowNull) {
			strSQL.append(" NOT NULL");
		}
		// default value
		if (!StringUtil.isNullOrEmpty(defaultValue)) {
			strSQL.append(" DEFAULT " + defaultValue);
		}

		return strSQL.toString();
	}
}
