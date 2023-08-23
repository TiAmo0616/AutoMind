package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * access_token 实体
 */
@Data
public class CompanyInfoObjVO {

    /**
     * 所属企业 uuid
     */
    private String corpid;

    /**
     * 语音链路企业 id
     */
    private String ov_corp_id;

    /**
     * 企业名称
     */
    private String corp_name;

    /**
     * 企业类型 id
     */
    private String corp_cate;

    /**
     * 企业类型名字
     */
    private String corp_cate_name;

    /**
     * 省，企业列表接口才有此字段
     */
    private String region_province_cn;

    /**
     * 市，企业列表接口才有此字段
     */
    private String region_city_cn;

    /**
     * 区，企业列表接口才有此字段
     */
    private String region_district_cn;

    /**
     * 添加时间，整数时间戳。
     */
    private String create_time;

    /**
     *最后修改时间，整数时间戳。
     */
    private String update_time;


}