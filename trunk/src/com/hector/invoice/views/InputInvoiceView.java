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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;
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
	LinearLayout tblListOrderNumber;
	EditText etAnsprechpartner;
	EditText etFirma;
	EditText etAddress;
	EditText etPLZ;
	EditText etStadt;
	EditText etProject;
	EditText etOrderOn;
	EditText etDelivery;
	EditText etCustomerInfo;
	Button btSelectAnsprechpartner;

	// check creating invoice
	boolean isCreatingInvoice = false;

	// current contact
	ContactDTO myContact = new ContactDTO();
	// invoice info
	InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();

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
		// create file DB if not exist
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
		etAddress = (EditText) findViewById(R.id.etAddress);
		etCompanyInfo = (EditText) findViewById(R.id.etCompanyInfo);
		etCustomerInfo = (EditText) findViewById(R.id.etCustomerInfo);
		etDelivery = (EditText) findViewById(R.id.etDelivery);
		etNumber = (EditText) findViewById(R.id.etNumber);
		etOrderOn = (EditText) findViewById(R.id.etOrderOn);
		etPLZ = (EditText) findViewById(R.id.etPLZ);
		etProject = (EditText) findViewById(R.id.etProject);
		etStadt = (EditText) findViewById(R.id.etStadt);

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
		btSelectAnsprechpartner = (Button) findViewById(R.id.btSelectAnsprechpartner);
		btSelectAnsprechpartner.setOnClickListener(this);
		btThema = (Button) findViewById(R.id.btThema);
		btThema.setOnClickListener(this);
		tblListOrderNumber = (LinearLayout) findViewById(R.id.tblListOrderNumber);
		this.updateAllControl();
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
	public void gotoContactListView(boolean isGetContact) {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		data.putBoolean(IntentConstants.INTENT_GET_CONTACT_OBJECT, isGetContact);
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_CONTACT_LIST_VIEW;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#receiveBroadcast(int,
	 * android.os.Bundle)
	 */
	@Override
	public void receiveBroadcast(int action, Bundle bundle) {
		// TODO Auto-generated method stub
		if (action == ActionEventConstant.BROAD_CAST_CONTACT_OBJECT) {
			updateContactDataForScreen((ContactDTO) bundle
					.getSerializable(IntentConstants.INTENT_CONTACT_OBJECT));
		} else if (action == ActionEventConstant.BROAD_CAST_INVOICE_OBJECT) {
			invoiceInfo = (InvoiceOrderNumberInfoView) bundle
					.getSerializable(IntentConstants.INTENT_INVOICE_INFO);
		}
		super.receiveBroadcast(action, bundle);
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
			this.createNewOrderNumber();
		} else if (v == ivContact) {
			this.gotoContactListView(false); // show list contact view
		} else if (v == ivExport) {
			showExportInvoiceScreen(); // export invoice
		} else if (v == ivNewInvoice) {
			this.isCreatingInvoice = true;
			this.updateAllControl(); // update all control
		} else if (v == ivOpen) {
			showInvoiceList(); // show list invoice
		} else if (v == ivSave) {
			requestSaveInvoice(); // request save invoice
		} else if (v == ivSetting) {
			this.showCompanyInfo(); // show company info
		} else if (v == btThema) {

		} else if (v == btSelectAnsprechpartner) {
			gotoContactListView(true);
		} else {

			super.onClick(v);
		}
	}

	/**
	 * 
	 * request save invoice
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void requestSaveInvoice() {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.REQUEST_SAVE_INVOICE;
		MainController.getInstance().handleViewEvent(event);
	}

	/**
	 * 
	 * show company info
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void showInvoiceList() {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_INVOICE_LIST_VIEW;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/**
	 * 
	 * show export invoice screen
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void showExportInvoiceScreen() {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_EXPORT_INVOICE_SCREEN;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/**
	 * 
	 * show company info
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void showCompanyInfo() {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_COMPANY_INFO_VIEW;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/**
	 * 
	 * create new row order for invoice
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void createNewOrderNumber() {
		DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
				this, tblListOrderNumber);
		tblListOrderNumber.addView(rowOrder);
	}

	/**
	 * 
	 * update control of screen
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 13, 2013
	 */
	public void updateAllControl() {
		if (this.isCreatingInvoice) {
			// clear data
			this.etAddress.setText("");
			this.etCompanyInfo.setText("");
			this.etCustomerInfo.setText("");
			this.etDelivery.setText("");
			this.etNumber.setText("");
			this.etOrderOn.setText("");
			this.etPLZ.setText("");
			this.etProject.setText("");
			this.etStadt.setText("");

			this.ivSave.setVisibility(View.VISIBLE);
			this.ivExport.setVisibility(View.VISIBLE);
			this.ivAdd.setVisibility(View.VISIBLE);
			this.btThema.setVisibility(View.VISIBLE);
		} else {
			ivSave.setVisibility(View.INVISIBLE);
			ivExport.setVisibility(View.INVISIBLE);
			ivAdd.setVisibility(View.INVISIBLE);
			btThema.setVisibility(View.INVISIBLE);
		}
	}
	
	public void updateInvoiceDataForScreen(){
		etAddress.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress);
		etCompanyInfo.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactName);
		etCustomerInfo
	}

	/**
	 * 
	 * update contact data for screen
	 * 
	 * @author: HaiTC3
	 * @param contactObj
	 * @return: void
	 * @throws:
	 * @since: Mar 15, 2013
	 */
	public void updateContactDataForScreen(ContactDTO contactObj) {
		this.myContact = contactObj;
		etNumber.setText(contactObj.contactName);
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
