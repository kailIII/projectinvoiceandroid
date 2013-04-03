/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseFragment;
import com.hector.invoice.common.BaseFragmentActivity;
import com.hector.invoice.common.OnEventControlListener;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;

/**
 * Mo ta muc dich cua lop
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class AngebotExportView extends BaseFragment implements
		OnEventControlListener {
	// parent activity
	BaseFragmentActivity parentActivity;
	LinearLayout llParentScreen;

	TextView tvContent1;
	TextView tvContent2;
	TextView tvContent3;
	TextView tvContent4;
	TextView tvContent5;
	ImageView ivLogo;
	LinearLayout tblListOrderNumber;
	static InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	static CompanyDTO companyInfo = new CompanyDTO();
	static String fileName = "";

	public static AngebotExportView newInstance(String title,
			InvoiceOrderNumberInfoView data, CompanyDTO dataCompany, String filName) {
		AngebotExportView f = new AngebotExportView();
		invoiceInfo = data;
		fileName = filName;
		companyInfo = dataCompany;
		Bundle args = new Bundle();
		args.putString("title", title);
		f.setArguments(args);
		return f;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jobaid.jobaids.common.BaseFragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		parentActivity = (BaseFragmentActivity) activity;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view1 = (View) inflater.inflate(
				R.layout.layout_page_angebot_export, container, false);
		this.initViewControl(view1);
		this.initData();
		return view1;
	}

	public void initViewControl(View v) {
		tvContent1 = (TextView) v.findViewById(R.id.tvContent1);
		tvContent2 = (TextView) v.findViewById(R.id.tvContent2);
		tvContent3 = (TextView) v.findViewById(R.id.tvContent3);
		tvContent4 = (TextView) v.findViewById(R.id.tvContent4);
		tvContent5 = (TextView) v.findViewById(R.id.tvContent5);
		ivLogo = (ImageView) v.findViewById(R.id.ivLogo);
		tblListOrderNumber = (LinearLayout) v
				.findViewById(R.id.tblListOrderNumber);
	}

	public void initData() {
		// show logo
		if (this.companyInfo.logo != null && this.companyInfo.logo.length > 0) {
			Bitmap bm = BitmapFactory.decodeByteArray(this.companyInfo.logo, 0,
					this.companyInfo.logo.length);
			ivLogo.setImageBitmap(bm);
		}

		// show content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("Firma \n ");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
						: " ")
						+ "\n");
		if (invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			strContent1
					.append("Herr "
							+ (invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
									: " ") + "\n");
		} else {
			strContent1
					.append("Frau "
							+ (invoiceInfo.invoiceOrder.contactInvoice.firstName != null ? invoiceInfo.invoiceOrder.contactInvoice.firstName
									: " ") + "\n");
		}
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactAddress != null ? invoiceInfo.invoiceOrder.contactInvoice.contactAddress
						: " ")
						+ "\n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactPLZ != null ? invoiceInfo.invoiceOrder.contactInvoice.contactPLZ
						: ""));
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactStadt != null ? invoiceInfo.invoiceOrder.contactInvoice.contactStadt
						: " "));

		tvContent1.setText(strContent1.toString());

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		strContent2
				.append((companyInfo.companyName != null ? companyInfo.companyName
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		strContent2
				.append((companyInfo.companyPLZ != null ? companyInfo.companyPLZ
						: " ")
						+ " "
						+ (companyInfo.companyCity != null ? companyInfo.companyCity
								: " ") + "\n \n ");
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur"
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		}
		strContent2
				.append("Tel: "
						+ (this.companyInfo.telephone != null ? this.companyInfo.telephone
								: " ") + "\n");
		strContent2.append("Fax: "
				+ (this.companyInfo.fax != null ? this.companyInfo.fax : " ")
				+ "\n");
		strContent2.append("Email: "
				+ (this.companyInfo.email != null ? this.companyInfo.email
						: " ") + "\n");
		tvContent2.setText(strContent2.toString());

		// content 3
		StringBuffer strContent3 = new StringBuffer();
		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		String line = "Datum: " + format.format(currentDateTime);
		strContent3.append(line + "\n");
		strContent3.append("Angebotsnr.: " + fileName.substring(0, fileName.length()-4) + "\n");
		tvContent3.setText(strContent3.toString());

		// table
		double total = 0;
		for (int i = 0, size = this.invoiceInfo.listOrderDetail.size(); i < size; i++) {
			InvoiceOrderDetailDTO dto = this.invoiceInfo.listOrderDetail.get(i);

			DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
					parentActivity, tblListOrderNumber, 1);
			rowOrder.etPos.setText(dto.pos);
			rowOrder.etPos.setEnabled(false);
			rowOrder.etBezeichnung.setText(dto.designation);
			rowOrder.etBezeichnung.setEnabled(false);
			rowOrder.etMenge.setText(dto.quantity);
			rowOrder.etMenge.setEnabled(false);
			rowOrder.etEinze.setText(dto.single_price + " Û ");
			rowOrder.etEinze.setEnabled(false);
			total += Double.valueOf(dto.total);
			rowOrder.etGesamt.setText(dto.total + " Û ");
			rowOrder.etGesamt.setEnabled(false);
			rowOrder.etArtNr.setVisibility(View.GONE);

			tblListOrderNumber.addView(rowOrder);
		}

		// content 4
		StringBuffer strContent4 = new StringBuffer();
		strContent4.append("Zwischensumme		" + String.valueOf(total) + " Û " + "\n");
		float vatValue = 0;
		if (!StringUtil.isNullOrEmpty(companyInfo.vatValue)) {
			vatValue = Float.valueOf(companyInfo.vatValue);
		}

		double newTotal = (vatValue * total) / 100;
//		strContent4.append((companyInfo.vatText != null ? companyInfo.vatText
//				: "0 ") + " von " + total + "	" + String.valueOf(newTotal));
		strContent4.append("Mehrwertsteuer " + (companyInfo.vatValue != null ? companyInfo.vatValue
				: "0 ") + "% 	von 	" + total + " Û " +  "	" + String.valueOf(newTotal) + " Û ");
		tvContent4.setText(strContent4.toString());

		// content 5
		StringBuffer strContent5 = new StringBuffer();
		strContent5.append("Gesamtsumme:		" + String.valueOf(total + newTotal) + " Û ");
		tvContent5.setText(strContent5.toString());
	}

	public void renderLayout() {
		// this.llParentScreen.removeAllViews();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobaid.jobaids.common.BaseFragment#onResume()
	 */
	@Override
	public void onResume() {
		this.renderLayout();
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobaid.jobaids.common.OnEventControlListener#onEvent(int,
	 * android.view.View, java.lang.Object)
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
		if (eventType == ActionEventConstant.EVENT_SHOW_FULL_IMAGE) {
		}
	}
}