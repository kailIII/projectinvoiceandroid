/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.dto.InvoiceInfoDTO;
import com.hector.invoice.dto.InvoiceOrderDTO;

/**
 * adapter for invoice order
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 14, 2013
 */
public class InvoiceOrderAdapter extends BaseAdapter {
	private static final int MIN_ROW = 5;
	private Context mContext;
	private ArrayList<InvoiceInfoDTO> listInvoice = new ArrayList<InvoiceInfoDTO>();
	BaseActivity listen;
	public int index = 0;

	public InvoiceOrderAdapter(Context context, ArrayList<InvoiceInfoDTO> list,
			BaseActivity listener) {
		mContext = context;
		listInvoice = list;
		this.listen = listener;
	}

	@Override
	public int getCount() {
		return listInvoice.size();
	}

	@Override
	public Object getItem(int arg0) {
		if (arg0 < 0 || arg0 > listInvoice.size() - 1)
			return null;
		return listInvoice.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		View vi = convertView;
		this.index = position;
		InvoiceInfoDTO itemData = this.listInvoice.get(position);
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			vi = li.inflate(R.layout.layout_row_item_contact, null);
			vi.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					listen.onEvent(
							ActionEventConstant.ACTION_CLICK_ROW_INVOICE_ORDER,
							null, listInvoice.get(position));
				}
			});
			TextView tvName = (TextView) vi.findViewById(R.id.tvName); // title
			ImageView ivDelete = (ImageView) vi.findViewById(R.id.ivDelete);
			tvName.setText(itemData.invoiceOrderInfo.contactName);
			ivDelete.setVisibility(View.GONE);
			return vi;
		} else {
			TextView tvName = (TextView) vi.findViewById(R.id.tvName); // title
			ImageView ivDelete = (ImageView) vi.findViewById(R.id.ivDelete);
			tvName.setText(itemData.invoiceOrderInfo.contactName);
			ivDelete.setVisibility(View.GONE);
		}

		return vi;
	}

	/**
	 * 
	 * set list invoice
	 * 
	 * @param @param listContact
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void setListInvoice(ArrayList<InvoiceInfoDTO> listContact) {
		if (listContact == null)
			return;
		if (listContact.isEmpty())
			return;

		this.listInvoice = listContact;
		notifyDataSetChanged();
	}

}
