package yunji;
/*
查询地图列表
使用方式：
需替换`DB_URL`、`DB_USERNAME`、`DB_PASSWORD`为你的MySQL数据库连接参数。注意替换`id = ?`和`parameters`为适用于你的实际情况的查询条件和表名。
（已在下面代码中标注出来）
之后，代码将从数据库中获取`placeld`和`apiUrl`参数，并使用这些参数调用`getMapList`方法。
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MapListApi {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//替换为数据库参数
    private static final String DB_USERNAME = "your_username";//替换为数据库参数
    private static final String DB_PASSWORD = "your_password";//替换为数据库参数

    public static void main(String[] args) {
        try {
            String placeld = getPlaceldFromDatabase();
            String apiUrl = getApiUrlFromDatabase();

            if (placeld != null && apiUrl != null) {
                String[] mapList = getMapList(apiUrl, placeld);
                if (mapList != null) {
                    for (String map : mapList) {
                        System.out.println("地图名称：" + map);
                    }
                }
            } else {
                System.err.println("从数据库中获取参数失败。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPlaceldFromDatabase() {
        String placeld = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT placeld FROM parameters WHERE id = ?";//根据实际情况替换修改
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
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

    private static String getApiUrlFromDatabase() {
        String apiUrl = null;
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT apiUrl FROM parameters WHERE id = ?";//根据实际情况替换修改
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                apiUrl = resultSet.getString("apiUrl");
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apiUrl;
    }

    private static String[] getMapList(String apiUrl, String placeld) {
        RestTemplate restTemplate = new RestTemplate();
        String urlWithParams = apiUrl + "?placeld=" + placeld;

        ResponseEntity<MapListResponse> response = restTemplate.getForEntity(urlWithParams, MapListResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            MapListResponse responseBody = response.getBody();
            if (responseBody != null) {
                if (responseBody.getErrCode() == 0) {
                    return responseBody.getData();
                } else {
                    System.err.println("调用接口时出错。Errcode: " + responseBody.getErrCode());
                }
            } else {
                System.err.println("调用接口时出错。响应体为空。");
            }
        } else {
            System.err.println("调用接口时出错。状态码：" + response.getStatusCode());
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