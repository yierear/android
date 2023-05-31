package com.example.finnal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

//推荐页
public class home_detail extends Activity {
    private home_detail.ViewHolder holder;
    private com.example.finnal.home home1;
    String name=null;
    private home home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homedetail);
        ListView mLvHome=findViewById(R.id.homelist);

        home1 =new home();
        home_detail.Newadptor newadptor =new home_detail.Newadptor();
        mLvHome.setAdapter(newadptor);

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
                cview=View.inflate(home_detail.this,R.layout.homedetail_item_layout,null);
                holder =new home_detail.ViewHolder();
                holder.title=cview.findViewById(R.id.home_title);
                holder.news=cview.findViewById(R.id.recommendation_text);
                holder.img=cview.findViewById(R.id.home_img);

                cview.setTag(holder);
            }else{
                holder=(home_detail.ViewHolder) cview.getTag();
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