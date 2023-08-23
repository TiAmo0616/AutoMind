package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 应用消息反参
 */
@Data
public class ApplicationMessageResultVO {

    /**
     * 消息反参
     */
    private List<MessageResultVO> result_list;
    

}
