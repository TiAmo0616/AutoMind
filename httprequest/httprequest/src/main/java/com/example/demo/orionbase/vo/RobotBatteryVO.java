package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 机器人状态反参
 */
@Data
public class RobotBatteryVO {

    /**
     * 当前电量剩余百分比
     */
    private String battery_rate;

    /**
     * 是否处于充电状态 0：否 1：是
     */
    private String is_charging;

}