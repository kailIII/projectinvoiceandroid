/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

import java.io.File;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.hector.invoice.R;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.Constants;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */

public class Utils {
	/**
	 * 
	 * show dialog confirm exit app
	 * 
	 * @param view
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Oct 16, 2012
	 */
	@SuppressWarnings("deprecation")
	public static void showDialogConfirmExitApp(final BaseActivity view) {
		String notice = StringUtil.getString(R.string.text_confirm_exit_app);
		String ok = StringUtil.getString(R.string.text_button_ok);
		String cancel = StringUtil.getString(R.string.text_button_no);
		if (view != null) {
			AlertDialog alertDialog = new AlertDialog.Builder(view).create();
			alertDialog.setMessage(notice);
			if (ok != null && !Constants.STR_BLANK.equals(ok)) {
				alertDialog.setButton2(ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.sendBroadcast(
										ActionEventConstant.EXIT_JOB_AIDS_APP,
										new Bundle());
								dialog.dismiss();
							}
						});
			}
			if (cancel != null && !Constants.STR_BLANK.equals(cancel)) {
				alertDialog.setButton(cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
			}
			alertDialog.show();
		}
	}

	/**
	 * 
	 * get color resource
	 * 
	 * @param @param id
	 * @param @return
	 * @return: int
	 * @author: HaiTC3
	 * @date: Feb 28, 2013
	 */
	public static int getColor(int id) {
		return InvoiceInfo.getInstance().getAppContext().getResources()
				.getColor(id);
	}
}
