/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.lib.SQLUtils;

/**
 * Screen input Invoice to Save to PDF file
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Feb 28, 2013
 */
public class InputInvoiceView extends BaseActivity {
	// icon new invoice
	ImageView ivNewInvoice;
	// icon open invoice
	ImageView ivOpen;
	// icon save invoice
	ImageView ivSave;
	// icon export invoice
	ImageView ivExport;
	// icon add order number
	ImageView ivAdd;
	// button btThema
	Button btThema;
	// icon setting
	ImageView ivSetting;
	// icon contact
	ImageView ivContact;
	// table tblListOrderNumber
	TableLayout tblListOrderNumber;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_main_page_view);

		// init view control
		this.initViewControl();
		SQLUtils.getInstance();
	}

	/**
	 * 
	 * init view control
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void initViewControl() {
		ivNewInvoice = (ImageView) findViewById(R.id.ivNewInvoice);
		ivNewInvoice.setOnClickListener(this);
		ivOpen = (ImageView) findViewById(R.id.ivOpen);
		ivOpen.setOnClickListener(this);
		ivSave = (ImageView) findViewById(R.id.ivSave);
		ivSave.setOnClickListener(this);
		ivExport = (ImageView) findViewById(R.id.ivExport);
		ivExport.setOnClickListener(this);
		ivAdd = (ImageView) findViewById(R.id.ivAdd);
		ivAdd.setOnClickListener(this);
		ivSetting = (ImageView) findViewById(R.id.ivSetting);
		ivSetting.setOnClickListener(this);
		ivContact = (ImageView) findViewById(R.id.ivContact);
		ivContact.setOnClickListener(this);
		btThema = (Button) findViewById(R.id.btThema);
		btThema.setOnClickListener(this);
		tblListOrderNumber = (TableLayout) findViewById(R.id.tblListOrderNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateView(java.lang.String,
	 * android.content.Context, android.util.AttributeSet)
	 */
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(name, context, attrs);

	}

	/**
	 * 
	 * show contact list view
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void gotoContactListView() {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_CONTACT_LIST_VIEW;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == ivAdd) {

		} else if (v == ivContact) {
			this.gotoContactListView();
		} else if (v == ivExport) {

		} else if (v == ivNewInvoice) {

		} else if (v == ivOpen) {

		} else if (v == ivSave) {

		} else if (v == ivSetting) {

		} else if (v == btThema) {

		} else {
			super.onClick(v);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.BaseActivity#onCreateOptionsMenu(android.view
	 * .Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// MenuInflater inflater = getMenuInflater();
		// inflater.inflate(R.layout.main_menu_layout, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onMenuItemSelected(int, android.view.MenuItem)
	 */
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.BaseActivity#handleModelViewEvent(com.hector
	 * .invoice.common.ModelEvent)
	 */
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent event = modelEvent.getActionEvent();
		switch (event.action) {
		default:
			super.handleModelViewEvent(modelEvent);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.BaseActivity#handleErrorModelViewEvent(com.
	 * hector.invoice.common.ModelEvent)
	 */
	@Override
	public void handleErrorModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		super.handleErrorModelViewEvent(modelEvent);
	}
}
