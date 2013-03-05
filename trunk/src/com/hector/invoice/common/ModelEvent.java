/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.common;

/**
 * model event when request use level service
 * 
 * @author: HaiTC3
 * @version: 1.1
 * @since: 1.0
 */
public class ModelEvent {
	private ActionEvent actionEvent;
	private Object modelData;
	private int modelCode;
	/**
	 * @return the actionEvent
	 */
	public ActionEvent getActionEvent() {
		return actionEvent;
	}

	/**
	 * @param actionEvent
	 *            the actionEvent to set
	 */
	public void setActionEvent(ActionEvent actionEvent) {
		this.actionEvent = actionEvent;
	}

	/**
	 * @return the modelData
	 */
	public Object getModelData() {
		return modelData;
	}

	/**
	 * @param modelData
	 *            the modelData to set
	 */
	public void setModelData(Object modelData) {
		this.modelData = modelData;
	}

	/**
	 * @return the modelCode
	 */
	public int getModelCode() {
		return modelCode;
	}

	/**
	 * @param modelCode the modelCode to set
	 */
	public void setModelCode(int modelCode) {
		this.modelCode = modelCode;
	}
}
