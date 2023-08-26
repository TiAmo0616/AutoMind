package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.datacount.DataCountActivity;
import com.example.myapp.map.maplist;

public class ShouyeActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye);
    }
    public void datacount(View view){
        startActivity(new Intent(getApplicationContext(), DataCountActivity.class));
    }
    public void mapcount(View view){
        startActivity(new Intent(getApplicationContext(), maplist.class));
    }
    public void taskstatus(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
    public void person(View view){
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

}
