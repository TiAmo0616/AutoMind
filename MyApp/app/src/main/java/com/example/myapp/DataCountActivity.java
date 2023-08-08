package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataCountActivity extends AppCompatActivity implements View.OnClickListener {
    ListView cityLv;
    List<String> mDatas;;//列表数据源
    private static final String TAG = "mysql-myapp-datacount";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datacount);
        cityLv = findViewById(R.id.city_lv);
        getList();
        //mDatas = getList();
        //mDatas = new ArrayList<>();
        //mDatas.add("猎户星空");
        //  mDatas.add("云迹科技");
        //mDatas.add("猎户星空");
        //cityLv.setAdapter(new CityManagerAdapter(this, mDatas));
    }


    @Override
    public void onClick(View view) {

    }

    public void add_company(View view){
        startActivity(new Intent(getApplicationContext(), add_company.class));
    }

    /*public List<String> getList() {
        List<String> lst = new ArrayList<>();
        // 连接数据库

        Connection connection = JDBCUtils.getConn();
        try {

            // 查询公司名称
            String sql = "SELECT companyname FROM company";
            if (connection != null){// connection不为null表示与数据库建立了连接
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null){
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String company = rs.getString("companyname");
                        System.out.println(company);
                        lst.add(company);
                    }

                    connection.close();
                    ps.close();
                    return lst;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return lst;
    }
*/

    public void getList() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<String> lst = new ArrayList<>();
                // 连接数据库
                Connection connection = JDBCUtils.getConn();
                try {
                    // 查询公司名称
                    String sql = "SELECT companyname FROM company";
                    if (connection != null) {
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                String company = rs.getString("companyname");
                                System.out.println(company);
                                lst.add(company);
                            }
                            rs.close();
                            ps.close();
                        }
                        connection.close();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // 在UI线程中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mDatas = lst;
                        cityLv.setAdapter(new CityManagerAdapter(DataCountActivity.this, mDatas));
                    }
                });
            }
        });
        executor.shutdown();
    }



}



