package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * ssoToken反参
 */
@Data
public class SSOAuthTokenVO {

    /**
     * 免登录跳转URL
     */
    private String redirect_url;

}
