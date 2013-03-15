/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import java.io.Serializable;

import android.database.Cursor;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 15, 2013
 */
public class InvoiceInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvoiceOrderDTO invoiceOrderInfo = new InvoiceOrderDTO();
	public ContactDTO contactInvoice = new ContactDTO();

	public InvoiceInfoDTO() {
		invoiceOrderInfo = new InvoiceOrderDTO();
		contactInvoice = new ContactDTO();
	}

	/**
	 * 
	 * init data
	 * 
	 * @param @param c
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void initDataWithCursor(Cursor c) {
		invoiceOrderInfo.initLogDTOFromCursor(c);
		contactInvoice.initLogDTOFromCursor(c);
	}
}
