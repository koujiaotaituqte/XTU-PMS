package com.example.exception;


//自定义异常
public class CustomException extends RuntimeException {
    private String msg;
    private String code;

    public CustomException(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public CustomException(String msg) {
        this.msg = msg;
        this.code = "500";
    }

    public CustomException() {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
