package com.example.demo.orionbase.service;

import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 导航相关接口
 */
@Component
@ComponentScan("com.example")
public class GoLocationApi {


    /**
     * 下发机器人导航到指定地点指令接口
     *
     * 给机器人下发导航到指定地点的指令，用于远程控制机器人导航到指定地点
     * @param msg_value
     * @param confirm_timeout
     * @return
     */
    public static void cmdNavigation(HttpSession session, String msg_value, String confirm_timeout) {
        String url = "robot/pipe/cmd_navigation?msg_value="+ msg_value + "&confirm_timeout="+confirm_timeout;
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 BaseResultVO 转参
    }

    /**
     * 查询下发机器人导航到指定地点指令应答接口
     *
     * 查询机器人下发导航到指定地点的指令的执行应答。
     * @return
     */
    public static void cmdNavigationResult(HttpSession session) {
        String url = "robot/pipe/cmd_navigation/result";
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 BaseResultVO 转参
    }

    /**
     * 下发机器人取消导航指令接口
     *
     * 给机器人下发取消导航的指令，用于远程控制机器人取消导航。
     * @return
     */
    public static void cmdNavigationStop(HttpSession session) {
        String url = "robot/pipe/cmd_navigation_stop";
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 BaseResultVO 转参
    }

    /**
     * 查询下发机器人取消导航指令应答接口
     *
     * 查询机器人下发取消导航的指令的执行应答。
     * @return
     */
    public static void cmdNavigationStopResult(HttpSession session) {
        String url = "robot/pipe/cmd_navigation_stop/result";
        //验证企业后台免登录跳转sso token接口
        JsonObject data = ApiAccessApi.sendGet(session, url);
        // TODO 做数据解析
        // 根据 BaseResultVO 转参
    }



    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

    }


}
