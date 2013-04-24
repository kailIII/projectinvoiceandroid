/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.NumberFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.inputmethod.InputMethodManager;

import com.hector.invoice.constant.Constants;
import com.hector.invoice.lib.ExternalStorage;

/**
 * 
 * xu ly cac van de lien quan chung
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class GlobalUtil {
	static GlobalUtil instance;

	public GlobalUtil() {

	}

	public static GlobalUtil getInstance() {
		if (instance == null) {
			instance = new GlobalUtil();
		}
		return instance;
	}

	/**
	 * 
	 * save object with file name
	 * 
	 * @author: HaiTC3
	 * @param object
	 * @param fileName
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void saveObject(Object object, String fileName) {
		try {
			FileOutputStream fos = InvoiceInfo.getInstance().getAppContext()
					.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.close();
			fos.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * read object from file name
	 * 
	 * @author: HaiTC3
	 * @param fileName
	 * @return
	 * @return: Object
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static Object readObject(String fileName) {
		Object object = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			if (isExistFile(fileName)) {
				fis = InvoiceInfo.getInstance().getAppContext()
						.openFileInput(fileName);
				if (fis != null) {// ton tai file
					ois = new ObjectInputStream(fis);
					object = ois.readObject();

				}
			}
		} catch (Throwable e) {
			object = null;
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				fis.close();
			} catch (Exception e) {
			}
		}
		return object;
	}

	/**
	 * 
	 * check file exits
	 * 
	 * @author: HaiTC3
	 * @param fileName
	 * @return
	 * @return: boolean
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static boolean isExistFile(String fileName) {
		try {
			if (!StringUtil.isNullOrEmpty(fileName)) {
				String[] s = InvoiceInfo.getInstance().getAppContext()
						.fileList();
				for (int i = 0, size = s.length; i < size; i++) {
					if (fileName.equals(s[i].toString())) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * show dialog confirm 2 button
	 * 
	 * @author: HaiTC3
	 * @param view
	 * @param notice
	 * @param ok
	 * @param actionOk
	 * @param cancel
	 * @param actionCancel
	 * @param data
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void showDialogConfirm(final BaseActivity view,
			CharSequence notice, String ok, final int actionOk, String cancel,
			final int actionCancel, final Object data) {
		if (view != null) {
			AlertDialog alertDialog = new AlertDialog.Builder(view).create();
			alertDialog.setMessage(notice);
			alertDialog.setOnCancelListener(view);
			if (ok != null && !Constants.STR_BLANK.equals(ok)) {
				alertDialog.setButton(ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionOk, null, data);
								dialog.dismiss();
							}
						});
			}
			if (cancel != null && !Constants.STR_BLANK.equals(cancel)) {
				alertDialog.setButton2(cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionCancel, null, data);
								dialog.dismiss();
							}
						});
			}
			alertDialog.show();
		}
	}

	/**
	 * 
	 * show dialog confirm 2 button co icon va title cho activity
	 * 
	 * @author: HaiTC3
	 * @param view
	 * @param icon
	 * @param title
	 * @param notice
	 * @param ok
	 * @param actionOk
	 * @param cancel
	 * @param actionCancel
	 * @param data
	 * @param isCanBack
	 * @param isTouchOutSide
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void showDialogConfirm(final BaseActivity view, int icon,
			String title, CharSequence notice, String ok, final int actionOk,
			String cancel, final int actionCancel, final Object data,
			boolean isCanBack, boolean isTouchOutSide) {
		if (view != null) {
			AlertDialog alertDialog = new AlertDialog.Builder(view).create();
			alertDialog.setIcon(icon);
			alertDialog.setTitle(title);
			alertDialog.setCancelable(isCanBack);
			alertDialog.setCanceledOnTouchOutside(isTouchOutSide);
			alertDialog.setMessage(notice);
			if (ok != null && !Constants.STR_BLANK.equals(ok)) {
				alertDialog.setButton(ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionOk, null, data);
								dialog.dismiss();
							}
						});
			}
			if (cancel != null && !Constants.STR_BLANK.equals(cancel)) {
				alertDialog.setButton2(cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionCancel, null, data);
								dialog.dismiss();
							}
						});
			}
			alertDialog.show();
		}
	}

	/**
	 * 
	 * Show dialog confrimed with action user define
	 * 
	 * @author: HaiTC3
	 * @param view
	 * @param title
	 * @param notice
	 * @param ok
	 * @param actionOk
	 * @param cancel
	 * @param actionCancel
	 * @param data
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void showDialogConfirm(final BaseActivity view, String title,
			CharSequence notice, String ok, final int actionOk, String cancel,
			final int actionCancel, final Object data) {
		if (view != null) {
			AlertDialog alertDialog = new AlertDialog.Builder(view).create();
			alertDialog.setTitle(title);
			alertDialog.setMessage(notice);
			if (ok != null && !Constants.STR_BLANK.equals(ok)) {
				alertDialog.setButton(ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionOk, null, data);
								dialog.dismiss();
							}
						});
			}
			if (cancel != null && !Constants.STR_BLANK.equals(cancel)) {
				alertDialog.setButton2(cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								view.onEvent(actionCancel, null, data);
								dialog.dismiss();
							}
						});
			}
			alertDialog.show();
		}
	}

	/**
	 * 
	 * FOrce hide Keyboard
	 * 
	 * @author: HaiTC3
	 * @param activity
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public static void forceHideKeyboard(Activity activity) {
		if (activity != null && activity.getCurrentFocus() != null) {
			InputMethodManager inputManager = (InputMethodManager) activity
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	/**
	 * 
	 * check file DB exists
	 * 
	 * @author: HaiTC3
	 * @return
	 * @return: boolean
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public boolean checkExistsDataBase() {
		boolean isExistData = false;
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(ExternalStorage
					.getFileDBPath(InvoiceInfo.getInstance().getAppContext())
					.getAbsolutePath()
					+ "/InvoiceDatabase", null, SQLiteDatabase.OPEN_READONLY);
			if (checkDB != null) {
				isExistData = true;
			}
		} catch (SQLiteException e) {
			// database doesn't exist yet.

		} catch (Throwable e) {
		} finally {
			try {
				if (checkDB != null) {
					checkDB.close();
					checkDB = null;
				}
			} catch (Exception e) {
			}
		}
		return isExistData;
	}

	/**
	 * 
	*  convert format number with two point after dicemal
	*  @author: HaiTC3
	*  @param amount
	*  @return
	*  @return: String
	*  @throws:
	*  @since: Apr 24, 2013
	 */
	public String convertFormatNumberOrder(double amount) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(amount);
	}
}
