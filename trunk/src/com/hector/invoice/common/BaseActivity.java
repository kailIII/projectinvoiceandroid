/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

import com.hector.invoice.R;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.Constants;
import com.hector.invoice.constant.IntentConstants;

/**
 * 
 * base activity of application
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public abstract class BaseActivity extends Activity implements
		OnCancelListener, OnClickListener {

	// define name action for broadcast
	public static final String INVOICE_ACTION = "com.hector.invoice.common.action";

	public static final int RQ_TAKE_PHOTO = 999;
	public static final int REQ_CODE_PICK_IMAGE = 1338;

	// dialog hien thi khi request
	private static ProgressDialog progressDlg;
	// activity living
	private boolean isActive;
	// check time out cancel progress dialog
	public boolean isTimeoutCancelDialog = false;
	// is finished activity
	public boolean isFinished = false;

	protected void onResume() {
		super.onResume();
		isActive = true;
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isActive = false;
	}

	// broadcast receiver
	BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			int action = intent.getExtras().getInt(
					IntentConstants.INTENT_BROAD_CAST_ACTION);
			int hasCode = intent.getExtras().getInt(
					IntentConstants.INTENT_BROAD_CAST_HASH_CODE);
			if (hasCode != BaseActivity.this.hashCode()) {
				receiveBroadcast(action, intent.getExtras());
			}
		}
	};

	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * send broad cast
	 * 
	 * @param @param action
	 * @param @param bundle
	 * @return: void
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	public void sendBroadcast(int action, Bundle bundle) {
		Intent intent = new Intent(INVOICE_ACTION);
		bundle.putInt(IntentConstants.INTENT_BROAD_CAST_ACTION, action);
		bundle.putInt(IntentConstants.INTENT_BROAD_CAST_HASH_CODE, intent
				.getClass().hashCode());
		intent.putExtras(bundle);
		sendBroadcast(intent);
	}

	/**
	 * 
	 * receive broadcast
	 * 
	 * @param @param action
	 * @param @param bundle
	 * @return: void
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	public void receiveBroadcast(int action, Bundle bundle) {
		try {
			switch (action) {
			case ActionEventConstant.EXIT_JOB_AIDS_APP:
				if (isActive) {
					try {
						showProgressDialog(Constants.STR_IS_PROCESSING, false,
								false, false, 0);
					} catch (Exception e) {
						// TODO: handle exception
					} finally {
						closeProgressDialog();
						finish();
					}
				}
				break;

			default:
				break;
			}
		} catch (Exception e) {

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		InvoiceInfo.getInstance().setAppContext(getApplicationContext());
		InvoiceInfo.getInstance().setActivityContext(this);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);

		try {
			IntentFilter filter = new IntentFilter(INVOICE_ACTION);
			registerReceiver(receiver, filter);
		} catch (Exception e) {
		}
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
	 * @param @param content
	 * @param @param cancleable
	 * @param @param cancelTouchOutside
	 * @param @param isTimeout
	 * @param @param numTimeout
	 * @return: void
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	public void showProgressDialog(String content, boolean cancleable,
			boolean cancelTouchOutside, boolean isTimeout, int numTimeout) {
		if (progressDlg != null && progressDlg.isShowing()) {
			closeProgressDialog();
		}
		progressDlg = ProgressDialog.show(this, "", content, true, true);
		progressDlg.setCancelable(cancleable);
		progressDlg.setCanceledOnTouchOutside(cancelTouchOutside);
		progressDlg.setOnCancelListener(this);

		if (isTimeout) {
			Handler handler = null;
			handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					if (progressDlg != null && progressDlg.isShowing()) {
						isTimeoutCancelDialog = true;
						progressDlg.cancel();
						progressDlg.dismiss();
					}
				}
			}, numTimeout);
		}
	}

	/**
	 * 
	 * close progress dialog
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
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

	@Override
	public void finish() {
		isFinished = true;
		super.finish();
	}

	@Override
	public void finishActivity(int requestCode) {
		isFinished = true;
		super.finishActivity(requestCode);
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
		try {
			unregisterReceiver(receiver);
		} catch (Exception e) {
		}
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.basic, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * handle model event for base activity
	 * 
	 * @author: HaiTC3
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		default:
			break;
		}
	}

	/**
	 * 
	 * handle error model event from service for base activity
	 * 
	 * @author: HaiTC3
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void handleErrorModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		default:
			break;
		}
	}

	/**
	 * 
	 * show dialog
	 * 
	 * @param @param mes
	 * @param @return
	 * @return: AlertDialog
	 * @author: HaiTC3
	 * @date: Mar 6, 2013
	 */
	public AlertDialog showDialog(final String mes) {
		AlertDialog alertDialog = null;
		try {
			alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setMessage(mes);
			alertDialog.setButton("Close",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							return;
						}
					});
			alertDialog.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alertDialog;
	}
}
