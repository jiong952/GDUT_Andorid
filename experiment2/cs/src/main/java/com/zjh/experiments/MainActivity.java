package com.zjh.experiments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RightFragment fragment_new;

    private Map<Integer,Bean> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);
        setData();
        switchData(map.get(1));
    }

    @Override
    public void onClick(View view) {
        textView1.setTextColor(this.getResources().getColor(R.color.dark_gray));
        textView2.setTextColor(this.getResources().getColor(R.color.dark_gray));
        textView3.setTextColor(this.getResources().getColor(R.color.dark_gray));
        textView4.setTextColor(this.getResources().getColor(R.color.dark_gray));
        switch (view.getId()){
            case R.id.textView1:
                textView1.setTextColor(this.getResources().getColor(R.color.black));
                switchData(map.get(1));
                break;
            case R.id.textView2:
                textView2.setTextColor(this.getResources().getColor(R.color.black));
                switchData(map.get(2));
                break;
            case R.id.textView3:
                textView3.setTextColor(this.getResources().getColor(R.color.black));
                switchData(map.get(3));
                break;
            case R.id.textView4:
                textView4.setTextColor(this.getResources().getColor(R.color.black));
                switchData(map.get(4));
                break;

        }
    }

    private void setData(){
        map=new HashMap<>();
        Bean bean1 = new Bean("计算机学院是广东工业大学重点建设学院之一，计算机本科专业已开办三十多年，涵盖计算机科学与技术、软件工程和网络空间安全三个一级学科。现有1个一级学科博士点、2个一级学科硕士点和2个工程硕士点，拥有完整的本-硕-博人才培养体系。",R.drawable.img1);
        Bean bean2 = new Bean("计算机学院现有专任教师111人，其中教授23人、副教授34人，享受国务院特殊津贴专家4人，科研师资队伍雄厚。多年来，学院重视科研团队建设，现有“信息物理融合系统团队”“无线大数据技术与应用团队”“高性能计算与数据科学团队”“数据挖掘与信息检索团队”“网络信息安全团队”“城市轨道交通网络控制芯片与系统创新团队”等一批年富力强的、高水平的科研团队，承担了一批国家和省级的重大专项项目，积极为地方经济发展服务，大力发展产学研合作，成效显著。",R.drawable.img2);
        Bean bean3 = new Bean("广东工业大学ACM-ICPC集训队（简称ACM集训队）创立自2002年，至今已有18年历史。我校ACM-ICPC集训队自成立以来已在ACM-ICPC上多次获奖，三次打入ACM-ICPC World Final，在非985211中鲜有学校获得这种荣誉，甚至超过了很多985211学校。我校ACM-ICPC集训队队员也是各大学院奖学金的常客。累计获得ACM亚洲赛级别金奖23枚，其中2016年获得6金5银、2017年6金6银、2018年5金6银、2019年3金5银的好成绩，总成绩超过了许多国内著名高校。",R.drawable.img3);
        Bean bean4 = new Bean("广东工业大学计算机学院学生会又名木棉菁英学生会，始终坚持“源于学生，服务学生”的工作宗旨，以积极向上的校园文化活动和贴心实用的校园服务引领广大青年。木棉菁英学生会设有团队建设、学风督导、就业服务和校园文化四大模块，积极开展形式多样的活动：通过网罗珠三角地区知名企业，打造IT行业求职宝典，举办知名企业校企交流会，为学生提供就业帮扶；精心组织各类文体活动，举办迎新晚会、毕业晚会、IT杯篮球赛、IT歌手大赛。",R.drawable.img4);
        map.put(1,bean1);
        map.put(2,bean2);
        map.put(3,bean3);
        map.put(4,bean4);
    }

    public void switchData(Bean bean) {
        TextView textView = this.findViewById(R.id.t_text);
        ImageView imageView = this.findViewById(R.id.i_image);
        textView.setText(bean.getTextName());
        imageView.setImageResource(bean.getImgId());
//        fragmentManager = getFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();//开启一个事务
//        //通过调用getInstance()方法实例化RightFragment
//        fragment_new = new RightFragment().getInstance(bean);
//        //调用replace()方法
//        fragmentTransaction.replace(R.id.right, fragment_new);
//        fragmentTransaction.commit();
    }
}