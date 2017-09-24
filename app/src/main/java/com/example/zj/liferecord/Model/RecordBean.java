package com.example.zj.liferecord.Model;

import android.media.AudioTrack;

import java.util.Date;

/**
 * Created by zj on 2017/8/26.
 */

public class RecordBean {

    private Integer mId;

    private String mTitle;

    private String mContent;

    private Integer mNumber;

    private Date mCreateTime;

    public RecordBean(Integer id, String Title, String Content, Date CreateTime,Integer Number){
        mId = id;
        mTitle = Title;
        mContent = Content;
        mCreateTime = CreateTime;
        mNumber = Number;
    }

    public String GetTitle(){
        return mTitle;
    }

    public  String getContent(){
        return mContent;
    }

    public  Integer getmNumber(){
        return  mNumber;
    }

    public  void setmNumber(int number){
        this.mNumber = number;
    }

    public  Integer getId(){
        return  mId;
    }
}
