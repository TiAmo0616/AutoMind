package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 企业用户接口反参
 */
@Data
public class CompanyUserResultVO {

    /**
     * 用户id
     */
    private CompanyUserVO user;

    /**
     * 企业信息
     */
    private CompanyInfoObjVO corp;


}