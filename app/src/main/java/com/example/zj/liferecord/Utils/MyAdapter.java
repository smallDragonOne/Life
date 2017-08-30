package com.example.zj.liferecord.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zj on 2017/8/30.
 */

public abstract class MyAdapter<T> extends BaseAdapter {

    private List<T> mDatas;

    private Context mContext;

    private int mLayoutId;

    public MyAdapter(List<T> Datas,Context context,int LayoutId){
        mDatas = Datas;
        mContext = context;
        mLayoutId = LayoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = ViewHolder.getViewHoder(mContext,convertView,parent,mLayoutId,position);
        Covert(holder,mDatas.get(position));
        return holder.getConvertView();
    }

    protected abstract void Covert(ViewHolder holder, T data);
}
