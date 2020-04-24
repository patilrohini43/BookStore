package com.bridgelabz.bookstore.exception;


public class UserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    int errorCode;
    String msg;

    public UserException(String errormessage)
    {
        super(errormessage);
    }
    public UserException(int errorCode, String msg)
    {
        this(msg);
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
