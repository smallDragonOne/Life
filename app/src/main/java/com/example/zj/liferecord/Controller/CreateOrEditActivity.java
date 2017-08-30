package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zj.liferecord.DataBase.IOperateDataBase;
import com.example.zj.liferecord.DataBase.OperateDataBase;
import com.example.zj.liferecord.DataBase.SqlLiteHelper;
import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Utils.Common;
import com.example.zj.liferecord.Views.NavInfoManager;

import java.util.Date;

/**
 * Created by zj on 2017/8/26.
 */

public class CreateOrEditActivity extends BaseActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void init(){
        setContentView(R.layout.record_editorcreate);
        mEditText = (EditText)findViewById(R.id.inputText);

        final NavInfoManager manager = new NavInfoManager(this, NavInfoManager.NavType.allNav);
        manager.SetTitle("lifeRecord");

        manager.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                if (!content.isEmpty()){
                    IOperateDataBase db = new OperateDataBase(CreateOrEditActivity.this);
                    SQLiteDatabase databse = db.getWriteDataBase();
                    databse.execSQL("insert into Record(Title,Content,CreateTime) values(?,?,?)",new Object[]{"",content,new Date()});
                    databse.close();
                    CreateOrEditActivity.this.finish();
                    Toast.makeText(CreateOrEditActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CreateOrEditActivity.this,"保存失败",Toast.LENGTH_SHORT).show();
                }

            }
        });

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        if (content != null){
            mEditText.setText(content);
        }

    }


}
