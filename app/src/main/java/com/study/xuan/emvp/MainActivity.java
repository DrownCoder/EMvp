package com.study.xuan.emvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.study.xuan.emvp.model.ImageInfo;
import com.study.xuan.emvp.model.Product;
import com.study.xuan.emvp.model.SingleImg;
import com.study.xuan.emvp.model.Text;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.presenter.CommunityLogic;
import com.study.xuan.emvp.presenter.MainLogic;
import com.study.xuan.emvp.presenter.OtherLogic;
import com.study.xuan.emvp.test.TestActivity;
import com.study.xuan.emvp.widget.UserInfoLayout;
import com.xuan.eapi.BaseLogic;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.adapter.MagicAdapter;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;
import com.xuan.eapi.helper.binder.ModelBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public static final String TITLE[] = new String[]{
            "简单列表",
            "多种样式",
    };
    public static final int EVENT[] = new int[]{
            0, 1
    };
    protected BaseLogic mPresenter;
    RecyclerView mRcy;
    private List<Text> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainLogic(this);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < TITLE.length; i++) {
            mData.add(new Text(TITLE[i], EVENT[i]));
        }
        ToolKitBuilder<Text> builder = new ToolKitBuilder<>(this);
        SlotContext slot = builder.setData(mData).setEventCenter(this).build();
        slot.bind(mRcy);
    }

    @Override
    public void onClick(View v) {
        Integer integer = (Integer) v.getTag();
        switch (integer) {
            case 0:
                Toast.makeText(MainActivity.this, "点击简单列表", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(MainActivity.this, "点击多种样式", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
        }
    }
}
