package com.example.zj.liferecord.DataProvide;

import android.content.Context;

import java.util.List;


/**
 * Created by zj on 2017/8/23.
 */

public interface IDataShowInfo {

    List<String> getData(Context context);

    void  insertData(Context context, String content);
}
