package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.lib.COMPANY_TABLE;

/**
 * 
 * luu thong tin lop hoc
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class CompanyDTO extends AbstractTableDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int SEX_MALE = 1;
	public static final int SEX_FEMALE = 0;
	
	// ma lop
	public long companyId;
	// ten lop
	public String logo;
	// ten lop de search
	public String companyName;
	// nam hoc
	public String companyAddress;
	// khoi hoc
	public String companyPLZ;
	// phan ban
	public String companyCity;
	// giao vien chu nhiem
	public String certificateOfOrigin;
	// buoi hoc (sang / chieu)
	public int sex; // 1: Male / 0: FeMale
	// hoc tieng dan toc
	public String fax;
	// lop ghep (co / khong)
	public String email;
	// ma lop ghep
	public String unitedStatesT;
	// ngoai ngu 1
	public String bankCompanyName;
	// ngoai ngu 2
	public String bankAcctnum;
	// tin hoc
	public String bankBLZ;
	// hoc nghe pho thong
	public String bankName;
	// thuoc diem truong
	public String vatText;
	// tinh chat
	public String vatValue;
	// mo ta
	public String invoiceConf;// mo ta
	// level id
	public String staffSale;// mo ta

	public CompanyDTO() {
		super(TableType.LIST_COMPANY);
	}

	/**
	 * 
	 * Khoi tao thong tin sau khi query database
	 * 
	 * @param c
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public void initLogDTOFromCursor(Cursor c) {
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_ID) >= 0) {
			companyId = c.getLong(c.getColumnIndex(COMPANY_TABLE.COMPANY_ID));
		}
		if (c.getColumnIndex(COMPANY_TABLE.LOGO) >= 0) {
			logo = c.getString(c.getColumnIndex(COMPANY_TABLE.LOGO));
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_NAME) >= 0) {
			companyName = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_NAME));
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_ADDRESS) >= 0) {
			companyAddress = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_ADDRESS));
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_PLZ) >= 0) {
			companyPLZ = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_PLZ));
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_CITY) >= 0) {
			companyCity = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_CITY));
		}
		if (c.getColumnIndex(COMPANY_TABLE.CERTIFICATE_OF_ORIGIN) >= 0) {
			certificateOfOrigin = c.getString(c
					.getColumnIndex(COMPANY_TABLE.CERTIFICATE_OF_ORIGIN));
		}
		if (c.getColumnIndex(COMPANY_TABLE.SEX) >= 0) {
			sex = c.getInt(c.getColumnIndex(COMPANY_TABLE.SEX));
		}
		if (c.getColumnIndex(COMPANY_TABLE.FAX) >= 0) {
			fax = c.getString(c.getColumnIndex(COMPANY_TABLE.FAX));
		}
		if (c.getColumnIndex(COMPANY_TABLE.EMAIL) >= 0) {
			email = c.getString(c.getColumnIndex(COMPANY_TABLE.EMAIL));
		}
		if (c.getColumnIndex(COMPANY_TABLE.UNITED_STATES_T) >= 0) {
			unitedStatesT = c.getString(c
					.getColumnIndex(COMPANY_TABLE.UNITED_STATES_T));
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_COMPANY_NAME) >= 0) {
			bankCompanyName = c.getString(c
					.getColumnIndex(COMPANY_TABLE.BANK_COMPANY_NAME));
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_ACCTNUM) >= 0) {
			bankAcctnum = c.getString(c
					.getColumnIndex(COMPANY_TABLE.BANK_ACCTNUM));
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_BLZ) >= 0) {
			bankBLZ = c.getString(c.getColumnIndex(COMPANY_TABLE.BANK_BLZ));
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_NAME) >= 0) {
			bankName = c.getString(c.getColumnIndex(COMPANY_TABLE.BANK_NAME));
		}
		if (c.getColumnIndex(COMPANY_TABLE.VAT_TEXT) >= 0) {
			vatText = c.getString(c.getColumnIndex(COMPANY_TABLE.VAT_TEXT));
		}
		if (c.getColumnIndex(COMPANY_TABLE.VAT_VALUE) >= 0) {
			vatValue = c.getString(c.getColumnIndex(COMPANY_TABLE.VAT_VALUE));
		}
		if (c.getColumnIndex(COMPANY_TABLE.INVOICE_CONF) >= 0) {
			invoiceConf = c.getString(c
					.getColumnIndex(COMPANY_TABLE.INVOICE_CONF));
		}
		if (c.getColumnIndex(COMPANY_TABLE.STAFF_SALE) >= 0) {
			staffSale = c.getString(c.getColumnIndex(COMPANY_TABLE.STAFF_SALE));
		}

	}
}
