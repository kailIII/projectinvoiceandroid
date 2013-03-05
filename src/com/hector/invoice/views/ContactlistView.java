/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.ListContactViewDTO;

/**
 * display list contact view screen
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class ContactlistView extends BaseActivity {

	boolean isDoneLoadFirst = false;
	ListContactViewDTO listContactInfo = new ListContactViewDTO();

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

		if (!isDoneLoadFirst) {
			this.requestGetListContact();
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
		if (this.isDoneLoadFirst) {

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
			this.listContactInfo = (ListContactViewDTO) modelEvent.getModelData();
			if (this.listContactInfo != null) {
				this.renderLayout();
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
}
