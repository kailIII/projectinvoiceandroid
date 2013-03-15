/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * list invoice number info
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 15, 2013
 */
public class ListInvoiceNumberInfoView implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<InvoiceOrderNumberInfoView> listInvoiceOrder = new ArrayList<InvoiceOrderNumberInfoView>();
	public int numInvoiceOrder = 0;

	public ListInvoiceNumberInfoView() {
		listInvoiceOrder = new ArrayList<InvoiceOrderNumberInfoView>();
		numInvoiceOrder = 0;
	}
}
