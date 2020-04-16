package com.bridgelabz.bookstore.exception;

public class BookException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	
	public BookException(String errormessage) {
		super(errormessage);
	}
	public BookException(int errorCode, String msg)
	{
		this(msg);
		this.errorCode=errorCode;
	}
	public BookException(int errorCode, String msg, Throwable throwable)
	{
		super(msg, throwable);
		this.errorCode=errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
}
}