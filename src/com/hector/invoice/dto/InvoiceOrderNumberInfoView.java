/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import java.io.Serializable;
import java.util.ArrayList;

import android.database.Cursor;

/**
 * invoice order infor view
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 15, 2013
 */
public class InvoiceOrderNumberInfoView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvoiceInfoDTO invoiceOrder;
	public ArrayList<InvoiceOrderDetailDTO> listOrderDetail;

	public InvoiceOrderNumberInfoView() {
		invoiceOrder = new InvoiceInfoDTO();
		listOrderDetail = new ArrayList<InvoiceOrderDetailDTO>();
	}

	/**
	 * 
	 * init data with cursor
	 * 
	 * @param @param c
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void parserInvoiceDataWithCursor(Cursor c) {
		invoiceOrder.initDataWithCursor(c);
	}
}
