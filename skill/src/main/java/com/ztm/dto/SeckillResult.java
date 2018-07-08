package com.ztm.dto;

public class SeckillResult<T> {
    private boolean success;

    private T data;

    private String error;

    public SeckillResult(String message){
        this.error = message;
    }

    public SeckillResult(boolean success, T data){
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String message){
        this.success = success;
        this.error = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
