/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

import android.app.Application;
import android.content.Context;

/**
 * application class
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class InvoiceInfo extends Application {
	Context appContext;// application context
	Context activityContext;// activity context
	private static InvoiceInfo instance = null;

	public InvoiceInfo() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	public static InvoiceInfo getInstance() {
		if (instance == null) {
			instance = new InvoiceInfo();
			instance.initialize();
		}
		return instance;
	}

	/**
	 * 
	 * init
	 * 
	 * @param 
	 * @return: void
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	private void initialize() {
		// TODO Auto-generated method stub

	}

	public void setAppContext(Context context) {
		this.appContext = context;

	}

	public Context getAppContext() {
		if (appContext == null) {
			appContext = new InvoiceInfo();
		}
		return appContext;
	}

	public void setActivityContext(Context context) {
		this.activityContext = context;

	}

	public Context getActivityContext() {
		return activityContext;
	}

}
