package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 企业管理员手机号发送登录验证码短信反参
 */
@Data
public class AdminAuthCodeResultVO {

    /**
     * 企业信息
     */
    private String auth_token;

}