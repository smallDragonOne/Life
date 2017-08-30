package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Views.NavInfoManager;

/**
 * Created by zj on 2017/8/28.
 */

public class StartActicity extends BaseActivity {

    private boolean isStart = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        setContentView(R.layout.start_layout);

        new NavInfoManager(this, NavInfoManager.NavType.noAll);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isStart){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            isStart = false;
        }

    }
}
