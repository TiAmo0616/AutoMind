package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 查询下发机器人指令应答接口公共应答参数
 */
@Data
public class RobotCmdQueryResultVO {

    private List<RobotCmdResultBatchVO> result_list;
}
