/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
 * Mo ta muc dich cua lop (interface)
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class RechnungExportView extends BaseFragment implements
		OnEventControlListener {
	// parent activity
	BaseFragmentActivity parentActivity;
	LinearLayout llParentScreen;
	ImageView ivContent;
	TextView tvContent1;
	TextView tvContent2;
	TextView tvContent3;
	TextView tvContent4;
	TextView tvContent5;
	TextView tvContent6;
	TextView tvContent7;
	ImageView ivLogo;
	LinearLayout tblListOrderNumber;
	static String fileName = "";

	static InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	static CompanyDTO companyInfo = new CompanyDTO();

	public static RechnungExportView newInstance(String title,
			InvoiceOrderNumberInfoView data, CompanyDTO dataCompany,
			String filName) {
		RechnungExportView f = new RechnungExportView();
		fileName = filName;
		invoiceInfo = data;
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
				R.layout.layout_page_rechnung_export, container, false);
		this.initView(view1);
		this.initData();
		return view1;
	}

	public void initView(View v) {
		tvContent1 = (TextView) v.findViewById(R.id.tvContent1);
		tvContent2 = (TextView) v.findViewById(R.id.tvContent2);
		tvContent3 = (TextView) v.findViewById(R.id.tvContent3);
		tvContent4 = (TextView) v.findViewById(R.id.tvContent4);
		tvContent5 = (TextView) v.findViewById(R.id.tvContent5);
		tvContent6 = (TextView) v.findViewById(R.id.tvContent6);
		tvContent7 = (TextView) v.findViewById(R.id.tvContent7);
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
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.firstName
							+ "\n");
		} else {
			strContent1.append("" + "\n");
		}

		if (invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			if (!StringUtil
					.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
				strContent1.append("Herr "
						+ invoiceInfo.invoiceOrder.contactInvoice.firstName
						+ "\n");

			} else {
				strContent1.append("Herr " + " " + "\n");
			}
		} else {
			if (!StringUtil
					.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.firstName)) {
				strContent1.append("Frau "
						+ invoiceInfo.invoiceOrder.contactInvoice.firstName
						+ "\n");
			} else {
				strContent1.append("Frau " + " " + "\n");
			}
		}
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactAddress)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactAddress
							+ "\n");
		} else {
			strContent1.append(" " + "\n");
		}
		if (!StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactPLZ)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactPLZ);
		} else {
			strContent1.append(" ");
		}
		if (StringUtil
				.isNullOrEmpty(invoiceInfo.invoiceOrder.contactInvoice.contactStadt)) {
			strContent1
					.append(invoiceInfo.invoiceOrder.contactInvoice.contactStadt);
		} else {
			strContent1.append(" ");
		}

		tvContent1.setText(strContent1.toString());

		// content 2
		StringBuffer strContent2 = new StringBuffer();
		if (!StringUtil.isNullOrEmpty(companyInfo.companyName)) {
			strContent2.append(companyInfo.companyName + "\n");
		} else {
			strContent2.append(" " + "\n");
		}
		strContent2
				.append((companyInfo.companyAddress != null ? companyInfo.companyAddress
						: " ")
						+ "\n");
		strContent2
				.append(companyInfo.companyPLZ != null ? companyInfo.companyPLZ
						: " "
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
		strContent2
				.append((this.companyInfo.unitedStatesT != null ? this.companyInfo.unitedStatesT
						: " ")
						+ "\n");
		tvContent2.setText(strContent2.toString());

		// content 3
		StringBuffer strContent3 = new StringBuffer();
		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		String line = "Datum: " + format.format(currentDateTime);
		strContent3.append(line + "\n");
		strContent3.append("Rechnungsnr: " + fileName.substring(0, fileName.length()-4) + "\n");
		tvContent3.setText(strContent3.toString());

		// table
		double total = 0;
		for (int i = 0, size = this.invoiceInfo.listOrderDetail.size(); i < size; i++) {
			InvoiceOrderDetailDTO dto = this.invoiceInfo.listOrderDetail.get(i);

			DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
					parentActivity, tblListOrderNumber, 2);
			rowOrder.etPos.setText(dto.pos);
			rowOrder.etPos.setEnabled(false);
			rowOrder.etBezeichnung.setText(dto.designation);
			rowOrder.etBezeichnung.setEnabled(false);
			rowOrder.etMenge.setText(dto.quantity);
			rowOrder.etMenge.setEnabled(false);
			rowOrder.etEinze.setText(dto.single_price + " € ");
			rowOrder.etEinze.setEnabled(false);
			total += Double.valueOf(dto.total);
			rowOrder.etGesamt.setText(dto.total + " € ");
			rowOrder.etGesamt.setEnabled(false);
			rowOrder.etArtNr.setVisibility(View.GONE);

			tblListOrderNumber.addView(rowOrder);
		}

		// content 4
		StringBuffer strContent4 = new StringBuffer();
		strContent4.append("Zwischensumme		" + String.valueOf(total) + " € " + "\n");
		float vatValue = 0;
		if (!StringUtil.isNullOrEmpty(companyInfo.vatValue)) {
			vatValue = Float.valueOf(companyInfo.vatValue);
		}
		double newTotal = (vatValue * total) / 100;
		strContent4.append((companyInfo.vatText != null ? companyInfo.vatText
				: " ") + "		" + String.valueOf(newTotal) + " € ");
		tvContent4.setText(strContent4.toString());

		// content 5
		StringBuffer strContent5 = new StringBuffer();
		strContent5.append("Gesamtsumme:		" + String.valueOf(total + newTotal) + " € ");
		tvContent5.setText(strContent5.toString());

		// content 6
		StringBuffer strContent6 = new StringBuffer();
		strContent6.append((companyInfo.bankName != null ? companyInfo.bankName
				: " "));
		tvContent6.setText(strContent6.toString());

		// content 7
		StringBuffer strContent7 = new StringBuffer();
		strContent7.append("BLZ: "
				+ (companyInfo.bankBLZ != null ? companyInfo.bankBLZ : " ")
				+ "\n");
		strContent7.append("Konto-Nr: "
				+ (companyInfo.bankAcctnum != null ? companyInfo.bankAcctnum
						: " ") + "\n");
		strContent7
				.append("Bank: "
						+ (companyInfo.bankCompanyName != null ? companyInfo.bankCompanyName
								: " ") + "\n \n");
		strContent7.append("Wir danken fur den Auftrag. " + "\n \n ");
		strContent7.append("Mit freundlichen Grussen " + "\n \n");
		strContent7
				.append((companyInfo.staffSale != null ? companyInfo.staffSale
						: " ") + "\n");
		tvContent7.setText(strContent7.toString());

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

	class myClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// TODO Auto-generated method stub
			super.onReceivedError(view, errorCode, description, failingUrl);
		}
	}
}