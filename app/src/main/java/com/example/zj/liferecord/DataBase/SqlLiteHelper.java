package com.example.zj.liferecord.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by zj on 2017/8/24.
 */

public class SqlLiteHelper extends SQLiteOpenHelper {

    private  Context mContext;

    public   final  static String DataBaseName = "ZjDatabase";

    public SqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseTableManager.Record);
        //Toast.makeText(mContext,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*
        db.execSQL("drop table if exists" + DataBaseTableManager.Record);
        onCreate(db);*/
        switch (oldVersion){
            case 1:
                db.execSQL("alter table Record add number int");
                break;
            default:
                break;
        }
    }
}
