package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * ssoToken反参
 */
@Data
public class AdminPageSsoVO {

    /**
     * 免登录跳转URL
     */
    private String redirect_url;

    /**
     * 跳转 url 有效期，单位是秒。
     * 例如 14400 表示跳转 URL 的有效期是 4 个小时。
     */
    private String expires_in;

}
