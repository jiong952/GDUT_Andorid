package com.zjh.experiments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class RightFragment extends Fragment {
    public RightFragment() {
    }
    public RightFragment getInstance(Bean bean) {
        RightFragment rightFragment = new RightFragment();
        //通过Bundle对象传递数据可以保证在设备横竖屏切换时传递的数据不丢失
        Bundle bundle = new Bundle();
        //将需要传递的字符串以键值对的形式传入bundle对象
        bundle.putString("textName", bean.getTextName());
        bundle.putInt("imgId",bean.getImgId());
        rightFragment.setArguments(bundle);
        return rightFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_layout, container, false);
        TextView textView = view.findViewById(R.id.t_text);
        ImageView imageView = view.findViewById(R.id.i_image);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            textView.setText(bundle.getString("textName"));
            imageView.setImageResource(bundle.getInt("imgId"));

        }
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //在这里执行一些操作，以便在替换 `RightFragment` 时能够正确地显示
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

