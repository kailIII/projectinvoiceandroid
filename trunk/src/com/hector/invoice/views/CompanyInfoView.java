/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ImageUtil;
import com.hector.invoice.common.ImageValidator;
import com.hector.invoice.common.ImageValidatorTakingPhoto;
import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.Constants;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.CompanyDTO;

/**
 * Company info view screen
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class CompanyInfoView extends BaseActivity {

	boolean isDoneLoadFirst = false;
	Button btBack;
	Button btCreate;
	ImageView ivCompanyAvatar;
	CompanyDTO companyInfo = new CompanyDTO();
	public File takenPhoto;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_company_info_view);
		initViewControl();
		if (!isDoneLoadFirst) {
			this.requestCompanyInfo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateView(java.lang.String,
	 * android.content.Context, android.util.AttributeSet)
	 */
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		// TODO Auto-generated method stub
		return super.onCreateView(name, context, attrs);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (this.isDoneLoadFirst) {
			this.renderLayout();
		}
		super.onResume();
	}

	/**
	 * 
	 * init view controls
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void initViewControl() {
		btBack = (Button) findViewById(R.id.btBack);
		btBack.setOnClickListener(this);
		btCreate = (Button) findViewById(R.id.btCreate);
		btCreate.setOnClickListener(this);
		ivCompanyAvatar = (ImageView) findViewById(R.id.ivCompanyAvatar);
		ivCompanyAvatar.setOnClickListener(this);
	}

	/**
	 * 
	 * request get list contact
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void requestCompanyInfo() {
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.GET_COMPANY_INFO;
		MainController.getInstance().handleViewEvent(action);
	}

	/**
	 * 
	 * request update company info
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void requestUpdateCompanyInfo() {
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		data.putSerializable(IntentConstants.INTENT_COMPANY_INFO,
				this.companyInfo);
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.REQUEST_UPDATE_COMPANY_INFO;
		MainController.getInstance().handleViewEvent(action);
	}

	/**
	 * 
	 * render layout
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void renderLayout() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.BaseActivity#handleModelViewEvent(com.hector
	 * .invoice.common.ModelEvent)
	 */
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent event = modelEvent.getActionEvent();
		switch (event.action) {
		case ActionEventConstant.GET_COMPANY_INFO:
			this.companyInfo = (CompanyDTO) modelEvent.getModelData();
			if (this.companyInfo != null) {
				isDoneLoadFirst = true;
				this.renderLayout();
			}
			break;
		case ActionEventConstant.REQUEST_UPDATE_COMPANY_INFO:
			int result = Integer.parseInt(String.valueOf(modelEvent
					.getModelData()));
			if (result == 1) {
				Bundle data = new Bundle();
				sendBroadcast(
						ActionEventConstant.BROAD_CAST_UPDATE_COMPANYINFO_SUCCESS,
						data);
				this.finish();
			}
			break;
		default:
			super.handleModelViewEvent(modelEvent);
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hector.invoice.common.BaseActivity#handleErrorModelViewEvent(com.
	 * hector.invoice.common.ModelEvent)
	 */
	@Override
	public void handleErrorModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		super.handleErrorModelViewEvent(modelEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		if (v == btBack) {
			this.finish();
		} else if (v == btCreate) {
			this.requestUpdateCompanyInfo();
		} else if (v == ivCompanyAvatar) {
			this.showTakingImageDialog();
		} else {
			super.onClick(v);
		}
	}

	/**
	 * 
	 * show taking image dialog
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	private void showTakingImageDialog() {
		final CharSequence[] choice = {
				StringUtil.getString(R.string.TEXT_CHOOSE_FROM_PHONE),
				StringUtil.getString(R.string.TEXT_TAKE_NEW_PHOTO) };

		final AlertDialog alert;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(StringUtil
				.getString(R.string.TEXT_CHOOSE_ALBUM_TO_SAVE));
		builder.setSingleChoiceItems(choice, -1,

		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					showGalaryImage();
				} else if (which == 1) {
					takenPhoto = takePhoto(CompanyInfoView.this,
							BaseActivity.RQ_TAKE_PHOTO);
				}
				dialog.dismiss();
			}
		});

		alert = builder.create();
		alert.show();
	}

	/**
	 * 
	 * take photo
	 * 
	 * @author: HaiTC3
	 * @param activity
	 * @param requestCode
	 * @return
	 * @return: File
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public static File takePhoto(BaseActivity activity, int requestCode) {
		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		final File path = new File(Environment.getExternalStorageDirectory(),
				InvoiceInfo.getInstance().getAppContext().getPackageName());
		if (!path.exists()) {
			path.mkdir();
		}
		String fileName = Constants.TEMP_IMG;
		File retFile = new File(path, fileName);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(retFile));
		// activity.isSaveInstance = true;
		activity.startActivityForResult(intent, requestCode);
		return retFile;
	}

	/**
	 * 
	 * show galary image to choose
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 9, 2013
	 */
	public void showGalaryImage() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		String filePath = "";
		switch (requestCode) {
		case RQ_TAKE_PHOTO: {
			if (resultCode == RESULT_OK) {
				filePath = takenPhoto.getAbsolutePath();
				ImageValidatorTakingPhoto validator = new ImageValidatorTakingPhoto(
						this, filePath, Constants.MAX_FULL_IMAGE_HEIGHT);
				validator.setDataIntent(data);
				if (validator.execute()) {
					// imageFile = takingFile;
					Bitmap bm = validator.getBitmap();
					try {
						ivCompanyAvatar.setImageBitmap(bm);
						byte[] bytearray = ImageUtil.getByteArrayOfBitmapDoNotRemoveRes(bm,
								200, 200);
						// update avatar
						if (this.companyInfo == null) {
							this.companyInfo = new CompanyDTO();
						}
						this.companyInfo.logo = bytearray;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		}
		case REQ_CODE_PICK_IMAGE: {
			if (resultCode != Activity.RESULT_OK) {
				return;
			}
			if (data.getData() != null) {
				// Bitmap avatar = null;
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				if (cursor != null) {
					cursor.moveToFirst();
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					filePath = cursor.getString(columnIndex);
					cursor.close();
				} else {
					filePath = selectedImage.toString();
				}

				File chooseFile = new File(filePath);
				// File chooseFile = new File(filePath);
				ImageValidator validator = new ImageValidator(this, filePath,
						Constants.MAX_FULL_IMAGE_HEIGHT);
				if (validator.execute()) {
					takenPhoto = chooseFile;
					Bitmap bm = validator.getBitmap();
					try {
						ivCompanyAvatar.setImageBitmap(bm);
						if (this.companyInfo == null) {
							this.companyInfo = new CompanyDTO();
						}
						byte[] bytearray = ImageUtil.getByteArrayOfBitmapDoNotRemoveRes(bm,
								200, 200);
						// update avatar
						this.companyInfo.logo = bytearray;
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		}
		default:
			super.onActivityResult(requestCode, resultCode, data);
			break;

		}
	}
}
