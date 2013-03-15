/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hector.invoice.common.StringUtil;
import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.CompanyDTO;

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
		ColumnTable logo = new ColumnTable(LOGO, ColumnTable.DATA_TYPE_TEXT,
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
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.companyName))) {
			editedValues.put(COMPANY_NAME, dto.companyName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.companyAddress))) {
			editedValues.put(COMPANY_ADDRESS, dto.companyAddress);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.companyPLZ))) {
			editedValues.put(COMPANY_PLZ, dto.companyPLZ);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.companyCity))) {
			editedValues.put(COMPANY_CITY, dto.companyCity);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.certificateOfOrigin))) {
			editedValues.put(CERTIFICATE_OF_ORIGIN, dto.certificateOfOrigin);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.sex))) {
			editedValues.put(SEX, String.valueOf(dto.sex));
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.fax))) {
			editedValues.put(FAX, dto.fax);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.email))) {
			editedValues.put(EMAIL, dto.email);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.unitedStatesT))) {
			editedValues.put(UNITED_STATES_T, dto.unitedStatesT);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.bankCompanyName))) {
			editedValues.put(BANK_COMPANY_NAME, dto.bankCompanyName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.bankAcctnum))) {
			editedValues.put(BANK_ACCTNUM, dto.bankAcctnum);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.bankBLZ))) {
			editedValues.put(BANK_BLZ, dto.bankBLZ);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.bankName))) {
			editedValues.put(BANK_NAME, dto.bankName);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.vatText))) {
			editedValues.put(VAT_TEXT, dto.vatText);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.vatValue))) {
			editedValues.put(VAT_VALUE, dto.vatValue);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.invoiceConf))) {
			editedValues.put(INVOICE_CONF, dto.invoiceConf);
		}
		if (!StringUtil.isNullOrEmpty(String.valueOf(dto.staffSale))) {
			editedValues.put(STAFF_SALE, dto.staffSale);
		}
		return editedValues;
	}
}
