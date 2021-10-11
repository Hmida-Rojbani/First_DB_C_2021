package de.tekup.db.errors;

import org.springframework.dao.DataIntegrityViolationException;

public class EmployeeSaveDBException extends DataIntegrityViolationException{

	public EmployeeSaveDBException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
