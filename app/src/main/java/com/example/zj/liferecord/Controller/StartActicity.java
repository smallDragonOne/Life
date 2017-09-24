package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.Toast;

import com.example.zj.liferecord.R;
import com.example.zj.liferecord.Views.NavInfoManager;

/**
 * Created by zj on 2017/8/28.
 */

public class StartActicity extends BaseActivity {

    private boolean isStart = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        new NavInfoManager(this, NavInfoManager.NavType.noAll);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isStart){
                    Intent intent = new Intent(StartActicity.this,MainActivity.class);
                    startActivity(intent);
                    isStart = false;
                }
            }
        },1500);

    }
}
