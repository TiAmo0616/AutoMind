package com.example.demo.orionbase.vo;

import lombok.Data;

/**
 * 查询应用消息反参实体
 */
@Data
public class MessageQueryResMsgVO {

    /**
     * 应用消息下发应答错误代码，0 是成功，非 0 是失 败。具体错误原因还需要参考 result_ret
     */
    private String reqid;

}
