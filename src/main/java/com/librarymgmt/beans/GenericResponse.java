package com.librarymgmt.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse {

	private boolean successFlag;
	
	private String successMessage;
	
	private String errorCode;
	
	private String errorMessage;
	

	public GenericResponse(boolean successFlag, String successMessage, String errorCode, String errorMessage) {
		super();
		this.successFlag = successFlag;
		this.successMessage = successMessage;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public GenericResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
