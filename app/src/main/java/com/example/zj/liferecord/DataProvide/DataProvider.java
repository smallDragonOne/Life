package com.example.zj.liferecord.DataProvide;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zj on 2017/8/23.
 */

public class DataProvider implements IDataShowInfo {

    private  String StorageKey = "RECORD";

    private  String Content = "CONTENT";

    public  DataProvider() {

    }

    private String getPreferencesData(Context context){
        SharedPreferences setting = context.getSharedPreferences(StorageKey,Context.MODE_PRIVATE);
        String content = setting.getString(Content,"");
        return  content;
    }

    private void insertPreferencesData(Context context,String content){
        SharedPreferences setting = context.getSharedPreferences(StorageKey,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(Content,content);
        editor.commit();
    }


    public List<String> getData(Context context) {
        String str = getPreferencesData(context);
        String[] arr = str.split(",");
        List list = Arrays.asList(arr);
        return list;
    }

    public void  insertData(Context context, String content){
        insertPreferencesData(context,content);
    }
}
