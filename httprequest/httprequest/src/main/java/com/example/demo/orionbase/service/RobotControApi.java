package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.RobotCmdParamsVO;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

import java.util.HashMap;

/**
 * 机器人相关接口
 */
@Component
@ComponentScan("com.example")
public class RobotControApi {


    /**
     * 下发机器人执行语音指令接口
     * @return
     */
    public static void execVoiceCmd(HttpSession session, RobotCmdParamsVO params) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_value", "打开开发者的演示示例");
        if(StringUtils.isNotBlank(params.getRobot_sn())) {
            formData.put("robot_sn", params.getRobot_sn());
        }else {
            formData.put("robot_uuid", params.getRobot_uuid());
        }
        //下发机器人执行语音指令接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_exec_voice_cmd", formData);
        // TODO 需要存储返回的信息id 便于查询
    }


    /**
     * 机器人下发播放TTS指令
     * @return
     */
    public static void cmdPlayTts(HttpSession session, RobotCmdParamsVO params) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_value", "打开开发者的演示示例");
        if(StringUtils.isNotBlank(params.getRobot_sn())) {
            formData.put("robot_sn", params.getRobot_sn());
        }else {
            formData.put("robot_uuid", params.getRobot_uuid());
        }
        //机器人下发播放TTS指令
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_play_tts", formData);
        // TODO 需要存储返回的信息id 便于查询
    }

    /**
     * 下发机器人休眠指令指令
     * @return
     */
    public static void cmdPowerSleep(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", "打开开发者的演示示例");
        //下发机器人休眠指令指令
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_power_sleep", formData);
        // TODO 需要存储返回的信息id 便于查询
    }

    /**
     * 查询下发机器人休眠指令应答接口
     * @return
     */
    public static void cmdPowerSleepResult(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", "打开开发者的演示示例");
        //查询下发机器人休眠指令应答接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_power_sleep/result", formData);
        // TODO 视情况而定需不需要存储本地查询的信息
    }

    /**
     * 下发机器人唤醒指令接口
     * @return
     */
    public static void cmdPowerWakeup(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("robot_sn", "MC1BCNA010021903NE05");
        formData.put("robot_uuid", "yY6UYjrrGACu2LPpoAfztw");
        //下发机器人唤醒指令接口
        JsonObject data =ApiAccessApi.sendPost(session,"robot/pipe/cmd_power_wakeup", formData);
        // TODO 需要存储返回的信息id 便于查询
    }

    /**
     * 查询下发机器人唤醒指令应答接口
     * @return
     */
    public static void cmdPowerWakeupResult(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", "打开开发者的演示示例");
        //查询下发机器人唤醒指令应答接口
        JsonObject data =ApiAccessApi.sendPost(session,"robot/pipe/cmd_power_wakeup/result", formData);
        // TODO 视情况而定需不需要存储本地查询的信息
    }

    /**
     * 下发机器人离开充电桩指令接口
     * @return
     */
    public static void cmdStopCharging(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", "打开开发者的演示示例");
        //下发机器人离开充电桩指令接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_stop_charging", formData);
        // TODO 需要存储返回的信息id 便于查询
    }

    /**
     * 获取机器人离开充电桩指令执行结果接口
     * @return
     */
    public static void cmdStopChargingResult(HttpSession session) {
        // TODO 需要获取机器人的信息
        Map<String, String> formData = new HashMap<>();
        formData.put("msg_id", "打开开发者的演示示例");
        //获取机器人离开充电桩指令执行结果接口
        JsonObject data = ApiAccessApi.sendPost(session,"robot/pipe/cmd_stop_charging/result", formData);
        // TODO 视情况而定需不需要存储本地查询的信息
    }

    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

        // TODO 该用实体类传参 方便接口调用
        RobotCmdParamsVO robotCmdParamsVO = new RobotCmdParamsVO();
        robotCmdParamsVO.setRobot_sn("MC1BCNA010021903NE05");

        //下发机器人执行语音指令接口
        execVoiceCmd(session, robotCmdParamsVO);

        //机器人下发播放TTS指令
        cmdPlayTts(session, robotCmdParamsVO);

        //下发机器人休眠指令指令
        cmdPowerSleep(session);

        //下发机器人休眠指令指令
        cmdPowerSleepResult(session);

        //下发机器人休眠指令指令
        cmdPowerWakeup(session);

        //查询下发机器人唤醒指令应答接口
        cmdPowerWakeupResult(session);

        //下发机器人离开充电桩指令接口
        cmdStopCharging(session);

        //获取机器人离开充电桩指令执行结果接口
        cmdPowerWakeupResult(session);

    }


}
