package com.example.zj.liferecord.Model;

import android.media.AudioTrack;

import java.util.Date;

/**
 * Created by zj on 2017/8/26.
 */

public class RecordBean {

    private Integer id;

    private String mTitle;

    private String mContent;

    private Date mCreateTime;

    public RecordBean(Integer id, String Title, String Content, Date CreateTime){
        id = id;
        mTitle = Title;
        mContent = Content;
        mCreateTime = CreateTime;
    }

    public String GetTitle(){
        return mTitle;
    }

    public  String getContent(){
        return mContent;
    }
}
