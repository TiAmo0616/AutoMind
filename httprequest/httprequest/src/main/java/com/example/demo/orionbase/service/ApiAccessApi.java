package com.example.demo.orionbase.service;

import com.example.demo.orionbase.vo.AccessToken;
import com.example.demo.orionbase.vo.BaseOpenDataResultVO;
import com.example.demo.orionbase.vo.BaseResultVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 基础组件
 */
@Component
@ComponentScan("com.example")
public class ApiAccessApi {

    private static String appId = "ori-e802392efc56ffcdba97ce35ddea11";

    private static String appSecret = "5c65892744ee1d7dcbffd02159ccbd06";

    private static String ov_corpid = "5c65892744ee1d7dcbffd02159ccbd06";

    private static String version = "v1/";

    private static String domain = "test-openapi.ainirobot.com/";

    private static String domain_open = "test-openapi.ainirobot.com/proxyopen/";

    /**
     * 拼接url
     * @return
     */
    private static String getUrl(String uri){
        return "https://"+domain + version + uri;
    }

    /**
     * 设置access_token
     * @param session
     * @return
     */
    private static String setAccess(HttpSession session){
        OkHttpClient client = new OkHttpClient();
        String url = "https://"+domain + version + "auth/get_token?grant_type=client_credential&appid="+appId+"&secret="+appSecret;

        Request request = new Request.Builder()
                .url(url)
                .build();


        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            // 处理响应
            if (response.isSuccessful()) {
                String successResultStr = "{\n" +
                        "    \"ret\": \"0\",\n" +
                        "    \"msg\": \"\",\n" +
                        "    \"stime\": \"1539336895\",\n" +
                        "    \"data\": {\n" +
                        "        \"access_token\": \"test_token\",\n" +
                        "        \"expires_in\": \"7200\"\n" +
                        "    }\n" +
                        "}";
                String responseBody = response.body() != null ? response.body().string() : "";
//                BaseResultVO access = new Gson().fromJson(responseBody, BaseResultVO.class);
//                AccessToken accessToken =  new Gson().fromJson(access.getData(), AccessToken.class);
//                session.setAttribute("access", accessToken.getAccess_token());
                //模拟正确返回
                BaseResultVO access = new Gson().fromJson(successResultStr, BaseResultVO.class);
                AccessToken accessToken =  new Gson().fromJson(access.getData(), AccessToken.class);
                session.setAttribute("access", accessToken.getAccess_token());
                return accessToken.getAccess_token();
            } else {
                System.out.println("Request failed, response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取access_token
     * @param session
     * @return
     */
    public static String getAccess(HttpSession session) {
        String access = (String) session.getAttribute("access");
        if(StringUtils.isBlank(access)) {
            access = setAccess(session);
        }
        return access;
    }

    /**
     * 设置access_token
     * @param session
     * @return
     */
    public static JsonObject sendGet(HttpSession session, String uri){
        OkHttpClient client = new OkHttpClient();
        String url = "https://"+domain + version + uri + "&appid="
                +appId+"&access_token="+getAccess(session)+"&is_batch=1&ctime="+String.valueOf(System.currentTimeMillis() / 1000);

        Request request = new Request.Builder()
                .url(url)
                .build();


        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            // 处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body() != null ? response.body().string() : "";
                BaseResultVO baseResultVO = new Gson().fromJson(responseBody, BaseResultVO.class);
                return baseResultVO.getData();
            } else {
                System.out.println("Request failed, response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置access_token
     * @param session
     * @return
     */
    public static BaseOpenDataResultVO sendGetForOpen(HttpSession session, String uri){
        OkHttpClient client = new OkHttpClient();
        String url = "https://"+domain_open + uri + "&appid="
                +appId+"&access_token="+getAccess(session)+"&ov_corpid="+ov_corpid;

        Request request = new Request.Builder()
                .url(url)
                .build();


        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            // 处理响应
            if (response.isSuccessful()) {
                String responseBody = response.body() != null ? response.body().string() : "";
                BaseOpenDataResultVO baseResultVO = new Gson().fromJson(responseBody, BaseOpenDataResultVO.class);
                return baseResultVO;
            } else {
                System.out.println("Request failed, response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     * @return
     */
    public static JsonObject sendPost(HttpSession session, String uri, Map<String, String> formData) {
        // 创建OkHttp客户端
        OkHttpClient client = new OkHttpClient();

        // 创建RequestBody对象
        RequestBody requestBody = createFormRequestBody(formData);
        String access = getAccess(session);
        String url = getUrl(uri);

        //公共参数
        formData.put("appid",appId);
        formData.put("access_token",access);
        //所有的指令接口，统一加上 is_batch=1 参数吧，批处理异步方式。
        // 这个参数默认是 0 是为了向下兼容旧的同步方式，is_batch=0不正常，提示'101001'，receive msg timeout。
        formData.put("is_batch","1");
        formData.put("ctime",String.valueOf(System.currentTimeMillis() / 1000));

        // 创建POST请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // 处理成功响应
                String responseData = response.body().string();
                System.out.println(responseData);

                BaseResultVO result = new Gson().fromJson(responseData, BaseResultVO.class);
                return result.getData();
            } else {
                // 处理错误响应
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            // 处理请求异常
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求 - 上传文件
     * @return
     */
    public static JsonObject sendPostForMultipart(HttpSession session, String uri, MultipartBody.Builder build) {
        // 创建OkHttp客户端
        OkHttpClient client = new OkHttpClient();

        String access = getAccess(session);
        String url = getUrl(uri);
        // 创建请求体
        build.setType(MultipartBody.FORM)
                .addFormDataPart("appid", appId)
                .addFormDataPart("access_token", access)
                //所有的指令接口，统一加上 is_batch=1 参数吧，批处理异步方式。
                // 这个参数默认是 0 是为了向下兼容旧的同步方式，is_batch=0不正常，提示'101001'，receive msg timeout。
                .addFormDataPart("is_batch", "1")
                .addFormDataPart("ctime", String.valueOf(System.currentTimeMillis() / 1000));

        MultipartBody requestBody = build.build();
        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                // 处理成功响应
                String responseData = response.body().string();
                System.out.println(responseData);

                BaseResultVO result = new Gson().fromJson(responseData, BaseResultVO.class);
                return result.getData();
            } else {
                // 处理错误响应
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            // 处理请求异常
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 构建application/x-www-form-urlencoded的RequestBody对象
     * @param formData
     * @return
     */
    private static RequestBody createFormRequestBody(Map<String, String> formData) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for (Map.Entry<String, String> entry : formData.entrySet()) {
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }

        return formBodyBuilder.build();
    }


    public static void main(String[] args) {
        // 创建一个模拟的HttpServletRequest对象
        HttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        String access = getAccess(session);
        System.out.println(access);
    }


}
