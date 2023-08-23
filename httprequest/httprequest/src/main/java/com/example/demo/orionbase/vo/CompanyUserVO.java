package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 公司实体
 */
@Data
public class CompanyUserVO {

    /**
     * 员工 id
     */
    private String user_uuid;

    /**
     * 软工角色 id
     * 6 普通员工
     * 5 企业管理员
     * 9 系统管理员
     *
     */
    private String role_id;

    /**
     * 姓名
     */
    private String full_name;

    /**
     * 性别
     * 0 未知
     * 1 男
     * 2 女
     */
    private String gender;

    /**
     * 头像 ur
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
     * 所属企业名称，外企业人员时有效，企业内人员使用 corpid 关 联所属企业。
     */
    private String corp;

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