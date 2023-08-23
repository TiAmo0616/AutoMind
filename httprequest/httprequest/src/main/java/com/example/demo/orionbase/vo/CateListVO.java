package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 行业列表实体
 */
@Data
public class CateListVO {

    /**
     * 一级行业
     */
    private List<CateDetailVO> pcate_list;

    /**
     * 二级行业
     */
    private List<CateDetailVO> cate_list;

}