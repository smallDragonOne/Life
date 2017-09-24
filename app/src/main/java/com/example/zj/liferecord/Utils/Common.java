package com.example.zj.liferecord.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.zj.liferecord.DataBase.IOperateDataBase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zj on 2017/8/26.
 */

public class Common {

    public  static Date getNowTime(){
        Calendar ca = Calendar.getInstance();
        return ca.getTime();
    }

    public static int getCurrentMaxNumber(IOperateDataBase operateDataBase) {
        SQLiteDatabase database = operateDataBase.getReadDataBase();

        Cursor cursor = database.rawQuery("select Max(number) as number from Record",null);
        if (cursor.moveToFirst()){
            int number = cursor.getInt(cursor.getColumnIndex("number"));
            database.close();
            return  number;
        }
        database.close();
        return  0;
        //Cursor cursor = database.query("Record",new String[]{"id","Title","Content","number"},null,null,null,null,null);
    }
}


