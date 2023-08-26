package com.example.myapp.map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.ShouyeActivity;
import com.example.myapp.utils.JDBCUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class maplist extends AppCompatActivity {
    ListView cityLv;
    List<String> mDatas;//列表数据源

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
        cityLv = findViewById(R.id.city_lv);
        mDatas = getmap();
        cityLv.setAdapter(new mapAdapter(this,mDatas));
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), ShouyeActivity.class));
    }

    public List<String>  getmap() {
        try {

            List<String> maplst = new ArrayList<>();
            // 连接数据库
            Connection connection = JDBCUtils.getConn();
            // 查询机器人编号
            String sql = "SELECT placeid,apiurl FROM robot";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String placeid = rs.getString("placeid");
                String apiUrl = rs.getString("apiurl");
                if (placeid != null && apiUrl != null) {
                    String[] mapList = getMapList(apiUrl, placeid);
                    if (mapList != null) {
                        maplst.addAll(Arrays.asList(mapList));
                    }
                } else {
                    return null;
                }

                // 关闭数据库连接
                rs.close();
                stmt.close();
                connection.close();

            }
            return maplst;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }

    private static String[] getMapList(String apiUrl, String placeld) {
        OkHttpClient client = new OkHttpClient();

        String urlWithParams = apiUrl + "?placeld=" + placeld;

        Request request = new Request.Builder()
                .url(urlWithParams)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                Gson gson = new Gson();
                MapListResponse responseBody = gson.fromJson(response.body().string(), MapListResponse.class);

                if (responseBody != null) {
                    if (responseBody.getErrCode() == 0) {
                        return responseBody.getData();
                    } else {
                        Log.e("API调用错误", "Errcode: " + responseBody.getErrCode());
                    }
                } else {
                    Log.e("API调用错误", "响应体为空");
                }
            } else {
                Log.e("API调用错误", "状态码：" + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class MapListResponse {
        private int errcode;
        private String[] data;

        public int getErrCode() {
            return errcode;
        }

        public void setErrCode(int errcode) {
            this.errcode = errcode;
        }

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }
    }


}