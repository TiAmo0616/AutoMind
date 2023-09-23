package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapp.yunji_task.CancelTask;
import com.example.myapp.yunji_task.DoorControl;
import com.example.myapp.yunji_task.ReturnToChargingDock;

public class LiehuTask extends AppCompatActivity {
    TextView tv_id;
    Button btn_voice;
    Button btn_tts;
    Button btn_sleep;
    Button btn_wakeup;
    Button btn_return_charge;
    Button btn_leave_charge;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liehu_task);
        tv_id = findViewById(R.id.robert_id);
        //获取intent
        Intent intent=getIntent();
        //获取数据并显示到text中
        tv_id.setText(intent.getStringExtra("id"));

        ImageButton btnImage = findViewById(R.id.btnImage);
        btnImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("我长按了图片按钮。");
                //语音识别
                return false;
            }
        });

    }

    public void onClick(View v){
        if (v.getId() == R.id.voice) {
            startActivity(new Intent(getApplicationContext(), CancelTask.class));
        } else if (v.getId() == R.id.TTS) {
            startActivity(new Intent(getApplicationContext(), ReturnToChargingDock.class));
        } else if (v.getId() == R.id.sleep) {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));

        }else if (v.getId() == R.id.wakeup) {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));

        }else if (v.getId() == R.id.returncharge) {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));
        }else {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));
        }
    }
}
