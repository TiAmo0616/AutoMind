package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 代理商信息接口反参
 */
@Data
public class AgencyUserResultVO {

    /**
     * 代理商管理员信息
     */
    private AgencyUserVO aguser;

    /**
     * 代理商企业信息
     */
    private AgencyDetailVO agency;

}