package com.example.finnal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    private Button home,reservation,discussion,my;//首页、预约、讨论、我的 按钮
    private ViewHolder holder;
    private com.example.finnal.home home1;
    String name=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mLvTuijian=findViewById(R.id.tuijian);
        initView();
        home1 =new home();
        Newadptor newadptor =new Newadptor();
        mLvTuijian.setAdapter(newadptor);
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");
    }

    private void initView() {
        home= findViewById(R.id.home);
        reservation= findViewById(R.id.reservation);
        discussion= findViewById(R.id.discussion);
        my=findViewById(R.id.my);

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(MainActivity.this,shucai_activity.class);
                intent1.putExtra("username", name);
                startActivity(intent1);
            }
        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2=new Intent(MainActivity.this,taolun.class);
                intent2.putExtra("username", name);
                startActivity(intent2);
            }
        });

        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(MainActivity.this, my.class);
                intent3.putExtra("username", name);
                startActivity(intent3);
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
                cview=View.inflate(MainActivity.this,R.layout.recom_item_layout,null);
                holder =new ViewHolder();
                holder.title=cview.findViewById(R.id.recommendation_title);
                holder.news=cview.findViewById(R.id.recommendation_text);
                holder.img=cview.findViewById(R.id.recommendation_img);

                cview.setTag(holder);
            }else{
                holder=(ViewHolder) cview.getTag();
            }

            holder.title.setText(com.example.finnal.home.getNames()[i]);
            holder.news.setText(com.example.finnal.home.getNews()[i]);
            holder.img.setBackgroundResource(com.example.finnal.home.getIcons()[i]);
            return cview;
        }
    }
        private class ViewHolder{
        private TextView title;
        private TextView news;
        private ImageView img;
    }
}