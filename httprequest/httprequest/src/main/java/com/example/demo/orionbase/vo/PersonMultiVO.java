package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 人员人脸实体
 */
@Data
public class PersonMultiVO {

    /**
     * 用户信息
     */
    private List<PersonListVO> multi_list;
    

}
