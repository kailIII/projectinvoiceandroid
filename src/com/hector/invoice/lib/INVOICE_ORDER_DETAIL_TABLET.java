/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 3, 2013
 */
public class INVOICE_ORDER_DETAIL_TABLET extends ABSTRACT_TABLE {

	public static final String INVOICE_ORDER_DETAIL_ID = "INVOICE_ORDER_DETAIL_ID";
	public static final String INVOICE_ORDER_ID = "INVOICE_ORDER_ID";
	public static final String POS = "POS";// pos
	public static final String DESIGNATION = "DESIGNATION";// bezeichnung
	public static final String ART_NR = "ART_NR";// ART_NR
	public static final String QUANTITY = "QUANTITY";// menge
	public static final String SINGLE_PRICE = "SINGLE_PRICE";// einzelpreis
	public static final String TOTAL = "TOTAL"; // gesamt

	// COMPANY TABLE
	public static final String INVOICE_ORDER_DETAIL_TABLET = "INVOICE_ORDER_DETAIL_TABLET";

	public INVOICE_ORDER_DETAIL_TABLET(SQLiteDatabase mDB) {
		this.tableName = INVOICE_ORDER_DETAIL_TABLET;
		this.columns = new String[] { INVOICE_ORDER_DETAIL_ID,
				INVOICE_ORDER_ID, POS, DESIGNATION, ART_NR, QUANTITY,
				SINGLE_PRICE, TOTAL };
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
		ColumnTable invoiceOrderDetailId = new ColumnTable(
				INVOICE_ORDER_DETAIL_ID, ColumnTable.DATA_TYPE_INTEGER, true,
				true, false, true, ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(invoiceOrderDetailId);

		// column CONTACT_ID
		ColumnTable invoiceOrderId = new ColumnTable(INVOICE_ORDER_ID,
				ColumnTable.DATA_TYPE_INTEGER, false, false, false, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(invoiceOrderId);

		// column CONTACT_NAME
		ColumnTable pos = new ColumnTable(POS, ColumnTable.DATA_TYPE_TEXT,
				false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(pos);

		// column CONTACT_ADDRESS
		ColumnTable designation = new ColumnTable(DESIGNATION,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(designation);

		// column CONTACT_PLZ
		ColumnTable art_nr = new ColumnTable(ART_NR,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(art_nr);

		// column CONTACT_STADT
		ColumnTable quantity = new ColumnTable(QUANTITY,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(quantity);

		// column FIRST_NAME
		ColumnTable single_price = new ColumnTable(SINGLE_PRICE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(single_price);

		// column LAST_NAME
		ColumnTable total = new ColumnTable(TOTAL, ColumnTable.DATA_TYPE_TEXT,
				false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(total);

		return listColumn;
	}

	/**
	 * insert row into db
	 */
	@Override
	protected long insert(AbstractTableDTO dto) {
		ContentValues value = initDataRow((InvoiceOrderDetailDTO) dto);
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
	public int update(InvoiceOrderDetailDTO dto) {
		ContentValues value = initDataRow(dto);
		String[] params = { String.valueOf(dto.invoiceOrderDetailId) };
		return update(value, INVOICE_ORDER_DETAIL_ID + " = ?", params);
	}

	/**
	 * update company info from abstract table dto
	 */
	@Override
	protected long update(AbstractTableDTO dto) {
		InvoiceOrderDetailDTO cusDTO = (InvoiceOrderDetailDTO) dto;
		ContentValues value = initDataRow(cusDTO);
		String[] params = { String.valueOf(cusDTO.invoiceOrderDetailId) };
		return update(value, INVOICE_ORDER_DETAIL_ID + " = ?", params);
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
		return delete(INVOICE_ORDER_DETAIL_ID + " = ?", params);
	}

	@Override
	protected long delete(AbstractTableDTO dto) {
		InvoiceOrderDetailDTO cusDTO = (InvoiceOrderDetailDTO) dto;
		String[] params = { String.valueOf(cusDTO.invoiceOrderDetailId) };
		return delete(INVOICE_ORDER_DETAIL_ID + " = ?", params);
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
	public InvoiceOrderDetailDTO getCompanyById(String id) {
		InvoiceOrderDetailDTO invoiceOrderInfo = new InvoiceOrderDetailDTO();
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
	public InvoiceOrderDetailDTO initLogDTOFromCursor(Cursor c) {
		InvoiceOrderDetailDTO invoiceOrderInfo = new InvoiceOrderDetailDTO();
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
	public ContentValues initDataRow(InvoiceOrderDetailDTO dto) {
		ContentValues editedValues = new ContentValues();
		editedValues.put(INVOICE_ORDER_DETAIL_ID,
				String.valueOf(dto.invoiceOrderDetailId));
		editedValues.put(INVOICE_ORDER_ID, String.valueOf(dto.invoiceOrderId));
		editedValues.put(POS, dto.pos);
		editedValues.put(DESIGNATION, dto.designation);
		editedValues.put(ART_NR, dto.art_nr);
		editedValues.put(QUANTITY, dto.quantity);
		editedValues.put(SINGLE_PRICE, dto.single_price);
		editedValues.put(TOTAL, dto.total);
		return editedValues;
	}

}
