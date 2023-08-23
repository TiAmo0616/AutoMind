package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 机器人状态反参
 */
@Data
public class RobotStatusInfoVO {

    /**
     * 电量信息
     */
    private RobotBatteryVO battery;

    /**
     * 位置信息
     */
    private RobotLocationVO location;

}