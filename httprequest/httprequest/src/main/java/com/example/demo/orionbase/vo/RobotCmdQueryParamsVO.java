package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 查询下发机器人指令应答接口公共请求参数
 */
@Data
public class RobotCmdQueryParamsVO {

    /**
     * 指令消息 id，多个使用英文逗号分隔
     */
    private String msg_id;
}
