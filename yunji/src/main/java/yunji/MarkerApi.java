package yunji;
/*
查询地图点位
使用方式如下：
以下代码已假设你的数据库表为`marker_details`，其中包含 `placeld`、`productld` 和 `floor` 列，用于存储点位ID、产品ID和楼层。
需替换 `DB_URL`、`DB_USERNAME` 和 `DB_PASSWORD` 为你的MySQL数据库连接参数。（已在下面代码中标出）
还需根据实际情况修改SQL查询语句和表名。
之后就可以从数据库中获取 `placeld`、`productld` 和 `floor` 参数，并调用 `getMarkers` 方法来获取点位信息了。
 */
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class MarkerApi {
    private static final String API_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//需替换为数据库参数
    private static final String DB_USERNAME = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            String placeld = getPlaceldFromDatabase();
            String productld = getProductldFromDatabase();
            int floor = getFloorFromDatabase();

            JSONArray markerArray = getMarkers(API_URL, placeld, productld, floor);
            if (markerArray != null) {
                for (int i = 0; i < markerArray.length(); i++) {
                    JSONObject marker = markerArray.getJSONObject(i);
                    String name = marker.getString("name");
                    String type = marker.getString("type");
                    int markerFloor = marker.getInt("floor");
                    System.out.println("点位名称：" + name);
                    System.out.println("点位类型：" + type);
                    System.out.println("楼层：" + markerFloor);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPlaceldFromDatabase() {
        String placeld = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT placeld FROM marker_details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                placeld = resultSet.getString("placeld");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return placeld;
    }

    private static String getProductldFromDatabase() {
        String productld = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT productld FROM marker_details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                productld = resultSet.getString("productld");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productld;
    }

    private static int getFloorFromDatabase() {
        int floor = 0;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT floor FROM marker_details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                floor = resultSet.getInt("floor");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return floor;
    }

    private static JSONArray getMarkers(String apiUrl, String placeld, String productld, int floor)
            throws IOException {
        String fullUrl = apiUrl + "?placeld=" + placeld + "&productld=" + productld + "&floor=" + floor;

        URL url = new URL(fullUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());

            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }

            scanner.close();

            // 解析返回的JSON数据
            JSONObject data = new JSONObject(response.toString());
            int errorCode = data.getInt("errcode");

            if (errorCode == 0) {
                return data.getJSONArray("data");
            } else {
                String errorMessage = data.getString("errmsg");
                System.out.println("请求失败：" + errorMessage);
            }
        } else {
            System.out.println("请求失败，错误码：" + responseCode);
        }

        conn.disconnect();
        return null;
    }
}