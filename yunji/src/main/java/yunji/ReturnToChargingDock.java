package yunji;
/*
返回待命点（充电桩）
使用方式如下：
需替换 `DB_URL`、`DB_USERNAME` 和 `DB_PASSWORD` 为你的MySQL数据库连接参数。（已在下面代码中标出）
还需根据实际情况修改SQL查询语句和表名。
之后就可以从数据库中获取机器人编号，并调用 `returnToChargingDock` 方法来发送回充指令了。
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

public class ReturnToChargingDock {
    private static final String API_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//需替换为数据库参数
    private static final String DB_USERNAME = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            String productId = getProductIdFromDatabase();
            boolean success = returnToChargingDock(API_URL, productId);
            System.out.println("回充指令是否执行成功： " + success);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getProductIdFromDatabase() {
        String productId = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT product_id FROM robot_details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                productId = resultSet.getString("product_id");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productId;
    }

    private static boolean returnToChargingDock(String url, String productId) throws IOException {
        // 创建URL对象
        URL apiUrl = new URL(url);

        // 创建HTTP连接
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // 设置请求方法为POST
        connection.setRequestMethod("POST");

        // 设置请求头
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // 启用输出流
        connection.setDoOutput(true);

        // 构建请求体参数
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("productld=").append(URLEncoder.encode(productId, StandardCharsets.UTF_8));
        requestBody.append("&forceCloseDoor=false"); // 设置是否强制关门，默认为false

        // 发送请求体数据
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input);
        }

        // 获取响应码
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析JSON响应
            JSONObject jsonObject = new JSONObject(response.toString());
            int errorCode = jsonObject.getInt("errcode");
            if (errorCode == 0) {
                return Boolean.parseBoolean(jsonObject.getString("data"));
            } else {
                throw new IOException("请求失败，错误码: " + errorCode);
            }
        } else {
            throw new IOException("请求失败，响应码: " + responseCode);
        }
    }
}