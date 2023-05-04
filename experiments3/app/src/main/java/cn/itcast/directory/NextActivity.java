package cn.itcast.directory;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    private List<Person> personList = new ArrayList<>();
    private MainActivity.MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        myHelper = new MainActivity.MyHelper(this);

        initPersons();// 初始化数据

        CollegeAdapter arrayAdapter = new CollegeAdapter(NextActivity.this, R.layout.person, personList);

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
    }


    private void initPersons() {
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("information", null, null, null, null,
                null, null);

        cursor.moveToFirst();
        personList.add(new Person("姓名：" + cursor.getString(1), "成绩：" + cursor.getString(2),cursor.getInt(3)));

        while (cursor.moveToNext())
            personList.add(new Person("姓名：" + cursor.getString(1), "成绩：" + cursor.getString(2),cursor.getInt(3)));

        cursor.close();
        db.close();
    }
}

class CollegeAdapter extends ArrayAdapter<Person> {

    private int resourceId;

    public CollegeAdapter(Context context, int textViewResourceId, List<Person> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person fruit = getItem(position); //获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        TextView fruitName = (TextView) view.findViewById(R.id.name);
        TextView collegeLocation = (TextView) view.findViewById(R.id.number);
        ImageView iconView = view.findViewById(R.id.icon);
        fruitName.setText(fruit.getName());
        collegeLocation.setText(fruit.getNumber());
        iconView.setImageDrawable(iconView.getResources().getDrawable(fruit.getpId()));
        return view;
    }
}

class Person {
    private String name;
    private String number;
    private int pId;

    public Person(String name, String number, int pId) {
        this.name = name;
        this.number = number;
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getpId() {
        return pId;
    }
}