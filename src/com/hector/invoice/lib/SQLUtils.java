/**
 * Copyright 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.hector.invoice.common.GlobalUtil;
import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.dto.AbstractTableDTO;
import com.hector.invoice.dto.CompanyDTO;
import com.hector.invoice.dto.ContactDTO;
import com.hector.invoice.dto.InvoiceInfoDTO;
import com.hector.invoice.dto.InvoiceOrderDetailDTO;
import com.hector.invoice.dto.InvoiceOrderNumberInfoView;
import com.hector.invoice.dto.ListContactViewDTO;

/**
 * 
 * Class connect to sqlite, access to DB local : insert, delete, update, clear
 * data, create table ....
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public class SQLUtils {
	// sqlite database
	private static SQLiteDatabase mDB;
	// instance
	private static SQLUtils instance = null;
	// lock object
	private static final Object lockObject = new Object();

	public static SQLUtils getInstance() {
		if (instance == null) {
			synchronized (lockObject) {
				if (instance == null) {
					instance = new SQLUtils();
				}
			}
		}
		if (mDB == null || !mDB.isOpen()) {
			synchronized (lockObject) {
				if (mDB == null || !mDB.isOpen()) {
					try {
						boolean dbExists = GlobalUtil.getInstance()
								.checkExistsDataBase();
						mDB = SQLiteDatabase.openOrCreateDatabase(
								ExternalStorage.getFileDBPath(
										InvoiceInfo.getInstance()
												.getAppContext())
										.getAbsolutePath()
										+ "/InvoiceDatabase", null);
						if (!dbExists) {
							createAllTableForDatabase();
						}

						// mDB = SQLiteDatabase.openOrCreateDatabase(
						// ExternalStorage.getFileDBPath(
						// InvoiceInfo.getInstance()
						// .getAppContext())
						// .getAbsolutePath()
						// + "/InvoiceDatabase", null,
						// new DatabaseErrorHandler() {
						//
						// public void onCorruption(
						// SQLiteDatabase dbObj) {
						// // TODO Auto-generated method stub
						//
						// }
						// });

						// mDB = SQLiteDatabase.openDatabase(
						// ExternalStorage.getFileDBPath(
						// GlobalInfo.getInstance()
						// .getAppContext())
						// .getAbsolutePath()
						// + "/InvoiceDatabase", null,
						// SQLiteDatabase.OPEN_READWRITE);
						// if (!dbExists) {
						// createAllTableForDatabase();
						// }
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * create all table for database
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public static void createAllTableForDatabase() {
		ArrayList<ABSTRACT_TABLE> listTable = new ArrayList<ABSTRACT_TABLE>();
		COMPANY_TABLE tbListClass = new COMPANY_TABLE(mDB);
		CONTACT_TABLET contactTable = new CONTACT_TABLET(mDB);
		INVOICE_ORDER_TABLET invoiceOrderTable = new INVOICE_ORDER_TABLET(mDB);
		INVOICE_ORDER_DETAIL_TABLET invoiceOrderDetailTable = new INVOICE_ORDER_DETAIL_TABLET(
				mDB);

		listTable.add(tbListClass);
		listTable.add(contactTable);
		listTable.add(invoiceOrderTable);
		listTable.add(invoiceOrderDetailTable);

		createListTableIntoDB(listTable);
	}

	/**
	 * 
	 * create one table in database
	 * 
	 * @param String
	 *            tableName, ArrayList <ColumnTable> columnInfo
	 * @return
	 * @return: boolean
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public boolean createOneTableIntoDatabase(String tableName,
			ArrayList<ColumnTable> listColumnInfo) {
		boolean result = false;
		StringBuffer strSQLCreateTable = new StringBuffer();
		strSQLCreateTable.append("CREATE TABLE IF NOT EXISTS " + tableName
				+ " (");
		for (int i = 0, size = listColumnInfo.size(); i < size; i++) {
			strSQLCreateTable.append(listColumnInfo.get(i)
					.generaSQLCreateColumn());
			if (i < (size - 1)) {
				strSQLCreateTable.append(" , ");
			}
		}
		strSQLCreateTable.append(" )");
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.execSQL(strSQLCreateTable.toString());
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * create one table into DB from tableInfo
	 * 
	 * @param tableInfo
	 * @return
	 * @return: boolean
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public boolean createOneTableIntoDatabase(ABSTRACT_TABLE tableInfo) {
		boolean result = false;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				mDB.execSQL(tableInfo.generaSQLCreateTable());
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * create list table for DB
	 * 
	 * @param listTable
	 * @return
	 * @return: boolean
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public static boolean createListTableIntoDB(
			ArrayList<ABSTRACT_TABLE> listTable) {
		boolean result = false;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				for (int i = 0, size = listTable.size(); i < size; i++) {
					ABSTRACT_TABLE tableTMP = listTable.get(i);
					mDB.execSQL(tableTMP.generaSQLCreateTable());
				}
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * drop one table in database if it exists
	 * 
	 * @author: HaiTC3
	 * @param tableName
	 * @return
	 * @return: boolean
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public boolean dropTableIfExists(String tableName) {
		boolean result = true;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				mDB.execSQL("DROP TABLE IF EXISTS " + tableName);
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * drop list table in DB if exists
	 * 
	 * @author: HaiTC3
	 * @param listTableName
	 * @return
	 * @return: boolean
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public boolean dropListTableIfExists(ArrayList<String> listTableName) {
		boolean result = true;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				for (int i = 0, size = listTableName.size(); i < size; i++) {
					mDB.execSQL("DROP TABLE IF EXISTS "
							+ listTableName.get(i).trim());
				}
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * clear all data in table but give keep table don't remove it
	 * 
	 * @param tableName
	 * @return
	 * @return: boolean
	 * @throws:
	 * @author: HaiTC3
	 * @date: Mar 2, 2013
	 */
	public boolean clearAllDataInTable(String tableName) {
		boolean result = true;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();

				mDB.execSQL("DELETE FROM " + tableName);

				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				result = false;
				e.printStackTrace();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * Delete all data in all table
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 2, 2013
	 */
	public void clearAllDataInDatabase() {
		StringBuilder sqlQuery = new StringBuilder();

		sqlQuery.append("SELECT name FROM sqlite_master ");
		sqlQuery.append("WHERE type IN ('table','view') AND name NOT LIKE 'sqlite_%' and name NOT LIKE 'android_%' ");
		sqlQuery.append("UNION ALL ");
		sqlQuery.append("SELECT name FROM sqlite_temp_master ");
		sqlQuery.append("WHERE type IN ('table','view') ");
		sqlQuery.append("ORDER BY 1 ");

		Cursor c = null;
		ArrayList<String> tableNames = new ArrayList<String>();

		try {
			c = mDB.rawQuery(sqlQuery.toString(), null);

			if (c != null) {
				if (c.moveToFirst()) {
					do {
						String name = c.getString(0);
						tableNames.add(name);
					} while (c.moveToNext());
				}
			}
		} catch (Exception ex) {
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		for (int i = 0, size = tableNames.size(); i < size; i++) {
			clearAllDataInTable(tableNames.get(i));
		}
	}

	public SQLiteDatabase getmDB() {
		return mDB;
	}

	// ------------PRIVATE------------//
	/**
	 * 
	 * insert dto to db local
	 * 
	 * @param tableDTO
	 * @return
	 * @return: long
	 * @throws:
	 * @author: HaiTC3
	 * @date: Mar 7, 2012
	 */
	private long insertDTO(AbstractTableDTO tableDTO) {
		long res = -1;
		if (AbstractTableDTO.TableType.LIST_COMPANY.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
			res = table.insert(tableDTO);
		} else if (AbstractTableDTO.TableType.LIST_CONTACT.equals(tableDTO
				.getType())) {
			CONTACT_TABLET table = new CONTACT_TABLET(mDB);
			res = table.insert(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER.equals(tableDTO
				.getType())) {
			INVOICE_ORDER_TABLET table = new INVOICE_ORDER_TABLET(mDB);
			res = table.insert(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER_DETAIL
				.equals(tableDTO.getType())) {
			INVOICE_ORDER_DETAIL_TABLET table = new INVOICE_ORDER_DETAIL_TABLET(
					mDB);
			res = table.insert(tableDTO);
		}
		return res;

	}

	/**
	 * 
	 * Update dto(value of row) to table
	 * 
	 * @param tableDTO
	 * @return
	 * @return: long
	 * @throws:
	 * @author: HaiTC3
	 * @date: mar 7, 2012
	 */
	private long updateDTO(AbstractTableDTO tableDTO) {
		long res = -1;
		if (AbstractTableDTO.TableType.LIST_COMPANY.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
			res = table.update(tableDTO);
		} else if (AbstractTableDTO.TableType.LIST_CONTACT.equals(tableDTO
				.getType())) {
			CONTACT_TABLET table = new CONTACT_TABLET(mDB);
			res = table.update(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER.equals(tableDTO
				.getType())) {
			INVOICE_ORDER_TABLET table = new INVOICE_ORDER_TABLET(mDB);
			res = table.update(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER_DETAIL
				.equals(tableDTO.getType())) {
			INVOICE_ORDER_DETAIL_TABLET table = new INVOICE_ORDER_DETAIL_TABLET(
					mDB);
			res = table.update(tableDTO);
		}
		return res;
	}

	/**
	 * 
	 * Delete dto(value of row) to table
	 * 
	 * @param tableDTO
	 * @return
	 * @return: long
	 * @throws:
	 * @author: HaiTC3
	 * @date: Mar 7, 2012
	 */
	private long deleteDTO(AbstractTableDTO tableDTO) {
		long res = -1;
		if (AbstractTableDTO.TableType.LIST_COMPANY.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
			res = table.delete(tableDTO);
		} else if (AbstractTableDTO.TableType.LIST_CONTACT.equals(tableDTO
				.getType())) {
			CONTACT_TABLET table = new CONTACT_TABLET(mDB);
			res = table.delete(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER.equals(tableDTO
				.getType())) {
			INVOICE_ORDER_TABLET table = new INVOICE_ORDER_TABLET(mDB);
			res = table.delete(tableDTO);
		} else if (AbstractTableDTO.TableType.INVOICE_ORDER_DETAIL
				.equals(tableDTO.getType())) {
			INVOICE_ORDER_DETAIL_TABLET table = new INVOICE_ORDER_DETAIL_TABLET(
					mDB);
			res = table.delete(tableDTO);
		}
		return res;
	}

	/**
	 * 
	 * Thuc thi mot mot hanh dong insert, update, delete mot doi tuong vao bang
	 * 
	 * @param dto
	 * @return
	 * @return: long ( -1 if fail)
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	private long execute(AbstractTableDTO dto) {
		long res = -1;
		if (AbstractTableDTO.TableAction.INSERT.equals(dto.getAction())) {
			res = insertDTO(dto);
		}
		if (AbstractTableDTO.TableAction.UPDATE.equals(dto.getAction())) {
			res = updateDTO(dto);
		}
		if (AbstractTableDTO.TableAction.DELETE.equals(dto.getAction())) {
			res = deleteDTO(dto);
		}
		return res;
	}

	// ------------PUBLIC-------------//
	/**
	 * 
	 * Insert dto
	 * 
	 * @param tableDTO
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public synchronized void insert(AbstractTableDTO tableDTO) {
		if (mDB != null && tableDTO != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				insertDTO(tableDTO);
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
	}

	/**
	 * 
	 * Update dto
	 * 
	 * @param tableDTO
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public synchronized void update(AbstractTableDTO tableDTO) {
		if (mDB != null && tableDTO != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				updateDTO(tableDTO);
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
	}

	/**
	 * 
	 * Delete dto
	 * 
	 * @param tableDTO
	 * @return
	 * @return: long
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public synchronized long delete(AbstractTableDTO tableDTO) {
		long res = -1;
		if (mDB != null && tableDTO != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				res = deleteDTO(tableDTO);
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return res;
	}

	/**
	 * 
	 * Thuc thi mot mot hanh dong insert, update, delete mot doi tuong vao bang
	 * 
	 * @param dto
	 * @return
	 * @return: long ( return -1 if fail)
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public synchronized long executeDTO(AbstractTableDTO dto) {
		long res = -1;
		if (mDB != null && dto != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				res = execute(dto);
				mDB.setTransactionSuccessful();

			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return res;
	}

	/**
	 * 
	 * Thuc thi mot mot hanh dong insert, update, delete mot d/s doi tuong vao
	 * bang
	 * 
	 * @param listDTO
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public synchronized void executeListDTO(ArrayList<AbstractTableDTO> listDTO) {
		if (mDB != null && listDTO != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				for (AbstractTableDTO dto : listDTO) {
					execute(dto);
				}
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
	}

	/**
	 * QUERY SQL
	 */

	/**
	 * 
	 * Thuc hien 1 cau lenh sql tong quat
	 * 
	 * @param sqlQuery
	 * @param params
	 * @return
	 * @return: Cursor
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public Cursor rawQuery(String sqlQuery, String[] params) {
		return mDB.rawQuery(sqlQuery, params);
	}

	/**
	 * 
	 * Xoa du lieu tu cau lenh sql
	 * 
	 * @param tableName
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public int delete(String tableName, String whereClause, String[] whereArgs) {
		int delete = mDB.delete(tableName, whereClause, whereArgs);
		return delete;
	}

	/**
	 * 
	 * Xoa du lieu tu cau lenh sql, roi xoa luon bang con tham chieu
	 * 
	 * @param tableName
	 * @param whereClause
	 * @param whereArgs
	 * @param childTables
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public boolean deleteCascade(String tableName, String whereClause,
			String[] whereArgs, String[] childTables) {
		boolean result = true;

		int delete = mDB.delete(tableName, whereClause, whereArgs);

		if (delete > 0) {
			for (int i = 0; i < childTables.length; i++) {
				String tbChild = childTables[i];
				int delete2 = mDB.delete(tbChild, whereClause, whereArgs);

				if (delete2 <= 0) {
					result = false;
					break;
				}
			}
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * 
	 * Update table tu cau lenh sql
	 * 
	 * @param tableName
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public int update(String tableName, ContentValues values,
			String whereClause, String[] whereArgs) {
		int update = -1;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				update = mDB.update(tableName, values, whereClause, whereArgs);
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return update;
	}

	/**
	 * 
	 * Insert thong tin tu cau lenh sql
	 * 
	 * @param nameTable
	 * @param nullColumnHack
	 * @param values
	 * @return
	 * @return: long
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public long insert(String nameTable, String nullColumnHack,
			ContentValues values) {
		long insert = -1;
		if (mDB != null && mDB.isOpen()) {
			try {
				mDB.beginTransaction();
				insert = mDB.insert(nameTable, nullColumnHack, values);
				mDB.setTransactionSuccessful();
			} finally {
				if (mDB != null) {
					mDB.endTransaction();
				}
			}
		}
		return insert;
	}

	/**
	 * 
	 * Exec cau lenh sql
	 * 
	 * @param sql
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public void execSQL(String sql) {
		mDB.execSQL(sql);
	}

	/**
	 * 
	 * Close DB
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public void closeDB() {
		try {
			if (mDB != null) {
				if (mDB.isOpen()) {
					mDB.close();
				}
				mDB = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * Lay thong tin cong ty
	 * 
	 * @param companyId
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: yenntth16
	 * @date: Dec 7, 2012
	 */
	public CompanyDTO getCompanyInfo(String companyId) {
		COMPANY_TABLE cusTable = new COMPANY_TABLE(mDB);
		CompanyDTO classInfo = cusTable.getCompanyById(companyId);
		return classInfo;
	}

	/**
	 * Luu lai thong tin company
	 * 
	 * @author: Nguyen Thanh Dung
	 * @param list
	 * @return: void
	 * @throws:
	 */

	public void saveListClass(CompanyDTO companyInfo) {
		try {
			mDB.beginTransaction();
			COMPANY_TABLE companyTable = new COMPANY_TABLE(mDB);
			companyTable.insert(companyInfo);
			mDB.setTransactionSuccessful();
		} catch (Exception ex) {

		} finally {
			mDB.endTransaction();
		}
	}

	/**
	 * 
	 * get list contact from db
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: ArrayList<ContactDTO>
	 * @throws:
	 * @since: Mar 5, 2013
	 */
	public ListContactViewDTO getListContactFromDB(Bundle data) {
		ListContactViewDTO listContact = new ListContactViewDTO();
		CONTACT_TABLET contactTable = new CONTACT_TABLET(mDB);

		try {
			listContact = contactTable.getListContact(data);
			return listContact;

		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 * request delete contact in db
	 * 
	 * @param @param data
	 * @param @return
	 * @return: int
	 * @author: HaiTC3
	 * @date: Mar 14, 2013
	 */
	public int requestDeleteContact(Bundle data) {
		CONTACT_TABLET contactTable = new CONTACT_TABLET(mDB);
		ContactDTO myContact = (ContactDTO) data
				.getSerializable(IntentConstants.INTENT_CONTACT_OBJECT);

		try {
			contactTable.delete(myContact);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 
	 * request create or update contact if it exist
	 * 
	 * @param @param data
	 * @param @return
	 * @return: int
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public int requestCreateOrUpdateContact(Bundle data) {
		CONTACT_TABLET contactTable = new CONTACT_TABLET(mDB);
		ContactDTO myContact = (ContactDTO) data
				.getSerializable(IntentConstants.INTENT_CONTACT_OBJECT);

		try {
			if (myContact.contactId >= 1) {
				if (myContact.isNullObject()) {
					contactTable.delete(myContact);
				} else {
					contactTable.update(myContact);
				}
			} else {
				contactTable.insert(myContact);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 
	 * get list invoice order
	 * 
	 * @param @param data
	 * @param @return
	 * @return: ListInvoiceNumberInfoView
	 * @author: HaiTC3
	 * @date: Mar 15, 2013
	 */
	public ArrayList<InvoiceInfoDTO> requestGetListInvoiceOrder(Bundle data) {
		INVOICE_ORDER_TABLET invoiceTable = new INVOICE_ORDER_TABLET(mDB);
		ArrayList<InvoiceInfoDTO> listInvoice = new ArrayList<InvoiceInfoDTO>();
		try {
			listInvoice = invoiceTable.getListInvoiceOrder(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listInvoice;
	}

	/**
	 * 
	 * get list invoice order detail with invoice order id
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: ArrayList<InvoiceOrderDetailDTO>
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public ArrayList<InvoiceOrderDetailDTO> getListInvoiceOrderDetail(
			Bundle data) {
		INVOICE_ORDER_DETAIL_TABLET invoiceDetailTable = new INVOICE_ORDER_DETAIL_TABLET(
				mDB);
		ArrayList<InvoiceOrderDetailDTO> listInvoice = new ArrayList<InvoiceOrderDetailDTO>();
		try {
			listInvoice = invoiceDetailTable
					.getListInvoiceDetailWithInvoiceOrderId(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listInvoice;
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
		COMPANY_TABLE companyTable = new COMPANY_TABLE(mDB);
		CompanyDTO companyInfo = new CompanyDTO();
		try {
			companyInfo = companyTable.getCompanyInfo(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyInfo;
	}

	/**
	 * 
	 * request update company info
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: int
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public int requestUpdateCompanyInfo(Bundle data) {
		COMPANY_TABLE companyInfo = new COMPANY_TABLE(mDB);
		CompanyDTO myContact = (CompanyDTO) data
				.getSerializable(IntentConstants.INTENT_COMPANY_INFO);

		try {
			if (myContact.companyId >= 1) {
				companyInfo.update(myContact);
			} else {
				companyInfo.insert(myContact);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();

			return 0;
		}
	}

	/**
	 * 
	 * request update invoice info to DB
	 * 
	 * @author: HaiTC3
	 * @param data
	 * @return
	 * @return: int
	 * @throws:
	 * @since: Mar 16, 2013
	 */
	public int requestUpdateInvoiceInfoToDB(Bundle data) {
		int result = 0;
		InvoiceOrderNumberInfoView invoiceInfo = (InvoiceOrderNumberInfoView) data
				.getSerializable(IntentConstants.INTENT_INVOICE_INFO);
		if (invoiceInfo != null) {
			try {
				long contactId = 0;
				// request insert or update contact table
				if (invoiceInfo.invoiceOrder.contactInvoice.contactId > 0) {
					// update contact
					ContactDTO contact = invoiceInfo.invoiceOrder.contactInvoice;
					this.update(contact);
				} else {
					// insert new contact
					ContactDTO contact = invoiceInfo.invoiceOrder.contactInvoice;
					contactId = this.insertDTO(contact);
				}

				// request insert or update invoice order table
				if (invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderId > 0) {
					// update invoice order
					this.update(invoiceInfo.invoiceOrder.invoiceOrderInfo);
					// remove invoice order detail after insert
					INVOICE_ORDER_DETAIL_TABLET invoiceOrderDetail = new INVOICE_ORDER_DETAIL_TABLET(
							mDB);
					invoiceOrderDetail
							.deleteRowInvoiceDetail(String
									.valueOf(invoiceInfo.invoiceOrder.invoiceOrderInfo.invoiceOrderId));
					// update invoice order detail
					for (int i = 0, size = invoiceInfo.listOrderDetail.size(); i < size; i++) {
						InvoiceOrderDetailDTO detail = invoiceInfo.listOrderDetail
								.get(i);
						invoiceOrderDetail.insert(detail);
					}
				} else {
					// insert new invoice order
					if (contactId > 0) {
						invoiceInfo.invoiceOrder.invoiceOrderInfo.contactId = contactId;
					}
					long invoicrOrderId = this
							.insertDTO(invoiceInfo.invoiceOrder.invoiceOrderInfo);
					// update invoice order id for list invoice order detail
					for (int i = 0, size = invoiceInfo.listOrderDetail.size(); i < size; i++) {
						invoiceInfo.listOrderDetail.get(i).invoiceOrderId = invoicrOrderId;
						this.insertDTO(invoiceInfo.listOrderDetail.get(i));
					}
				}
			} catch (Exception e) {
				result = 0;
				// TODO: handle exception
			} finally {
				result = 1;
			}
		}

		return result;
	}
}
