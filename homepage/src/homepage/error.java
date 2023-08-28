package homepage;

import java.io.BufferedReader;
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
public class error {
	private static final String DB_URL = "jdbc:mysql://your_database_name";//需替换为数据库参数
    private static final String DB_USER = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            String taskState = getRobotTaskState();
            System.out.println("当前任务状态： " + taskState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTaskState(String url, String productld) throws IOException {
        // 创建URL对象
        URL apiUrl = new URL(url + "?productld=" + URLEncoder.encode(productld, StandardCharsets.UTF_8));

        // 创建HTTP连接
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // 设置请求方法为GET
        connection.setRequestMethod("GET");

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
                return jsonObject.getString("data");
            } else {
                throw new IOException("请求失败，错误码: " + errorCode);
            }
        } else {
            throw new IOException("请求失败，响应码: " + responseCode);
        }
    }

    // 封装的函数
    public static String getRobotTaskState() throws IOException {
        String apiUrl = "https://api.yunjichina.com.cn";
        String robotName = "机器人名称"; // 替换为实际的机器人名称

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 查询机器人编号
            String sql = "SELECT robot_id FROM robot_table WHERE robot_name = ?";//需替换为数据库参数
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, robotName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("robot_id");
                return getTaskState(apiUrl, robotId);
            } else {
                throw new IOException("未找到机器人编号");
            }
        } catch (SQLException e) {
            throw new IOException("数据库连接失败", e);
        }
    }
}
