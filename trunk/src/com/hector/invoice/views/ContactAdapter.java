/**
 * Copyright 2013 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.views;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hector.invoice.R;
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

	public ContactAdapter(Context context, ArrayList<ContactDTO> list) {
		mContext = context;
		mContacts = list;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		View vi = convertView;
		if (convertView == null) {
			LayoutInflater li = (LayoutInflater) mContext
					.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			vi = li.inflate(R.layout.layout_row_item_contact, null);

			TextView tvName = (TextView) vi.findViewById(R.id.tvName); // title
			ImageView ivDelete = (ImageView) vi.findViewById(R.id.ivDelete);

			
			return vi;
		} else {
		}

		return convertView;
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
