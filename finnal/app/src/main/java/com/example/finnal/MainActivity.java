package com.example.finnal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.List;

public class MainActivity extends Activity {

    private Button Bhome,Breservation,Bcomment,Bmy,play_music;//首页、预约、讨论、我的 按钮
    private ViewHolder holder;
    private com.example.finnal.home home1;
    String name=null;
    private home home;
    final String[] musics={"Back To December","梦中的婚礼","安静","少女的心","剑仙","完美的邂逅"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewFlipper flipper = findViewById(R.id.flipper);
        flipper.startFlipping();
        ListView mLvHome=findViewById(R.id.homelist);//首页场地列表
        initView();

        home1 =new home();
        Newadptor newadptor =new Newadptor();
        mLvHome.setAdapter(newadptor);//ListView
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");

        mLvHome.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView,View view,int i,long l){
                Intent intent3=new Intent(MainActivity.this, home_detail.class);
                startActivity(intent3);
            }
        });

    }

    private void initView() {
    //按钮
        Bhome= findViewById(R.id.home);
        Breservation= findViewById(R.id.reservation);
        Bcomment= findViewById(R.id.comment);
        Bmy=findViewById(R.id.my);
        play_music=findViewById(R.id.music);
        Breservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this, room_activity.class);
                intent1.putExtra("username", name);
                startActivity(intent1);
            }
        });

        Bcomment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent2=new Intent(MainActivity.this, comment.class);
                intent2.putExtra("username", name);
                startActivity(intent2);
            }
        });

        Bmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MainActivity.this, my.class);
                intent3.putExtra("username", name);
                startActivity(intent3);
            }
        });

        play_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("选择一个背景音乐")
//                        .setIcon(R.drawable.music_collection)
                        .setItems(musics, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        // 启动服务播放背景音乐
                                        Intent intentMusic = new Intent(MainActivity.this, MyIntentService.class);
                                        String action_1 = MyIntentService.ACTION_MUSIC_1;
                                        // 设置action
                                        intentMusic.setAction(action_1);
                                        startService(intentMusic);
                                        break;
//                                    case 1:
//                                        // 启动服务播放背景音乐
//                                        intentMusic = new Intent(EditActivity.this, MyIntentService.class);
//                                        String action_2 = MyIntentService.ACTION_MUSIC_2;
//                                        // 设置action
//                                        intentMusic.setAction(action_2);
//                                        startService(intentMusic);
//                                        break;
//                                    case 2:
//                                        // 启动服务播放背景音乐
//                                        intentMusic = new Intent(EditActivity.this, MyIntentService.class);
//                                        String action_3 = MyIntentService.ACTION_MUSIC_3;
//                                        // 设置action
//                                        intentMusic.setAction(action_3);
//                                        startService(intentMusic);
//                                        break;
//                                    case 3:
//                                        // 启动服务播放背景音乐
//                                        intentMusic = new Intent(EditActivity.this, MyIntentService.class);
//                                        String action_4 = MyIntentService.ACTION_MUSIC_4;
//                                        // 设置action
//                                        intentMusic.setAction(action_4);
//                                        startService(intentMusic);
//                                        break;
//                                    case 4:
//                                        // 启动服务播放背景音乐
//                                        intentMusic = new Intent(EditActivity.this, MyIntentService.class);
//                                        String action_5 = MyIntentService.ACTION_MUSIC_5;
//                                        // 设置action
//                                        intentMusic.setAction(action_5);
//                                        startService(intentMusic);
//                                        break;
//                                    case 5:
//                                        // 启动服务播放背景音乐
//                                        intentMusic = new Intent(EditActivity.this, MyIntentService.class);
//                                        String action_6 = MyIntentService.ACTION_MUSIC_6;
//                                        // 设置action
//                                        intentMusic.setAction(action_6);
//                                        startService(intentMusic);
//                                        break;
                                }
                            }
                        }).create().show();
            }
        });
    }

    private class Newadptor extends BaseAdapter{
        @Override
        public int getCount() {
            return com.example.finnal.home.getNames().length;
        }

        @Override
        public Object getItem(int i) {
            return com.example.finnal.home.getNames()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View cview, ViewGroup viewGroup) {
            if(cview==null){
                cview=View.inflate(MainActivity.this,R.layout.home_item_layout,null);
                holder =new ViewHolder();
                holder.title=cview.findViewById(R.id.home_title);

                holder.img=cview.findViewById(R.id.home_img);

                cview.setTag(holder);
            }else{
                holder=(ViewHolder) cview.getTag();
            }

            holder.title.setText(com.example.finnal.home.getNames()[i]);

            holder.img.setBackgroundResource(com.example.finnal.home.getIcons()[i]);
            return cview;
        }
    }
        private class ViewHolder{
        private TextView title;

        private ImageView img;
    }
}