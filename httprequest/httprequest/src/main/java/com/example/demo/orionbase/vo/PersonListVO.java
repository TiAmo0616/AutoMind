package com.example.demo.orionbase.vo;

import lombok.Data;

import java.util.List;

/**
 * 人员人脸实体
 */
@Data
public class PersonListVO {

    /**
     * index 是照片的索引
     * 如果是通过 face_file_id 传递的图片则 index 是 file_id
     *
     * 如果是通过 face_content_N 上传的图片则
     * index是 // face_content_N（例如 face_content_1）
     */
    private String index;

    /**
     * 用户信息
     */
    private List<PersonUserVO> person_list;
    

}
