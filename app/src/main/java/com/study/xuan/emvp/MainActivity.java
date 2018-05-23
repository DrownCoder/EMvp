package com.study.xuan.emvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.adapter.EAdapter;
import com.study.xuan.emvp.model.ImageInfo;
import com.study.xuan.emvp.model.SingleImg;
import com.study.xuan.emvp.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    protected IBasePresenter mPresenter;
    RecyclerView mRcy;
    private List<Object> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new SingleImg());
        mData.add(new ImageInfo(ComponentId.IMAGE_TWO_VH));
        mData.add(new UserInfo());
        mData.add(new ImageInfo(ComponentId.TEXT_IMG));

        mRcy.setAdapter(new EAdapter(this, mData));
    }
}
