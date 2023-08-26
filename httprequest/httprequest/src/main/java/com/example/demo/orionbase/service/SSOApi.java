package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * sso相关接口
 */
@Component
@ComponentScan("com.example")
public class SSOApi {


    /**
     * 此接口中的corpid 或者 ov_corpid 二选一必传
     * 验证企业后台免登录跳转sso token接口
     *
     * 此接口中的corpid 或者 ov_corpid 二选一必传
     * @param sso_token
     * @return
     */
    public static void ssoToken(HttpSession session, String sso_token, String corpid, String ov_corpid) {
        String url = "";
        if(StringUtils.isNotBlank(corpid)) {
            url = "sso/auth_sso_token?sso_token="+ sso_token + "&corpid="+corpid;
        }else {
            url = "sso/auth_sso_token?sso_token="+ sso_token + "&ov_corpid="+ov_corpid;
        }
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);

        // TODO 做数据解析
        // 根据 SSOTokenVO实体类中 user_type属性做实体转换
    }

    /**
     * 第三方可以通过此接口获取到免登录都企业后台的跳转 URL。
     * 由于此 URL 有效期是 1 分 钟，所以需要接入方只在需时获取跳转 URL，并在获取到跳转 URL 之后立即做跳转，防止超过有效期。
     *
     * 获取第三方免登录到企业后台的跳转URL接口
     *
     * 此接口中的corpid 或者 ov_corpid 二选一必传
     * @param corpid
     * @param ov_corpid
     * @param sso_acct_type 第三方账号类型，由我方分配，请接入方与我方沟 后通分配。例如 3part_cmcm(必填)
     * @param sso_acct_id 第三方账号 id。如果是腾讯等平台接入请传入腾讯 等平台的 unionid 或 openid。
     * @param sso_acct_mobile 第三方账号对应的手机号。此手机号需要在我方企业后台也存在，如果我方企业后台不存在则无法免登录。(必填)
     * @param sso_acct_name 第三方账号昵称或者姓名。如果传递了此参数，免登录都我方企业后台之后，当前账号的姓名会显示 成此参数的值。
     * @param sso_acct_avatar_url 第三方账号头像 URL。如果传递了此参数，免登录 都我方企业后台之后，当前账号的头像会显示成此 参数的值
     * @return
     */
    public static void authSsoToken(HttpSession session, String corpid, String ov_corpid,
                                    String sso_acct_type, String sso_acct_id, String sso_acct_mobile,
                                    String sso_acct_name, String sso_acct_avatar_url) {
        String url = "";
        if(StringUtils.isNotBlank(corpid)) {
            url = "sso/auth_sso_token?corpid=" + corpid;
        }else {
            url = "sso/auth_sso_token?ov_corpid=" + ov_corpid;
        }
        // 添加信息
        if(StringUtils.isNotBlank(sso_acct_type)) {
            url += "&sso_acct_type=" + sso_acct_type;
        }
        if(StringUtils.isNotBlank(sso_acct_id)) {
            url += "&sso_acct_id=" + sso_acct_id;
        }
        if(StringUtils.isNotBlank(sso_acct_mobile)) {
            url += "&sso_acct_mobile=" + sso_acct_mobile;
        }
        if(StringUtils.isNotBlank(sso_acct_name)) {
            url += "&sso_acct_name=" + sso_acct_name;
        }
        if(StringUtils.isNotBlank(sso_acct_avatar_url)) {
            url += "&sso_acct_avatar_url=" + sso_acct_avatar_url;
        }
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 SSOAuthTokenVO 中的 redirect_url 为跳转地址
    }

    /**
     * 获取第三方免登录到企业后台子页面的跳转URL接口
     * 第三方可以通过此接口获取到免登录都企业后台子页面的跳转 URL。
     * 请接入方记住此跳 转 URL，并在同一个用户的同一个登录态会话下尽量复用有效期内的跳转 URL（不同的用户 以及不同的登录态会话请不要复用）
     * 我方可能会限制同一个 appid 的同一个用户每天获取子 页面跳转 URL 的次数，
     * 以防止每次进入我方子页面都创建新的登录态会话导致过多的会话资 源占用。
     *
     * @param  sso_acct_type  第三方账号类型，由我方分配，请接入方与我方沟 后通分配。例如 3part_cmcm(必填)
     * @param sso_acct_id 第三方账号 id。如果是腾讯等平台接入请传入腾讯 等平台的 unionid 或 openid。
     * @param sso_acct_mobile  第三方账号对应的手机号。此手机号需要在我方企 业后台也存在，如果我方企业后台不存在则无法免 登录。(必填)
     * @param sso_acct_name 第三方账号昵称或者姓名。如果传递了此参数，免 登录都我方企业后台之后，当前账号的姓名会显示 成此参数的值。
     * @param sso_acct_avatar_url 第三方账号头像 URL。如果传递了此参数，免登录 都我方企业后台之后，当前账号的头像会显示成此 参数的值
     * @param target_route 企业后台子页面的路由地址，不同的子页面的路由地址不同，请在接入的时候与我方联系获取具体子页面的路由地址。(必填)
     *                     例如 /web/person/#/vipmanager
     * @param target_args 附加到跳转 URL 中的参数，格式为 json 对象字符 串。
     *                    不同的子页面可能会需要传入一些业务参数，
     *                    请在接入的时候与我方联系获取具体子页面的附加 参数。
     *                    例如 {"robot_sn":"SN"}
     * @return
     */
    public static void corpAdminPageSso(HttpSession session, String sso_acct_type, String sso_acct_id,
                                    String sso_acct_mobile, String sso_acct_name, String sso_acct_avatar_url,
                                    String target_route, String target_args) {
        String url = "";
        if(StringUtils.isNotBlank(sso_acct_type)) {
            url += "sso/sso/corp_admin_page_sso?sso_acct_type=" + sso_acct_type;
        }
        // 添加信息
        if(StringUtils.isNotBlank(sso_acct_id)) {
            url += "&sso_acct_id=" + sso_acct_id;
        }
        if(StringUtils.isNotBlank(sso_acct_mobile)) {
            url += "&sso_acct_mobile=" + sso_acct_mobile;
        }
        if(StringUtils.isNotBlank(sso_acct_name)) {
            url += "&sso_acct_name=" + sso_acct_name;
        }
        if(StringUtils.isNotBlank(sso_acct_avatar_url)) {
            url += "&sso_acct_avatar_url=" + sso_acct_avatar_url;
        }
        if(StringUtils.isNotBlank(target_route)) {
            url += "&target_route=" + target_route;
        }
        if(StringUtils.isNotBlank(target_args)) {
            url += "&target_args=" + target_args;
        }
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 AdminPageSsoVO 解析
    }


    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        //验证企业后台免登录跳转sso token接口
        ssoToken(session, "aaaaaaa", "bbbbb", null);


    }


}
