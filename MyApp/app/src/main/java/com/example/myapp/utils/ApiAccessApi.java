package com.example.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.myapp.vo.AccessToken;
import com.example.myapp.vo.BaseOpenDataResultVO;
import com.example.myapp.vo.BaseResultVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiAccessApi {
    private static final String TAG = "mysql-myapp-ApiAccessApi";

    private static String appId = "ori-e802392efc56ffcdba97ce35ddea11";

    private static String appSecret = "5c65892744ee1d7dcbffd02159ccbd06";

    private static String ov_corpid = "5c65892744ee1d7dcbffd02159ccbd06";

    private static String version = "v1/";

    private static String domain = "test-openapi.ainirobot.com/";

    private static String domain_open = "test-openapi.ainirobot.com/proxyopen/";

    private static String getUrl(String uri) {
        return "https://" + domain + version + uri;
    }

    public static String setAccess() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://" + domain + version + "auth/get_token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;

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
                BaseResultVO access = new Gson().fromJson(successResultStr, BaseResultVO.class);
                AccessToken accessToken = new Gson().fromJson((String) access.getData(), AccessToken.class);
                return accessToken.getAccess_token();
            } else {
                System.out.println("Request failed, response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAccess(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String access = sharedPreferences.getString("access", null);
        if (TextUtils.isEmpty(access)) {
            access = setAccess();
            if (!TextUtils.isEmpty(access)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("access", access);
                editor.apply();
            }
        }
        return access;
    }

    public static JsonObject sendGet(String uri, Context context) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://" + domain + version + uri + "&appid="
                + appId + "&access_token=" + getAccess(context) + "&is_batch=1&ctime=" + String.valueOf(System.currentTimeMillis() / 1000);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body() != null ? response.body().string() : "";
                BaseResultVO baseResultVO = new Gson().fromJson(responseBody, BaseResultVO.class);
                return (JsonObject) baseResultVO.getData();
            } else {
                System.out.println("Request failed, response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static BaseOpenDataResultVO sendGetForOpen(Context context, String uri) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://" + domain_open + uri + "&appid="
                + appId + "&access_token=" + getAccess(context) + "&ov_corpid=" + ov_corpid;

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

    public static RequestBody createFormRequestBody(Map<String, String> formData) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for (Map.Entry<String, String> entry : formData.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            formBodyBuilder.add(key, value);
        }

        return formBodyBuilder.build();
    }

    public static JsonObject sendPost(String uri, Map<String, String> formData) {
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = createFormRequestBody(formData);
        String url = getUrl(uri);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                System.out.println(responseData);

                Gson gson = new Gson();
                BaseResultVO result = gson.fromJson(responseData, BaseResultVO.class);
                return (JsonObject) result.getData();
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}