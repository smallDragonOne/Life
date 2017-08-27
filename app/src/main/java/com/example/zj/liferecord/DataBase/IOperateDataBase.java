package com.example.zj.liferecord.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zj on 2017/8/24.
 */

public interface IOperateDataBase {

    SQLiteDatabase getWriteDataBase();

    SQLiteDatabase getReadDataBase();
}
