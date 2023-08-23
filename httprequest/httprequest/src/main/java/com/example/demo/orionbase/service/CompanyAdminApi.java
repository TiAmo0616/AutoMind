package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 企业信息接口
 */
@Component
@ComponentScan("com.example")
public class CompanyAdminApi {

    /**
     * 获取企业员工信息
     *
     * user_uuid	string	否	要获取的员工 uuid，只支持获取 1 个员工。此参数和 mobile 参数至少传递 1 个。
     * mobile	    string	否	要获取的员工手机号，只支持获取 1 个员工。此参数和 user_uuid 参数至少传递 1 个。
     *
     * @return
     */
    public static void userInfo(HttpSession session, String user_uuid, String mobile) {
        String url = "user/user_info?user_uuid=&mobile=";
        if(StringUtils.isNotBlank(user_uuid)) {
            url.replace("user_uuid=", "user_uuid=" + user_uuid);
        }
        if(StringUtils.isNotBlank(mobile)) {
            url.replace("mobile=", "mobile=" + mobile);
        }
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyUserResultVO 实体类解析数据
    }

    /**
     * 企业管理员登录密码验证
     *
     * mobile	string	是	管理员手机号
     * password	string	是	管理员密码
     *
     * @return
     */
    public static void adminPasswordAuth(HttpSession session, String mobile, String password) {
        String url = "user/admin_password_auth?mobile="+mobile+"&password="+password;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyUserResultVO 实体类解析数据

    }

    /**
     * 企业管理员发送登录验证码短信
     *
     * mobile	string	是	管理员手机号
     *
     * @return
     */
    public static void sendAdminAuthCode(HttpSession session, String mobile) {
        String url = "user/send_admin_auth_code?mobile="+mobile;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AdminAuthCodeResultVO 实体类解析数据
        // TODO 这一步获取到的auth_token 下个接口会用到

    }

    /**
     * 企业管理员短信登录验证码验证
     *
     * mobile	    string	是	管理员手机号
     * auth_code	string	是	短信验证码，一般是 6 位数字。
     * auth_token	string	是	验证码会话 token。从调用发送登录验证码短信接口获取到 的验证码会话 token，原样传递过来。
     *
     * @return
     */
    public static void adminAuthCodeAuth(HttpSession session, String mobile, String auth_code, String auth_token) {
        String url = "user/admin_auth_code_auth?mobile="+mobile+"&auth_code="+auth_code+"&auth_token="+auth_token;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 CompanyUserResultVO 实体类解析数据
    }

    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
