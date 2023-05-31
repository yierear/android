package com.example.finnal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AlertDialog.Builder(AlarmActivity.this)
                .setTitle("提示")
                .setMessage("您预约的图书馆时间到了")
                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int which) {
                        AlarmActivity.this.finish();
                    }
                }).create().show();
    }
}