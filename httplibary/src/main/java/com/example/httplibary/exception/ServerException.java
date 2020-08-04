package com.example.httplibary.exception;

/**
 * @date：2020/8/3
 * @describe：
 * @author：FanYaJun
 */
public class ServerException extends RuntimeException {

    int code;
    String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;


    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
