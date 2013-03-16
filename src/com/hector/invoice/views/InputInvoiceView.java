/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;
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
	EditText etBestellt;
	EditText etLieferdatum;
	EditText etKunden;
	Button btSelectAnsprechpartner;
	TextView tvInvoiceNumber;
	RadioButton rbMale;
	RadioButton rbFeMale;

	EditText etInvoiceName;
	Button btOK;
	Button btCancel;
	// check creating invoice
	boolean isCreatingInvoice = false;

	// invoice info
	InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	// company info
	CompanyDTO myCompanyInfo = new CompanyDTO();
	// check load first
	boolean isDoneLoadFirst = false;

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

		if (!isDoneLoadFirst) {
			this.getCompanyInfo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	/**
	 * 
	 * get company info
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void getCompanyInfo() {
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.GET_COMPANY_INFO;
		MainController.getInstance().handleViewEvent(action);
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

		etAnsprechpartner = (EditText) findViewById(R.id.etAnsprechpartner);
		etFirma = (EditText) findViewById(R.id.etFirma);
		etAddress = (EditText) findViewById(R.id.etAddress);

		etPLZ = (EditText) findViewById(R.id.etPLZ);
		etProject = (EditText) findViewById(R.id.etProject);
		etStadt = (EditText) findViewById(R.id.etStadt);
		etBestellt = (EditText) findViewById(R.id.etBestellt);
		etLieferdatum = (EditText) findViewById(R.id.etLieferdatum);
		etKunden = (EditText) findViewById(R.id.etKunden);
		tvInvoiceNumber = (TextView) findViewById(R.id.tvInvoiceNumber);

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
		rbFeMale = (RadioButton) findViewById(R.id.rbFeMale);
		rbMale = (RadioButton) findViewById(R.id.rbMale);
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
			this.updateInvoiceDataForScreen();
		} else if (action == ActionEventConstant.BROAD_CAST_UPDATE_COMPANYINFO_SUCCESS) {
			this.getCompanyInfo();
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
			showDialogInputInvoiceName();
			// requestSaveInvoice(); // request save invoice
		} else if (v == ivSetting) {
			this.showCompanyInfo(); // show company info
		} else if (v == btThema) {

		} else if (v == btSelectAnsprechpartner) {
			gotoContactListView(true);
		} else if (v == btOK) {
			this.requestSaveInvoice();
		} else if (v == btCancel) {
			if (alertProductDetail != null && alertProductDetail.isShowing()) {
				alertProductDetail.dismiss();
			}
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
		generalInvoiceDataSaveToDB();
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		data.putSerializable(IntentConstants.INTENT_INVOICE_INFO,
				this.invoiceInfo);
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.REQUEST_SAVE_INVOICE;
		MainController.getInstance().handleViewEvent(event);
	}

	/**
	 * 
	 * general invoice data save to DB
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void generalInvoiceDataSaveToDB() {
		if (this.invoiceInfo == null) {
			this.invoiceInfo = new InvoiceOrderNumberInfoView();
		}
		if (this.invoiceInfo.invoiceOrder.contactInvoice == null) {
			this.invoiceInfo.invoiceOrder.contactInvoice = new ContactDTO();
		}

		// general invoice contact info
		this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress = etAddress
				.getText().toString();
		this.invoiceInfo.invoiceOrder.contactInvoice.firstName = etAnsprechpartner
				.getText().toString();
		this.invoiceInfo.invoiceOrder.contactInvoice.contactName = etFirma
				.getText().toString();
		this.invoiceInfo.invoiceOrder.contactInvoice.contactPLZ = etPLZ
				.getText().toString();
		this.invoiceInfo.invoiceOrder.contactInvoice.contactStadt = etStadt
				.getText().toString();

		// general invoice info
		if (this.invoiceInfo.invoiceOrder.contactInvoice.contactId > 0) {
			this.invoiceInfo.invoiceOrder.invoiceOrderInfo.contactId = this.invoiceInfo.invoiceOrder.contactInvoice.contactId;
			this.invoiceInfo.invoiceOrder.invoiceOrderInfo.contactName = this.invoiceInfo.invoiceOrder.contactInvoice.contactName;
		} else {
			this.invoiceInfo.invoiceOrder.invoiceOrderInfo.contactName = this.invoiceInfo.invoiceOrder.contactInvoice.contactName;
		}
		String invoiceName = etInvoiceName.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceName = invoiceName;

		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.project = etProject
				.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn = etBestellt
				.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber = etKunden
				.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery = etLieferdatum
				.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.contactName = etFirma
				.getText().toString();
		this.invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderNumber = tvInvoiceNumber
				.getText().toString();

		// general invoice detail
		this.invoiceInfo.listOrderDetail.clear();
		for (int i = 1, size = tblListOrderNumber.getChildCount(); i < size; i++) {
			DisplayItemOrderNumberRow rowOrder = (DisplayItemOrderNumberRow) tblListOrderNumber
					.getChildAt(i);
			InvoiceOrderDetailDTO invoiceDetail = new InvoiceOrderDetailDTO();
			if (this.invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderId > 0) {
				invoiceDetail.invoiceOrderId = this.invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderId;
			}
			invoiceDetail.pos = rowOrder.etPos.getText().toString();
			invoiceDetail.designation = rowOrder.etBezeichnung.getText()
					.toString();
			invoiceDetail.art_nr = rowOrder.etArtNr.getText().toString();
			invoiceDetail.quantity = rowOrder.etMenge.getText().toString();
			invoiceDetail.single_price = rowOrder.etEinze.getText().toString();
			invoiceDetail.total = rowOrder.etGesamt.getText().toString();
			this.invoiceInfo.listOrderDetail.add(invoiceDetail);
		}
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
			this.invoiceInfo = new InvoiceOrderNumberInfoView();
			this.etAddress.setText("");
			this.etAnsprechpartner.setText("");
			this.etBestellt.setText("");
			this.etFirma.setText("");
			this.etKunden.setText("");
			this.etLieferdatum.setText("");
			this.etPLZ.setText("");
			this.etProject.setText("");
			this.etStadt.setText("");
			this.tvInvoiceNumber.setText("");

			View headerView = tblListOrderNumber.getChildAt(0);
			tblListOrderNumber.removeAllViews();
			tblListOrderNumber.addView(headerView);

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

	/**
	 * 
	 * update invoice data for screen
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void updateInvoiceDataForScreen() {
		// update contact info
		etAddress
				.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress);
		etAnsprechpartner
				.setText(this.invoiceInfo.invoiceOrder.contactInvoice.firstName
						+ " "
						+ this.invoiceInfo.invoiceOrder.contactInvoice.lastName);
		etFirma.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactName);
		etPLZ.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactPLZ);
		etStadt.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactStadt);
		if (this.invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			rbMale.setChecked(true);
			rbFeMale.setChecked(false);
		} else {
			rbMale.setChecked(false);
			rbFeMale.setChecked(true);
		}

		// update invoice order info
		etProject
				.setText(this.invoiceInfo.invoiceOrder.invoiceOrderInfo.project);
		etBestellt
				.setText(this.invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn);
		etKunden.setText(this.invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber);
		etLieferdatum
				.setText(this.invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery);

		tvInvoiceNumber
				.setText(this.invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderNumber);

		// update save order detail
		if (this.invoiceInfo.listOrderDetail != null) {
			View headerView = tblListOrderNumber.getChildAt(0);
			tblListOrderNumber.removeAllViews();
			tblListOrderNumber.addView(headerView);
			for (int i = 0, size = this.invoiceInfo.listOrderDetail.size(); i < size; i++) {
				InvoiceOrderDetailDTO invoiceDetail = this.invoiceInfo.listOrderDetail
						.get(i);

				DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
						this, tblListOrderNumber);
				rowOrder.updateLayoutWithData(invoiceDetail);
				tblListOrderNumber.addView(rowOrder);
			}
		}

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
		this.invoiceInfo.invoiceOrder.contactInvoice = contactObj;
		etAnsprechpartner.setText(contactObj.firstName + " "
				+ contactObj.lastName);
		etFirma.setText(contactObj.contactName);
		etAddress.setText(contactObj.contactAddress);
		etPLZ.setText(contactObj.contactPLZ);
		etStadt.setText(contactObj.contactStadt);
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
		case ActionEventConstant.GET_COMPANY_INFO:
			this.myCompanyInfo = (CompanyDTO) modelEvent.getModelData();
			if (this.myCompanyInfo != null) {
				isDoneLoadFirst = true;
			}
			break;
		case ActionEventConstant.REQUEST_SAVE_INVOICE:
			int result = Integer.valueOf(String.valueOf(modelEvent
					.getModelData()));
			if (result == 1) {
				// insert thanh cong
			}
			break;
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

	// dialog product detail view
	AlertDialog alertProductDetail;

	public void showDialogInputInvoiceName() {
		if (alertProductDetail == null) {
			Builder build = new AlertDialog.Builder(this);
			LayoutInflater inflater = this.getLayoutInflater();
			View view = inflater.inflate(
					R.layout.layout_custom_dialog_input_invoice_name, null);

			etInvoiceName = (EditText) view.findViewById(R.id.etInvoiceName);
			btOK = (Button) view.findViewById(R.id.btOK);
			btOK.setOnClickListener(this);
			btCancel = (Button) view.findViewById(R.id.btCancel);
			btCancel.setOnClickListener(this);
			build.setView(view);
			alertProductDetail = build.create();

			Window window = alertProductDetail.getWindow();
			window.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 255,
					255, 255)));
			window.setLayout(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT);
			window.setGravity(Gravity.CENTER);
		}
		alertProductDetail.show();
	}
}
