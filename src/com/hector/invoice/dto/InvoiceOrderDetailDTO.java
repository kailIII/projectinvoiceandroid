/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.constant.Constants;
import com.hector.invoice.lib.INVOICE_ORDER_DETAIL_TABLET;

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
	public String pos  = Constants.STR_BLANK;
	public String designation  = Constants.STR_BLANK;
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
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.INVOICE_ORDER_DETAIL_ID) >= 0) {
			invoiceOrderDetailId = c
					.getLong(c
							.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.INVOICE_ORDER_DETAIL_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.INVOICE_ORDER_ID) >= 0) {
			invoiceOrderId = c
					.getLong(c
							.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.INVOICE_ORDER_ID));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.POS) >= 0) {
			pos = c.getString(c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.POS));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.DESIGNATION) >= 0) {
			designation = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.DESIGNATION));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.ART_NR) >= 0) {
			art_nr = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.ART_NR));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.QUANTITY) >= 0) {
			quantity = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.QUANTITY));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.SINGLE_PRICE) >= 0) {
			single_price = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.SINGLE_PRICE));
		}
		if (c.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.TOTAL) >= 0) {
			total = c.getString(c
					.getColumnIndex(INVOICE_ORDER_DETAIL_TABLET.TOTAL));
		}

	}

}
