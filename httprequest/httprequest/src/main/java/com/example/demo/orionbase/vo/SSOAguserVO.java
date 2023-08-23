package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * ssoToken反参
 */
@Data
public class SSOAguserVO {

    /**
     * 是否是从其他平台免登录到 EO 后台之后再发起的免登 录。 0：否 1：是
     */
    private String is_sso;

    /**
     * 用户 uuid
     */
    private String aguser_id;

    /**
     * 代理商 uuid
     */
    private String agency_id;

    /**
     * 语音中控代理商 id
     */
    private String ov_agency_id;

}
