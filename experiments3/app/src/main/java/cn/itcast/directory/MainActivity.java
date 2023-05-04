package cn.itcast.directory;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    MyHelper myHelper;
    private EditText mEtName;
    private EditText mEtPhone;
    private TextView mTvShow;
    private ImageView iconView;

    public Integer[] images = new Integer[]{
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_3,
            R.drawable.img_4,
            R.drawable.img_5,
            R.drawable.img_6,
            R.drawable.img_7,
            R.drawable.img_8
    };
    public int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHelper = new MyHelper(this);
        init();//初始化控件
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void init() {
        mEtName = findViewById(R.id.et_name);
        mEtPhone = findViewById(R.id.et_phone);
        mTvShow = findViewById(R.id.tv_show);
        iconView = findViewById(R.id.iconView);
        iconView.setImageDrawable(getResources().getDrawable(images[index]));
        Button mBtnX = findViewById(R.id.btn_xia);
        Button mBtnAdd = findViewById(R.id.btn_add);
        Button mBtnQuery = findViewById(R.id.btn_query);
        mBtnX.setOnClickListener(this);
        mBtnAdd.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        String name;
        String phone;
        int pId = images[index];
        SQLiteDatabase db;
        ContentValues values;
        switch (v.getId()) {
            case R.id.btn_xia: //下一张
                index = (index + 1) % 8;
                iconView.setImageDrawable(getResources().getDrawable(images[index]));
                break;

            case R.id.btn_add: //添加数据
                name = mEtName.getText().toString();
                phone = mEtPhone.getText().toString();
                // 添加图片
                if (name.equals("") || phone.equals(""))
                {
                    Toast.makeText(this, "信息不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }

                db = myHelper.getWritableDatabase();//获取可读写SQLiteDatabse对象
                values = new ContentValues();       // 创建ContentValues对象
                values.put("name", name);           // 将数据添加到ContentValues对象
                values.put("phone", phone);
                values.put("pId",pId);
                db.insert("information", null, values);
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.btn_query: //查询数据
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information", null, null, null, null,
                        null, null);
                if (cursor.getCount() == 0) {
                    mTvShow.setText("");
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                    break;
                }
                Intent intent = new Intent(this, NextActivity.class);
                startActivity(intent);
                break;
        }
    }

    static class MyHelper extends SQLiteOpenHelper {
        public MyHelper(Context context) {
            super(context, "itcast.db", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20),phone VARCHAR(20),pId INTEGER  NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}
