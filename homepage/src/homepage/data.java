package homepage;
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

public class data {
	private static final String API_URL = "https://api.yunjichina.com.cn";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_name";//���滻Ϊ���ݿ����
    private static final String DB_USERNAME = "your_username";//���滻Ϊ���ݿ����
    private static final String DB_PASSWORD = "your_password";//���滻Ϊ���ݿ����

    public static void main(String[] args) {
        try {
            String productId = getProductIdFromDatabase();
            boolean success = returnToChargingDock(API_URL, productId);
            System.out.println("�س�ָ���Ƿ�ִ�гɹ��� " + success);
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
        // ����URL����
        URL apiUrl = new URL(url);

        // ����HTTP����
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // �������󷽷�ΪPOST
        connection.setRequestMethod("POST");

        // ��������ͷ
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        // ���������
        connection.setDoOutput(true);

        // �������������
        StringBuilder requestBody = new StringBuilder();
        requestBody.append("productld=").append(URLEncoder.encode(productId, StandardCharsets.UTF_8));
        requestBody.append("&forceCloseDoor=false"); // �����Ƿ�ǿ�ƹ��ţ�Ĭ��Ϊfalse

        // ��������������
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input);
        }

        // ��ȡ��Ӧ��
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // ��ȡ��Ӧ����
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // ����JSON��Ӧ
            JSONObject jsonObject = new JSONObject(response.toString());
            int errorCode = jsonObject.getInt("errcode");
            if (errorCode == 0) {
                return Boolean.parseBoolean(jsonObject.getString("data"));
            } else {
                throw new IOException("����ʧ�ܣ�������: " + errorCode);
            }
        } else {
            throw new IOException("����ʧ�ܣ���Ӧ��: " + responseCode);
        }
    }
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";//���滻Ϊ���ݿ����
    private static final String DB_USER = "your_username";//���滻Ϊ���ݿ����
    private static final String DB_PASSWORD = "your_password";//���滻Ϊ���ݿ����

    public static void main(String[] args) {
        try {
            // �������ݿ�
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // ��ѯ�����˱��
            String sql = "SELECT robot_id FROM robot_table WHERE robot_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "����������"); // �滻Ϊʵ�ʵĻ���������
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("robot_id");

                // ��ȡ������״̬
                String apiUrl = "https://api.yunjichina.com.cn";
                JSONObject status = getRobotStatus(apiUrl, robotId);

                if (status != null) {
                    System.out.println("������״̬��");
                    System.out.println("��ǰ����¥�㣺" + status.getInt("currentFloor"));
                    System.out.println("ʣ�������" + status.getInt("powerPercent") + "%");
                    System.out.println("�Ƿ����У�" + (status.getInt("chargeState") == 1 ? "��" : "��"));
                    System.out.println("�Ƿ�ͣ��" + status.getBoolean("estop"));
                    System.out.println("�Ƿ���У�" + status.getBoolean("idle"));
                    System.out.println("��������ϴ�ʱ�䣺" + status.getLong("ts"));
                } else {
                    System.out.println("δ�ܻ�ȡ������״̬");
                }
            } else {
                System.out.println("δ�ҵ������˱��");
            }

            // �ر����ݿ�����
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getRobotStatus(String url, String robotId) throws IOException {
        // ... ʡ��ԭ�е� getRobotStatus ʵ�� ...
        return null;
    }
}
