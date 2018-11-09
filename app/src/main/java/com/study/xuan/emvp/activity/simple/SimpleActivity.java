package com.study.xuan.emvp.activity.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.SimpleModel;
import com.xuan.eapi.context.SlotContext;

import java.util.ArrayList;
import java.util.List;

public class SimpleActivity extends AppCompatActivity {
    private RecyclerView mRcy;
    private List<SimpleModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add(new SimpleModel("第" + i + "个"));
        }
        SlotContext<SimpleModel> slotContext = new SlotContext<>(this, mData);
        slotContext.bind(mRcy);
    }
}
