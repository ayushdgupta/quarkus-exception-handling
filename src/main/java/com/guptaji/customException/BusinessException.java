package com.guptaji.customException;

public class BusinessException extends RuntimeException{

    public int status;
    public String apiName;

    public BusinessException(String message, int status, String apiName) {
        super(message);
        this.status = status;
        this.apiName = apiName;
    }

    public BusinessException() {
        // default constructor
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}
