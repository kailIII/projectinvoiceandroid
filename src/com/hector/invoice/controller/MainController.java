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
import com.hector.invoice.views.CompanyInfoView;
import com.hector.invoice.views.ContactlistView;
import com.hector.invoice.views.CreateUpdateContactInfoView;
import com.hector.invoice.views.InputInvoiceView;
import com.hector.invoice.views.InvoiceOrderListView;
import com.hector.invoice.views.TabExportInvoiceOrder;

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
				case ActionEventConstant.REQUEST_DELETE_CONTACT:
					MainModelServices.getInstance().requestDeleteContact(e);
					break;
				case ActionEventConstant.REQUEST_UPDATE_CREATE_CONTACT:
					MainModelServices.getInstance()
							.requestUpdateOrCreateContact(e);
					break;
				case ActionEventConstant.REQUEST_GET_LIST_INVOICE_ORDER:
					MainModelServices.getInstance().requestGetListInvoiceOrder(
							e);
					break;
				case ActionEventConstant.REQUEST_SAVE_INVOICE:
					MainModelServices.getInstance().requestSaveInvoiceInfoToDB(
							e);
					break;
				case ActionEventConstant.REQUEST_GET_DETAIL_INVOICE:
					MainModelServices.getInstance()
							.getListInvoiceOrderDetail(e);
					break;
				case ActionEventConstant.GET_COMPANY_INFO:
					MainModelServices.getInstance().getCompanyInfo(e);
					break;
				case ActionEventConstant.REQUEST_UPDATE_COMPANY_INFO:
					MainModelServices.getInstance().requestUpdateCompanyInfo(e);
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
			break;
		}
		case ActionEventConstant.SHOW_CRATE_CONTACT_VIEW: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, CreateUpdateContactInfoView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			break;
		}
		case ActionEventConstant.SHOW_INVOICE_LIST_VIEW: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, InvoiceOrderListView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			break;
		}
		case ActionEventConstant.SHOW_COMPANY_INFO_VIEW: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, CompanyInfoView.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
			break;
		}
		case ActionEventConstant.SHOW_EXPORT_INVOICE_SCREEN: {
			BaseActivity sender = (BaseActivity) e.sender;
			extras = (Bundle) e.viewData;
			intent = new Intent(sender, TabExportInvoiceOrder.class);
			intent.putExtras(extras);
			sender.startActivity(intent);
//			sender.closeProgressDialog();
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
