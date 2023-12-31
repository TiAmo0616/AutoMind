package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapp.dao.UserDo;
//import com.mysql.cj.protocol.Message;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mysql-myapp-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void reg(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void login(View view){

        EditText EditTextAccount = findViewById(R.id.uesrAccount);
        EditText EditTextPassword = findViewById(R.id.userPassword);

        new Thread(){
            @Override
            public void run() {
                UserDo userDao = new UserDo();
                int msg = userDao.login(EditTextAccount.getText().toString(),EditTextPassword.getText().toString());
                hand1.sendEmptyMessage(msg);
            }
        }.start();

    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ShouyeActivity.class);
                startActivity(intent);
            } else if (msg.what== 2){
                Toast.makeText(getApplicationContext(), "密码错误", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3){
                Toast.makeText(getApplicationContext(), "账号不存在", Toast.LENGTH_LONG).show();
            }
        }
    };

}

