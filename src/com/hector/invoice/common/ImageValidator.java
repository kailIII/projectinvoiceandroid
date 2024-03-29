/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import java.io.File;

import android.graphics.Bitmap;
import android.net.Uri;

import com.hector.invoice.R;

/**
 *  validate hinh anh (interface)
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jun 29, 2011
 */
public class ImageValidator {
	//DMD set mac dinh la 10Mb
	static final int MAX_FILE_SIZE = 1024*1024*10;//kich thuoc toi da
	public static final String IMAGE_PATH_PICASA = "content://com.android.sec.gallery3d";
	//public static final String IMAGE_PATH_PICASA = "content://com.google.android.gallery3d";
	static final String STR_MAX_FILE_SIZE = "10 Mb";//chuoi kich thuoc toi da
	BaseActivity context = null;//context
	String filePath = null;//filePath
	Bitmap bitmapData = null;//thumbNail
	int maxDimensionAvatar = 0;//maxDimensionAvatar
	public ImageValidator (BaseActivity context, String filePath, int maxDimensionAvatar) {
		this.context = context;
		this.filePath = filePath;
		this.maxDimensionAvatar = maxDimensionAvatar;
	}
	
	/**
	*  kiem tra hinh anh hop le
	*  @author: AnhND
	*  @return
	*  @return: boolean
	*  @throws:
	 */
	public boolean execute() {
		boolean res = true;
		int index = -1;
		index = filePath.indexOf("///");
		if(index != -1){
			filePath = filePath.substring(index + 2);
		}
		File file = new File(filePath);
		if (file.length() > MAX_FILE_SIZE) {
			context.showDialog(StringUtil.getString(R.string.IMG_MAX_SIZE) + " " + STR_MAX_FILE_SIZE);
			res = false;
		}
		try {
			if(!filePath.toString().startsWith(IMAGE_PATH_PICASA)){
				bitmapData = ImageUtil.readImageFromSDCard(filePath, maxDimensionAvatar);
			}else{
				bitmapData = ImageUtil.getBitmapFromPicasaUri(Uri.parse(filePath));
			}
		} catch (OutOfMemoryError e1) {
			System.gc();
			if(bitmapData != null && bitmapData.isRecycled()){
				bitmapData.recycle();
				bitmapData = null;
			}
			filePath = null;
			context.showDialog(StringUtil.getString(R.string.ERROR_OUT_MEMORY));
			res = false;
		} catch (Exception e) {
			context.showDialog(StringUtil.getString(R.string.IMG_INVALID_FORMAT));
			res = false;
		}
		return res;
	}
	
	
	/**
	*  tra ve bitmap sau khi decode
	*  @author: HaiTC
	*  @return: Bitmap
	*  @throws:
	*/
	public Bitmap getBitmap() {
		return bitmapData;
	}
}
