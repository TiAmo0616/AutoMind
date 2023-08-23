package com.example.demo.orionbase.vo;

import com.google.gson.JsonObject;
import lombok.Data;

import java.util.List;

/**
 * 基础反参
 */
@Data
public class OpenDataVO {

    /**
     * list信息
     */
    private List<JsonObject> list;

    /**
     * 分页信息
     */
    private PaginationVO pagination;
}
