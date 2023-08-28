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
	private static final String DB_URL = "jdbc:mysql://your_database_name";//���滻Ϊ���ݿ����
    private static final String DB_USER = "your_username";//���滻Ϊ���ݿ����
    private static final String DB_PASSWORD = "your_password";//���滻Ϊ���ݿ����

    public static void main(String[] args) {
        try {
            String taskState = getRobotTaskState();
            System.out.println("��ǰ����״̬�� " + taskState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getTaskState(String url, String productld) throws IOException {
        // ����URL����
        URL apiUrl = new URL(url + "?productld=" + URLEncoder.encode(productld, StandardCharsets.UTF_8));

        // ����HTTP����
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // �������󷽷�ΪGET
        connection.setRequestMethod("GET");

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
                return jsonObject.getString("data");
            } else {
                throw new IOException("����ʧ�ܣ�������: " + errorCode);
            }
        } else {
            throw new IOException("����ʧ�ܣ���Ӧ��: " + responseCode);
        }
    }

    // ��װ�ĺ���
    public static String getRobotTaskState() throws IOException {
        String apiUrl = "https://api.yunjichina.com.cn";
        String robotName = "����������"; // �滻Ϊʵ�ʵĻ���������

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // ��ѯ�����˱��
            String sql = "SELECT robot_id FROM robot_table WHERE robot_name = ?";//���滻Ϊ���ݿ����
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, robotName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("robot_id");
                return getTaskState(apiUrl, robotId);
            } else {
                throw new IOException("δ�ҵ������˱��");
            }
        } catch (SQLException e) {
            throw new IOException("���ݿ�����ʧ��", e);
        }
    }
}
