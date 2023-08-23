package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 分页反参
 */
@Data
public class PaginationVO {

    /**
     * 页码
     */
    private String pageIndex;

    /**
     * 每页展示数量
     */
    private String pageSize;

    /**
     * 总条数
     */
    private String total;
}
