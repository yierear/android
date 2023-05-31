package com.example.finnal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

//讨论页

public class comment extends Activity {
    private ListView mListView;
    private Button mBtn_insert;
    private EditText text;
    private SimpleCursorAdapter mSimpleCursorAdapter;
    private SQLiteDatabase mDbWriter;
    private DBHelper dbhelper;
    String name=null;
    int signal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();

        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");
        signal=intent.getIntExtra("flag",0);
        mBtn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                text.setText("");
            }
        });

        dbhelper=new DBHelper(this);
        mDbWriter = dbhelper.getWritableDatabase();

        mSimpleCursorAdapter =new SimpleCursorAdapter(comment.this,R.layout.taolun_item_layout,null,
                new String[]{"userid","comment"},new int[]{R.id.id,R.id.pinglunlist}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(mSimpleCursorAdapter);
        refreshListview();
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                new AlertDialog.Builder(comment.this).setTitle("提示").setMessage("是否删除该评论")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteData(position);
                    }
                }).setNegativeButton("取消",null).show();
                return true;
            }
        });


    }

    private void initView() {
        mListView =  findViewById(R.id.myListview);
        mBtn_insert =  findViewById(R.id.insert);
        text =  findViewById(R.id.xiepinglun);
    }

    public void refreshListview() {
        Cursor mCursor = mDbWriter.query("comment", null, null, null, null, null, null);
        mSimpleCursorAdapter.changeCursor(mCursor);
    }
    public void insertData() {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("userid", name);
        mContentValues.put("comment", text.getText().toString().trim());
        mDbWriter.insert("comment", null, mContentValues);
        refreshListview();
    }
    public void deleteData(int position) {
        Cursor mCursor = mSimpleCursorAdapter.getCursor();
        mCursor.moveToPosition(position);
        @SuppressLint("Range") int itemId = mCursor.getInt(mCursor.getColumnIndex("_id"));
        @SuppressLint("Range") String username = mCursor.getString(mCursor.getColumnIndex("userid"));
        if(username.equals(name)){
            mDbWriter.delete("comment", "_id=?", new String[]{itemId + ""});
            refreshListview();
        }else if(username.length()==0){
            Toast.makeText(comment.this,"您无权删除游客信息！！", Toast.LENGTH_SHORT).show();
        }
        else{
            new AlertDialog.Builder(comment.this).setTitle("提示").setMessage("您无权限删除该信息！").show();
        }
    }
}