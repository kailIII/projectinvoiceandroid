/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;


/**
 * apstract class for controller
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public abstract class AbstractController {
	// handle view event
	abstract public void handleViewEvent(ActionEvent e);
	// handle switch activity
    abstract public void handleSwitchActivity(ActionEvent e);
}

