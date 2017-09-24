package com.example.zj.liferecord.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zj on 2017/8/24.
 */

public class OperateDataBase implements IOperateDataBase {


    private  Context mContext;

    private  SqlLiteHelper mHelper;

    public OperateDataBase(Context context){
        if (mContext == null){
            mContext = context;
        }
    }

    @Override
    public SQLiteDatabase getWriteDataBase() {

        return GetSqlLite().getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadDataBase() {

        return GetSqlLite().getReadableDatabase();
    }

    private  SqlLiteHelper GetSqlLite(){
        if (mHelper == null){
            mHelper = new SqlLiteHelper(mContext,SqlLiteHelper.DataBaseName,null,2);
        }

        return  mHelper;
    }
}
