package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.dao.addDo;

public class add_company  extends AppCompatActivity {
    EditText company = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companymanager);

        company = findViewById(R.id.w_company1);
    }

    public void back(View view){
        startActivity(new Intent(getApplicationContext(), DataCountActivity.class));
    }
    public void add_one(View view) {
        String company1 = company.getText().toString();
        new Thread(){
            @Override
            public void run() {
                addDo a = new addDo();
                boolean flag = a.add_company(company1);
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
                startActivity(new Intent(getApplicationContext(), DataCountActivity.class));
            }
        }
    };

}
