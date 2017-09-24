package com.example.zj.liferecord.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.Toast;

import com.example.zj.liferecord.MyApplication;

/**
 * Created by zj on 2017/8/28.
 */

public class BaseActivity extends Activity {


    private static MyApplication myApplication = MyApplication.getMyApplication();
    private Activity mBaseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        mBaseActivity = this;

        addActivity();
    }


    public  void  addActivity(){
        myApplication.addActivity(mBaseActivity);
    }

    public  void removeActivity(){
        myApplication.removeActivity(mBaseActivity);
    }

    public  void removeAllActivity(){
        myApplication.removeAllActivity();

    }

    public void ShowToast(String content){
        Toast.makeText(mBaseActivity,content,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
