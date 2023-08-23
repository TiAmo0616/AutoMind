package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 机器人信息反参
 */
@Data
public class RobotInfoDetailResultVO {

    /**
     * 所属企业 uuid
     */
    private String corpid;

    /**
     * 机器人 uuid
     */
    private String robot_uuid;

    /**
     * 机器人 sn
     */
    private String robot_sn;

    /**
     * 机器人名称
     */
    private String robot_name;

    /**
     * 机器人版本
     */
    private String robot_version;

    /**
     * 机器人型号
     */
    private String robot_model;

    /**
     * 机器人模式
     */
    private String robot_mode;

    /**
     * 绑定到当前企业的时间
     */
    private String bind_time;

    /**
     * 机器人在线状态。0：不在线，1：在线。
     * 在线状态是根据机器人发送的心跳来判断的，
     * 从离线更新成 在线会有大约 5 秒的延迟，从在线更新成离线会有大约 10 秒的延迟
     */
    private String online_status;
}