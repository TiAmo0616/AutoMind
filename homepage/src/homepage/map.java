package homepage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class map {
	private static final String API_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//���滻Ϊ���ݿ����
    private static final String DB_USERNAME = "your_username";//���滻Ϊ���ݿ����
    private static final String DB_PASSWORD = "your_password";//���滻Ϊ���ݿ����

    public static void main(String[] args) {
        try {
            String placeld = getPlaceldFromDatabase();
            String mapNameValue = getMapNameValueFromDatabase();

            JSONObject mapDetails = getMapDetails(API_URL, placeld, mapNameValue);
            if (mapDetails != null) {
                String mapName = mapDetails.getString("mapName");
                System.out.println("��ͼ���ƣ�" + mapName);

                JSONArray floorFiles = mapDetails.getJSONArray("floorFiles");
                System.out.println("¥���ͼ�ļ���");
                for (int i = 0; i < floorFiles.length(); i++) {
                    JSONObject file = floorFiles.getJSONObject(i);
                    String fileName = file.getString("name");
                    String fileType = file.getString("type");
                    String fileUrl = file.getString("url");
                    System.out.println("- �ļ�����" + fileName);
                    System.out.println("  ���ͣ�" + fileType);
                    System.out.println("  �������ӣ�" + fileUrl);
                }

                JSONObject forbidden = mapDetails.getJSONObject("forbidden");
                String forbiddenFileName = forbidden.getString("name");
                String forbiddenFileType = forbidden.getString("type");
                String forbiddenFileUrl = forbidden.getString("url");
                System.out.println("\n�����������ļ���");
                System.out.println("�ļ�����" + forbiddenFileName);
                System.out.println("���ͣ�" + forbiddenFileType);
                System.out.println("�������ӣ�" + forbiddenFileUrl);
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

            // �������ص�JSON����
            JSONObject data = new JSONObject(response.toString());
            int errorCode = data.getInt("errcode");

            if (errorCode == 0) {
                return data.getJSONObject("data");
            } else {
                String errorMessage = data.getString("errmsg");
                System.out.println("����ʧ�ܣ�" + errorMessage);
            }
        } else {
            System.out.println("����ʧ�ܣ������룺" + responseCode);
        }

        conn.disconnect();
        return null;
    }
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//�滻Ϊ���ݿ����
	    private static final String DB_USERNAME = "your_username";//�滻Ϊ���ݿ����
	    private static final String DB_PASSWORD = "your_password";//�滻Ϊ���ݿ����

	    public static void main(String[] args) {
	        try {
	            String placeld = getPlaceldFromDatabase();
	            String apiUrl = getApiUrlFromDatabase();

	            if (placeld != null && apiUrl != null) {
	                String[] mapList = getMapList(apiUrl, placeld);
	                if (mapList != null) {
	                    for (String map : mapList) {
	                        System.out.println("��ͼ���ƣ�" + map);
	                    }
	                }
	            } else {
	                System.err.println("�����ݿ��л�ȡ����ʧ�ܡ�");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private static String getPlaceldFromDatabase() {
	        String placeld = null;
	        try {
	            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

	            String sql = "SELECT placeld FROM parameters WHERE id = ?";//����ʵ������滻�޸�
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

	            String sql = "SELECT apiUrl FROM parameters WHERE id = ?";//����ʵ������滻�޸�
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
	                    System.err.println("���ýӿ�ʱ����Errcode: " + responseBody.getErrCode());
	                }
	            } else {
	                System.err.println("���ýӿ�ʱ������Ӧ��Ϊ�ա�");
	            }
	        } else {
	            System.err.println("���ýӿ�ʱ����״̬�룺" + response.getStatusCode());
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
	    private static final String API_URL = "https://api.yunjichina.com.cn";
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//���滻Ϊ���ݿ����
	    private static final String DB_USERNAME = "your_username";//���滻Ϊ���ݿ����
	    private static final String DB_PASSWORD = "your_password";//���滻Ϊ���ݿ����

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
	                    System.out.println("��λ���ƣ�" + name);
	                    System.out.println("��λ���ͣ�" + type);
	                    System.out.println("¥�㣺" + markerFloor);
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

	            // �������ص�JSON����
	            JSONObject data = new JSONObject(response.toString());
	            int errorCode = data.getInt("errcode");

	            if (errorCode == 0) {
	                return data.getJSONArray("data");
	            } else {
	                String errorMessage = data.getString("errmsg");
	                System.out.println("����ʧ�ܣ�" + errorMessage);
	            }
	        } else {
	            System.out.println("����ʧ�ܣ������룺" + responseCode);
	        }

	        conn.disconnect();
	        return null;
	    }
}
