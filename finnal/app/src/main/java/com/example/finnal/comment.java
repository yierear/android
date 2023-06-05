package com.example.finnal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

//讨论页

public class comment extends Activity {
    private static final int REQUEST_CODE_CAMERA = 1;
    private ListView mListView;
    private Button mBtn_insert;
    private Button mBtn_send_picture;
    private EditText text;
    private ImageView comment_pic;
    private SimpleCursorAdapter mSimpleCursorAdapter ;
    private SQLiteDatabase mDbWriter;
    private DBHelper dbhelper;
    String name=null;
    int signal;
    private static final int ACTION_CHOOSE_IMAGE = 0x201;
    private Bitmap mBitmap;
    private byte[] pic_byte;
    private String mImagePath;
    private Uri imageUri;
    private String path=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        initView();
        //用户名
        Intent intent=this.getIntent();
        name=intent.getStringExtra("username");
        signal=intent.getIntExtra("flag",0);
        //存放评论
//        mSimpleCursorAdapter= new SimpleCursorAdapter(comment.this,R.layout.comment_item_layout,null,
//                new String[]{"userid","comment"},new int[]{R.id.id,R.id.commentList}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mSimpleCursorAdapter= new SimpleCursorAdapter(comment.this,R.layout.comment_item_layout,null,
                new String[]{"userid","comment","picture"},new int[]{R.id.id,R.id.commentList,R.id.sendPic}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mSimpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int i) {
                view.setVisibility(View.VISIBLE);
                if (view.getId() == R.id.sendPic) {
                    byte[] imageBytes = cursor.getBlob(i);
                    if (imageBytes != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes,0, imageBytes.length);
                        ImageView imageView = (ImageView) view;
                        imageView.setImageBitmap(bitmap);
                    } else {
                        // 如果没有图片则隐藏ImageView
                         view.setVisibility(View.GONE);
                    }
                    return true;
                }
                return false;

            }

        });
        mBtn_send_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent_pic=new Intent(comment.this,send_picture.class);
  //              startActivityForResult(intent_pic,0);
//
                setDialog();
                switch (view.getId()) {
                    
                    case R.id.btn_choose_img:
                        //选择照片按钮
                        Toast.makeText(comment.this, "请选择照片", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_open_camera:
                        //拍照按钮
                        Toast.makeText(comment.this, "即将打开相机", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        mBtn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                text.setText("");
                comment_pic.setWillNotDraw(true);
            }
        });

        dbhelper=new DBHelper(this);
        mDbWriter = dbhelper.getWritableDatabase();

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

    private void setDialog() {
        Dialog mCameraDialog = new Dialog(this, R.style.BottomDialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(
                R.layout.activity_send_picture, null);
        //初始化视图
        root.findViewById(R.id.btn_choose_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_pic = new Intent(Intent.ACTION_PICK);
                intent_pic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent_pic, ACTION_CHOOSE_IMAGE);
                mCameraDialog.dismiss();
            }
        });
        root.findViewById(R.id.btn_open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageName = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
//              File outputImage=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/data/com.example.woundapplication/"+imageName+".jpg");

                File outputImage = new File(getExternalCacheDir(), imageName+".jpg");

                Objects.requireNonNull(outputImage.getParentFile()).mkdirs();
//              Log.e("", outputImage.getAbsolutePath());
                /*
                创建一个File文件对象，用于存放摄像头拍下的图片，
                把它存放在应用关联缓存目录下，调用getExternalCacheDir()可以得到这个目录，为什么要
                用关联缓存目录呢？由于android6.0开始，读写sd卡列为了危险权限，使用的时候必须要有权限，
                应用关联目录则可以跳过这一步
                 */
                try//判断图片是否存在，存在则删除在创建，不存在则直接创建
                {
                    if(outputImage.exists())
                    {
                        outputImage.delete();
                    }
                    boolean a = outputImage.createNewFile();
                    Log.e("createNewFile", String.valueOf(a));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24)
                    //判断安卓的版本是否高于7.0，高于则调用高于的方法，低于则调用低于的方法
                    //把文件转换成Uri对象
                    /*
                    因为android7.0以后直接使用本地真实路径是不安全的，会抛出异常。
                    FileProvider是一种特殊的内容提供器，可以对数据进行保护
                     */
                {
                    imageUri= FileProvider.getUriForFile(comment.this,
                            "com.buildmaterialapplication.fileprovider",outputImage);
                    //对应Mainfest中的provider
//                  imageUri=Uri.fromFile(outputImage);
                    path=imageUri.getPath();
                    Log.e(">7:",path);
                }
                else {
                    imageUri= Uri.fromFile(outputImage);
                    path=imageUri.getPath();

                    Log.e("<7:",imageUri.getPath());

                }

                Intent intent0=new Intent("android.media.action.IMAGE_CAPTURE");
                intent0.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent0,REQUEST_CODE_CAMERA);
                mCameraDialog.dismiss();
            }
        });
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.x = 0; // 新位置X坐标
        lp.y = 0; // 新位置Y坐标
        lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        root.measure(0, 0);
        lp.height = root.getMeasuredHeight();

        lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }


    private void initView() {
        mListView =  findViewById(R.id.myListview);
        mBtn_insert =  findViewById(R.id.insert);
        mBtn_send_picture=findViewById(R.id.send_picture);
        text =  findViewById(R.id.write_comment);
        comment_pic=findViewById(R.id.comment_pic);
    }

    public void refreshListview() {
        Cursor mCursor = mDbWriter.query("comment", null, null, null, null, null, null);
        mSimpleCursorAdapter.changeCursor(mCursor);
    }
    public void insertData() {
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("userid", name);
        mContentValues.put("comment", text.getText().toString().trim());
        mContentValues.put("picture",pic_byte);
        mDbWriter.insert("comment", null, mContentValues);
        pic_byte=null;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case ACTION_CHOOSE_IMAGE:
                if (data == null || data.getData() == null) {
                    System.out.println("----错误");
                    return;
                }
                try {
                    System.out.println(data.getData()+"----");
                    mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("----"+mBitmap);
                if (mBitmap == null) {
                    System.out.println("----mBitmap为null");
                    return;
                }
                break;
            case REQUEST_CODE_CAMERA:
                if (resultCode == RESULT_OK) {
//                    mBitmap = BitmapFactory.decodeFile(mImagePath);
                    try {
                        mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    mImagePath=imageUri.getPath().toString();
                    Log.e("", imageUri.getAuthority());

                    Log.e("mImagePath",mImagePath);
                    @SuppressLint("SdCardPath") String fileName = mImagePath;

                }
                break;
        }

        // 转换为BitmapDrawable对象
        BitmapDrawable bmpDraw=new BitmapDrawable(mBitmap);
        // 显示位图
        comment_pic.setWillNotDraw(false);
        comment_pic.setImageDrawable(bmpDraw);
        //转换成可用来存储的byte[]类型
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        pic_byte=stream.toByteArray();
    }
}
