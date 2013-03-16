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
import android.widget.ListView;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.ListContactViewDTO;

/**
 * display list contact view screen
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class ContactlistView extends BaseActivity {

	ListView lvListContact;
	boolean isDoneLoadFirst = false;
	boolean isGetContactObj = false;
	ListContactViewDTO listContactInfo = new ListContactViewDTO();
	Button btBack;
	Button btCreate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_contact_list_view);

		lvListContact = (ListView) this.findViewById(R.id.lvListContact);
		btBack = (Button) this.findViewById(R.id.btBack);
		btBack.setOnClickListener(this);
		btCreate = (Button) this.findViewById(R.id.btCreate);
		btCreate.setOnClickListener(this);
		if (!isDoneLoadFirst) {
			this.requestGetListContact();
		}
		Bundle data = this.getIntent().getExtras();
		isGetContactObj = data
				.getBoolean(IntentConstants.INTENT_GET_CONTACT_OBJECT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (this.isDoneLoadFirst) {
			this.renderLayout();
		}
		super.onResume();
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
	 * request get list contact
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void requestGetListContact() {
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.GET_LIST_CONTACT;
		MainController.getInstance().handleViewEvent(action);
	}

	/**
	 * 
	 * render list contact
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void renderLayout() {
		ContactAdapter myAdapter = new ContactAdapter(this,
				this.listContactInfo.listContact, this, isGetContactObj);
		lvListContact.setAdapter(myAdapter);
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
		case ActionEventConstant.GET_LIST_CONTACT:
			this.listContactInfo = (ListContactViewDTO) modelEvent
					.getModelData();
			if (this.listContactInfo != null) {
				this.renderLayout();
			}
			break;
		case ActionEventConstant.REQUEST_DELETE_CONTACT:
			int result = (Integer) modelEvent.getModelData();
			if (result == 1) {
				this.requestGetListContact();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btBack) {
			this.finish();
		} else if (v == btCreate) {
			this.gotoCreateContactView(null);
		} else {
			super.onClick(v);
		}
	}

	/**
	 * 
	 * show create contact view
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void gotoCreateContactView(ContactDTO ctObject) {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		if (ctObject != null) {
			data.putSerializable(IntentConstants.INTENT_CONTACT_OBJECT,
					ctObject);
		}
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.SHOW_CRATE_CONTACT_VIEW;
		MainController.getInstance().handleSwitchActivity(event);
	}

	/**
	 * 
	 * request delete contact
	 * 
	 * @param @param myContact
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void requestDeleteContact(ContactDTO myContact) {
		ActionEvent event = new ActionEvent();
		Bundle data = new Bundle();
		if (myContact != null) {
			data.putSerializable(IntentConstants.INTENT_CONTACT_OBJECT,
					myContact);
		}
		event.viewData = data;
		event.sender = this;
		event.action = ActionEventConstant.REQUEST_DELETE_CONTACT;
		MainController.getInstance().handleViewEvent(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onEvent(int,
	 * android.view.View, java.lang.Object)
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
		if (eventType == ActionEventConstant.ACTION_CLICK_ROW_CONTACT) {
			ContactDTO myData = (ContactDTO) data;
			if (!this.isGetContactObj) {
				gotoCreateContactView(myData);
			} else {
				Bundle dataObject = new Bundle();
				dataObject.putSerializable(
						IntentConstants.INTENT_CONTACT_OBJECT, myData);
				sendBroadcast(ActionEventConstant.BROAD_CAST_CONTACT_OBJECT,
						dataObject);
				this.finish();
			}
		} else if (eventType == ActionEventConstant.ACTION_CLICK_DELETE_CONTACT) {
			ContactDTO myData = (ContactDTO) data;
			requestDeleteContact(myData);
		} else {
			super.onEvent(eventType, control, data);
		}
	}
}
