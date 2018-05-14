package com.study.xuan.emvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.adapter.EAdapter;

/**
 * Author : xuan.
 * Date : 2018/5/4.
 * Description :input the description of this file
 */

public class BaseActivity extends FragmentActivity{
    protected IBasePresenter mPresenter;
    RecyclerView mRcy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mRcy.setAdapter(new EAdapter());
    }
}
