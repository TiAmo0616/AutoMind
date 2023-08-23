package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * ssoToken反参
 */
@Data
public class SSOUserVO {

    /**
     * 是否是从其他平台免登录到接待后台之后再发起的免登 录。 0：否 1：是
     */
    private String is_sso;

    /**
     * 用户 uuid
     */
    private String user_uuid;

    /**
     * 企业 uuid
     */
    private String corpid;

    /**
     * 语音中控企业 id
     */
    private String ov_corpid;

}
