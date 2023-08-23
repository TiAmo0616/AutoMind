package com.example.demo.orionbase.vo;

import com.google.gson.JsonObject;
import lombok.Data;

/**
 * 基础反参
 */
@Data
public class BaseResultVO {

    private String ret;

    private String code;

    private String msg;

    private String stime;

    private JsonObject data;
}
