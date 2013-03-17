/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

/**
 * Tab factory
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class TabFactory implements TabContentFactory {
	private final Context mContext;

	/**
	 * @param context
	 */
	public TabFactory(Context context) {
		mContext = context;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
	 */
	public View createTabContent(String tag) {
		View v = new View(mContext);
		v.setMinimumWidth(0);
		v.setMinimumHeight(0);
		return v;
	}
}
