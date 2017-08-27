package com.example.zj.liferecord.Utils;

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
}


