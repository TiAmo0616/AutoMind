package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.RobotCmdParamsVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用消息相关
 */
@Component
@ComponentScan("com.example")
public class ApplicationMessageApi {


    /**
     * 应用消息发送接口
     *
     * 此接口用于给机器人下发应用消息，可以是 opk 类的应用，也可以是 apk 类的应用。
     * 此接口是异步发送接口，是否下发成功需要通过调用 查询应用消息发送结果接口 来查询 下发结果。
     * @return
     */
    public static void sendMsg(HttpSession session, String robot_sn, String robot_uuid,
                                    String to_app_type, String to_app_id, String payload) {
        Map<String, String> formData = new HashMap<>();
        if(StringUtils.isNotBlank(robot_sn)) {
            formData.put("robot_sn", robot_sn);
        }else {
            formData.put("robot_uuid", robot_uuid);
        }
        formData.put("to_app_type", to_app_type);
        formData.put("to_app_id", to_app_id);
        formData.put("payload", payload);

        //应用消息发送接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/app_message/send", formData);
        // TODO 需要存储返回的信息id 便于查询
        //  应用消息下发应答列表。列表数组的元素数量和顺序 与请求的机器人数量和顺序相同。
        //使用 ApplicationMessageResultVO 解析数据

    }


    /**
     * 查询应用消息发送结果接口
     *
     * 查询机给器人下发应用消息发送的结果
     * @return
     */
    public static void queryMsg(HttpSession session, String msg_id) {
        Map<String, String> formData = new HashMap<>();
        // 从发送接口获取到的应用消息 id，多个使用英文逗号分隔。
        // 最多一次支持 1000 个 msg_id。
        formData.put("msg_id", msg_id);

        //应用消息发送接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/app_message/send/result", formData);
        // TODO 需要存储返回的信息 便于查询
        //使用 MessageQueryListResultVO 解析数据

    }



    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

    }


}
