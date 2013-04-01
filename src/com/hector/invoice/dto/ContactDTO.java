/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.lib.CONTACT_TABLE;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 3, 2013
 */
public class ContactDTO extends AbstractTableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SEX_MALE = 1;
	public static final int SEX_REMALE = 0;

	public long contactId;
	public String contactName;
	public String contactAddress;
	public String contactPLZ;
	public String contactStadt;
	public String firstName;
	public String lastName;
	public int sex; // 1: Male / 0: FeMale

	public ContactDTO() {
		super(TableType.LIST_CONTACT);
		contactId = -1;
		contactName = "";
		contactAddress = "";
		contactPLZ = "";
		contactStadt = "";
		firstName = "";
		lastName = "";
		sex = SEX_MALE;
	}

	/**
	 * 
	 * init object with cursor
	 * 
	 * @param @param c
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 3, 2013
	 */
	public void initLogDTOFromCursor(Cursor c) {
		if (c.getColumnIndex(CONTACT_TABLE.CONTACT_ID) >= 0) {
			contactId = c.getLong(c.getColumnIndex(CONTACT_TABLE.CONTACT_ID));
		}
		if (c.getColumnIndex(CONTACT_TABLE.CONTACT_NAME) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.CONTACT_NAME)) != null) {
			contactName = c.getString(c
					.getColumnIndex(CONTACT_TABLE.CONTACT_NAME));
		} else {
			contactName = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.CONTACT_ADDRESS) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.CONTACT_ADDRESS)) != null) {
			contactAddress = c.getString(c
					.getColumnIndex(CONTACT_TABLE.CONTACT_ADDRESS));
		} else {
			contactAddress = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.CONTACT_PLZ) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.CONTACT_PLZ)) != null) {
			contactPLZ = c.getString(c
					.getColumnIndex(CONTACT_TABLE.CONTACT_PLZ));
		} else {
			contactPLZ = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.CONTACT_STADT) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.CONTACT_STADT)) != null) {
			contactStadt = c.getString(c
					.getColumnIndex(CONTACT_TABLE.CONTACT_STADT));
		} else {
			contactStadt = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.FIRST_NAME) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.FIRST_NAME)) != null) {
			firstName = c.getString(c.getColumnIndex(CONTACT_TABLE.FIRST_NAME));
		} else {
			firstName = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.LAST_NAME) >= 0
				&& c.getString(c.getColumnIndex(CONTACT_TABLE.LAST_NAME)) != null) {
			lastName = c.getString(c.getColumnIndex(CONTACT_TABLE.LAST_NAME));
		} else {
			lastName = "";
		}
		if (c.getColumnIndex(CONTACT_TABLE.SEX) >= 0) {
			sex = c.getInt(c.getColumnIndex(CONTACT_TABLE.SEX));
		} else {
			sex = SEX_MALE;
		}
	}

	/**
	 * check object null to delete in DB Mo ta chuc nang cua ham
	 * 
	 * @author: HaiTC3
	 * @return
	 * @return: boolean
	 * @throws:
	 * @since: Mar 15, 2013
	 */
	public boolean isNullObject() {
		boolean isNull = true;
		if (this.contactId > 0) {
			if (!StringUtil.isNullOrEmpty(contactName)) {
				isNull = false;
			}
			if (isNull && !StringUtil.isNullOrEmpty(contactAddress)) {
				isNull = false;
			}
			if (isNull && !StringUtil.isNullOrEmpty(contactPLZ)) {
				isNull = false;
			}
			if (isNull && !StringUtil.isNullOrEmpty(contactStadt)) {
				isNull = false;
			}
			if (isNull && !StringUtil.isNullOrEmpty(firstName)) {
				isNull = false;
			}
			if (isNull && !StringUtil.isNullOrEmpty(lastName)) {
				isNull = false;
			}
		}
		return isNull;
	}
}
