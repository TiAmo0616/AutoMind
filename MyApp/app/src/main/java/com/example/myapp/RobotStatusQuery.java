package com.example.myapp;
/*
基本状态查询
使用方式如下：
需将下面代码中的 `your_database_name`、`your_username` 和 `your_password` 替换为你的数据库名称、用户名和密码。（已在下面代码中标出）
还需根据你的数据库结构，调整 SQL 查询语句和结果集的字段名称。
 */
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapp.utils.JDBCUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RobotStatusQuery {

    public static void main(String[] args) {
        try {

            String [] res = new String [6];
            // 连接数据库
            Connection connection = JDBCUtils.getConn();
            // 查询机器人编号
            String sql = "SELECT id FROM robot";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("id");

                // 获取机器人状态
                String apiUrl = "https://api.yunjichina.com.cn";
                JSONObject status = getRobotStatus(apiUrl, robotId);

                if (status != null) {
                    res[0] = String.valueOf(status.getInt("currentFloor"));
                    res[1] = (status.getInt("powerPercent") + "%");
                    res[2] =  (status.getInt("chargeState") == 1 ? "是" : "否");
                    res[3] = (status.getBoolean("estop") ?"是" : "否");
                    res[4] = status.getBoolean("idle")?"是" : "否";
                    res[5] = String.valueOf(status.getLong("ts"));


                } else {
                    System.out.println("未能获取机器人状态");
                }
            } else {
                System.out.println("未找到机器人编号");
            }

            // 关闭数据库连接
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException | IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    static JSONObject getRobotStatus(String url, String robotId) throws IOException {
        // ... 省略原有的 getRobotStatus 实现 ...
        return null;
    }
}