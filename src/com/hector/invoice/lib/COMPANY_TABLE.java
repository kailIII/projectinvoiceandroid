package com.hector.invoice.lib;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * thong tin danh sach cac lop hoc
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.0
 */
public class COMPANY_TABLE extends ABSTRACT_TABLE {
	// ma lop
	public static final String CLASS_ID = "CLASS_ID";
	// ten lop
	public static final String CLASS_NAME = "CLASS_NAME";
	// nam hoc
	public static final String YEAR = "YEAR";
	// khoi hoc
	public static final String BLOCK = "BLOCK";
	// phan ban
	public static final String DEPARTMENT = "DEPARTMENT";
	// giao vien chu nhiem
	public static final String TEACHER = "TEACHER";
	// buoi hoc (sang/chieu)
	public static final String SESSION = "SESSION";
	// hoc tieng dan toc 
	public static final String LANGUAGE = "LANGUAGE";
	// lop ghep 
	public static final String MULTIGRADE = "MULTIGRADE";
	// ma lop ghep (chi ton tai khi MULTIGRADE = 1
	public static final String MULTIGRADE_ID = "MULTIGRADE_ID";
	// ngoai ngu 1
	public static final String LANGUAGE1 = "LANGUAGE1";
	// ngoai ngu 2
	public static final String LANGUAGE2 = "LANGUAGE2";
	// tin hoc 
	public static final String INFORMATICS = "INFORMATICS";
	// hoc nghe pho thong
	public static final String VOCATIONALSCHOOL = "VOCATIONALSCHOOL";
	// thuoc diem truong 
	public static final String SITE = "SITE";
	// tinh chat
	public static final String NATURE = "NATURE";
	// mo ta
	public static final String DESCRIPTION = "DESCRIPTION";
	// ten lop
	public static final String CLASS_TABLE = "CLASS";
	// level id
	public static final String LEVELID = "LEVELID";

