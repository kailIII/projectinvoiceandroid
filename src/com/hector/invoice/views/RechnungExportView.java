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
	TextView tvContent8;
	TextView tvContent9;
	TextView tvContent10;
	TextView tvContent11;
	TextView tvContent12;
	TextView tvContent13;
	TextView tvContent14;
	TextView tvContent15;
	TextView tvContent16;
	TextView tvContent17;
	TextView tvContent18;
	TextView tvContent19;
	ImageView ivLogo;
	LinearLayout tblListOrderNumber;

	static InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	static CompanyDTO companyInfo = new CompanyDTO();

	public static RechnungExportView newInstance(String title,
			InvoiceOrderNumberInfoView data, CompanyDTO dataCompany) {
		RechnungExportView f = new RechnungExportView();
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
		// llParentScreen = (LinearLayout)
		// view1.findViewById(R.id.lvListContact);
		this.initData();

		// WebView mWebView = (WebView) view1.findViewById(R.id.wbContent);
		//
		// mWebView.setWebViewClient(new myClient());
		// mWebView.getSettings().setJavaScriptEnabled(true);
		// mWebView.getSettings().setLightTouchEnabled(true);
		// mWebView.getSettings().setUserAgentString("0");
		// mWebView.getSettings().setUseWideViewPort(true);
		//
		// mWebView.loadUrl("http://docs.google.com/gview?embedded=true&url=" +
		// "file:///data/data/com.hector.invoice/cache/DATABASE/MWS-Bugs.pdf");

		// mWebView.getSettings().setJavaScriptEnabled(true);
		// mWebView.getSettings().setPluginsEnabled(true);
		//
		// mWebView.setContentDescription("application/pdf");
		// mWebView.getSettings().setLoadsImagesAutomatically(true);
		// mWebView.getSettings().setAppCacheEnabled(true);

		// File file = new File(ExternalStorage.getFileDBPath(
		// InvoiceInfo.getInstance()
		// .getAppContext())
		// .getAbsolutePath() + "/def.webarchive");
		// Uri path = Uri.fromFile(file);
		//
		// mWebView.loadUrl(path.toString());

		// mWebView.loadUrl("file:///" +
		// ExternalStorage.getFileDBPath(
		// InvoiceInfo.getInstance().getAppContext())
		// .getAbsolutePath() + "/def.webarchive");
		// mWebView.loadUrl("file:///data/data/com.hector.invoice/cache/DATABASE/"
		// + "MyPDFFILE.pdf");
		// mWebView.loadUrl("http://hcmpc.vn/customer/upload/taive/TrinhTuThanhToanTienDienQuaATM.pdf");
		// setContentView(mWebView);

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
		tvContent8 = (TextView) v.findViewById(R.id.tvContent8);
		tvContent9 = (TextView) v.findViewById(R.id.tvContent9);
		tvContent10 = (TextView) v.findViewById(R.id.tvContent10);
		tvContent11 = (TextView) v.findViewById(R.id.tvContent11);
		tvContent12 = (TextView) v.findViewById(R.id.tvContent12);
		tvContent13 = (TextView) v.findViewById(R.id.tvContent13);
		tvContent14 = (TextView) v.findViewById(R.id.tvContent14);
		tvContent15 = (TextView) v.findViewById(R.id.tvContent15);
		tvContent16 = (TextView) v.findViewById(R.id.tvContent16);
		ivLogo = (ImageView) v.findViewById(R.id.ivLogo);
		tblListOrderNumber = (LinearLayout) v
				.findViewById(R.id.tblListOrderNumber);
	}

	public void initData() {
		if (this.companyInfo.logo != null && this.companyInfo.logo.length > 0) {
			Bitmap bm = BitmapFactory.decodeByteArray(this.companyInfo.logo, 0,
					this.companyInfo.logo.length);
			ivLogo.setImageBitmap(bm);
		}

		tvContent1.setText(this.companyInfo.companyName);
		tvContent2
				.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactName);
		tvContent3.setText(this.companyInfo.companyAddress);
		String st = "";
		if (this.invoiceInfo.invoiceOrder.contactInvoice.sex == ContactDTO.SEX_MALE) {
			st = "Herr "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName;
		} else {
			st = "Frau "
					+ this.invoiceInfo.invoiceOrder.contactInvoice.firstName;
		}
		tvContent4.setText(st);
		tvContent5.setText(this.companyInfo.companyPLZ + " "
				+ this.companyInfo.companyCity);

		tvContent6
				.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactAddress);

		tvContent7
				.setText(this.invoiceInfo.invoiceOrder.contactInvoice.contactPLZ
						+ " "
						+ this.invoiceInfo.invoiceOrder.contactInvoice.contactStadt);

		tvContent8.setText("lhre Ansprechpartner/in");

		if (this.companyInfo.sex == ContactDTO.SEX_MALE) {
			st = "							" + "Herr " + this.companyInfo.certificateOfOrigin;
		} else {
			st = "							" + "Faur" + this.companyInfo.certificateOfOrigin;
		}
		tvContent9.setText(st);
		tvContent10.setText("Tel: " + this.companyInfo.telephone);
		tvContent11.setText("Fax: " + this.companyInfo.fax);

		tvContent12.setText("Email: " + this.companyInfo.email);

		tvContent13.setText(this.companyInfo.unitedStatesT);

		tvContent14.setText("Rechnung");

		Date currentDateTime = new Date();
		SimpleDateFormat format = null;
		format = new SimpleDateFormat("dd.MM.yyyy");
		String line = "							" + "Datum: " + format.format(currentDateTime);
		tvContent15.setText(line);

		tvContent16.setText("Rechnungsnr: " + "file name");

		double total = 0;
		for (int i = 0, size = this.invoiceInfo.listOrderDetail.size(); i < size; i++) {
			InvoiceOrderDetailDTO dto = this.invoiceInfo.listOrderDetail.get(i);

			DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
					parentActivity, tblListOrderNumber);
			rowOrder.etPos.setText(dto.pos);
			rowOrder.etPos.setEnabled(false);
			rowOrder.etBezeichnung.setText(dto.designation);
			rowOrder.etBezeichnung.setEnabled(false);
			rowOrder.etMenge.setText(dto.quantity);
			rowOrder.etMenge.setEnabled(false);
			rowOrder.etEinze.setText(dto.single_price);
			rowOrder.etEinze.setEnabled(false);
			total += Double.valueOf(dto.total);
			rowOrder.etGesamt.setText(dto.total);
			rowOrder.etGesamt.setEnabled(false);
			rowOrder.etArtNr.setVisibility(View.GONE);

			tblListOrderNumber.addView(rowOrder);
		}

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