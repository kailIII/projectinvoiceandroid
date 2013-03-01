/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

/**
 * actionevent object to swith activity
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */

public class ActionEvent {
	// action
	public int action;
	// model data (data get from model level)
	public Object modelData;
	// view data (data send from view level)
	public Object viewData;
	// user data
	public Object userData;
	// sender (view send action)
	public Object sender;
	// tag
	public int tag = 0;

	public AbstractController controller;

	public void reset() {
		action = 0;
		modelData = null;
		viewData = null;
		userData = null;
		sender = null;
	}
}
