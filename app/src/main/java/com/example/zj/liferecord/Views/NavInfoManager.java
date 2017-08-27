package com.example.zj.liferecord.Views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.zj.liferecord.R;

/**
 * Created by zj on 2017/8/26.
 */

public class NavInfoManager implements View.OnClickListener {

    private TextView mTx_back;
    private TextView mTx_right;
    private TextView mTitle;

    private  Activity mActivity;
    public  enum NavType{
        allNav,
        justBack,
        justRight,
        NoTitle,

    }

    public NavInfoManager(Activity activity, NavType type){
        mTx_back = (TextView)activity.findViewById(R.id.back);
        mTx_back.setOnClickListener(this);
        mTx_right = (TextView)activity.findViewById(R.id.right);
        mTitle = (TextView)activity.findViewById(R.id.title);
        mActivity = activity;
        dealNavShow(type);
    }

    public void SetTitle(String title){
        mTitle.setText(title);
    }

    private void  dealNavShow(NavType type){

        if (type == NavType.NoTitle){
            mTitle.setVisibility(View.GONE);
        }

        if (type == NavType.allNav){

        }
        else if (type == NavType.justBack){
            mTx_right.setVisibility(View.GONE);

        }
        else if (type == NavType.justRight){
            mTx_back.setVisibility(View.GONE);
        }
    }

    public TextView getRightTextView(){
        return mTx_right;
    }

    public void  setRightText(String text){
        mTx_right.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (v == mTx_back){
            mActivity.finish();
        }
        else if (v == mTx_right){

        }
    }


}
