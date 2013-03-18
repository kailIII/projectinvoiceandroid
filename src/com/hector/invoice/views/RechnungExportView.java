/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseFragment;
import com.hector.invoice.common.BaseFragmentActivity;
import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.common.OnEventControlListener;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;
import com.hector.invoice.lib.ExternalStorage;

/**
 * Mo ta muc dich cua lop (interface)
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class RechnungExportView extends BaseFragment implements
		OnEventControlListener {
	// parent activity
	BaseFragmentActivity parentActivity;
	LinearLayout llParentScreen;
	ArrayList<Integer> listIconItem;

	boolean isLoadedData = false;

	public static RechnungExportView newInstance(String title) {
		RechnungExportView f = new RechnungExportView();
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
				R.layout.layout_page_rechnung_export, container, false);
		// llParentScreen = (LinearLayout)
		// view1.findViewById(R.id.lvListContact);
		if (!isLoadedData) {
			this.initData();
		}

		WebView mWebView = (WebView) view1.findViewById(R.id.wbContent);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setPluginsEnabled(true);
		
		mWebView.setContentDescription("application/pdf");
//		mWebView.getSettings().setLoadsImagesAutomatically(true);
//		mWebView.getSettings().setAppCacheEnabled(true);
		
//		File file = new File(ExternalStorage.getFileDBPath(
//				InvoiceInfo.getInstance()
//				.getAppContext())
//		.getAbsolutePath() + "/def.webarchive");
//		Uri path = Uri.fromFile(file);
//        
//		mWebView.loadUrl(path.toString());
		
//		mWebView.loadUrl("file:///" +
//				ExternalStorage.getFileDBPath(
//						InvoiceInfo.getInstance().getAppContext())
//						.getAbsolutePath() +  "/def.webarchive");
//		mWebView.loadUrl("file:///data/data/com.hector.invoice/cache/DATABASE/" + "MyPDFFILE.pdf");
		mWebView.loadUrl("http://hcmpc.vn/customer/upload/taive/TrinhTuThanhToanTienDienQuaATM.pdf");
		// setContentView(mWebView);

		return view1;
	}

	public void initData() {

		// list item icon
		listIconItem = new ArrayList<Integer>();

		isLoadedData = true;
	}

	public void renderLayout() {
		// this.llParentScreen.removeAllViews();
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