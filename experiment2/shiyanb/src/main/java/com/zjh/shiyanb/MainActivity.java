package com.zjh.shiyanb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        btn.setOnClickListener((view) -> {
            Intent i = new Intent(this, SubActivity.class);
            startActivityForResult(i, 1);
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView tv = findViewById(R.id.textView);
        String user = data.getStringExtra("user");
        String password = data.getStringExtra("password");

        tv.setText("注册信息：\n用户名：" + user + "\n密码：" + password);
    }
}