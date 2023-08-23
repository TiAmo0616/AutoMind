package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 代理商相关接口
 */
@Component
@ComponentScan("com.example")
public class AguserAdminApi {


    /**
     * 获取代理商信息
     *
     * agency_id	string	是	要获取的代理商 id，只支持获取 1 个代理商。
     *
     * @return
     */
    public static void agencyInfo(HttpSession session, String agency_id) {
        String url = "agency/agency_info?agency_id="+agency_id;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AgencyResultVO 实体类解析数据
    }

    /**
     * 获取代理商管理员信息
     *
     * 获取一个代理商管理员信息。
     * aguser_id	string	否	要要获取的管理员 id，只支持获取 1 个管理员。此参数和 mobile 参数至少传递 1 个。
     * mobile	    string	否	要获取的管理员手机号，只支持获取 1 个管理员。此参数和 aguser_id 参数至少传递 1 个。
     *
     * @return
     */
    public static void aguserInfo(HttpSession session, String agency_id, String mobile) {
        String url = "aguser/aguser_info?agency_id="+agency_id+"&mobile="+mobile;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AgencyUserResultVO 实体类解析数据
    }

    /**
     * 代理商管理员登录密码验证
     *
     * mobile	string	是	管理员手机号
     * password	string	是	管理员密码
     *
     * @return
     */
    public static void agAdminPasswordAuth(HttpSession session, String mobile, String password) {
        String url = "aguser/admin_password_auth?mobile="+mobile+"&password="+password;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AgencyUserResultVO 实体类解析数据

    }

    /**
     * 代理商管理员发送登录验证码短信
     *
     * mobile	    string	是	管理员手机号
     *
     * @return
     */
    public static void agAdminAuthCodeAuth(HttpSession session, String mobile) {
        String url = "aguser/send_admin_auth_code?mobile="+mobile;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AdminAuthCodeResultVO 实体类解析数据
        // TODO 这一步获取到的auth_token 下个接口会用到
    }


    /**
     * 代理商管理员短信登录验证码验证
     *
     * mobile	    string	是	管理员手机号
     * auth_code	string	是	短信验证码，一般是 6 位数字。
     * auth_token	string	是	验证码会话 token。从调用发送登录验证码短信接口获取到 的验证码会话 token，原样传递过来。
     *
     * @return
     */
    public static void agAdminAuthCodeAuth(HttpSession session, String mobile, String auth_code, String auth_token) {
        String url = "aguser/admin_auth_code_auth?mobile="+mobile+"&auth_code="+auth_code+"&auth_token="+auth_token;
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AgencyUserResultVO 实体类解析数据
    }

    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();


    }


}
