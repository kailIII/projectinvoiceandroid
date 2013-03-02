package com.hector.invoice.dto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.database.Cursor;

import com.hector.invoice.common.GlobalUtil;
import com.hector.invoice.common.StringUtil;
import com.hector.invoice.constant.Constants;
import com.hector.invoice.constant.IntentConstants;
import com.hector.invoice.lib.AbstractTableDTO;
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
	// ma lop
	public int classID;
	// ten lop
	public String className;
	// ten lop de search
	private String classNameText;
	// nam hoc
	public String year;
	// khoi hoc
	public String block;
	// phan ban
	public String department;
	// giao vien chu nhiem
	public String teacher;
	// buoi hoc (sang / chieu)
	public String session;
	// hoc tieng dan toc
	public String language;
	// lop ghep (co / khong)
	public String multigrade;
	// ma lop ghep
	public String multigradeId;
	// ngoai ngu 1
	public String language1;
	// ngoai ngu 2
	public String language2;
	// tin hoc
	public String informatics;
	// hoc nghe pho thong
	public String vocationalschool;
	// thuoc diem truong
	public String site;
	// tinh chat
	public String nature;
	// mo ta
	public String description;// mo ta
	//level id
	private int levelId;// mo ta

	public CompanyDTO() {
		super(TableType.LIST_CLASS);
	}

	public String getclassID() {
		return Integer.toString(classID);
	}

	public void setclassID(int classID) {
		this.classID = classID;
	}

	public String getclassName() {
		return className;
	}

	public void setclassName(String className) {
		this.className = className;
	}

	/**
	 * @return the classNameText
	 */
	public String getClassNameText() {
		return classNameText;
	}

	/**
	 * @param classNameText the classNameText to set
	 */
	public void setClassNameText(String classNameText) {
		this.classNameText = classNameText;
	}

	public String getyear() {
		return year;
	}

	public void setyear(String year) {
		this.year = year;
	}

	public String getblock() {
		return block;
	}

	public void setblock(String block) {
		this.block = block;
	}

	public String getdepartment() {
		return department;
	}

	public void setdepartment(String department) {
		this.department = department;
	}

	public String getteacher() {
		return teacher;
	}

	public void setteacher(String teacher) {
		this.teacher = teacher;
	}

	public String getsession() {
		return session;
	}

	public void setsession(String session) {
		this.session = session;
	}

	public String getlanguage() {
		return language;
	}

	public void setlanguage(String language) {
		this.language = language;
	}

	public String getmultigrade() {
		return multigrade;
	}

	public void setmultigrade(String multigrade) {
		this.multigrade = multigrade;
	}

	public String getlanguage1() {
		return language1;
	}

	public void setlanguage1(String language1) {
		this.language1 = language1;
	}

	public String getlanguage2() {
		return language2;
	}

	public void setlanguage2(String language2) {
		this.language2 = language2;
	}

	public String getinformatics() {
		return informatics;
	}

	public void setinformatics(String informatics) {
		this.informatics = informatics;
	}

	public String getvocationalschool() {
		return vocationalschool;
	}

	public void setvocationalschool(String vocationalschool) {
		this.vocationalschool = vocationalschool;
	}

	public String getsite() {
		return vocationalschool;
	}

	public void setsite(String site) {
		this.site = site;
	}

	public String getnature() {
		return nature;
	}

	public void setnature(String nature) {
		this.nature = nature;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
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
		setclassID(c.getInt(c.getColumnIndex(COMPANY_TABLE.CLASS_ID)));
		setclassName(c.getString(c.getColumnIndex(COMPANY_TABLE.CLASS_NAME)));
		setClassNameText("lop " + className.toLowerCase());
		setyear(c.getString(c.getColumnIndex(COMPANY_TABLE.YEAR)));
		setblock(c.getString(c.getColumnIndex(COMPANY_TABLE.BLOCK)));
		setdepartment(c.getString(c.getColumnIndex(COMPANY_TABLE.DEPARTMENT)));
		setteacher(c.getString(c.getColumnIndex(COMPANY_TABLE.TEACHER)));
		setsession(c.getString(c.getColumnIndex(COMPANY_TABLE.SESSION)));
		setlanguage(c.getString(c.getColumnIndex(COMPANY_TABLE.LANGUAGE)));
		setmultigrade(c.getString(c.getColumnIndex(COMPANY_TABLE.MULTIGRADE)));
		setlanguage1(c.getString(c.getColumnIndex(COMPANY_TABLE.LANGUAGE1)));
		setlanguage2(c.getString(c.getColumnIndex(COMPANY_TABLE.LANGUAGE2)));
		setinformatics(c.getString(c.getColumnIndex(COMPANY_TABLE.INFORMATICS)));
		setvocationalschool(c.getString(c.getColumnIndex(COMPANY_TABLE.VOCATIONALSCHOOL)));
		setsite(c.getString(c.getColumnIndex(COMPANY_TABLE.SITE)));
		setnature(c.getString(c.getColumnIndex(COMPANY_TABLE.NATURE)));
		setdescription(c.getString(c.getColumnIndex(COMPANY_TABLE.DESCRIPTION)));
		setLevelId(c.getInt(c.getColumnIndex(COMPANY_TABLE.LEVELID)));
	}

	/**
	 * 
	 * Generate cau lenh SQL update
	 * 
	 * @return
	 * @return: JSONObject
	 * @throws:
	 * @author: HaiTC3
	 * @date: Dec 10, 2012
	 */
	public JSONObject generateUpdateFromOrderSql() {
		JSONObject json = new JSONObject();
		try {
			json.put(IntentConstants.INTENT_TYPE, TableAction.UPDATE);
			json.put(IntentConstants.INTENT_TABLE_NAME,
					COMPANY_TABLE.COMPANY_TABLE);
			JSONArray params = new JSONArray();
			params.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.CLASS_ID, classID,
					null));
			json.put(IntentConstants.INTENT_LIST_PARAM, params);

			// ds where params --> insert khong co menh de where
			JSONArray wheres = new JSONArray();
			wheres.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.CLASS_ID, classID,
					null));
			json.put(IntentConstants.INTENT_LIST_WHERE_PARAM, wheres);

		} catch (JSONException e) {
		}
		return json;
	}

	/**
	 * Generate cau lenh update last order after delete
	 */
	public JSONObject generateUpdateLastOrder(String classID2) {
		JSONObject json = new JSONObject();
		try {
			json.put(IntentConstants.INTENT_TYPE, TableAction.UPDATE);
			json.put(IntentConstants.INTENT_TABLE_NAME,
					COMPANY_TABLE.COMPANY_TABLE);

			// ds params
			JSONArray params = new JSONArray();
			if (StringUtil.isNullOrEmpty(classID2)) {
				params.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.CLASS_ID, "",
						DATA_TYPE.NULL.toString()));
			} else {
				params.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.CLASS_ID,
						classID2, null));
			}
			json.put(IntentConstants.INTENT_LIST_PARAM, params);

			// ds where params --> insert khong co menh de where
			JSONArray wheres = new JSONArray();
			wheres.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.COMPANY_TABLE,
					classID, null));
			json.put(IntentConstants.INTENT_LIST_WHERE_PARAM, wheres);

		} catch (JSONException e) {
		}
		return json;
	}

	/**
	 * generate cau lenh update location trong CLASSinfo
	 */
	public JSONObject generateUpdateLocationSql() {
		JSONObject updateLocationJson = new JSONObject();
		try {
			updateLocationJson.put(IntentConstants.INTENT_TYPE,
					TableAction.UPDATE);
			updateLocationJson.put(IntentConstants.INTENT_TABLE_NAME,
					COMPANY_TABLE.COMPANY_TABLE);

			// ds params
			JSONArray detailPara = new JSONArray();
			detailPara.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.CLASS_NAME,
					className, null));
			updateLocationJson.put(IntentConstants.INTENT_LIST_PARAM,
					detailPara);

			JSONArray wheres = new JSONArray();
			wheres.put(GlobalUtil.getJsonColumn(COMPANY_TABLE.COMPANY_TABLE,
					classID, null));
			updateLocationJson.put(IntentConstants.INTENT_LIST_WHERE_PARAM,
					wheres);
		} catch (JSONException e) {
		}

		return updateLocationJson;
	}

	/**
	*  Parse du lieu tu doi tuong json
	*  @author: Nguyen Thanh Dung
	*  @param classObject
	*  @return: void
	*  @throws: 
	*/
	
	public void initFromJson(JSONObject classObject) {
		try {
			classID = classObject.getInt("ClassID");
			className = classObject.getString("DisplayName") == null ? Constants.STR_BLANK : classObject.getString("DisplayName");
			setClassNameText("lop " + className.toLowerCase());
			teacher = classObject.isNull("HeadTeacher") == true ? Constants.STR_BLANK : classObject.getString("HeadTeacher");
			description = classObject.isNull("Description") == true ? Constants.STR_BLANK : classObject.getString("Description");
			language = classObject.isNull("EthnicLanguages") == true ? Constants.STR_BLANK : classObject.getString("EthnicLanguages");
			language1 = classObject.isNull("Language1") == true ? Constants.STR_BLANK : classObject.getString("Language1");
			language2 = classObject.isNull("Language2") == true ? Constants.STR_BLANK : classObject.getString("Language2");
			year = classObject.isNull("Year") == true ? Constants.STR_BLANK : classObject.getString("Year");
			multigrade = classObject.isNull("Combine_class") == true ? Constants.STR_BLANK : classObject.getString("Combine_class"); 
			multigradeId = classObject.isNull("Combine_code") == true ? Constants.STR_BLANK : classObject.getString("Combine_code");
			informatics = classObject.isNull("IsComputing") == true ? Constants.STR_BLANK : classObject.getString("IsComputing");
			block = classObject.isNull("Level") == true ? Constants.STR_BLANK : classObject.getString("Level");
			department = classObject.isNull("SubCommittee") == true ? Constants.STR_BLANK : classObject.getString("SubCommittee");
			vocationalschool = classObject.isNull("Occupation") == true ? Constants.STR_BLANK : classObject.getString("Occupation");
			session = classObject.isNull("Session") == true ? Constants.STR_BLANK : classObject.getString("Session");
			site = classObject.isNull("SchoolSubsidiaryID") == true ? Constants.STR_BLANK : classObject.getString("SchoolSubsidiaryID");
			nature = classObject.isNull("Property") == true ? Constants.STR_BLANK : classObject.getString("Property");
			levelId = classObject.getInt("LevelID");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}

	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

}
