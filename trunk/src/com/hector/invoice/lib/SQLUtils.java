/**
 * Copyright 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.hector.invoice.common.GlobalUtil;
import com.hector.invoice.common.InvoiceInfo;
import com.hector.invoice.common.StringUtil;

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
						boolean dbExists = GlobalUtil.checkExistsDataBase();
						mDB = SQLiteDatabase.openOrCreateDatabase(
								ExternalStorage.getFileDBPath(
										InvoiceInfo.getInstance()
												.getAppContext())
										.getAbsolutePath()
										+ "/InvoiceDatabase", null,
								new DatabaseErrorHandler() {

									public void onCorruption(
											SQLiteDatabase dbObj) {
										// TODO Auto-generated method stub

									}
								});
						// mDB = SQLiteDatabase.openDatabase(
						// ExternalStorage.getFileDBPath(
						// GlobalInfo.getInstance()
						// .getAppContext())
						// .getAbsolutePath()
						// + "/SmasDatabase", null,
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
		STUDENT_TABLE studentTable = new STUDENT_TABLE(mDB);
		PROPERTIES_STUDENT_TABLE properties_student_table = new PROPERTIES_STUDENT_TABLE(
				mDB);
		LEARN_TABLE learn_table = new LEARN_TABLE(mDB);
		PROCESS_OF_CLASS_TABLE processOfClassTable = new PROCESS_OF_CLASS_TABLE(
				mDB);
		LEARN_TO_CLASS_TABLE laerntoclass_table = new LEARN_TO_CLASS_TABLE(mDB);
		PRAISE_TABLE praise_table = new PRAISE_TABLE(mDB);
		DISCIPLINE_TABLE discipline_table = new DISCIPLINE_TABLE(mDB);
		INFOPERSONAL_TABLE infopersoval_table = new INFOPERSONAL_TABLE(mDB);
		ETHNICS_TABLE ethnics_table = new ETHNICS_TABLE(mDB);
		RELIGION_TABLE religion_table = new RELIGION_TABLE(mDB);

		listTable.add(tbListClass);
		listTable.add(studentTable);
		listTable.add(properties_student_table);
		listTable.add(learn_table);
		listTable.add(processOfClassTable);
		listTable.add(laerntoclass_table);
		listTable.add(praise_table);
		listTable.add(discipline_table);
		listTable.add(infopersoval_table);
		listTable.add(ethnics_table);
		listTable.add(religion_table);

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
	 * drop list table in DB
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
		if (AbstractTableDTO.TableType.LIST_CLASS.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
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
		if (AbstractTableDTO.TableType.LIST_CLASS.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
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
		if (AbstractTableDTO.TableType.LIST_CLASS.equals(tableDTO.getType())) {
			COMPANY_TABLE table = new COMPANY_TABLE(mDB);
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
	 * ------------------ LAY DU LIEU DANH SACH LOP
	 * HOC---------------------------
	 */

	/**
	 * 
	 * Lay thong tin lop hoc
	 * 
	 * @param classID
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: yenntth16
	 * @date: Dec 7, 2012
	 */
	public CompanyDTO getClassInfo(String classID) {
		COMPANY_TABLE cusTable = new COMPANY_TABLE(mDB);
		CompanyDTO classInfo = cusTable.getClassById(classID);
		return classInfo;
	}

	/**
	 * Luu lai thong tin cac lop hoc
	 * 
	 * @author: Nguyen Thanh Dung
	 * @param list
	 * @return: void
	 * @throws:
	 */

	public void saveListClass(ClassListDTO list) {
		try {
			mDB.beginTransaction();
			COMPANY_TABLE classTable = new COMPANY_TABLE(mDB);
			for (CompanyDTO classDTO : list.getListclass()) {
				classTable.insert(classDTO);
			}

			mDB.setTransactionSuccessful();
		} catch (Exception ex) {

		} finally {
			mDB.endTransaction();
		}
	}

	/**
	 * Lay tat ca cac lop trong bang
	 * 
	 * @author: Nguyen Thanh Dung
	 * @return
	 * @return: ClassListDTO
	 * @throws:
	 */

	public ClassListDTO getClassList() {
		ClassListDTO list = null;

		COMPANY_TABLE classTable = new COMPANY_TABLE(mDB);
		list = classTable.getAllRow();

		return list;
	}

	/**
	 * Thêm danh sach hoc sinh xuong db
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListStudientDTO(StudientListDTO list) {

		STUDENT_TABLE studentTable = new STUDENT_TABLE(mDB);
		studentTable.saveList(list);
	}

	/**
	 * Lay danh sach hoc sinh tu db
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public StudientListDTO getListStudientDTO(int idClass) {
		StudientListDTO list = new StudientListDTO();

		STUDENT_TABLE studentTable = new STUDENT_TABLE(mDB);
		ArrayList<StudentDTO> pupilList = studentTable.getListStudent(String
				.valueOf(idClass));
		if (pupilList != null) {
			list.pupilList = pupilList;
			list.totalPupil = list.pupilList.size();
		}
		return list;
	}

	// ///////////==============================/////////////////////////////////
	/**
	 * Thêm chi tiet student xuong db
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void savePropertiesStudientDTO(
			PropertiesStudientDTO propertiesStudientDTO) {

		PROPERTIES_STUDENT_TABLE properties_student_table = new PROPERTIES_STUDENT_TABLE(
				mDB);
		properties_student_table.saveList(propertiesStudientDTO);
	}

	/**
	 * Lay thong tin sinh vien
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public PropertiesStudientDTO getPropertiesStudientDTO(int pupilFileID) {
		PropertiesStudientDTO propertiesStudientDTO = new PropertiesStudientDTO();

		PROPERTIES_STUDENT_TABLE properties_table = new PROPERTIES_STUDENT_TABLE(
				mDB);
		propertiesStudientDTO = properties_table
				.getPropertiesStudentbyId(String.valueOf(pupilFileID));
		return propertiesStudientDTO;
	}

	// ///////////==============================/////////////////////////////////
	/**
	 * Thêm danh sach hoc luc va hanh kiem xuong db
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListLearnDTO(ListLearnDTO ListLearnDTO) {

		LEARN_TABLE LEARN_TABLE = new LEARN_TABLE(mDB);
		LEARN_TABLE.saveList(ListLearnDTO);
	}

	/**
	 * Lay thong tin hoc luc va hanh kiem
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListLearnDTO getListLearnDTO(int pupilFileID) {
		ListLearnDTO listLearnDTO = new ListLearnDTO();

		LEARN_TABLE learn_table = new LEARN_TABLE(mDB);
		ArrayList<LearnDTO> listLear = new ArrayList<LearnDTO>();
		listLear.add(learn_table.getlearnbyId(String.valueOf(pupilFileID)));
		listLearnDTO.setLearnList(listLear);
		listLearnDTO.setTotalLearn(listLearnDTO.getLearnList().size());
		return listLearnDTO;
	}

	// /////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach qua trinh len lop xuong db
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListProcessOfClassDTO(ListProcessOfClassDTO list) {

		PROCESS_OF_CLASS_TABLE processOfClassTable = new PROCESS_OF_CLASS_TABLE(
				mDB);

		processOfClassTable.saveListProcessOfClass(list);
	}

	/**
	 * Lay danh sach qua trinh len lop tu db
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListProcessOfClassDTO getListProcessOfClassDTO(int pupilFileID) {
		ListProcessOfClassDTO list = new ListProcessOfClassDTO();

		PROCESS_OF_CLASS_TABLE processOfClassTable = new PROCESS_OF_CLASS_TABLE(
				mDB);
		list.setlistProcessOfClass(processOfClassTable
				.getListProcessOfClass(String.valueOf(pupilFileID)));
		list.setTotalListProcessOfClass(list.getlistProcessOfClass().size());
		return list;
	}

	// ///////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach cac lop da hoc
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListLearnToClassDTO(ListLearnToClassDTO list) {

		LEARN_TO_CLASS_TABLE learntoclass = new LEARN_TO_CLASS_TABLE(mDB);

		learntoclass.saveListLearnToClass(list);
	}

	/**
	 * Lay danh sach cac lop da hoc
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListLearnToClassDTO getListLearnToClassDTO(int pupilFileID) {
		ListLearnToClassDTO list = new ListLearnToClassDTO();

		LEARN_TO_CLASS_TABLE learntoclasstable = new LEARN_TO_CLASS_TABLE(mDB);
		list.setListLearnToClassDTO(learntoclasstable
				.getListLearnToClass(String.valueOf(pupilFileID)));
		list.setTotalLearnToClass(list.getListLearnToClassDTO().size());
		return list;
	}

	// ///////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach khen thuong
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListPraise(ListPraiseDTO list) {

		PRAISE_TABLE praisetable = new PRAISE_TABLE(mDB);

		praisetable.saveListPraise(list);
	}

	/**
	 * Lay danh sach khen thuong
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListPraiseDTO getListPraisePropertiesStudent(int pupilFileID) {
		ListPraiseDTO list = new ListPraiseDTO();

		PRAISE_TABLE praisetable = new PRAISE_TABLE(mDB);
		list.setListPraise(praisetable.getListPraisePropertiesStudent(String
				.valueOf(pupilFileID)));
		list.setTotalPraise(list.getListPraise().size());
		return list;
	}

	// ///////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach ky luat
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListDisciplinaryDTO(ListDisciplinaryDTO list) {

		DISCIPLINE_TABLE discipline = new DISCIPLINE_TABLE(mDB);

		discipline.saveListDisciplinary(list);
	}

	/**
	 * Lay danh sach ky luat
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListDisciplinaryDTO getListDisciplinePropertiesStudent(
			int pupilFileID) {
		ListDisciplinaryDTO list = new ListDisciplinaryDTO();

		DISCIPLINE_TABLE discipline = new DISCIPLINE_TABLE(mDB);
		list.setListDisciplinary(discipline
				.getListDisciplinePropertiesStudent(String.valueOf(pupilFileID)));
		list.setTotalDisciplinary(list.getListDisciplinary().size());
		return list;
	}

	// ///////////==============================/////////////////////////////////
	/**
	 * Thêm chi tiet nguoi xuong db
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveInfoPersonalDTO(InfoPersonalDTO infoPersonalDTO) {

		INFOPERSONAL_TABLE infopersonal_student_table = new INFOPERSONAL_TABLE(
				mDB);
		infopersonal_student_table.insert(infoPersonalDTO);
	}

	/**
	 * Lay thong tin nguoi
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public InfoPersonalDTO getInfoPersonalDTO(int teacherID) {
		InfoPersonalDTO infoPersonalDTO = new InfoPersonalDTO();

		INFOPERSONAL_TABLE infopersonal_student_table = new INFOPERSONAL_TABLE(
				mDB);
		infoPersonalDTO = infopersonal_student_table
				.getInfoPersonalbyId(StringUtil.getString(teacherID));
		return infoPersonalDTO;
	}

	/**
	 * cap nhat thong tin nguoi dung
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public long updateInfoPersonalDTO(InfoPersonalDTO infoPersonalDTO) {
		long flag;
		INFOPERSONAL_TABLE infopersonal_student_table = new INFOPERSONAL_TABLE(
				mDB);
		flag = infopersonal_student_table.update(infoPersonalDTO);
		return flag;
	}

	// ///////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach dan toc
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListEthnicDTO(ListEthnicsDTO list) {

		ETHNICS_TABLE ethnics = new ETHNICS_TABLE(mDB);

		ethnics.saveListEthnicsDTO(list);
	}

	/**
	 * Lay danh sach dan toc
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListEthnicsDTO getListEthnicsDTO() {
		ListEthnicsDTO list = new ListEthnicsDTO();

		ETHNICS_TABLE ethnics = new ETHNICS_TABLE(mDB);
		list = ethnics.getAllList();
		return list;
	}

	// ///////////==============================/////////////////////////////////

	/**
	 * Thêm danh sach ton giao
	 * 
	 * @author: DucHHA
	 * @param list
	 * @return: void
	 * @throws:
	 */
	public void saveListReligionDTO(ListReligionDTO list) {

		RELIGION_TABLE religion = new RELIGION_TABLE(mDB);

		religion.saveList(list);
	}

	/**
	 * Lay danh sach ton giao
	 * 
	 * @author: DucHHA
	 * @return
	 * @return: StudientListDTO
	 * @throws:
	 */
	public ListReligionDTO getListReligionDTO() {
		ListReligionDTO listReligionDTO = new ListReligionDTO();

		RELIGION_TABLE religion = new RELIGION_TABLE(mDB);
		listReligionDTO = religion.getAllList();
		return listReligionDTO;
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: Nguyen Thanh Dung
	 * @return: void
	 * @throws:
	 */

	public void clearClassTable() {
		COMPANY_TABLE classTable = new COMPANY_TABLE(mDB);
		classTable.delete(null, null);
	}

}
