/**
 * Copyright 2011 Viettel Telecom. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import android.view.View;

/**
 * onevent control listener
 * @author: HAi
 * @since : May 21, 2011
 * 
 */
public interface OnEventControlListener{
	void onEvent( int eventType, View control, Object data);
}
