package cn.itcast.musicplayer;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private static SeekBar sb;
    private static TextView tv_progress, tv_total;
    private ObjectAnimator animator;
    private Button button;
    private ImageView iv_music;
    private MusicService.MusicControl musicControl;
    private TextView title;
    MyServiceConn conn;
    Intent intent;
    private boolean isUnbind = false;//记录服务是否被解绑
    private boolean isPlay = false; //是否在播放
    private int index = 0; //播放列表
    public Integer[] musics_Id = new Integer[]{
            R.raw.daoshu,
            R.raw.gaobaiqiqiu,
            R.raw.music,
            R.raw.juhao,
            R.raw.qingtian,
    };
    public Integer[] imgs_Id = new Integer[]{
            R.drawable.daoshu,
            R.drawable.gaobai,
            R.drawable.timian,
            R.drawable.juhao,
            R.drawable.qingtian
    };
    public String[] musics_Name = new String[]{"倒数","告白气球","体面","句号","晴天"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void init() {
        tv_progress = findViewById(R.id.tv_progress);
        tv_total = findViewById(R.id.tv_total);
        title = findViewById(R.id.tv_music_title);
        sb = findViewById(R.id.sb);
        findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_continue_play).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);
        intent = new Intent(this, MusicService.class);//创建意图对象
        conn = new MyServiceConn();                       //创建服务连接对象
        bindService(intent, conn, BIND_AUTO_CREATE);  //绑定服务
        Log.i("serviceee","绑定服务");
        //为滑动条添加事件监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean
                    fromUser) {                          //滑动条进度改变时，会调用此方法
                if (progress == seekBar.getMax()) { //当滑动条滑到末端时，结束动画
                    animator.pause();                   //停止播放动画
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//滑动条开始滑动时调用
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //滑动条停止滑动时调用
                //根据拖动的进度改变音乐播放进度
                int progress = seekBar.getProgress();//获取seekBar的进度
                musicControl.seekTo(progress);         //改变播放进度
            }
        });
        iv_music = findViewById(R.id.iv_music);
        animator = ObjectAnimator.ofFloat(iv_music, "rotation", 0f, 360.0f);
        animator.setDuration(10000);  //动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(-1);  //-1表示设置动画无限循环
        iv_music.setImageDrawable(getResources().getDrawable(imgs_Id[index]));
        TextView title = findViewById(R.id.tv_music_title);
        title.setText(musics_Name[index]);
        Spinner spinner = findViewById(R.id.spinner1);
        //为选择列表框添加OnItemSelecedListener事件监听器
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int pos,
                                       long id) {
                if(pos == 5) return;
                index = pos;
                iv_music.setImageDrawable(getResources().getDrawable(imgs_Id[index]));
                title.setText(musics_Name[index]);
                musicControl.init(musics_Id[index]);
                animator.start();               //播放动画
                musicControl.continuePlay();        //播放音乐
                isPlay = true;
                button = findViewById(R.id.btn_pause);
                button.setText("点击暂停");
            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        spinner.setSelection(5);
    }
    public static Handler handler = new Handler() {//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@SuppressLint("HandlerLeak") Message msg) {
            Bundle bundle = msg.getData(); //获取从子线程发送过来的音乐播放进度
            int duration = bundle.getInt("duration");                  //歌曲的总时长
            int currentPostition = bundle.getInt("currentPosition");//歌曲当前进度
            sb.setMax(duration);                //设置SeekBar的最大值为歌曲总时长
            sb.setProgress(currentPostition);//设置SeekBar当前的进度位置
            //歌曲的总时长
            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;
            String strMinute = null;
            String strSecond = null;
            if (minute < 10) {              //如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute; //在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {             //如果歌曲的时间中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            tv_total.setText(strMinute + ":" + strSecond);
            //歌曲当前播放时长
            minute = currentPostition / 1000 / 60;
            second = currentPostition / 1000 % 60;
            if (minute < 10) {             //如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {               //如果歌曲的时间中的秒钟小于10
                strSecond = "0" + second;  //在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            tv_progress.setText(strMinute + ":" + strSecond);
        }
    };
    class MyServiceConn implements ServiceConnection { //用于实现连接服务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MusicService.MusicControl) service;
            musicControl.init(musics_Id[index]);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
    private void unbind(boolean isUnbind){
        if(!isUnbind){                  //判断服务是否被解绑
            musicControl.pausePlay();//暂停播放音乐
            unbindService(conn);      //解绑服务
            Log.i("serviceee","解绑服务");
            stopService(intent);      //停止服务
            Log.i("serviceee","停止服务");
        }
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:                //上一首
                index = index - 1;
                if(index < 0) index = 4;
                iv_music.setImageDrawable(getResources().getDrawable(imgs_Id[index]));
                title = findViewById(R.id.tv_music_title);
                title.setText(musics_Name[index]);
                musicControl.init(musics_Id[index]);
                animator.start();               //播放动画
                musicControl.continuePlay();        //播放音乐
                isPlay = true;
                Button button = findViewById(R.id.btn_pause);
                button.setText("点击暂停");
                break;
            case R.id.btn_pause:               //播放和暂停按钮点击事件
                if(isPlay){
                    musicControl.pausePlay();     //暂停播放音乐
                    animator.pause();              //暂停播放动画
                    isPlay = false;
                    button = findViewById(R.id.btn_pause);
                    button.setText("点击播放");
                }else{
                    musicControl.continuePlay();        //播放音乐
                    if(sb.getProgress() == 0){
                        animator.start();               //播放动画
                    }else {
                        animator.resume();               //继续动画
                    }
                    isPlay = true;
                    button = findViewById(R.id.btn_pause);
                    button.setText("点击暂停");
                }
                break;
            case R.id.btn_continue_play:     //下一首
                index = (index + 1) %  5;
                iv_music.setImageDrawable(getResources().getDrawable(imgs_Id[index]));
                title = findViewById(R.id.tv_music_title);
                title.setText(musics_Name[index]);
                musicControl.init(musics_Id[index]);
                animator.start();               //播放动画
                musicControl.continuePlay();        //播放音乐
                isPlay = true;
                button = findViewById(R.id.btn_pause);
                button.setText("点击暂停");
                break;
            case R.id.btn_exit:                //退出按钮点击事件
                unbind(isUnbind);               //解绑服务绑定
                isUnbind = true;                //完成解绑服务
                finish();                         //关闭音乐播放界面
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind(isUnbind); //解绑服务
    }
}
