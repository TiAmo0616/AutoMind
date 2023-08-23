package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.PersonUserVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.MultipartBody;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 人员人脸相关接口
 */
@Component
@ComponentScan("com.example")
public class PersonFaceApi {

    /**
     * 图片文件上传
     * @param content 图片原始内容流
     * @return
     */
    public static void imageUpload(HttpSession session, String content) {
        //action 上传图片动作类型，支持如下几种类型 ● face_search：人脸照片 (就一种 写死了)
        MultipartBody.Builder param =  new MultipartBody.Builder()
                                        .addFormDataPart("content", content)
                                        .addFormDataPart("action","face_search");
        JsonObject data = ApiAccessApi.sendPostForMultipart(session,"media/image_upload", param);
        // TODO 需要存储返回的信息id 便于查询
        // 根据 ImageUrlVO 解析文件id
    }

    /**
     * 关联规则
     * 1. 在人脸系统中，每张人脸照片都对应一个人员（person），同一个人员可以设置多张 人脸照片（目前最多 3 张），
     *          但是一个人员的人脸照片（同一张照片或者被识别成同 一个人员的不同的照片）只能对应这一个人员，不能被关联到其他人员身上；
     *
     * 2. 在调用下述添加人员接口或者修改人员接口的时候，系统会检查传递过来的人脸照片是否已经关联到了其他人员，
     *          如果已经关联到其他人员了则会给出错误提示信息；
     *
     * 推荐方法
     *  1. 添加人员
     *
     *       a. 首先通过图片文件上传接口把人脸照片传递到系统中，上传之后会得到这张照 片的 file_id，
     *           如果想要给人员添加多张人脸照片可以多次调用图片文件上传接 口先把这些人脸照片都上传上来；
     *
     *       b. 然后调用人脸搜索接口来检查人脸照片是否已经关联到了系统的人员中，此接 口支持同时传递多张（目前最多支持 3 张）人脸照片；
     *
     *       c. 如果没有关联再调用人员添加接口来添加人员；
     *
     *  2. 修改人员，推荐采用与添加人员同样的方法。
     *
     *  人脸平台接口公共请求参数
     *  参数	类型	必需	参数说明
     * corpid	    string	否	企业 uuid，此参数和 ov_corpid 至少传递其中 1 个。
     * ov_corpid	string	否	语音链路企业 id，此参数和 corpid 至少传递其中 1 个。
     */



    /**
     * 添加人员
     * @param face_content_1 图片原始内容流
     * @param face_content_2 图片原始内容流
     * @param face_content_3 图片原始内容流
     * @return
     */
    public static void addUser(HttpSession session, PersonUserVO user, String face_content_1,
                                   String face_content_2, String face_content_3) {
        // TODO 如果采用 file_id 方式 需要在这里查询用户之前上传图片后 返回的file_id

        //action 上传图片动作类型，支持如下几种类型 ● face_search：人脸照片 (就一种 写死了)
        MultipartBody.Builder param =  new MultipartBody.Builder()
                .addFormDataPart("person", new Gson().toJson(user))
                //file_id 方式互斥 拿到第一步查询的 file_id 如有多个 逗号分隔 最多三个
                //.addFormDataPart("file_id","1,2,3")
                // ( face_content_1 图片原始流这里也是 最多三个 不用1 2 3都传)
                .addFormDataPart("face_content_1",face_content_1)
                .addFormDataPart("face_content_2",face_content_2)
                .addFormDataPart("face_content_3",face_content_3);
        JsonObject data = ApiAccessApi.sendPostForMultipart(session,"person/add_person", param);
        // TODO 需要存储返回的信息id 便于查询
        // 根据 PersonUserResultVO 解析用户id
    }

    /**
     * 修改人员
     * @param face_content_1 图片原始内容流
     * @param face_content_2 图片原始内容流
     * @param face_content_3 图片原始内容流
     * @return
     */
    public static void modifyUser(HttpSession session, PersonUserVO user, String face_content_1,
                                   String face_content_2, String face_content_3) {
        // TODO 如果采用 file_id 方式 需要在这里查询用户之前上传图片后 返回的file_id

        //action 上传图片动作类型，支持如下几种类型 ● face_search：人脸照片 (就一种 写死了)
        MultipartBody.Builder param =  new MultipartBody.Builder()
                .addFormDataPart("person", new Gson().toJson(user))
                //file_id 方式互斥 拿到第一步查询的 file_id 如有多个 逗号分隔 最多三个
                //.addFormDataPart("file_id","1,2,3")
                // ( face_content_1 图片原始流这里也是 最多三个 不用1 2 3都传)
                .addFormDataPart("face_content_1",face_content_1)
                .addFormDataPart("face_content_2",face_content_2)
                .addFormDataPart("face_content_3",face_content_3);
        JsonObject data = ApiAccessApi.sendPostForMultipart(session,"person/modify_person", param);
        // TODO 需要存储返回的信息id 便于查询
        // 根据 PersonUserResultVO 解析用户id
    }

    /**
     * 删除人员
     * @param personId 用户id
     * @return
     */
    public static void delUser(HttpSession session, String personId) {

        Map<String, String> formData = new HashMap<>();
        formData.put("person_id", personId);
        //下发机器人执行语音指令接口
        JsonObject data = ApiAccessApi.sendPost(session,"person/delete_person", formData);
    }


    /**
     * 通过多张人脸搜索人员
     * @param face_content_1 图片原始内容流
     * @param face_content_2 图片原始内容流
     * @param face_content_3 图片原始内容流
     * @return
     */
    public static void queryUser(HttpSession session, String face_content_1,
                                  String face_content_2, String face_content_3) {
        // TODO 如果采用 file_id 方式 需要在这里查询用户之前上传图片后 返回的file_id

        //action 上传图片动作类型，支持如下几种类型 ● face_search：人脸照片 (就一种 写死了)
        MultipartBody.Builder param =  new MultipartBody.Builder()
                //file_id 方式互斥 拿到第一步查询的 file_id 如有多个 逗号分隔 最多三个
                //.addFormDataPart("face_file_id","1,2,3")
                // ( face_content_1 图片原始流这里也是 最多三个 不用1 2 3都传)
                .addFormDataPart("face_content_1",face_content_1)
                .addFormDataPart("face_content_2",face_content_2)
                .addFormDataPart("face_content_3",face_content_3);
        JsonObject data = ApiAccessApi.sendPostForMultipart(session,"person/search_person_by_multi_face", param);
        // TODO 需要存储返回的信息 便于查询
        // 根据 PersonMultiVO 解析用户信息
    }

    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();

    }


}
