/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.ListContactViewDTO;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 3, 2013
 */
public class CONTACT_TABLET extends ABSTRACT_TABLE {
	public static final String CONTACT_ID = "CONTACT_ID";
	public static final String CONTACT_NAME = "CONTACT_NAME";
	public static final String CONTACT_ADDRESS = "CONTACT_ADDRESS";
	public static final String CONTACT_PLZ = "CONTACT_PLZ";
	public static final String CONTACT_STADT = "CONTACT_STADT";
	public static final String FIRST_NAME = "FIRST_NAME";
	public static final String LAST_NAME = "LAST_NAME";
	public static final String SEX = "SEX";

	// COMPANY TABLE
	public static final String CONTACT_TABLET = "CONTACT_TABLET";

	public CONTACT_TABLET(SQLiteDatabase mDB) {
		this.tableName = CONTACT_TABLET;
		this.columns = new String[] { CONTACT_ID, CONTACT_NAME,
				CONTACT_ADDRESS, CONTACT_PLZ, CONTACT_STADT, FIRST_NAME,
				LAST_NAME, SEX };
		this.sqlGetCountQuerry += this.tableName + ";";
		this.mDB = mDB;
		this.listColumn = newListColumn();
	}

	/**
	 * 
	 * new list column
	 * 
	 * @param @return
	 * @return: ArrayList<ColumnTable>
	 * @author: HaiTC3
	 * @date: Mar 3, 2013
	 */
	public ArrayList<ColumnTable> newListColumn() {
		ArrayList<ColumnTable> listColumn = new ArrayList<ColumnTable>();
		// column CONTACT_ID
		ColumnTable contactId = new ColumnTable(CONTACT_ID,
				ColumnTable.DATA_TYPE_INTEGER, true, true, false, true,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactId);

		// column CONTACT_NAME
		ColumnTable contactName = new ColumnTable(CONTACT_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactName);

		// column CONTACT_ADDRESS
		ColumnTable contactAddress = new ColumnTable(CONTACT_ADDRESS,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactAddress);

		// column CONTACT_PLZ
		ColumnTable contactPlz = new ColumnTable(CONTACT_PLZ,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactPlz);

		// column CONTACT_STADT
		ColumnTable contactStadt = new ColumnTable(CONTACT_STADT,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactStadt);

		// column FIRST_NAME
		ColumnTable firstName = new ColumnTable(FIRST_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(firstName);

		// column LAST_NAME
		ColumnTable lastName = new ColumnTable(LAST_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(lastName);
		// column SEX
		ColumnTable multigrade = new ColumnTable(SEX,
				ColumnTable.DATA_TYPE_INTEGER, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(multigrade);

		return listColumn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.lib.ABSTRACT_TABLE#insert(com.hector.invoice.dto.
	 * AbstractTableDTO)
	 */
	@Override
	protected long insert(AbstractTableDTO dto) {
		ContentValues value = initDataRow((ContactDTO) dto);
		return insert(null, value);
	}

	public int update(ContactDTO dto) {
		ContentValues value = initDataRow(dto);
		String[] params = { String.valueOf(dto.contactId) };
		return update(value, CONTACT_ID + " = ?", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.lib.ABSTRACT_TABLE#update(com.hector.invoice.dto.
	 * AbstractTableDTO)
	 */
	@Override
	protected long update(AbstractTableDTO dto) {
		ContactDTO cusDTO = (ContactDTO) dto;
		ContentValues value = initDataRow(cusDTO);
		String[] params = { String.valueOf(cusDTO.contactId) };
		return update(value, CONTACT_ID + " = ?", params);
	}

	public int delete(String id) {
		String[] params = { id };
		return delete(CONTACT_ID + " = ?", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.lib.ABSTRACT_TABLE#delete(com.hector.invoice.dto.
	 * AbstractTableDTO)
	 */
	@Override
	protected long delete(AbstractTableDTO dto) {
		ContactDTO cusDTO = (ContactDTO) dto;
		String[] params = { String.valueOf(cusDTO.contactId) };
		return delete(CONTACT_ID + " = ?", params);
	}

	/**
	 * 
	 * get row in db flow id
	 * 
	 * @param id
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public ContactDTO getContactById(String id) {
		ContactDTO contactInfo = new ContactDTO();
		Cursor c = null;
		try {
			String[] params = { id };
			c = query(CONTACT_ID + " = ?", params, null, null, null);
			if (c != null) {
				if (c.moveToFirst()) {
					contactInfo.initLogDTOFromCursor(c);
				}
			}
		} catch (Exception ex) {
			c = null;
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return contactInfo;
	}

	/**
	 * 
	 * general contentValues
	 * 
	 * @param dto
	 * @return
	 * @return: ContentValues
	 * @throws:
	 * @author: HaiTC3
	 * @date: Mar 3, 2012
	 */
	public ContentValues initDataRow(ContactDTO dto) {
		ContentValues editedValues = new ContentValues();
		if (dto.contactId > 0) {
			editedValues.put(CONTACT_ID, String.valueOf(dto.contactId));
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.contactName))) {
			editedValues.put(CONTACT_NAME, dto.contactName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.contactAddress))) {
			editedValues.put(CONTACT_ADDRESS, dto.contactAddress);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.contactAddress))) {
			editedValues.put(CONTACT_PLZ, dto.contactPLZ);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.contactStadt))) {
			editedValues.put(CONTACT_STADT, dto.contactStadt);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.firstName))) {
			editedValues.put(FIRST_NAME, dto.firstName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.lastName))) {
			editedValues.put(LAST_NAME, dto.lastName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.sex))) {
			editedValues.put(SEX, String.valueOf(dto.sex));
		}
		return editedValues;
	}

	/**
	 * 
	 * get list contact
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: ListContactViewDTO
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public ListContactViewDTO getListContact(Bundle data) {
		ListContactViewDTO listContact = new ListContactViewDTO();
		String page = "";
		if(!StringUtil.isNullOrEmpty(data.getString(IntentConstants.INTENT_PAGE))){
			page = data.getString(IntentConstants.INTENT_PAGE);
		}
		StringBuffer queryGetlistContact = new StringBuffer();
		ArrayList<String> listParams = new ArrayList<String>();
		queryGetlistContact.append("select * from CONTACT_TABLET ");
		String getCountContact = " select count(*) as total_row from ("
				+ queryGetlistContact.toString() + ") ";

		String[] paramsGetListProduct = new String[] {};
		paramsGetListProduct = listParams
				.toArray(new String[listParams.size()]);

		Cursor c = null;
		Cursor cTmp = null;
		long startTime = System.currentTimeMillis();
		try {
			// get total row first
			Log.v("start time: ", String.valueOf(System.currentTimeMillis()));
			int total = 0;
			cTmp = rawQuery(getCountContact, paramsGetListProduct);
			if (cTmp != null) {
				cTmp.moveToFirst();
				total = cTmp.getInt(0);
				listContact.totalContactList = total;
			}
			// end
			c = rawQuery(queryGetlistContact + page, paramsGetListProduct);

			if (c != null) {

				if (c.moveToFirst()) {
					do {
						ContactDTO contactInfo = new ContactDTO();

						contactInfo.initLogDTOFromCursor(c);
						listContact.listContact.add(contactInfo);
					} while (c.moveToNext());
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (c != null) {
				c.close();
			}
			if (cTmp != null) {
				cTmp.close();
			}
		}

		Log.v("endTime", String.valueOf(System.currentTimeMillis() - startTime));

		return listContact;
	}
}
