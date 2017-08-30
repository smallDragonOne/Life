package com.example.zj.liferecord.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zj on 2017/8/30.
 */

public class ViewHolder {


    private int mPosition;

    private View mConverView;

    private SparseArray<View> mViews;

    public ViewHolder(Context context, ViewGroup parent,int layoutId,int postion){
        this.mPosition = postion;
        mViews = new SparseArray<>();
        mConverView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConverView.setTag(this);
    }

    public static ViewHolder getViewHoder(Context context,View ConverView, ViewGroup parent,int layoutId,int postion){
        if (ConverView == null){
            return new ViewHolder(context,parent,layoutId,postion);
        }
        else {
            ViewHolder holder = (ViewHolder)ConverView.getTag();
            holder.mPosition = postion;
            return holder;
        }
    }

    public <T extends View> T getViews(int ViewId){
        View view = mViews.get(ViewId);
        if (view == null){
            view = (View)mConverView.findViewById(ViewId);
            mViews.put(ViewId,view);
        }
        return (T) view;
    }

    public int getPosition(){
        return  mPosition;
    }

    public View getConvertView(){
        return mConverView;
    }

}
