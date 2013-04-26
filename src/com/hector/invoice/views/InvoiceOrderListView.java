/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.hector.invoice.R;
import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.BaseActivity;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.ActionEventConstant;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.InvoiceInfoDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;

/**
 * Mo ta muc dich cua lop
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class InvoiceOrderListView extends BaseActivity {

	Button btBack;
	ListView lvListInvoiceOrder;
	boolean isDoneLoadFirst = false;
	ArrayList<InvoiceInfoDTO> listInvoice = new ArrayList<InvoiceInfoDTO>();
	InvoiceInfoDTO currentInvoice = new InvoiceInfoDTO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_list_invoice_order_view);

		lvListInvoiceOrder = (ListView) this
				.findViewById(R.id.lvListInvoiceOrder);
		btBack = (Button) this.findViewById(R.id.btBack);
		btBack.setOnClickListener(this);
		if (!isDoneLoadFirst) {
			this.requestGetListInvoiceOrder();
		}
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
	 * request get list invoice order
	 * 
	 * @param
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void requestGetListInvoiceOrder() {
		this.showProgressDialog(StringUtil.getString(R.string.LOADING));
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.REQUEST_GET_LIST_INVOICE_ORDER;
		MainController.getInstance().handleViewEvent(action);
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
	 * @see
	 * com.hector.invoice.common.BaseActivity#handleModelViewEvent(com.hector
	 * .invoice.common.ModelEvent)
	 */
	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		// TODO Auto-generated method stub
		ActionEvent event = modelEvent.getActionEvent();
		switch (event.action) {
		case ActionEventConstant.REQUEST_GET_LIST_INVOICE_ORDER:
			listInvoice = (ArrayList<InvoiceInfoDTO>) modelEvent.getModelData();
			if (listInvoice.size() > 0) {
				renderLayout();
			}
			this.closeProgressDialog();
			break;
		case ActionEventConstant.REQUEST_GET_DETAIL_INVOICE:
			ArrayList<InvoiceOrderDetailDTO> listInvoiceDetail = (ArrayList<InvoiceOrderDetailDTO>) modelEvent
					.getModelData();
			InvoiceOrderNumberInfoView invoiceInfo = new InvoiceOrderNumberInfoView();
			invoiceInfo.invoiceOrder = this.currentInvoice;
			invoiceInfo.listOrderDetail = listInvoiceDetail;
			Bundle data = new Bundle();
			data.putSerializable(IntentConstants.INTENT_INVOICE_INFO,
					invoiceInfo);
			sendBroadcast(ActionEventConstant.BROAD_CAST_INVOICE_OBJECT, data);
			this.closeProgressDialog();
			this.finish();
			break;
		default:
			break;
		}
		super.handleModelViewEvent(modelEvent);
	}

	/**
	 * 
	 * render list invoice order
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void renderLayout() {
		InvoiceOrderAdapter myAdapter = new InvoiceOrderAdapter(this,
				this.listInvoice, this);
		lvListInvoiceOrder.setAdapter(myAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btBack) {
			this.finish();
		} else {
			super.onClick(v);
		}
	}

	/**
	 * 
	 * get invoice detail
	 * 
	 * @param @param invoiceInfo
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void requestGetDetailInvoice(InvoiceInfoDTO invoiceInfo) {
		this.showProgressDialog(StringUtil.getString(R.string.LOADING));
		this.currentInvoice = invoiceInfo;
		ActionEvent action = new ActionEvent();
		Bundle data = new Bundle();
		data.putString(IntentConstants.INTENT_INVOICE_ORDER_ID,
				String.valueOf(invoiceInfo.invoiceOrderInfo.invoiceOrderId));
		action.viewData = data;
		action.sender = this;
		action.action = ActionEventConstant.REQUEST_GET_DETAIL_INVOICE;
		MainController.getInstance().handleViewEvent(action);
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
		this.closeProgressDialog();
		super.handleErrorModelViewEvent(modelEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hector.invoice.common.BaseActivity#onEvent(int,
	 * android.view.View, java.lang.Object)
	 */
	@Override
	public void onEvent(int eventType, View control, Object data) {
		// TODO Auto-generated method stub
		if (eventType == ActionEventConstant.ACTION_CLICK_ROW_INVOICE_ORDER) {
			this.requestGetDetailInvoice((InvoiceInfoDTO) data);
		} else {
			super.onEvent(eventType, control, data);
		}
	}
}
