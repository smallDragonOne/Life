package com.example.zj.liferecord;

import android.app.Activity;
import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zj on 2017/8/28.
 */

public class MyApplication extends Application {

    public static MyApplication myApplication = null;
    private List<Activity> activities;
    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;
        if (activities == null){
            activities = new ArrayList<>();
        }
    }


    public void  addActivity(Activity activity){
        activities.add(activity);
    }

    public  void  removeActivity(Activity activity){
        if (activities.contains(activity)){
            activities.remove(activity);
            activity.finish();
        }
    }

    public  void  removeAllActivity(){
        for(Activity activity: activities){
            activity.finish();
        }
    }

    public  long  getActivityCount(){
        return activities.size();
    }

    public  static  MyApplication getMyApplication(){
        return  myApplication;
    }
}
