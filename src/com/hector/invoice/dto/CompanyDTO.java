package com.hector.invoice.dto;

import android.database.Cursor;

import com.hector.invoice.constant.Constants;
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
	public byte[] logo;
	// ten lop de search
	public String companyName = Constants.STR_BLANK;
	// nam hoc
	public String companyAddress = Constants.STR_BLANK;
	// khoi hoc
	public String companyPLZ = Constants.STR_BLANK;
	// phan ban
	public String companyCity = Constants.STR_BLANK;
	// giao vien chu nhiem
	public String certificateOfOrigin = Constants.STR_BLANK;
	// telephone
	public String telephone = Constants.STR_BLANK;
	// buoi hoc (sang / chieu)
	public int sex; // 1: Male / 0: FeMale
	// hoc tieng dan toc
	public String fax = Constants.STR_BLANK;
	// lop ghep (co / khong)
	public String email = Constants.STR_BLANK;
	// ma lop ghep
	public String unitedStatesT = Constants.STR_BLANK;
	// ngoai ngu 1
	public String bankCompanyName = Constants.STR_BLANK;
	// ngoai ngu 2
	public String bankAcctnum = Constants.STR_BLANK;
	// tin hoc
	public String bankBLZ = Constants.STR_BLANK;
	// hoc nghe pho thong
	public String bankName = Constants.STR_BLANK;
	// thuoc diem truong
	public String vatText = Constants.STR_BLANK;
	// tinh chat
	public String vatValue = Constants.STR_BLANK;
	// mo ta
	public String invoiceConf = Constants.STR_BLANK;// mo ta
	// level id
	public String staffSale = Constants.STR_BLANK;// mo ta

	public CompanyDTO() {
		super(TableType.LIST_COMPANY);
		vatValue = "0";
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
			logo = c.getBlob(c.getColumnIndex(COMPANY_TABLE.LOGO));
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_NAME) >= 0) {
			companyName = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_NAME));
		} else {
			companyName = "";
		}

		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_ADDRESS) >= 0) {
			companyAddress = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_ADDRESS));
		} else {
			companyAddress = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_PLZ) >= 0) {
			companyPLZ = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_PLZ));
		} else {
			companyPLZ = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.COMPANY_CITY) >= 0) {
			companyCity = c.getString(c
					.getColumnIndex(COMPANY_TABLE.COMPANY_CITY));
		} else {
			companyCity = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.CERTIFICATE_OF_ORIGIN) >= 0) {
			certificateOfOrigin = c.getString(c
					.getColumnIndex(COMPANY_TABLE.CERTIFICATE_OF_ORIGIN));
		} else {
			certificateOfOrigin = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.SEX) >= 0) {
			sex = c.getInt(c.getColumnIndex(COMPANY_TABLE.SEX));
		} else {
			sex = ContactDTO.SEX_MALE;
		}
		if (c.getColumnIndex(COMPANY_TABLE.TELEPHONE) >= 0) {
			telephone = c.getString(c.getColumnIndex(COMPANY_TABLE.TELEPHONE));
		} else {
			telephone = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.FAX) >= 0) {
			fax = c.getString(c.getColumnIndex(COMPANY_TABLE.FAX));
		} else {
			fax = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.EMAIL) >= 0) {
			email = c.getString(c.getColumnIndex(COMPANY_TABLE.EMAIL));
		} else {
			email = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.UNITED_STATES_T) >= 0) {
			unitedStatesT = c.getString(c
					.getColumnIndex(COMPANY_TABLE.UNITED_STATES_T));
		} else {
			unitedStatesT = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_COMPANY_NAME) >= 0) {
			bankCompanyName = c.getString(c
					.getColumnIndex(COMPANY_TABLE.BANK_COMPANY_NAME));
		} else {
			bankCompanyName = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_ACCTNUM) >= 0) {
			bankAcctnum = c.getString(c
					.getColumnIndex(COMPANY_TABLE.BANK_ACCTNUM));
		} else {
			bankAcctnum = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_BLZ) >= 0) {
			bankBLZ = c.getString(c.getColumnIndex(COMPANY_TABLE.BANK_BLZ));
		} else {
			bankBLZ = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.BANK_NAME) >= 0) {
			bankName = c.getString(c.getColumnIndex(COMPANY_TABLE.BANK_NAME));
		} else {
			bankName = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.VAT_TEXT) >= 0) {
			vatText = c.getString(c.getColumnIndex(COMPANY_TABLE.VAT_TEXT));
		} else {
			vatText = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.VAT_VALUE) >= 0) {
			vatValue = c.getString(c.getColumnIndex(COMPANY_TABLE.VAT_VALUE));
		} else {
			vatValue = "0";
		}
		if (c.getColumnIndex(COMPANY_TABLE.INVOICE_CONF) >= 0) {
			invoiceConf = c.getString(c
					.getColumnIndex(COMPANY_TABLE.INVOICE_CONF));
		} else {
			invoiceConf = "";
		}
		if (c.getColumnIndex(COMPANY_TABLE.STAFF_SALE) >= 0) {
			staffSale = c.getString(c.getColumnIndex(COMPANY_TABLE.STAFF_SALE));
		} else {
			staffSale = "";
		}
	}
}
