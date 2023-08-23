package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 代理商信息接口反参
 */
@Data
public class AgencyUserVO {

    /**
     * 所属代理商 id
     */
    private String agency_id;

    /**
     * 管理员 id
     */
    private String aguser_id;

    /**
     * 软工角色 id，详见参数 oUser.role_id 约定
     */
    private String role_id;

    /**
     * 姓名
     */
    private String full_name;

    /**
     * 性别，详见参数 gender 约定
     */
    private String gender;

    /**
     * 头像 url
     */
    private String avatar_url;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 部门
     */
    private String dept;

    /**
     * 职务
     */
    private String job;

    /**
     * 添加时间，整数时间戳。
     */
    private String create_time;

    /**
     * 最后修改时间，整数时间戳。
     */
    private String update_time;
}