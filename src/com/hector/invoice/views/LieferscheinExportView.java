/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.util.ArrayList;

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
public class LieferscheinExportView extends BaseFragment implements
		OnEventControlListener {
	// parent activity
	BaseFragmentActivity parentActivity;
	LinearLayout llParentScreen;

	ArrayList<Integer> listIconItem2;

	TextView tvContent1;
	TextView tvContent2;
	TextView tvContent3;
	ImageView ivLogo;
	LinearLayout tblListOrderNumber;
	static String fileName = "";

	static InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
	static CompanyDTO companyInfo = new CompanyDTO();

	public static LieferscheinExportView newInstance(String title,
			InvoiceOrderNumberInfoView data, CompanyDTO dataCompany, String filName) {
		LieferscheinExportView f = new LieferscheinExportView();
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
				R.layout.layout_page_lieferschein_export, container, false);
		this.initView(view1);
		this.initData();
		return view1;
	}

	public void initView(View v) {
		tvContent1 = (TextView) v.findViewById(R.id.tvContent1);
		tvContent2 = (TextView) v.findViewById(R.id.tvContent2);
		tvContent3 = (TextView) v.findViewById(R.id.tvContent3);
		ivLogo = (ImageView) v.findViewById(R.id.ivLogo);
		tblListOrderNumber = (LinearLayout) v
				.findViewById(R.id.tblListOrderNumber);
	}

	/**
	 * 
	 * init data for screen
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Nov 3, 2012
	 */
	public void initData() {
		// show logo
		if (this.companyInfo.logo != null && this.companyInfo.logo.length > 0) {
			Bitmap bm = BitmapFactory.decodeByteArray(this.companyInfo.logo, 0,
					this.companyInfo.logo.length);
			ivLogo.setImageBitmap(bm);
		}

		// show content 1
		StringBuffer strContent1 = new StringBuffer();
		strContent1.append("Firma \n");
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactName != null ? invoiceInfo.invoiceOrder.contactInvoice.contactName
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
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactPLZ != null ? (invoiceInfo.invoiceOrder.contactInvoice.contactPLZ + " ")
						: ""));
		strContent1
				.append((invoiceInfo.invoiceOrder.contactInvoice.contactStadt != null ? invoiceInfo.invoiceOrder.contactInvoice.contactStadt
						: ""));

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
								: " ") + "\n\n");
		strContent2.append("lhre Ansprechpartner/in \n");
		if (companyInfo.sex == ContactDTO.SEX_MALE) {
			strContent2
					.append("Herr "
							+ (companyInfo.certificateOfOrigin != null ? companyInfo.certificateOfOrigin
									: " ") + "\n");
		} else {
			strContent2
					.append("Faur "
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
		strContent3
				.append("Bestellt am: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.orderedOn
								: " ") + "\n");
		strContent3
				.append("Lieferdatum: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.delivery
								: " ") + "\n");
		strContent3
				.append("Kunden-Nr.: "
						+ (invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber != null ? invoiceInfo.invoiceOrder.invoiceOrderInfo.customerNumber
								: " ") + "\n");
		strContent3.append("Lieferschein-Nr: " + fileName.substring(0, fileName.length()-4) + "\n");
		tvContent3.setText(strContent3.toString());

		for (int i = 0, size = this.invoiceInfo.listOrderDetail.size(); i < size; i++) {
			InvoiceOrderDetailDTO dto = this.invoiceInfo.listOrderDetail.get(i);

			DisplayItemOrderNumberRow rowOrder = new DisplayItemOrderNumberRow(
					parentActivity, tblListOrderNumber, 2);
			rowOrder.etPos.setText(dto.pos);
			rowOrder.etPos.setEnabled(false);
			rowOrder.etMenge.setText(dto.quantity);
			rowOrder.etMenge.setEnabled(false);
			rowOrder.etArtNr.setText(dto.art_nr);
			rowOrder.etArtNr.setEnabled(false);
			rowOrder.etBezeichnung.setText(dto.designation);
			rowOrder.etBezeichnung.setEnabled(false);
			rowOrder.etEinze.setVisibility(View.GONE);
			rowOrder.etGesamt.setVisibility(View.GONE);

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
}