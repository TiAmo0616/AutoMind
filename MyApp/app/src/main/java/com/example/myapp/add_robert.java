package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.dao.addDo;
import com.example.myapp.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class add_robert  extends AppCompatActivity {
    EditText id = null;
    EditText company = null;
    EditText name = null;
    EditText api = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robertmanager);
        id = findViewById(R.id.w_id);
        company = findViewById(R.id.w_company);
        name = findViewById(R.id.w_name);
        api = findViewById(R.id.w_api);
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), yunjiStatusActivity.class));
    }

    public void add_one(View view) {
        String id1 = id.getText().toString();
        String company1 = company.getText().toString();
        String name1 = name.getText().toString();
        String api1 = api.getText().toString();

        new Thread(){
            @Override
            public void run() {
                addDo a = new addDo();
                boolean flag = a.add_(id1, name1, api1, company1);
                int msg = 0;
                if (flag) {
                    msg = 1;
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
                Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_LONG).show();
            }
            else  {
                Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                //将想要传递的数据用putExtra封装在intent中
                intent.putExtra("a","添加机器人");
                setResult(RESULT_CANCELED,intent);
                finish();
                startActivity(new Intent(getApplicationContext(), yunjiStatusActivity.class));
            }
        }
    };
}
