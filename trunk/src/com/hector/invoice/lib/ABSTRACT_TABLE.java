/**
 * Copyright 2012 Mr.Hai Mobile Developer. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * abstract class TABLE
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public abstract class ABSTRACT_TABLE {
	// trang thai khi row duoc tao
	public static final int CREATED_STATUS = 0;
	// trang thai khi row duoc chuyen thanh cong len server
	public static final int TRANSFERED_STATUS = 1;
	// trang thai khi dong bo thanh cong
	public static final int SYNCHRONIZED_STATUS = 2;
	// colum phuc vu syn
	public static final String SYN_STATE = "SYN_STATE";
	// list column
	protected String[] columns;
	// sql get count querry
	public String sqlGetCountQuerry = "SELECT COUNT(*) FROM ";
	// table name
	public String tableName = "";
	// sqlite database
	public SQLiteDatabase mDB;
	// list column
	public ArrayList<ColumnTable> listColumn;

	/**
	 * abstract method
	 */
	abstract protected long insert(AbstractTableDTO dto);

	abstract protected long update(AbstractTableDTO dto);

	abstract protected long delete(AbstractTableDTO dto);

	/**
	 * 
	 * genera sql create table
	 * 
	 * @return
	 * @return: String
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public String generaSQLCreateTable() {
		StringBuffer strSQLCreateTable = new StringBuffer();
		strSQLCreateTable.append("CREATE TABLE IF NOT EXISTS " + tableName
				+ " (");
		for (int i = 0, size = listColumn.size(); i < size; i++) {
			strSQLCreateTable.append(listColumn.get(i).generaSQLCreateColumn());
			if (i < (size - 1)) {
				strSQLCreateTable.append(" , ");
			}
		}
		strSQLCreateTable.append(" )");
		return strSQLCreateTable.toString();
	}

	/**
	 * Lay so luong record cua table
	 * 
	 * @author: TruongHN
	 * @return: long
	 * @throws:
	 */
	public long getCount() {
		SQLiteStatement statement = compileStatement(sqlGetCountQuerry);
		long count = statement.simpleQueryForLong();
		return count;
	}

	/**
	 * Get MaxId trong table khi truyen vao tableName & ten column
	 * 
	 * @author: TruongHN
	 * @param tableName
	 * @param columnIdName
	 * @throws Exception
	 * @return: int
	 * @throws:
	 */
	public int getMaxIdInTable(String columnIdName) throws Exception {
		int maxId = -1;
		Cursor cursor = null;
		try {
			StringBuilder sqlState = new StringBuilder();
			sqlState.append("select ifnull(max(" + columnIdName + "), 0) as "
					+ columnIdName + " from " + tableName);
			cursor = rawQuery(sqlState.toString(), null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					maxId = cursor.getInt(cursor.getColumnIndex(columnIdName));
				}
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return maxId;
	}

	/**
	 * 
	 * update row
	 * 
	 * @param values
	 * @param whereClause
	 * @param whereArgs
	 * @return
	 * @return: int
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 7, 2012
	 */
	public int update(ContentValues values, String whereClause,
			String[] whereArgs) {
		int upda = mDB.update(tableName, values, whereClause, whereArgs);
		return upda;
	}

	/**
	 * 
	 * raw query
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

	public Cursor rawQueries(String sqlQuery, ArrayList<String> params) {
		String[] strParams = null;
		if (params != null) {
			strParams = new String[params.size()];
			for (int i = 0, s = params.size(); i < s; i++) {
				strParams[i] = params.get(i);
			}
		}
		return mDB.rawQuery(sqlQuery, strParams);
	}

	public void execSQL(String sql) {
		mDB.execSQL(sql);
	}

	public SQLiteStatement compileStatement(String sqlQuery) {
		return mDB.compileStatement(sqlQuery);
	}

	public long insert(String nullColumnHack, ContentValues values) {
		long insert = mDB.insert(tableName, nullColumnHack, values);
		return insert;
	}

	public long insert(String nameTable, String nullColumnHack,
			ContentValues values) {
		long insert = mDB.insert(nameTable, nullColumnHack, values);
		return insert;
	}

	public int delete(String whereClause, String[] whereArgs) {
		int delete = mDB.delete(tableName, whereClause, whereArgs);
		// Log.i("Truong, delete - ", String.valueOf(delete));
		return delete;
	}

	public Cursor query(String selection, String[] selectionArgs,
			String groupBy, String having, String orderBy) {
		Cursor re = null;
		try {
			re = mDB.query(tableName, columns, selection, selectionArgs,
					groupBy, having, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return re;
	}
}
