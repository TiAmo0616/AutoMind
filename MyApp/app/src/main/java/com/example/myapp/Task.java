package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.helloworld.dao.UserDo;
import com.example.helloworld.entity.User;
import com.example.helloworld.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Task extends AppCompatActivity {
    private static final String TAG = "mysql-myapp-Task";
    Button button = null;
    EditText robertid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        button = findViewById(R.id.btn_search);
        robertid = findViewById(R.id.robertid);
    }


    public void onClick(View view){
        String id = robertid.getText().toString();
        new Thread(){
            @Override
            public void run() {

                int msg = 0;
                Connection connection = JDBCUtils.getConn();
                try {
                    String sql = "select * from robert where company = ?";
                    if (connection != null){// connection不为null表示与数据库建立了连接
                        PreparedStatement ps = connection.prepareStatement(sql);
                        if (ps != null) {
                            ps.setString(1, id);
                            ResultSet rs = ps.executeQuery();

                            while (rs.next()) {
                                //注意：下标是从1开始
                                String company = rs.getString(4);
                                if (company.equals("云迹科技")){msg = 1;}
                                else msg = 2;
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d(TAG, "异常findUser：" + e.getMessage());
                }


                hand.sendEmptyMessage(msg);
            }
        }.start();


    }

    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler()
    {
        public void handleMessage(Message msg) {
            if(msg.what == 0) {
                Toast.makeText(getApplicationContext(),"该机器人不存在",Toast.LENGTH_LONG).show();
            } else if(msg.what == 1) {
                Intent intent = null;  //这个变量初始申明为空
                intent = new Intent(Task.this, YunjiTask.class);//跳转界面
                intent.putExtra("id",robertid.getText().toString().trim() );
                startActivity(intent);
            } else if(msg.what == 2) {
                Intent intent = null;  //这个变量初始申明为空
                intent = new Intent(Task.this, LiehuTask.class);//跳转界面
                intent.putExtra("id",robertid.getText().toString().trim() );
                startActivity(intent);
            }
        }
    };
}
