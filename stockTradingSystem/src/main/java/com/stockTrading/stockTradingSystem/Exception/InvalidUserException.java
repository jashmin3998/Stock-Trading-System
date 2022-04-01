package com.stockTrading.stockTradingSystem.Exception;


public class InvalidUserException extends  Exception{


    private String errorMsg;
    public InvalidUserException(String errorMsg){
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
