package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 代理商信息接口反参
 */
@Data
public class AgencyDetailVO {

    /**
     * 代理商 id
     */
    private String agency_id;

    /**
     * 代理商名称
     */
    private String agency_name;

    /**
     * 添加时间，整数时间戳。
     */
    private String create_time;

    /**
     * 最后修改时间，整数时间戳。
     */
    private String update_time;
}