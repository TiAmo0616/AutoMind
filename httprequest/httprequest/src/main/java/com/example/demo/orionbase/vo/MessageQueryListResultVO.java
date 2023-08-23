package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 查询应用消息反参
 */
@Data
public class MessageQueryListResultVO {

    /**
     * 消息反参
     */
    private List<MessageQueryResultVO> result_list;
    

}
