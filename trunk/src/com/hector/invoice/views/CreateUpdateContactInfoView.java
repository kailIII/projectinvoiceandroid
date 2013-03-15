/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.ContactDTO;

/**
 * function create or update contact info view
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class CreateUpdateContactInfoView extends BaseActivity {
	ContactDTO currentContact = null;

	Button btBack;
	Button btCreate;
	EditText etContactName;
	EditText etAddress;
	EditText etPLZ;
	EditText etStadt;
	EditText etVorname;
	EditText etNachname;
	RadioButton rbMale;
	RadioButton rbFeMale;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_create_update_contact_info_view);
		Bundle data = this.getIntent().getExtras();
		if (data.getSerializable(IntentConstants.INTENT_CONTACT_OBJECT) != null) {
			this.currentContact = (ContactDTO) data
					.getSerializable(IntentConstants.INTENT_CONTACT_OBJECT);
		}
		this.initView();
	}

	/**
	 * 
	 * init view control
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void initView() {
		btBack = (Button) findViewById(R.id.btBack);
		btBack.setOnClickListener(this);
		btCreate = (Button) findViewById(R.id.btCreate);
		btCreate.setOnClickListener(this);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etContactName = (EditText) findViewById(R.id.etContactName);
		etNachname = (EditText) findViewById(R.id.etNachname);
		etPLZ = (EditText) findViewById(R.id.etPLZ);
		etStadt = (EditText) findViewById(R.id.etStadt);
		etVorname = (EditText) findViewById(R.id.etVorname);
		rbFeMale = (RadioButton) findViewById(R.id.rbFeMale);
		rbMale = (RadioButton) findViewById(R.id.rbMale);
		rbMale.setChecked(true);
		// udpate data for screen
		if (this.currentContact != null && this.currentContact.contactId >= 0) {
			etAddress.setText(this.currentContact.contactAddress);
			etContactName.setText(this.currentContact.contactName);
			etNachname.setText(this.currentContact.lastName);
			etPLZ.setText(this.currentContact.contactPLZ);
			etStadt.setText(this.currentContact.contactStadt);
			etVorname.setText(this.currentContact.firstName);
			if (this.currentContact.sex == ContactDTO.SEX_MALE) {
				this.rbMale.setChecked(true);
				this.rbFeMale.setChecked(false);
			} else {
				this.rbFeMale.setChecked(true);
				this.rbMale.setChecked(false);
			}
		}
	}

	/**
	 * 
	 * general data to update or insert to DB
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void generalDataToUpdateOrInsert() {
		if (this.currentContact == null) {
			this.currentContact = new ContactDTO();
		}
		this.currentContact.contactAddress = etAddress.getText().toString()
				.trim();
		this.currentContact.contactName = etContactName.getText().toString()
				.trim();
		this.currentContact.lastName = etNachname.getText().toString().trim();
		this.currentContact.contactPLZ = etPLZ.getText().toString().trim();
		this.currentContact.contactStadt = etStadt.getText().toString().trim();
		this.currentContact.firstName = etVorname.getText().toString().trim();
		if (this.rbFeMale.isChecked()) {
			this.currentContact.sex = ContactDTO.SEX_REMALE;
		} else {
			this.currentContact.sex = ContactDTO.SEX_MALE;
		}
	}

	/**
	 * 
	 * request update or create contact
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void updateContactToDB() {
		this.generalDataToUpdateOrInsert();
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		data.putSerializable(IntentConstants.INTENT_CONTACT_OBJECT,
				this.currentContact);
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.REQUEST_UPDATE_CREATE_CONTACT;
		MainController.getInstance().handleViewEvent(action);

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
		case ActionEventConstant.REQUEST_UPDATE_CREATE_CONTACT:
			int result = (Integer) modelEvent.getModelData();
			if (result == 1) {
				this.finish();
			}
			break;

		default:
			break;
		}
		super.handleModelViewEvent(modelEvent);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btCreate) {
			this.updateContactToDB();
		} else if (v == btBack) {
			this.finish();
		}
		super.onClick(v);
	}
}
