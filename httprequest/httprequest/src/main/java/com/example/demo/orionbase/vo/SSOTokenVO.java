package com.example.demo.orionbase.vo;

import com.google.gson.JsonObject;
import lombok.Data;

/**
 * ssoToken反参
 */
@Data
public class SSOTokenVO {

    /**
     * 免登录来源平台类型
     * corp_admin：从接待企业后台发起的免登录 eo_admin：从 EO 代理商后台发起的免登录
     */
    private String user_type;

    /**
     * 接待企业后台的用户关键信息对象，仅当 user_type=corp_admin 的时候包含此对象
     */
    private SSOUserVO user;

    /**
     * EO 代理商后台的用户关键信息对象，仅当 user_type=eo_admin 的时候包含此对象
     */
    private SSOAguserVO aguser;

}
