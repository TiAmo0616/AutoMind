package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 行业实体
 */
@Data
public class CateDetailVO {

    /**
     * 所属一级行业 id
     */
    private String corp_pcate;

    /**
     * 行业id
     */
    private String corp_cate;

    /**
     * 行业名称
     */
    private String corp_name;

    /**
     * 一级行业名称-二级行业名称
     */
    private String corp_mname;

    /**
     * 行业对应的中控场景 id
     */
    private String corp_scenario;

}