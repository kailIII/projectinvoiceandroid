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

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.InvoiceInfoDTO;
import com.hector.invoice.dto.InvoiceOrderDTO;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 3, 2013
 */
public class INVOICE_ORDER_TABLE extends ABSTRACT_TABLE {

	public static final String INVOICE_ORDER_ID = "INVOICE_ORDER_ID";
	public static final String INVOICE_ORDER_NAME = "INVOICE_ORDER_NAME";// invoice
																			// name
	public static final String CONTACT_ID = "CONTACT_ID";
	public static final String CONTACT_NAME = "CONTACT_NAME"; // ansprechpartner
	public static final String PROJECT = "PROJECT"; // projekt
	public static final String ORDERED_ON = "ORDERED_ON"; // bestellt am:
	public static final String DELIVERY = "DELIVERY"; // lieferdatum
	public static final String CUSTOMER_NUMBER = "CUSTOMER_NUMBER"; // kunden-Nr
	public static final String INVOICE_ORDER_NUMBER = "INVOICE_ORDER_NUMBER"; // Rechnungsnummer

	// COMPANY TABLE
	public static final String INVOICE_ORDER_TABLE = "INVOICE_ORDER_TABLE";

	public INVOICE_ORDER_TABLE(SQLiteDatabase mDB) {
		this.tableName = INVOICE_ORDER_TABLE;
		this.columns = new String[] { INVOICE_ORDER_ID, INVOICE_ORDER_NAME,
				CONTACT_ID, CONTACT_NAME, PROJECT, ORDERED_ON, DELIVERY,
				CUSTOMER_NUMBER, INVOICE_ORDER_NUMBER };
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
		ColumnTable invoiceOrderId = new ColumnTable(INVOICE_ORDER_ID,
				ColumnTable.DATA_TYPE_INTEGER, true, true, false, true,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(invoiceOrderId);

		// column INVOICE_ORDER_NAME
		ColumnTable invoiceName = new ColumnTable(INVOICE_ORDER_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(invoiceName);

		// column CONTACT_NAME
		ColumnTable contactid = new ColumnTable(CONTACT_ID,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactid);

		// column CONTACT_ADDRESS
		ColumnTable contactName = new ColumnTable(CONTACT_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(contactName);

		// column CONTACT_PLZ
		ColumnTable project = new ColumnTable(PROJECT,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(project);

		// column CONTACT_STADT
		ColumnTable orderedOn = new ColumnTable(ORDERED_ON,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(orderedOn);

		// column FIRST_NAME
		ColumnTable delivery = new ColumnTable(DELIVERY,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(delivery);

		// column LAST_NAME
		ColumnTable customerNumber = new ColumnTable(CUSTOMER_NUMBER,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(customerNumber);
		// column SEX
		ColumnTable invoiceOrderNumber = new ColumnTable(INVOICE_ORDER_NUMBER,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(invoiceOrderNumber);

		return listColumn;
	}

	/**
	 * insert row into db
	 */
	@Override
	protected long insert(AbstractTableDTO dto) {
		ContentValues value = initDataRow((InvoiceOrderDTO) dto);
		return insert(null, value);
	}

	/**
	 * 
	 * update company info from company dto
	 * 
	 * @param dto
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public int update(InvoiceOrderDTO dto) {
		ContentValues value = initDataRow(dto);
		String[] params = { String.valueOf(dto.invoiceOrderId) };
		return update(value, INVOICE_ORDER_ID + " = ?", params);
	}

	/**
	 * update company info from abstract table dto
	 */
	@Override
	protected long update(AbstractTableDTO dto) {
		InvoiceOrderDTO cusDTO = (InvoiceOrderDTO) dto;
		ContentValues value = initDataRow(cusDTO);
		String[] params = { String.valueOf(cusDTO.invoiceOrderId) };
		return update(value, INVOICE_ORDER_ID + " = ?", params);
	}

	/**
	 * 
	 * remove row in db
	 * 
	 * @param id
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public int delete(String id) {
		String[] params = { id };
		return delete(INVOICE_ORDER_ID + " = ?", params);
	}

	@Override
	protected long delete(AbstractTableDTO dto) {
		InvoiceOrderDTO cusDTO = (InvoiceOrderDTO) dto;
		String[] params = { String.valueOf(cusDTO.invoiceOrderId) };
		return delete(INVOICE_ORDER_ID + " = ?", params);
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
	public InvoiceOrderDTO getCompanyById(String id) {
		InvoiceOrderDTO invoiceOrderInfo = new InvoiceOrderDTO();
		Cursor c = null;
		try {
			String[] params = { id };
			c = query(INVOICE_ORDER_ID + " = ?", params, null, null, null);
			if (c != null) {
				if (c.moveToFirst()) {
					invoiceOrderInfo.initLogDTOFromCursor(c);
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

		return invoiceOrderInfo;
	}

	/**
	 * 
	 * init log
	 * 
	 * @param c
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public InvoiceOrderDTO initLogDTOFromCursor(Cursor c) {
		InvoiceOrderDTO invoiceOrderInfo = new InvoiceOrderDTO();
		invoiceOrderInfo.initLogDTOFromCursor(c);
		return invoiceOrderInfo;
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
	 * @date: Dec 10, 2012
	 */
	public ContentValues initDataRow(InvoiceOrderDTO dto) {
		ContentValues editedValues = new ContentValues();
		if (dto.invoiceOrderId > 0) {
			editedValues.put(INVOICE_ORDER_ID,
					String.valueOf(dto.invoiceOrderId));
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.contactId))) {
			editedValues.put(CONTACT_ID, String.valueOf(dto.contactId));
		}
		if (String.valueOf(dto.invoiceName) != null) {
			editedValues.put(INVOICE_ORDER_NAME, dto.invoiceName);
		}
		if (String.valueOf(dto.contactName) != null) {
			editedValues.put(CONTACT_NAME, dto.contactName);
		}
		if (String.valueOf(dto.project) != null) {
			editedValues.put(PROJECT, dto.project);
		}
		if (String.valueOf(dto.project) != null) {
			editedValues.put(ORDERED_ON, dto.orderedOn);
		}
		if (String.valueOf(dto.delivery) != null) {
			editedValues.put(DELIVERY, dto.delivery);
		}
		if (String.valueOf(dto.customerNumber) != null) {
			editedValues.put(CUSTOMER_NUMBER, dto.customerNumber);
		}
		if (String.valueOf(dto.invoiceOrderNumber) != null) {
			editedValues.put(INVOICE_ORDER_NUMBER, dto.invoiceOrderNumber);
		}
		return editedValues;
	}

	/**
	 * 
	 * get list invoice order
	 * 
	 * @param @return
	 * @return: ListInvoiceNumberInfoView
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public ArrayList<InvoiceInfoDTO> getListInvoiceOrder(Bundle data) {
		ArrayList<InvoiceInfoDTO> listInvoice = new ArrayList<InvoiceInfoDTO>();
		StringBuffer queryGetlistContact = new StringBuffer();
		ArrayList<String> listParams = new ArrayList<String>();
		queryGetlistContact
				.append("select IO.*, CT.* from INVOICE_ORDER_TABLE IO LEFT JOIN CONTACT_TABLE CT ON IO.CONTACT_ID = CT.CONTACT_ID");

		String[] paramsGetListProduct = new String[] {};
		paramsGetListProduct = listParams
				.toArray(new String[listParams.size()]);

		Cursor c = null;
		Cursor cTmp = null;
		try {
			c = rawQuery(queryGetlistContact.toString(), paramsGetListProduct);

			if (c != null) {

				if (c.moveToFirst()) {
					do {
						InvoiceInfoDTO invoiceInfo = new InvoiceInfoDTO();

						invoiceInfo.initDataWithCursor(c);
						listInvoice.add(invoiceInfo);
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

		return listInvoice;
	}

}
