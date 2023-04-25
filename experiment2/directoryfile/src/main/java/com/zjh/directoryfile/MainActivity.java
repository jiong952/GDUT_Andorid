package com.zjh.directoryfile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private EditText mEtName;
    private EditText mEtPhone;
    private TextView mTvShow;
    private Button mBtnAdd;
    private Button mBtnQuery;
    private Button mBtnUpdate;
    private Button mBtnDelete;

    private Map<String,String> stringMap = new HashMap<>(); //全局记忆化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvShow = findViewById(R.id.tv_show);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnQuery = findViewById(R.id.btn_query);
        mBtnUpdate = findViewById(R.id.btn_update);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String name, phone;
        switch (v.getId()) {
            case R.id.btn_add: //添加数据
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();
                stringMap.put(name,phone);
                SaveFile.saveUserInfo(this, stringMap);
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query: //查询数据
                if (stringMap.size() == 0) {
                    mTvShow.setText("");
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    mTvShow.setText("");
                }
                for(String key : stringMap.keySet()){
                    mTvShow.append("\n" + "Name :  " + key +
                            "  ；Tel :  " + stringMap.get(key));
                }
                break;
            case R.id.btn_update: //修改数据
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();
                stringMap.put(name,phone);
                SaveFile.saveUserInfo(this, stringMap);
                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete: //清空数据
                stringMap.clear();
                SaveFile.saveUserInfo(this, stringMap);
                Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