	public COMPANY_TABLE(SQLiteDatabase mDB) {
		this.tableName = CLASS_TABLE;
		this.columns = new String[] { CLASS_ID, CLASS_NAME, YEAR, BLOCK,
				DEPARTMENT, TEACHER, SESSION, LANGUAGE, MULTIGRADE, LANGUAGE1,
				LANGUAGE2, INFORMATICS, VOCATIONALSCHOOL, SITE, NATURE,
				DESCRIPTION, LEVELID };
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
		// column CLASS_ID
		ColumnTable classId = new ColumnTable(CLASS_ID,
				ColumnTable.DATA_TYPE_INTEGER, true, true, true, true,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(classId);

		// column CLASS_NAME
		ColumnTable className = new ColumnTable(CLASS_NAME,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(className);
		
		// column YEAR
		ColumnTable year = new ColumnTable(YEAR,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(year);
		
		// column BLOCK
		ColumnTable block = new ColumnTable(BLOCK,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(block);

		// column DEPARTMENT
		ColumnTable department = new ColumnTable(DEPARTMENT,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(department);

		// column TEACHER
		ColumnTable teacher = new ColumnTable(TEACHER,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(teacher);

		// column SESSION
		ColumnTable session = new ColumnTable(SESSION,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(session);
		// column LANGUAGE
		ColumnTable language = new ColumnTable(LANGUAGE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language);
		// column MULTIGRADE
		ColumnTable multigrade = new ColumnTable(MULTIGRADE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(multigrade);
		// column LANGUAGE1
		ColumnTable language1 = new ColumnTable(LANGUAGE1,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language1);
		// column LANGUAGE2
		ColumnTable language2 = new ColumnTable(LANGUAGE2,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(language2);
		// column INFORMATICS
		ColumnTable informatics = new ColumnTable(INFORMATICS,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(informatics);
		// column VOCATIONALSCHOOL
		ColumnTable vocationalschool = new ColumnTable(VOCATIONALSCHOOL,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(vocationalschool);
		// column SITE
		ColumnTable site = new ColumnTable(SITE, ColumnTable.DATA_TYPE_TEXT,
				false, false, true, false, ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(site);
		// column NATURE
		ColumnTable nature = new ColumnTable(NATURE,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(nature);
		// column DESCRIPTION
		ColumnTable description = new ColumnTable(DESCRIPTION,
				ColumnTable.DATA_TYPE_TEXT, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(description);
		//column LEVELID
		ColumnTable levelId = new ColumnTable(LEVELID,
				ColumnTable.DATA_TYPE_INTEGER, false, false, true, false,
				ColumnTable.DEFAULT_VALUE_CURRENT_NONE,
				ColumnTable.ORDER_TYPE_ASC);
		listColumn.add(levelId);

		return listColumn;
	}

	/**
	 * Them 1 dong xuong db
	 */
	@Override
	protected long insert(AbstractTableDTO dto) {
		ContentValues value = initDataRow((CompanyDTO) dto);
		return insert(null, value);
	}

	/**
	 * 
	 * Cap nhat danh sach lop
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
		String[] params = { "" + dto.getclassID() };
		return update(value, CLASS_ID + " = ?", params);
	}

	@Override
	protected long update(AbstractTableDTO dto) {
		CompanyDTO cusDTO = (CompanyDTO) dto;
		ContentValues value = initDataRow(cusDTO);
		String[] params = { "" + cusDTO.getclassID() };
		return update(value, CLASS_ID + " = ?", params);
	}

	/**
	 * 
	 * Xoa 1 dong trong db
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
		return delete(CLASS_ID + " = ?", params);
	}

	@Override
	protected long delete(AbstractTableDTO dto) {
		CompanyDTO cusDTO = (CompanyDTO) dto;
		String[] params = { cusDTO.getclassID() };
		return delete(CLASS_ID + " = ?", params);
	}

	/**
	 * 
	 * Lay 1 dong trong db theo id
	 * 
	 * @param id
	 * @return
	 * @return: ClassDTO
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public CompanyDTO getClassById(String id) {
		CompanyDTO list_CLASS_DTO = null;
		Cursor c = null;
		try {
			String[] params = { id };
			c = query(CLASS_ID + " = ?", params, null, null, null);
			if (c != null) {
				if (c.moveToFirst()) {
					list_CLASS_DTO = initLogDTOFromCursor(c);
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

		return list_CLASS_DTO;
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
		CompanyDTO classDTO = new CompanyDTO();
		classDTO.setclassID(c.getInt(c.getColumnIndex(CLASS_ID)));
		classDTO.setclassName(c.getString(c.getColumnIndex(CLASS_NAME)));
		classDTO.setyear(c.getString(c.getColumnIndex("YEAR")));
		classDTO.setblock(c.getString(c.getColumnIndex("BLOCK")));
		classDTO
				.setdepartment(c.getString(c.getColumnIndex("DEPARTMENT")));
		classDTO.setteacher(c.getString(c.getColumnIndex("TEACHER")));
		classDTO.setsession(c.getString(c.getColumnIndex("SESSION")));
		classDTO
				.setmultigrade(c.getString(c.getColumnIndex("MULTIGRADE")));
		classDTO.setlanguage1(c.getString(c.getColumnIndex("LANGUAGE1")));
		classDTO.setlanguage2(c.getString(c.getColumnIndex("LANGUAGE2")));
		classDTO.setinformatics(c.getString(c
				.getColumnIndex("INFORMATICS")));
		classDTO.setvocationalschool(c.getString(c
				.getColumnIndex("VOCATIONALSCHOOL")));
		classDTO.setsite(c.getString(c.getColumnIndex("SITE")));
		classDTO.setnature(c.getString(c.getColumnIndex("NATURE")));
		classDTO.setdescription(c.getString(c
				.getColumnIndex("DESCRIPTION")));
		classDTO.setLevelId(c.getInt(c
				.getColumnIndex("LEVELID")));

		return classDTO;
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
		editedValues.put(CLASS_ID, dto.getclassID());
		editedValues.put(CLASS_NAME, dto.getclassName());
		editedValues.put(YEAR, dto.getyear());
		editedValues.put(BLOCK, dto.getblock());
		editedValues.put(DEPARTMENT, dto.getdepartment());
		editedValues.put(TEACHER, dto.getteacher());
		editedValues.put(SESSION, dto.getsession());
		editedValues.put(LANGUAGE, dto.getlanguage());
		editedValues.put(MULTIGRADE, dto.getmultigrade());
		editedValues.put(LANGUAGE1, dto.getlanguage1());
		editedValues.put(LANGUAGE2, dto.getlanguage2());
		editedValues.put(INFORMATICS, dto.getinformatics());
		editedValues.put(VOCATIONALSCHOOL, dto.getvocationalschool());
		editedValues.put(SITE, dto.getsite());
		editedValues.put(NATURE, dto.getnature());
		editedValues.put(DESCRIPTION, dto.getdescription());
		editedValues.put(LEVELID, dto.getLevelId());
		return editedValues;
	}

	/**
	*  Lay tat ca cac lop trong bang
	*  @author: Nguyen Thanh Dung
	*  @return
	*  @return: ClassListDTO
	*  @throws: 
	*/
	
	public ClassListDTO getAllRow() {
		Cursor c = null;
		ClassListDTO list = null;
		
		try {
			c = query(null, null, null, null, null);
			if(c != null) {
				if (c.moveToFirst()) {
					list = new ClassListDTO();
					list.parseCustomerInfo(c);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		return list;
	}

	
}
