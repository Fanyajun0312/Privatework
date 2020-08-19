package com.example.three.bean;

import com.google.gson.JsonElement;

import java.io.Serializable;

/**
 * @date：2020/8/10
 * @describe：
 * @author：FanYaJun
 */
public class Responses  {
    int Status;
    String Message;
    JsonElement data;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Responses{" +
                "Status=" + Status +
                ", Message='" + Message + '\'' +
                ", data=" + data +
                '}';
    }
}
