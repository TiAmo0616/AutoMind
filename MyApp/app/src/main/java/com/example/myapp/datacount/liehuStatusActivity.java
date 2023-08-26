package com.example.myapp.datacount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class liehuStatusActivity extends AppCompatActivity implements View.OnClickListener{
    ListView cityLv;
    List<String> mDatas;//列表数据源
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liehustatus);
        cityLv = findViewById(R.id.city_lv);
        mDatas = new ArrayList<>();
        mDatas = getIdlist();

        cityLv.setAdapter(new liehuStatusAdapter(this,mDatas));
    }
    @Override
    public void onClick(View view) {

    }
    public List<String> getIdlist(){
        try {

            List<String> idlist = new ArrayList<>();
            // 连接数据库
            Connection connection = JDBCUtils.getConn();
            // 查询机器人编号
            String sql = "SELECT id FROM robot where companyName='猎户星空'";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String robotId = rs.getString("id");
                idlist.add(robotId);


                // 关闭数据库连接
                rs.close();
                stmt.close();
                connection.close();

                return idlist;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
    public void back(View view){
        startActivity(new Intent(getApplicationContext(), DataCountActivity.class));
    }
}
