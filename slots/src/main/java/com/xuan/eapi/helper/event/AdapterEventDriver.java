package com.xuan.eapi.helper.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.xuan.eapi.lifecycle.IGC;

/**
 * Author : xuan.
 * Date : 2018/12/8.
 * Description :the description of this file
 */
public class AdapterEventDriver<T> implements EventDriver<T>, IGC {
    protected RecyclerView.Adapter mAdapter;
    protected Context mContext;

    public AdapterEventDriver(Context context,RecyclerView.Adapter adapter) {
        this.mContext = context;
        this.mAdapter = adapter;
    }

    public void setAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public void onDoEvent(int eventID, T data) {
    }

    @Override
    public void onDestroy() {
        mAdapter = null;
        mContext = null;
    }
}
