/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.ListContactViewDTO;

/**
 * 
 * company information
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public class COMPANY_TABLE extends ABSTRACT_TABLE {
	/**
	 * company info
	 */
	// company id
	public static final String COMPANY_ID = "COMPANY_ID";
	// logo
	public static final String LOGO = "LOGO";
	// company name
	public static final String COMPANY_NAME = "COMPANY_NAME";
	// company address
	public static final String COMPANY_ADDRESS = "COMPANY_ADDRESS";
	// company plz
	public static final String COMPANY_PLZ = "COMPANY_PLZ";
	// company city
	public static final String COMPANY_CITY = "COMPANY_CITY";
	/**
	 * company data
	 */
	// CERTIFICATE OF ORIGIN / C/O
	public static final String CERTIFICATE_OF_ORIGIN = "CERTIFICATE_OF_ORIGIN";
	// sex | geschlech
	public static final String SEX = "SEX";
	// TELEPHONE
	public static final String TELEPHONE = "TELEPHONE";
	// FAX
	public static final String FAX = "FAX";
	// EMAIL
	public static final String EMAIL = "EMAIL";
	// UNITED_STATES_T | UST
	public static final String UNITED_STATES_T = "UNITED_STATES_T";
	/**
	 * bank information
	 */
	// bank name
	public static final String BANK_COMPANY_NAME = "INFORMATICS";
	// BANK kontonr
	public static final String BANK_ACCTNUM = "BANK_ACCTNUM";
	// BANK_BLZ
	public static final String BANK_BLZ = "BANK_BLZ";
	// bank name
	public static final String BANK_NAME = "BANK_NAME";
	/**
	 * VAT
	 */
	// vat text
	public static final String VAT_TEXT = "VAT_TEXT";
	// VAT_VALUE
	public static final String VAT_VALUE = "VAT_VALUE";
	// INVOICE_CONF / rechnungsnr conf
	public static final String INVOICE_CONF = "INVOICE_CONF";
	// staff sale / sachbearbeiter
	public static final String STAFF_SALE = "STAFF_SALE";

	// COMPANY TABLE
	public static final String COMPANY_TABLE = "COMPANY_TABLE";

	public COMPANY_TABLE(SQLiteDatabase mDB) {
		this.tableName = COMPANY_TABLE;
		this.columns = new String[] { COMPANY_ID, LOGO, COMPANY_NAME,
				COMPANY_ADDRESS, COMPANY_ADDRESS, COMPANY_PLZ, COMPANY_CITY,
				CERTIFICATE_OF_ORIGIN, SEX, TELEPHONE, FAX, EMAIL,
				UNITED_STATES_T, BANK_COMPANY_NAME, BANK_ACCTNUM, BANK_BLZ,
				BANK_NAME, VAT_TEXT, VAT_VALUE, INVOICE_CONF, STAFF_SALE };
		this.sqlGetCountQuerry += this.tableName + ";";
		this.mDB = mDB;
		this.listColumn = newListColumn();
	}

	/**
	 * 
	 * new list column for table
	 * 
	 * @return
	 * @return: ArrayList<ColumnTable>
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public ArrayList<ColumnTable> newListColumn() {
		ArrayList<ColumnTable> listColumn = new ArrayList<ColumnTable>();
		// column COMPANY_ID
		ColumnTable CompanyId = new ColumnTable(COMPANY_ID,
				ColumnTable.DATA_TYPE_INTEGER, true, true, false, true,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(CompanyId);

		// column LOGO
		ColumnTable logo = new ColumnTable(LOGO, ColumnTable.DATA_TYPE_BLOB,
				false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(logo);

		// column company Name
		ColumnTable companyName = new ColumnTable(COMPANY_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(companyName);

		// column COMPANY_ADDRESS
		ColumnTable department = new ColumnTable(COMPANY_ADDRESS,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(department);

		// column COMPANY_PLZ
		ColumnTable teacher = new ColumnTable(COMPANY_PLZ,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(teacher);

		// column COMPANY_CITY
		ColumnTable session = new ColumnTable(COMPANY_CITY,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(session);

		// column CERTIFICATE_OF_ORIGIN
		ColumnTable language = new ColumnTable(CERTIFICATE_OF_ORIGIN,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language);
		// column SEX
		ColumnTable multigrade = new ColumnTable(SEX,
				ColumnTable.DATA_TYPE_INTEGER, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(multigrade);
		// column TELEPHONE
		ColumnTable language1 = new ColumnTable(TELEPHONE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language1);
		// column FAX
		ColumnTable language2 = new ColumnTable(FAX,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language2);
		// column EMAIL
		ColumnTable informatics = new ColumnTable(EMAIL,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(informatics);

		// column UNITED_STATES_T
		ColumnTable vocationalschool = new ColumnTable(UNITED_STATES_T,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(vocationalschool);
		// column BANK_COMPANY_NAME
		ColumnTable site = new ColumnTable(BANK_COMPANY_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(site);
		// column BANK_ACCTNUM
		ColumnTable nature = new ColumnTable(BANK_ACCTNUM,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(nature);
		// column BANK_BLZ
		ColumnTable description = new ColumnTable(BANK_BLZ,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(description);
		// column BANK_NAME
		ColumnTable levelId = new ColumnTable(BANK_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(levelId);
		// column VAT_TEXT
		ColumnTable vatText = new ColumnTable(VAT_TEXT,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(vatText);
		// column VAT_VALUE
		ColumnTable vatValue = new ColumnTable(VAT_VALUE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(vatValue);
		// column INVOICE_CONF
		ColumnTable InvoiceConf = new ColumnTable(INVOICE_CONF,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(InvoiceConf);

		// column STAFF_SALE
		ColumnTable staffSale = new ColumnTable(STAFF_SALE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(staffSale);

		return listColumn;
	}

	/**
	 * insert row into db
	 */
	@Override
	protected long insert(AbstractTableDTO dto) {
		ContentValues value = initDataRow((CompanyDTO) dto);
		return insert(null, value);
	}

	/**
	 * 
	 * update company info from company dto
	 * 
	 * @param dto
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public int update(CompanyDTO dto) {
		ContentValues value = initDataRow(dto);
		String[] params = { String.valueOf(dto.companyId) };
		return update(value, COMPANY_ID + " = ?", params);
	}

	/**
	 * update company info from abstract table dto
	 */
	@Override
	protected long update(AbstractTableDTO dto) {
		CompanyDTO cusDTO = (CompanyDTO) dto;
		ContentValues value = initDataRow(cusDTO);
		String[] params = { String.valueOf(cusDTO.companyId) };
		return update(value, COMPANY_ID + " = ?", params);
	}

	/**
	 * 
	 * remove row in db
	 * 
	 * @param id
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public int delete(String id) {
		String[] params = { id };
		return delete(COMPANY_ID + " = ?", params);
	}

	@Override
	protected long delete(AbstractTableDTO dto) {
		CompanyDTO cusDTO = (CompanyDTO) dto;
		String[] params = { String.valueOf(cusDTO.companyId) };
		return delete(COMPANY_ID + " = ?", params);
	}

	/**
	 * 
	 * get row in db flow id
	 * 
	 * @param id
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public CompanyDTO getCompanyById(String id) {
		CompanyDTO companyInfo = new CompanyDTO();
		Cursor c = null;
		try {
			String[] params = { id };
			c = query(COMPANY_ID + " = ?", params, null, null, null);
			if (c != null) {
				if (c.moveToFirst()) {
					companyInfo.initLogDTOFromCursor(c);
				}
			}
		} catch (Exception ex) {
			c = null;
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return companyInfo;
	}

	/**
	 * 
	 * init log
	 * 
	 * @param c
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public CompanyDTO initLogDTOFromCursor(Cursor c) {
		CompanyDTO companyInfo = new CompanyDTO();
		companyInfo.initLogDTOFromCursor(c);
		return companyInfo;
	}

	/**
	 * 
	 * general contentValues
	 * 
	 * @param dto
	 * @return
	 * @return: ContentValues
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public ContentValues initDataRow(CompanyDTO dto) {
		ContentValues editedValues = new ContentValues();
		if (dto.companyId > 0) {
			editedValues.put(COMPANY_ID, String.valueOf(dto.companyId));
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.logo))) {
			editedValues.put(LOGO, dto.logo);
		}
		if (String.valueOf(dto.companyName) != null) {
			editedValues.put(COMPANY_NAME, dto.companyName);
		}
		if (String.valueOf(dto.companyAddress) != null) {
			editedValues.put(COMPANY_ADDRESS, dto.companyAddress);
		}
		if (String.valueOf(dto.companyPLZ) != null) {
			editedValues.put(COMPANY_PLZ, dto.companyPLZ);
		}
		if (String.valueOf(dto.companyCity) != null) {
			editedValues.put(COMPANY_CITY, dto.companyCity);
		}
		if (String.valueOf(dto.certificateOfOrigin) != null) {
			editedValues.put(CERTIFICATE_OF_ORIGIN, dto.certificateOfOrigin);
		}
		if (String.valueOf(dto.sex) != null) {
			editedValues.put(SEX, String.valueOf(dto.sex));
		}
		if (String.valueOf(dto.fax) != null) {
			editedValues.put(FAX, dto.fax);
		}
		if (String.valueOf(dto.email) != null) {
			editedValues.put(EMAIL, dto.email);
		}
		if (String.valueOf(dto.unitedStatesT) != null) {
			editedValues.put(UNITED_STATES_T, dto.unitedStatesT);
		}
		if (String.valueOf(dto.bankCompanyName) != null) {
			editedValues.put(BANK_COMPANY_NAME, dto.bankCompanyName);
		}
		if (String.valueOf(dto.bankAcctnum) != null) {
			editedValues.put(BANK_ACCTNUM, dto.bankAcctnum);
		}
		if (String.valueOf(dto.bankBLZ) != null) {
			editedValues.put(BANK_BLZ, dto.bankBLZ);
		}
		if (String.valueOf(dto.bankName) != null) {
			editedValues.put(BANK_NAME, dto.bankName);
		}
		if (String.valueOf(dto.vatText) != null) {
			editedValues.put(VAT_TEXT, dto.vatText);
		}
		if (String.valueOf(dto.vatValue) != null) {
			editedValues.put(VAT_VALUE, dto.vatValue);
		}
		if (String.valueOf(dto.invoiceConf) != null) {
			editedValues.put(INVOICE_CONF, dto.invoiceConf);
		}
		if (String.valueOf(dto.staffSale) != null) {
			editedValues.put(STAFF_SALE, dto.staffSale);
		}
		return editedValues;
	}

	/**
	 * 
	 * get company info
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: CompanyDTO
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public CompanyDTO getCompanyInfo(Bundle data) {
		CompanyDTO companyInfo = new CompanyDTO();
		StringBuffer queryGetlistContact = new StringBuffer();
		queryGetlistContact.append("select * from COMPANY_TABLE limit 1 ");

		String[] paramsGetListProduct = new String[] {};

		Cursor c = null;
		try {
			c = rawQuery(queryGetlistContact.toString(), paramsGetListProduct);

			if (c != null) {

				if (c.moveToFirst()) {
					companyInfo.initLogDTOFromCursor(c);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (c != null) {
				c.close();
			}
		}
		return companyInfo;
	}
}
