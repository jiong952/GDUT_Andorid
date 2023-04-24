package com.zjh.shiyanb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

         Button btn = findViewById(R.id.btn_regist);
         EditText number = findViewById(R.id.edtTxt_number);
         EditText password = findViewById(R.id.edtTxt_nickName);

         btn.setOnClickListener((view) -> {
             Intent i = new Intent();
             i.putExtra("user", number.getText().toString());
             i.putExtra("password", password.getText().toString());
             setResult(1, i);
             finish();
         });
    }
}