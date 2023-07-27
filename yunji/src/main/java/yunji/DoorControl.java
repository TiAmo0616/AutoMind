package yunji;
/*
开关门
使用方式如下：
需将 `DB_URL`、`DB_USERNAME` 和 `DB_PASSWORD` 替换为你的MySQL数据库连接参数。（已在下面代码中标出）
还需根据实际情况修改SQL查询语句和表名。
之后就可以从数据库中获取机器人编号，并调用 `sendDoorRequest` 方法来发送开门和关门请求了。
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class DoorControl {
    private static final String OPEN_URL = "https://api.yunjichina.com.cn";
    private static final String CLOSE_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//需替换为数据库参数
    private static final String DB_USERNAME = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            String productId = getProductIdFromDatabase();

            // 发送开门请求
            boolean openResult = sendDoorRequest(OPEN_URL, productId);
            System.out.println("开门是否成功: " + openResult);

            // 发送关门请求
            boolean closeResult = sendDoorRequest(CLOSE_URL, productId);
            System.out.println("关门是否成功: " + closeResult);
        } catch (IOException e) {
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

    private static boolean sendDoorRequest(String url, String productId) throws IOException {
        // 创建URL对象
        URL apiUrl = new URL(url);

        // 创建HTTP连接
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // 设置请求方法为POST
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // 设置请求头
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // 构建请求参数
        String params = "productld=" + URLEncoder.encode(productId, StandardCharsets.UTF_8);

        // 发送请求参数
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.writeBytes(params);
        outputStream.flush();
        outputStream.close();

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

            return jsonObject.getBoolean("data");
        } else {
            throw new IOException("请求失败，响应码: " + responseCode);
        }
    }
}