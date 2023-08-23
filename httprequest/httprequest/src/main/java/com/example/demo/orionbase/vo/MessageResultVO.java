package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 发送应用消息反参实体
 */
@Data
public class MessageResultVO {

    /**
     * 应用消息下发应答错误代码，0 是成功，非 0 是失败 ，此参数只包含在下发消息之前的逻辑验证相关的错误，
     * 例如机器人 sn 是否正确等，由于消息是异步发 送，发送结果的查询方法详见接口说明。
     */
    private String ret;

    /**
     * 应用消息下发应答错误信息
     */
    private String msg;

    /**
     * 应用消息 id。如果应用消息下发失败则无此参数。
     */
    private String msg_id;

}
