/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.lib.INVOICE_ORDER_TABLET;

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
	// ten lop
	public long contactId;
	// ten lop de search
	public String contactName;
	// nam hoc
	public String project;
	// khoi hoc
	public String orderedOn;
	// phan ban
	public String delivery;
	// giao vien chu nhiem
	public String customerNumber;
	// buoi hoc (sang / chieu)
	public String invoiceOrderNumber; // 1: Male / 0: FeMale

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
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.INVOICE_ORDER_ID) >= 0) {
			invoiceOrderId = c.getLong(c
					.getColumnIndex(INVOICE_ORDER_TABLET.INVOICE_ORDER_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.CONTACT_ID) >= 0) {
			contactId = c.getLong(c
					.getColumnIndex(INVOICE_ORDER_TABLET.CONTACT_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.CONTACT_NAME) >= 0) {
			contactName = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.CONTACT_NAME));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.PROJECT) >= 0) {
			project = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.PROJECT));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.ORDERED_ON) >= 0) {
			orderedOn = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.ORDERED_ON));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.DELIVERY) >= 0) {
			delivery = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.DELIVERY));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.CUSTOMER_NUMBER) >= 0) {
			customerNumber = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.CUSTOMER_NUMBER));
		}
		if (c.getColumnIndex(INVOICE_ORDER_TABLET.INVOICE_ORDER_NUMBER) >= 0) {
			invoiceOrderNumber = c.getString(c
					.getColumnIndex(INVOICE_ORDER_TABLET.INVOICE_ORDER_NUMBER));
		}

	}

}
