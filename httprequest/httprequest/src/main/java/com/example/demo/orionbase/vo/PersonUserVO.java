package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 人员人脸实体
 */
@Data
public class PersonUserVO {

    /**
     * 用户id
     */
    private String person_id;

    /**
     * 姓名
     */
    private String full_name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别 0位置 1男 2女
     */
    private String gender;

}
