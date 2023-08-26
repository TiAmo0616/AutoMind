package com.example.myapp.vo;

import com.google.gson.JsonObject;

public class BaseResultVO {

    private String ret;

    private String code;

    private String msg;

    private String stime;

    private JsonObject data;

    public Object getData() {
        return data;
    }
}
