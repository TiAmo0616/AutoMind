package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.helloworld.yunji_task.CancelTask;
import com.example.helloworld.yunji_task.ReturnToChargingDock;
import com.example.helloworld.yunji_task.DoorControl;

public class YunjiTask extends AppCompatActivity {
    TextView tv_id;
    Button btn_cancel;
    Button btn_return_charge;
    Button btn_openDoor;
    Button btn_closeDoor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunji_task);

        tv_id = findViewById(R.id.robert_id);
        //获取intent
        Intent intent=getIntent();
        //获取数据并显示到text中
        tv_id.setText(intent.getStringExtra("id"));

        btn_cancel = findViewById(R.id.cancel);
        btn_return_charge = findViewById(R.id.return_charge);
        btn_openDoor = findViewById(R.id.openDoor);
        btn_closeDoor = findViewById(R.id.closeDoor);

        btn_cancel.setOnClickListener(this::onClick);
        btn_return_charge.setOnClickListener(this::onClick);
        btn_openDoor.setOnClickListener(this::onClick);
        btn_closeDoor.setOnClickListener(this::onClick);


    }
    public void onClick(View v){
        if (v.getId() == R.id.cancel) {
            startActivity(new Intent(getApplicationContext(), CancelTask.class));
        } else if (v.getId() == R.id.return_charge) {
            startActivity(new Intent(getApplicationContext(), ReturnToChargingDock.class));

        } else if (v.getId() == R.id.openDoor) {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));

        }else {
            startActivity(new Intent(getApplicationContext(), DoorControl.class));
        }
    }




}