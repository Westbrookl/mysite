package com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BusinessException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(BusinessException.class);
    protected String errorCode;
    protected String[] errorMessageArguments;

    protected BusinessException(){
        this("");
    }
    public  BusinessException(String message){
        super(message);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }


    public BusinessException(String message,Throwable cause){
        super(message,cause);
        this.errorCode = "fail";
        this.errorMessageArguments = new String[0];
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String[] getErrorMessageArguments() {
        return errorMessageArguments;
    }

    public void setErrorMessageArguments(String[] errorMessageArguments) {
        this.errorMessageArguments = errorMessageArguments;
    }
    public static BusinessException withErrorCode(String errorCode){
        BusinessException businessException = new BusinessException();
//        businessException.errorCode = errorCode;
        businessException.setErrorCode(errorCode);
        return businessException;
    }
    public  BusinessException withErrorMessageArguments(String... errorMessageArguements){
        if(errorMessageArguements!=null){
            this.errorMessageArguments = errorMessageArguements;
        }
        return this;
    }
}
