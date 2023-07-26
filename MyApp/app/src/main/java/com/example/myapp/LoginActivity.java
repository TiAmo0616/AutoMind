package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    //声明控件
    //登陆界面的控件
    EditText user_name;
    EditText user_password;

    Button denglu;
    Button zhuce;

    //声明数据库
    Mysql mysql;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找到当且xml文件内的控件ID
        //数据编辑框的ID
        user_name = this.findViewById(R.id.user_name);
        user_password = this.findViewById(R.id.user_password);
        //按键属性的ID
        denglu = this.findViewById(R.id.denglu);
        zhuce = this.findViewById(R.id.zhuce);

        //取出数据库内的数据
        mysql = new Mysql(this,"Userinfo",null,1);
        db = mysql.getReadableDatabase();
        //登录按键按下之后处理的事情
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //需要获取的输入的用户名和密码
                String storage_username = user_name.getText().toString();//用户控件.得到数据.转换为字符串；
                String storage_userpassword = user_password.getText().toString();//用户控件.得到数据.转换为字符串；

                //查询用户名和密码相同的数据
                Cursor cursor = db.query("logins",new String[]{"usname","uspwd"}," usname=? and uspwd=?",
                        new String[]{storage_username,storage_userpassword},null,null,null);
                int flag = cursor.getCount(); //查询出来的记录项的条数，若没有该用户则为0条
                //登录成功后响应的数据
                if (flag!=0){
                    Toast.makeText(getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();//显示登录成功的弹窗，简单写法
                    Intent intent = null;  //这个变量初始申明为空
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    //假设正确的账号和密码分别是（VIP账号，没有写入数据库，无需注册账号）
                    if (storage_username.equals("DPT") && storage_userpassword.equals("123456")) {
                        //如果正确
                        Toast.makeText(getApplicationContext(), "超级VIP登录成功！", Toast.LENGTH_SHORT).show();//显示登录成功的弹窗，简单写法
                        Intent intent = null;  //这个变量初始申明为空
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "用户名输入错误或密码不正确，请重新登录！", Toast.LENGTH_SHORT).show();//获取显示的内容
                    }
                }

            }
        });
        //注册按键按下之后，响应的事件
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现界面跳转，从登录界面跳转到注册界面
                Intent intent = null;  //这个变量初始申明为空
                intent = new Intent(LoginActivity.this, RegisterActivity.class);//跳转界面
                startActivity(intent);
            }
        });
    }
}