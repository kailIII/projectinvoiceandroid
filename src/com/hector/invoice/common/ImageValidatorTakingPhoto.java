/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;

import com.hector.invoice.constant.Constants;

/**
 *  Mo ta muc dich cua lop (interface)
 *  @author: HaiTC
 *  @version: 1.0
 *  @since: Jul 28, 2011
 */
public class ImageValidatorTakingPhoto extends ImageValidator {
	Intent data;
	/**
	 * @param context
	 * @param filePath
	 * @param maxDimensionAvatar
	 */
	public ImageValidatorTakingPhoto(BaseActivity context,
			String filePath, int maxDimensionAvatar) {
		super(context, filePath, maxDimensionAvatar);
		// TODO Auto-generated constructor stub
	}
	
	public void setDataIntent(Intent data) {
		this.data = data;
	}
	
	/* (non-Javadoc)
	 * @see com.hai.utils.ImageValidator#execute()
	 */
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		if (data != null) {
			Bitmap tempBitmap = null;
			tempBitmap = (Bitmap) data.getExtras().get("data");
			if (tempBitmap != null) {
				FileOutputStream out = null;
				try {
					int srcWidth = tempBitmap.getWidth();
					int srcHeight = tempBitmap.getHeight();
					
					out = new FileOutputStream(	filePath);
					
					if(srcWidth > 0 && srcHeight > 0 && 
							(srcWidth > Constants.MAX_IMAGE_WIDTH_HEIGHT || srcHeight > Constants.MAX_IMAGE_WIDTH_HEIGHT)){
						Bitmap scaledBitmap = ImageUtil.resizeImageWithOrignal(tempBitmap, Constants.MAX_IMAGE_WIDTH_HEIGHT);
						scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
						scaledBitmap.recycle();
						scaledBitmap = null;
					}else{
						tempBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
					}
				} catch (FileNotFoundException e) {
				} finally {
					try {
						if (out != null) {
							out.close();
						}
						if (tempBitmap != null) {
							tempBitmap.recycle();
						}
					} catch (IOException e) {
					}
				}
			}
		}
		return super.execute();
	}
}
