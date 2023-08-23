package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 下发机器人指令接口公共应答参数-多机器人批处理
 */
@Data
public class RobotCmdResultBatchVO {

    private List<RobotCmdResultSingleVO> result_list;
}
