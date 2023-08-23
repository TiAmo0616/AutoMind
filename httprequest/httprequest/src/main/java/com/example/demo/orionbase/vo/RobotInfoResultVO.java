package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 机器人信息反参
 */
@Data
public class RobotInfoResultVO {

    /**
     * 机器人信息
     */
    private RobotInfoDetailResultVO robot;

    /**
     * 机器人上报的电量等状态信息对象
     */
    private RobotStatusInfoVO robot_report_status;

    /**
     * 机器人所属企业信息对象
     */
    private CompanyInfoObjVO corp;

}