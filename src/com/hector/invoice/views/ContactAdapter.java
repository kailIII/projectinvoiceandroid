/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.dto.ContactDTO;

/**
 * Class description
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0 | Mar 14, 2013
 */
public class ContactAdapter extends BaseAdapter {
	private static final int MIN_ROW = 5;
	private Context mContext;
	private ArrayList<ContactDTO> mContacts = new ArrayList<ContactDTO>();
	BaseActivity listen;
	public int index = 0;

	public ContactAdapter(Context context, ArrayList<ContactDTO> list,
			BaseActivity listener) {
		mContext = context;
		mContacts = list;
		this.listen = listener;
	}

	@Override
	public int getCount() {
		return mContacts.size();
	}

	@Override
	public Object getItem(int arg0) {
		if (arg0 < 0 || arg0 > mContacts.size() - 1)
			return null;
		return mContacts.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		View vi = convertView;
		this.index = position;
		ContactDTO itemData = this.mContacts.get(position);
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			vi = li.inflate(R.layout.layout_row_item_contact, null);
			vi.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					listen.onEvent(
							ActionEventConstant.ACTION_CLICK_ROW_CONTACT, null,
							mContacts.get(position));
				}
			});
			TextView tvName = (TextView) vi.findViewById(R.id.tvName); // title
			final ImageView ivDelete = (ImageView) vi
					.findViewById(R.id.ivDelete);
			vi.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					ivDelete.setVisibility(View.VISIBLE);
					return false;
				}
			});
			tvName.setText(itemData.firstName);
			ivDelete.setVisibility(View.INVISIBLE);
			ivDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					listen.onEvent(
							ActionEventConstant.ACTION_CLICK_DELETE_CONTACT,
							null, mContacts.get(index));
				}
			});
			return vi;
		} else {
			TextView tvName = (TextView) vi.findViewById(R.id.tvName); // title
			ImageView ivDelete = (ImageView) vi.findViewById(R.id.ivDelete);
			tvName.setText(itemData.firstName);
			ivDelete.setVisibility(View.INVISIBLE);
		}

		return vi;
	}

	public void setContact(ArrayList<ContactDTO> listContact) {
		if (listContact == null)
			return;
		if (listContact.isEmpty())
			return;

		this.mContacts = listContact;
		notifyDataSetChanged();
	}

}
