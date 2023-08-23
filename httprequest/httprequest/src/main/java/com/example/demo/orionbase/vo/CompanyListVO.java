package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 公司列表实体
 */
@Data
public class CompanyListVO {

    /**
     * 企业信息
     */
    private List<CompanyInfoObjVO> corp_list;

}