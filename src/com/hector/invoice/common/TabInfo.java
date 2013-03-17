/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 *  tab info
 *  @author: HaiTC3
 *  @version: 1.0
 *  @since: 1.1
 */
public class TabInfo {
	public String tag;
	public Class<?> clss;
	public Bundle args;
	public Fragment fragment;

	public TabInfo(String tag, Class<?> clazz, Bundle args) {
		this.tag = tag;
		this.clss = clazz;
		this.args = args;
	}
}
