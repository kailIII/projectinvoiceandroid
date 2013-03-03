/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.dto;

import java.io.Serializable;

/**
 * abstract class AbstractTableDTO
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
@SuppressWarnings("serial")
public abstract class AbstractTableDTO implements Serializable {
	public enum TableAction {
		INSERT, UPDATE, DELETE,

	}

	public enum TableType {
		LIST_COMPANY, LIST_CONTACT, INVOICE_ORDER, INVOICE_ORDER_DETAIL,

	}

	public enum DATA_TYPE {
		SEQUENCE, DATE, SYSDATE, COMMON, OPERATION, NULL
	}

	public enum DATA_VALUE {
		sysdate,
	}

	private TableType type;
	private TableAction action;

	public TableAction getAction() {
		return action;
	}

	public void setAction(TableAction action) {
		this.action = action;
	}

	public TableType getType() {
		return type;
	}

	public void setType(TableType type) {
		this.type = type;
	}

	protected AbstractTableDTO(TableType type) {
		this.type = type;
	}

	protected AbstractTableDTO() {
	}
}
