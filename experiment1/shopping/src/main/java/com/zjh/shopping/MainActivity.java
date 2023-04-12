package com.zjh.shopping;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends Activity {
    private ListView mListView;
    //商品名称与价格数据集合
    private String[] titles = {"必胜客", "麦当劳", "1点点", "古茗", "茶救星球", "塔斯汀","萨莉亚","茶百道"};
    private String[] prices = {"38元", "36元", "14元", "11元", "13元",
            "14元", "50元", "14元"};
    //图片数据集合
    private int[] icons = {R.drawable.bi, R.drawable.maidanglao, R.drawable.diandian,
            R.drawable.guming, R.drawable.chajiu, R.drawable.ta,R.drawable.saliya,R.drawable.chabaidao};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.lv); //初始化ListView控件
        MyBaseAdapter mAdapter = new MyBaseAdapter(); //创建一个Adapter的实例
        mListView.setAdapter(mAdapter);                  //设置Adapter
    }

    class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {   //获取条目的总数
            return titles.length; //返回条目的总数
        }

        @Override
        public Object getItem(int position) {
            return titles[position]; //返回条目的数据对象
        }

        @Override
        public long getItemId(int position) {
            return position; //返回条目的Id
        }
        //获取条目的视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                //将list_item.xml文件找出来并转换成View对象
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                //找到list_item.xml中创建的TextView
                holder = new ViewHolder();
                holder.title =  convertView.findViewById(R.id.title);
                holder.price = convertView.findViewById(R.id.price);
                holder.iv = convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(titles[position]);
            holder.price.setText(prices[position]);
            holder.iv.setBackgroundResource(icons[position]);
            return convertView;
        }
        class ViewHolder {
            TextView title, price;
            ImageView iv;
        }
    }
}
