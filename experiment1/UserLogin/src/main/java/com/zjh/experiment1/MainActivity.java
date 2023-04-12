package com.zjh.experiment1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 找到账号和密码输入框
        EditText accountEditText = findViewById(R.id.edit_username);
        EditText passwordEditText = findViewById(R.id.edit_password);

        // 找到登录按钮，并添加点击事件监听器
        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入的账号和密码
                String account = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // 验证账号和密码
                if (account.equals("answer") && password.equals("123")) {
                    // 登录成功，显示成功信息并设置颜色为绿色
                    TextView errorTextView = findViewById(R.id.text_error);
                    errorTextView.setVisibility(View.VISIBLE);
                    errorTextView.setTextColor(Color.GREEN); // 设置字体颜色为绿色
                    errorTextView.setText("登录成功");
                } else {
                    // 登录失败，显示错误信息并设置颜色为红色
                    TextView errorTextView = findViewById(R.id.text_error);
                    errorTextView.setVisibility(View.VISIBLE);
                    errorTextView.setTextColor(Color.RED); // 设置字体颜色为红色
                    errorTextView.setText("用户名或密码错误，请重新输入");
                }
            }
        });
    }

}