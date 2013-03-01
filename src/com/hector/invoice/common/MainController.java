/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.views.InputInvoiceView;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Feb 28, 2013
 */
public class MainController extends AbstractController {
	// instance
	static MainController instance;

	protected MainController() {
	}

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobaid.jobaids.AbstractController#handleViewEvent(com.jobaid.jobaids
	 * .ActionEvent)
	 */
	@Override
	public void handleViewEvent(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobaid.jobaids.AbstractController#handleSwitchActivity(com.jobaid
	 * .jobaids.ActionEvent)
	 */
	@Override
	public void handleSwitchActivity(ActionEvent e) {
		// TODO Auto-generated method stub
		Activity base = (Activity) e.sender;
		Intent intent;
		Bundle extras;
		switch (e.action) {
		case ActionEventConstant.SHOW_MAIN_MENU: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, InputInvoiceView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			sender.finish();
			break;
		}
		default:
			break;
		}
	}
}
