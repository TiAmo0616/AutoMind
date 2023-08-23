package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 机器人状态反参
 */
@Data
public class RobotLocationVO {

    /**
     * 机器人的当前位置，默认语言。
     */
    private String pos_name;

    /**
     * 机器人的当前位置，多语言对象。
     */
    private RobotPosAllNameVO pos_all_name;

    /**
     * 是否处于急停状态。 0：否 1：是
     */
    private String emergency;

}