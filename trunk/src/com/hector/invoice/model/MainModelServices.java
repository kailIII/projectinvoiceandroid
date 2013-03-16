/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.model;

import java.util.ArrayList;

import android.os.Bundle;

import com.hector.invoice.common.ActionEvent;
import com.hector.invoice.common.ModelEvent;
import com.hector.invoice.constant.ErrorConstants;
import com.hector.invoice.controller.MainController;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.InvoiceInfoDTO;
import com.hector.invoice.dto.InvoiceOrderDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;
import com.hector.invoice.dto.ListContactViewDTO;
import com.hector.invoice.dto.ListInvoiceNumberInfoView;
import com.hector.invoice.lib.SQLUtils;

/**
 * main model sẻvices
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class MainModelServices {
	protected static MainModelServices instance;

	protected MainModelServices() {
	}

	public static MainModelServices getInstance() {
		if (instance == null) {
			instance = new MainModelServices();
		}
		return instance;
	}

	/**
	 * 
	 * get list contact
	 * 
	 * @author: HaiTC3
	 * @param event
	 * @return: void
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public void requestGetListContact(ActionEvent event) {

		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		ListContactViewDTO listContact = null;
		try {
			listContact = SQLUtils.getInstance().getListContactFromDB(data);
			if (listContact != null) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(listContact);
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * request delete contact
	 * 
	 * @param @param event
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public void requestDeleteContact(ActionEvent event) {

		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			int index = SQLUtils.getInstance().requestDeleteContact(data);
			if (index == 1) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(String.valueOf(index));
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * rquest update or create contact
	 * 
	 * @param @param event
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void requestUpdateOrCreateContact(ActionEvent event) {

		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			int index = SQLUtils.getInstance().requestCreateOrUpdateContact(
					data);
			if (index == 1) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(String.valueOf(index));
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * request get list invoice order
	 * 
	 * @param @param event
	 * @return: void
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public void requestGetListInvoiceOrder(ActionEvent event) {

		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			ArrayList<InvoiceInfoDTO> listInvoice = SQLUtils.getInstance()
					.requestGetListInvoiceOrder(data);
			if (listInvoice != null) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(listInvoice);
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * save invoice info to DB
	 * 
	 * @author: HaiTC3
	 * @param event
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void requestSaveInvoiceInfoToDB(ActionEvent event) {

		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			int result = SQLUtils.getInstance().requestUpdateInvoiceInfoToDB(
					data);
			if (result == 1) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(String.valueOf(result));
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * get list invoice order detail
	 * 
	 * @author: HaiTC3
	 * @param event
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void getListInvoiceOrderDetail(ActionEvent event) {
		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			ArrayList<InvoiceOrderDetailDTO> result = SQLUtils.getInstance()
					.getListInvoiceOrderDetail(data);
			if (result != null) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(result);
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * get company info
	 * 
	 * @author: HaiTC3
	 * @param event
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void getCompanyInfo(ActionEvent event) {
		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			CompanyDTO companyInfo = SQLUtils.getInstance()
					.getCompanyInfo(data);
			if (companyInfo != null) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(companyInfo);
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}

	/**
	 * 
	 * update company info
	 * 
	 * @author: HaiTC3
	 * @param event
	 * @return: void
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public void requestUpdateCompanyInfo(ActionEvent event) {
		ModelEvent model = new ModelEvent();
		model.setActionEvent(event);
		Bundle data = (Bundle) event.viewData;
		try {
			int result = SQLUtils.getInstance().requestUpdateCompanyInfo(data);
			if (result == 1) {
				model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
				model.setModelData(String.valueOf(result));
				MainController.getInstance().handleModelEvent(model);
			} else {
				model.setModelCode(ErrorConstants.ERROR_COMMON);
				MainController.getInstance().handleErrorModelEvent(model);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			model.setModelCode(ErrorConstants.ERROR_COMMON);
			MainController.getInstance().handleErrorModelEvent(model);
		}
	}
}
