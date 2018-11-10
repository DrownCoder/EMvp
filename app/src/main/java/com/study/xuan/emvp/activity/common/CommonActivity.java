package com.study.xuan.emvp.activity.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.userinfo.CommunityLogic;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/11/8.
 * Description :the description of this file
 */
public class CommonActivity extends Activity {
    private RecyclerView mRcy;
    private List<CommonModel> mData;
    private SlotContext<CommonModel> slotContext;
    @SuppressLint("HandlerLeak")
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            slotContext.setData(mData).notifyDataSetChanged();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        slotContext = new ToolKitBuilder<>(this, mData).build();
        slotContext.registerLogic(new CommunityLogic(this))
                .registerLogic(new CommonLogic(slotContext));
        slotContext.bind(mRcy);
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mData = new ArrayList<>();
                for (int i = 0; i < 50; i++) {
                    CommonModel model = new CommonModel();
                    model.pos = i;
                    mData.add(model);
                }
                handler.sendMessage(Message.obtain());
            }
        }).start();
    }
}
