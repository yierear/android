package com.example.finnal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class my extends Activity {
    Button login;
    Button more;
    Button exit;
    TextView username2;
    String name=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        initView();
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");
        username2.setText(name);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(my.this,login.class);
                startActivity(intent1);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent2 = new Intent(my.this,my.class);
               intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

               my.this.startActivity(intent2);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  AlertDialog.Builder(my.this).setMessage("更多功能，尽情期待").show();
            }
        });
    }

    private void initView() {
        login=findViewById(R.id.login);
        more=findViewById(R.id.more);
        username2=findViewById(R.id.username2);
        exit = findViewById(R.id.exit);
    }

}