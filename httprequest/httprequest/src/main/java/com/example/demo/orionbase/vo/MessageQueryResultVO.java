package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 查询应用消息反参实体
 */
@Data
public class MessageQueryResultVO {

    /**
     * 应用消息下发应答错误代码，0 是成功，非 0 是失 败。具体错误原因还需要参考 result_ret
     */
    private String ret;

    /**
     * 应用消息下发应答错误信息，具体错误信息还需要参 考 result_msg
     */
    private String msg;

    /**
     * 应用消息 id。如果 应用 下发失败则无此参数
     */
    private String msg_id;

    /**
     * 机器人端返回的应用消息应答错误代码，0 是成功， 非 0 是失败。
     * 如果没有收到机器人端返回的应用消息 应答则无此参数
     */
    private String result_ret;

    /**
     * 机器人端返回的应用消息应答提示信息，一般只有在 发送失败的情况下有效。
     * 如果没有收到机器人端返回 的应用消息应答则无此参数。
     */
    private String result_msg;

    /**
     * 机器人的应用消息应答，一般调用方不需要关注。
     * 如果没有收到机器人端返回的应用消息应答则无此参数。
     */
    private MessageQueryResMsgVO response_msg;

}
