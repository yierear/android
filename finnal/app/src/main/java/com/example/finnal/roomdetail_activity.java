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

public class roomdetail_activity extends Activity {
    private roomdetail_activity.ViewHolder holder;
    private home home;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Intent intent=getIntent();
        name=intent.getStringExtra("username");
        ListView mLvroom=findViewById(R.id.reservation);

        home =new home();

        roomdetail_activity.reserveadaptor reserveadaptor=new roomdetail_activity.reserveadaptor();
        mLvroom.setAdapter(reserveadaptor);

        mLvroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent3=new Intent(roomdetail_activity.this, reserve.class);
                intent3.putExtra("username",name);
                startActivity(intent3);
            }
        });

    }

    //预约适配器
    private class reserveadaptor extends BaseAdapter {
        @Override
        public int getCount() {
            return home.getNames().length;
        }

        @Override
        public Object getItem(int i) {
            return home.getNames()[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View cview, ViewGroup viewGroup) {
            if(cview==null){
                cview=View.inflate(roomdetail_activity.this,R.layout.home_item_layout,null);
                holder =new roomdetail_activity.ViewHolder();
                holder.title=cview.findViewById(R.id.home_title);
                holder.news=cview.findViewById(R.id.recommendation_text);
                holder.img=cview.findViewById(R.id.home_img);

                cview.setTag(holder);
            }else{
                holder=(roomdetail_activity.ViewHolder) cview.getTag();
            }

            holder.title.setText(home.getNames()[i]);
            //holder.news.setText(home.getNews()[i]);
            holder.img.setBackgroundResource(home.getIcons()[i]);
            return cview;
        }
    }

    private class ViewHolder{
        private TextView title;
        private TextView news;
        private ImageView img;
    }
}
