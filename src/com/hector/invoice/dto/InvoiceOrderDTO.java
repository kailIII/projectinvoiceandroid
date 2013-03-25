/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.constant.Constants;
import com.hector.invoice.lib.INVOICE_ORDER_TABLE;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 4, 2013`
 */
public class InvoiceOrderDTO extends AbstractTableDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ma lop
	public long invoiceOrderId;
	// invoice name
	public String invoiceName = Constants.STR_BLANK;
	// contactId
	public long contactId;
	// contactName
	public String contactName = Constants.STR_BLANK;
	// project
	public String project = Constants.STR_BLANK;
	// orderedOn
	public String orderedOn = Constants.STR_BLANK;
	// delivery
	public String delivery = Constants.STR_BLANK;
	// customerNumber
	public String customerNumber = Constants.STR_BLANK;
	// invoiceOrderNumber
	public String invoiceOrderNumber = Constants.STR_BLANK;

	public InvoiceOrderDTO() {
		super(TableType.INVOICE_ORDER);
	}

	/**
	 * 
	 * Khoi tao thong tin sau khi query database
	 * 
	 * @param c
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public void initLogDTOFromCursor(Cursor c) {
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_ID) >= 0) {
			invoiceOrderId = c.getLong(c
					.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.CONTACT_ID) >= 0) {
			contactId = c.getLong(c
					.getColumnIndex(INVOICE_ORDER_TABLE.CONTACT_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_NAME) >= 0) {
			invoiceName = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_NAME));
		} else {
			invoiceName = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.CONTACT_NAME) >= 0) {
			contactName = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.CONTACT_NAME));
		} else {
			contactName = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.PROJECT) >= 0) {
			project = c
					.getString(c.getColumnIndex(INVOICE_ORDER_TABLE.PROJECT));
		} else {
			project = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.ORDERED_ON) >= 0) {
			orderedOn = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.ORDERED_ON));
		} else {
			orderedOn = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.DELIVERY) >= 0) {
			delivery = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.DELIVERY));
		} else {
			delivery = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.CUSTOMER_NUMBER) >= 0) {
			customerNumber = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.CUSTOMER_NUMBER));
		} else {
			customerNumber = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_NUMBER) >= 0) {
			invoiceOrderNumber = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLE.INVOICE_ORDER_NUMBER));
		} else {
			invoiceOrderNumber = "";
		}

	}

}
