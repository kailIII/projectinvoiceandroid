/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseFragment;
import com.hector.invoice.common.BaseFragmentActivity;
import com.hector.invoice.common.OnEventControlListener;
import com.hector.invoice.constant.ActionEventConstant;

/**
 * Mo ta muc dich cua lop (interface)
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class LieferscheinExportView extends BaseFragment implements
		OnEventControlListener {
	// parent activity
	BaseFragmentActivity parentActivity;
	LinearLayout llParentScreen;

	ArrayList<Integer> listIconItem2;

	boolean isLoadedData = false;

	public static LieferscheinExportView newInstance(String title) {
		LieferscheinExportView f = new LieferscheinExportView();
		Bundle args = new Bundle();
		args.putString("title", title);
		f.setArguments(args);
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobaid.jobaids.common.BaseFragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		parentActivity = (BaseFragmentActivity) activity;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view1 = (View) inflater.inflate(
				R.layout.layout_page_lieferschein_export, container, false);
		// llParentScreen = (LinearLayout)
		// view1.findViewById(R.id.lvListContact);
		if (!isLoadedData) {
			this.initData();
		}
		return view1;
	}

	/**
	 * 
	 * init data for screen
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Nov 3, 2012
	 */
	public void initData() {
		// init object 1
		this.initDataForObject1();
		// init object 2
		this.initDataForObject2();
		isLoadedData = true;
	}

	/**
	 * 
	 * init data for object 1
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Nov 3, 2012
	 */
	public void initDataForObject1() {
	}

	/**
	 * 
	 * init data for object 2
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Nov 3, 2012
	 */
	public void initDataForObject2() {
	}

	public void renderLayout() {
		this.llParentScreen.removeAllViews();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobaid.jobaids.common.BaseFragment#onResume()
	 */
	@Override
	public void onResume() {
		if (this.isLoadedData) {
			this.renderLayout();
		}
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobaid.jobaids.common.OnEventControlListener#onEvent(int,
	 * android.view.View, java.lang.Object)
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
		if (eventType == ActionEventConstant.EVENT_SHOW_FULL_IMAGE) {
		}
	}
}