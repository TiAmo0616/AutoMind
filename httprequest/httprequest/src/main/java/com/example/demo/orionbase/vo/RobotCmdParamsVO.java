package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 下发机器人指令接口公共请求参数-单机器人/多机器人批量
 */
@Data
public class RobotCmdParamsVO {

    //robot_sn 和 robot_uuid只需要传一个就可以
    //批量传输的时候 逗号拼接就可以 比如  1,2,3

    /**
     * 机器人 sn。此参数和 robot_uuid 参数只传递其中一个即可。
     * 机器人 sn，多个用英文逗号分隔。此参数和 robot_uuid 参数 只传递其中一个即可。最多一次支持 1000 个机器人。
     */
    private String robot_sn;

    /**
     * 机器人 uuid。此参数和 robot_sn 参数只传递其中一个即可
     * 机器人 uuid，多个用英文逗号分隔。此参数和 robot_sn 参数 只传递其中一个即可。最多一次支持 1000 个机器人。
     */
    private String robot_uuid;
}
