package com.stockTrading.stockTradingSystem.model;

public class Response {
    private boolean success;
    private String error;

    public Response(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
