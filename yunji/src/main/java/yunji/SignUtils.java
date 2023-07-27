package yunji;
/*
接口签名
使用方式如下：
需将 `DB_URL`、`DB_USERNAME` 和 `DB_PASSWORD` 替换为你的MySQL数据库连接参数。（已在下面代码中标出）
还需根据实际情况修改SQL查询语句和表名。
之后就可以通过调用 `getAppnameFromDatabase` 和 `getSecretFromDatabase` 方法从数据库中获取应用名称和密钥，并调用 `sign` 方法生成签名。
 */
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUtils {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//需替换为数据库参数
    private static final String DB_USERNAME = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            Map<String, String> params = new HashMap<>();
            // 添加其他参数
            params.put("param1", "value1");

            String appname = getAppnameFromDatabase();
            String secret = getSecretFromDatabase();

            String sign = SignUtils.sign(params, appname, secret);
            System.out.println("签名结果：" + sign);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String sign(Map<String, String> params, String appname, String secret) {
        long ts = System.currentTimeMillis() + 10 * 60 * 1000; // 当前时间戳加上10分钟
        List<String> kvs = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = StringUtils.trim(entry.getKey());
            String value = StringUtils.trim(entry.getValue());
            if (key.equalsIgnoreCase("appname") ||
                    key.equalsIgnoreCase("secret") ||
                    key.equalsIgnoreCase("ts") ||
                    key.equalsIgnoreCase("sign") ||
                    StringUtils.isBlank(value)) {
                continue;
            }
            kvs.add(key + ":" + value);
        }
        Collections.sort(kvs);
        kvs.add("appname:" + appname);
        kvs.add("secret:" + secret);
        kvs.add("ts:" + ts);
        return DigestUtils.md5DigestAsHex(StringUtils.join(kvs, "|").getBytes());
    }

    private static String getAppnameFromDatabase() throws SQLException {
        String appname = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String sql = "SELECT appname FROM app_info WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1); // 替换为实际的id
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                appname = resultSet.getString("appname");
            }

            resultSet.close();
            statement.close();
        }
        return appname;
    }

    private static String getSecretFromDatabase() throws SQLException {
        String secret = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {

            String sql = "SELECT secret FROM app_info WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1); // 替换为实际的id
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                secret = resultSet.getString("secret");
            }

            resultSet.close();
            statement.close();
        }
        return secret;
    }
}