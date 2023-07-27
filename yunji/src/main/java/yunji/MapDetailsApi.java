package yunji;
/*
获取指定地图详细信息
使用方式如下：
以下代码已假设你的数据库表为`map_details`，其中包含 `placeld` 和 `map_name` 列，用于存储地点ID和地图名称。
需替换 `DB_URL`、`DB_USERNAME` 和 `DB_PASSWORD` 为你的MySQL数据库连接参数。（已在下面代码标出）
还需根据实际情况修改SQL查询语句和表名。
之后就可以从数据库中获取 `placeld` 和 `mapNameValue` 参数，并调用 `getMapDetails` 方法来获取地图详情了。
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
import org.json.JSONObject;
import org.json.JSONArray;

public class MapDetailsApi {
    private static final String API_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//需替换为数据库参数
    private static final String DB_USERNAME = "your_username";//需替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//需替换为数据库参数

    public static void main(String[] args) {
        try {
            String placeld = getPlaceldFromDatabase();
            String mapNameValue = getMapNameValueFromDatabase();

            JSONObject mapDetails = getMapDetails(API_URL, placeld, mapNameValue);
            if (mapDetails != null) {
                String mapName = mapDetails.getString("mapName");
                System.out.println("地图名称：" + mapName);

                JSONArray floorFiles = mapDetails.getJSONArray("floorFiles");
                System.out.println("楼层地图文件：");
                for (int i = 0; i < floorFiles.length(); i++) {
                    JSONObject file = floorFiles.getJSONObject(i);
                    String fileName = file.getString("name");
                    String fileType = file.getString("type");
                    String fileUrl = file.getString("url");
                    System.out.println("- 文件名：" + fileName);
                    System.out.println("  类型：" + fileType);
                    System.out.println("  下载链接：" + fileUrl);
                }

                JSONObject forbidden = mapDetails.getJSONObject("forbidden");
                String forbiddenFileName = forbidden.getString("name");
                String forbiddenFileType = forbidden.getString("type");
                String forbiddenFileUrl = forbidden.getString("url");
                System.out.println("\n禁行线配置文件：");
                System.out.println("文件名：" + forbiddenFileName);
                System.out.println("类型：" + forbiddenFileType);
                System.out.println("下载链接：" + forbiddenFileUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPlaceldFromDatabase() {
        String placeld = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT placeld FROM map_details";
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

    private static String getMapNameValueFromDatabase() {
        String mapNameValue = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT map_name FROM map_details";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                mapNameValue = resultSet.getString("map_name");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mapNameValue;
    }

    private static JSONObject getMapDetails(String apiUrl, String placeld, String mapNameValue)
            throws IOException {
        String fullUrl = apiUrl + "?placeld=" + placeld + "&mapName=" + mapNameValue;

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
                return data.getJSONObject("data");
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