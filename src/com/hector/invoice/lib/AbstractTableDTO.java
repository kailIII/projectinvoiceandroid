package com.hector.invoice.lib;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractTableDTO implements Serializable{
	public enum TableAction{
		INSERT,
		UPDATE,
		DELETE,
		
	}
	public enum TableType {
		LIST_CLASS,
		LIST_PUPIL,
		
		
	}
	
	public enum DATA_TYPE{
		SEQUENCE,
		DATE,
		SYSDATE,
		COMMON,
		OPERATION,
		NULL
	}
	public enum DATA_VALUE{
		sysdate,
	}
	
	private TableType type;
	private TableAction action;
	
	public TableAction getAction(){
		return action;
	}
	
	public void setAction(TableAction action){
		this.action = action;
	}
	
	public TableType getType() {
		return type;
	}

	public void setType(TableType type){
		this.type = type;
	}
	
	protected AbstractTableDTO(TableType type) {
		this.type = type;
	}
	
	protected AbstractTableDTO() {
	}
}
