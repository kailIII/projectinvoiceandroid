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
	public DisplayItemOrderNumberRow(Context context, View aRow) {
		super(context);
		LayoutInflater vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = vi.inflate(R.layout.layout_order_number_row, this);

		etPos = (EditText) view.findViewById(R.id.etPos);
		etBezeichnung = (EditText) view.findViewById(R.id.etBezeichnung);
		etArtNr = (EditText) view.findViewById(R.id.etArtNr);
		etMenge = (EditText) view.findViewById(R.id.etMenge);
		etEinze = (EditText) view.findViewById(R.id.etEinze);
		etGesamt = (EditText) view.findViewById(R.id.etGesamt);
	}

}
