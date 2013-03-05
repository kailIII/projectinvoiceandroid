/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * list contact dto
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class ListContactViewDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// list contact
	public ArrayList<ContactDTO> listContact = new ArrayList<ContactDTO>();
	// total contact
	public int totalContactList = 0;

	public ListContactViewDTO() {
		listContact = new ArrayList<ContactDTO>();
		totalContactList = 0;
	}
}
