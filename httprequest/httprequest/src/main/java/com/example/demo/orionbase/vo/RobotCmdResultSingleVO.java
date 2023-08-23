package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 下发机器人指令接口公共应答参数-单机器人
 */
@Data
public class RobotCmdResultSingleVO {

    /**
     * 指令消息 id。如果指令下发失败则无此参数
     */
    private String msg_id;

    /**
     * 机器人端返回的指令执行应答错误代码，0 是成功，
     * 非 0 是 失败。如果是异步指令或者没有收到机器人端返回的指令 执行应答则无此参数
     */
    private String result_ret;

    /**
     * 机器人端返回的指令执行应答错误代码，0 是成功，
     * 非 0 是 失败。如果是异步指令或者没有收到机器人端返回的指令 执行应答则无此参数
     */
    private String result_msg;

    /**
     * 机器人端返回的指令执行应答数据，可能是 object，也可能 是 null。
     * 如果是异步指令或者没有收到机器人端返回的指令 执行应答则无此参数
     */
    private String result_data;

}
