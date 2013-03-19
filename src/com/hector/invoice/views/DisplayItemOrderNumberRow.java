/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hector.invoice.R;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;

/**
 * Mo ta muc dich cua lop
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class DisplayItemOrderNumberRow extends LinearLayout {
	Context _context;
	View view;
	EditText etPos;
	EditText etBezeichnung;
	EditText etArtNr;
	EditText etMenge;
	EditText etEinze;
	EditText etGesamt;

	/**
	 * @param context
	 * @param attrs
	 */
	public DisplayItemOrderNumberRow(Context context, View aRow, int type) {
		super(context);
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (type == 0) {
			view = vi.inflate(R.layout.layout_order_number_row, this);
		}
		else{
			view = vi.inflate(R.layout.layout_order_number_row_2, this);
		}

		etPos = (EditText) view.findViewById(R.id.etPos);
		etBezeichnung = (EditText) view.findViewById(R.id.etBezeichnung);
		etArtNr = (EditText) view.findViewById(R.id.etArtNr);
		etMenge = (EditText) view.findViewById(R.id.etMenge);
		etMenge.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				double menge = Double.valueOf(etMenge.getText().toString());
				double einze = Double.valueOf(etEinze.getText().toString());
				etGesamt.setText(String.valueOf(menge * einze));
			}
		});
		etEinze = (EditText) view.findViewById(R.id.etEinze);
		etEinze.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub

				double menge = Double.valueOf(etMenge.getText().toString());
				double einze = Double.valueOf(etEinze.getText().toString());
				etGesamt.setText(String.valueOf(menge * einze));
			}
		});
		etGesamt = (EditText) view.findViewById(R.id.etGesamt);
		etGesamt.setEnabled(false);
	}

	/**
	 * 
	 * update data for screen
	 * 
	 * @author: HaiTC3
	 * @param invoiceDetail
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void updateLayoutWithData(InvoiceOrderDetailDTO invoiceDetail) {
		if (!StringUtil.isNullOrEmpty(invoiceDetail.pos)) {
			etPos.setText(invoiceDetail.pos);
		}
		if (!StringUtil.isNullOrEmpty(invoiceDetail.designation)) {
			etBezeichnung.setText(invoiceDetail.designation);
		}
		if (!StringUtil.isNullOrEmpty(invoiceDetail.art_nr)) {
			etArtNr.setText(invoiceDetail.art_nr);
		}
		if (!StringUtil.isNullOrEmpty(invoiceDetail.quantity)) {
			etMenge.setText(invoiceDetail.quantity);
		}
		if (!StringUtil.isNullOrEmpty(invoiceDetail.single_price)) {
			etEinze.setText(invoiceDetail.single_price);
		}
		if (!StringUtil.isNullOrEmpty(invoiceDetail.total)) {
			etGesamt.setText(invoiceDetail.total);
		}
	}

}
