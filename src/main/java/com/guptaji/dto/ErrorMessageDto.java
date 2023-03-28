package com.guptaji.dto;

public class ErrorMessageDto {

    private int status;
    private String message;
    private String apiName;

    public ErrorMessageDto(int status, String message, String apiName) {
        this.status = status;
        this.message = message;
        this.apiName = apiName;
    }

    public ErrorMessageDto() {
        // default constructor
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    @Override
    public String toString() {
        return "ErrorMessageDto{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", apiName='" + apiName + '\'' +
                '}';
    }
}
