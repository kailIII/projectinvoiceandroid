/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.lib.CONTACT_TABLET;

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
		if (c.getColumnIndex(CONTACT_TABLET.CONTACT_ID) >= 0) {
			contactId = c.getLong(c.getColumnIndex(CONTACT_TABLET.CONTACT_ID));
		}
		if (c.getColumnIndex(CONTACT_TABLET.CONTACT_NAME) >= 0) {
			contactName = c.getString(c
					.getColumnIndex(CONTACT_TABLET.CONTACT_NAME));
		}
		if (c.getColumnIndex(CONTACT_TABLET.CONTACT_ADDRESS) >= 0) {
			contactAddress = c.getString(c
					.getColumnIndex(CONTACT_TABLET.CONTACT_ADDRESS));
		}
		if (c.getColumnIndex(CONTACT_TABLET.CONTACT_PLZ) >= 0) {
			contactPLZ = c.getString(c
					.getColumnIndex(CONTACT_TABLET.CONTACT_PLZ));
		}
		if (c.getColumnIndex(CONTACT_TABLET.CONTACT_STADT) >= 0) {
			contactStadt = c.getString(c
					.getColumnIndex(CONTACT_TABLET.CONTACT_STADT));
		}
		if (c.getColumnIndex(CONTACT_TABLET.FIRST_NAME) >= 0) {
			firstName = c
					.getString(c.getColumnIndex(CONTACT_TABLET.FIRST_NAME));
		}
		if (c.getColumnIndex(CONTACT_TABLET.LAST_NAME) >= 0) {
			lastName = c.getString(c.getColumnIndex(CONTACT_TABLET.LAST_NAME));
		}
		if (c.getColumnIndex(CONTACT_TABLET.SEX) >= 0) {
			sex = c.getInt(c.getColumnIndex(CONTACT_TABLET.SEX));
		}
	}
}
