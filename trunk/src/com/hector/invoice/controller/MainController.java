/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.hector.invoice.common.AbstractController;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.ErrorConstants;
import com.hector.invoice.model.MainModelServices;
import com.hector.invoice.views.ContactlistView;
import com.hector.invoice.views.InputInvoiceView;

/**
 * Mo ta muc dich cua lop
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
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
	 * com.hector.invoice.common.AbstractController#handleViewEvent(com.hector
	 * .invoice.common.ActionEvent)
	 */
	@Override
	public void handleViewEvent(final ActionEvent e) {
		// TODO Auto-generated method stub
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			protected Void doInBackground(Void... params) {
				switch (e.action) {
				case ActionEventConstant.GET_LIST_CONTACT:
					MainModelServices.getInstance().requestGetListContact(e);
					break;
				default:// test
					// UserModel.getInstance().requestTest(e);
					break;
				}
				// }
				return null;
			}
		};
		task.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.AbstractController#handleSwitchActivity(com
	 * .hector.invoice.common.ActionEvent)
	 */
	@Override
	public void handleSwitchActivity(ActionEvent e) {
		// TODO Auto-generated method stub
		Activity base = (Activity) e.sender;
		Intent intent;
		Bundle extras;
		switch (e.action) {
		case ActionEventConstant.SHOW_INPUT_INVOICE_VIEW: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, InputInvoiceView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			sender.finish();
			break;
		}
		case ActionEventConstant.SHOW_CONTACT_LIST_VIEW: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, ContactlistView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			sender.finish();
			break;
		}
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.AbstractController#handleModelEvent(com.hector
	 * .invoice.common.ModelEvent)
	 */
	@Override
	public void handleModelEvent(final ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_CODE_SUCCESS) {
			final ActionEvent e = modelEvent.getActionEvent();
			if (e.sender != null) {
				if (e.sender instanceof BaseActivity) {
					final BaseActivity sender = (BaseActivity) e.sender;
					if (sender.isFinished)
						return;
					sender.runOnUiThread(new Runnable() {
						public void run() {
							// TODO Auto-generated method stub
							sender.handleModelViewEvent(modelEvent);
						}
					});
				}
			} else {
				handleErrorModelEvent(modelEvent);
			}
		} else {
			handleErrorModelEvent(modelEvent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.AbstractController#handleErrorModelEvent(com
	 * .hector.invoice.common.ModelEvent)
	 */
	@Override
	public void handleErrorModelEvent(final ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent e = modelEvent.getActionEvent();
		if (e.sender instanceof BaseActivity) {
			final BaseActivity sender = (BaseActivity) e.sender;
			if (sender.isFinished) {
				return;
			}
			sender.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					sender.handleErrorModelViewEvent(modelEvent);
				}
			});
		}
	}

}
