package com.example.httplibary.demo;

import com.google.gson.JsonElement;

/**
 * @date：2020/8/4
 * @describe：
 * @author：FanYaJun
 */
public class ResponseFanyj {
    private JsonElement data;
    private int errorCode;
    private String errorMsg;

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
