/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.constant.Constants;
import com.hector.invoice.lib.INVOICE_ORDER_DETAIL_TABLE;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 4, 2013
 */
public class InvoiceOrderDetailDTO extends AbstractTableDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ma lop
	public long invoiceOrderDetailId;
	public long invoiceOrderId;
	public String pos = Constants.STR_BLANK;
	public String designation = Constants.STR_BLANK;
	public String art_nr = Constants.STR_BLANK;
	public String quantity = Constants.STR_BLANK;
	public String single_price = Constants.STR_BLANK;
	public String total = "0";

	public InvoiceOrderDetailDTO() {
		super(TableType.INVOICE_ORDER_DETAIL);
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
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.INVOICE_ORDER_DETAIL_ID) >= 0) {
			invoiceOrderDetailId = c
					.getLong(c
							.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.INVOICE_ORDER_DETAIL_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.INVOICE_ORDER_ID) >= 0) {
			invoiceOrderId = c
					.getLong(c
							.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.INVOICE_ORDER_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.POS) >= 0 && c.getString(c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.POS)) != null) {
			pos = c.getString(c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.POS));
		} else {
			pos = "1";
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.DESIGNATION) >= 0 && c.getString(c
				.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.DESIGNATION)) != null ) {
			designation = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.DESIGNATION));
		} else {
			designation = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.ART_NR) >= 0 && c.getString(c
				.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.ART_NR)) != null ) {
			art_nr = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.ART_NR));
		} else {
			art_nr = "";
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.QUANTITY) >= 0 && c.getString(c
				.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.QUANTITY)) != null ) {
			quantity = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.QUANTITY));
		} else {
			quantity = "0";
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.SINGLE_PRICE) >= 0) {
			single_price = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.SINGLE_PRICE));
		} else {
			single_price = "0";
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.TOTAL) >= 0) {
			total = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLE.TOTAL));
		} else {
			total = "0";
		}

	}

}
