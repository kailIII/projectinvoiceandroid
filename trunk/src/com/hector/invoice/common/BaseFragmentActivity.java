/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;

/**
 * base fragment activity for detail screen subtopic
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class BaseFragmentActivity extends FragmentActivity implements
		OnCancelListener {

	// dialog display when request
	private static ProgressDialog progressDlg;

	public TabHost mTabHost;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// hien tai dang bi loi gi do chua kiem tra duoc, loi thuong xuyen xay
		// ra khi lau lau khoi dong chuong trinh
		// if (savedInstanceState != null &&
		// savedInstanceState.getString(IntentConstants.INTENT_CURRENT_TAB_SELECTED)
		// != null) {
		// mTabHost.setCurrentTabByTag(savedInstanceState.getString(IntentConstants.INTENT_CURRENT_TAB_SELECTED));
		// }
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		InvoiceInfo.getInstance().setAppContext(getApplicationContext());
		InvoiceInfo.getInstance().setActivityContext(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#setContentView(android.view.View)
	 */
	@Override
	public void setContentView(View view) {
		// TODO Auto-generated method stub
		super.setContentView(view);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#setContentView(int)
	 */
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
	}

	/**
	 * 
	 * show progress dialog
	 * 
	 * @author: HaiTC3
	 * @param content
	 * @param cancleable
	 * @return: void
	 * @throws:
	 */
	public void showProgressDialog(String content, boolean cancleable,
			boolean isTimeout, int numTimeout) {
		if (progressDlg != null && progressDlg.isShowing()) {
			closeProgressDialog();
		}
		progressDlg = ProgressDialog.show(this, "", content, true, true);
		progressDlg.setCancelable(cancleable);
		progressDlg.setCanceledOnTouchOutside(false);
		progressDlg.setOnCancelListener(this);

		if (isTimeout) {
			Handler handler = null;
			handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					progressDlg.cancel();
					progressDlg.dismiss();
				}
			}, numTimeout);
		}
	}

	/**
	 * 
	 * close progress dialog
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 */
	public void closeProgressDialog() {
		if (progressDlg != null) {
			try {
				progressDlg.dismiss();
				progressDlg = null;
			} catch (Exception e) {
				Log.v("Exception", e.toString());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.content.DialogInterface.OnCancelListener#onCancel(android.content
	 * .DialogInterface)
	 */
	@Override
	public void onCancel(DialogInterface dialog) {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
