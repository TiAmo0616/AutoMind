package yunji;
/*
基本状态查询
使用方式如下：
需将下面代码中的 `your_database_name`、`your_username` 和 `your_password` 替换为你的数据库名称、用户名和密码。（已在下面代码中标出）
还需根据你的数据库结构，调整 SQL 查询语句和结果集的字段名称。
 */
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;

public class RobotStatusQuery {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";//需替换为数据库参数
    private static final String DB_USER = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            // 连接数据库
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // 查询机器人编号
            String sql = "SELECT robot_id FROM robot_table WHERE robot_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "机器人名称"); // 替换为实际的机器人名称
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("robot_id");

                // 获取机器人状态
                String apiUrl = "https://api.yunjichina.com.cn";
                JSONObject status = getRobotStatus(apiUrl, robotId);

                if (status != null) {
                    System.out.println("机器人状态：");
                    System.out.println("当前所在楼层：" + status.getInt("currentFloor"));
                    System.out.println("剩余电量：" + status.getInt("powerPercent") + "%");
                    System.out.println("是否充电中：" + (status.getInt("chargeState") == 1 ? "是" : "否"));
                    System.out.println("是否急停：" + status.getBoolean("estop"));
                    System.out.println("是否空闲：" + status.getBoolean("idle"));
                    System.out.println("最后心跳上传时间：" + status.getLong("ts"));
                } else {
                    System.out.println("未能获取机器人状态");
                }
            } else {
                System.out.println("未找到机器人编号");
            }

            // 关闭数据库连接
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getRobotStatus(String url, String robotId) throws IOException {
        // ... 省略原有的 getRobotStatus 实现 ...
        return null;
    }
}