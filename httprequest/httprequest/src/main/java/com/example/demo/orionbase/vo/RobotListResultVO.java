package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 机器人信息反参
 */
@Data
public class RobotListResultVO {

    /**
     * 机器人信息
     */
    private List<RobotInfoDetailResultVO> robot_list;

}