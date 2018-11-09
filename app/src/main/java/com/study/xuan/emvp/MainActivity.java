package com.study.xuan.emvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.study.xuan.emvp.activity.common.CommonActivity;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.study.xuan.emvp.activity.mix.MixActivity;
import com.study.xuan.emvp.activity.person.PersonModelActivity;
import com.study.xuan.emvp.activity.simple.SimpleActivity;
import com.study.xuan.emvp.model.Text;
import com.study.xuan.emvp.presenter.MainLogic;
import com.study.xuan.emvp.test.TestActivity;
import com.xuan.eapi.viewmodel.BaseLogic;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    public static final String TITLE[] = new String[]{
            "全局模式",
            "多种样式",
            "个人模式",
            "简单模式",
            "MIX模式"
    };
    public static final int EVENT[] = new int[]{
            0, 1, 2, 3, 4
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
        SlotContext slot = builder.attachRule(Text.class).setData(mData).setEventCenter(this)
                .build();
        slot.bind(mRcy);
    }

    @Override
    public void onClick(View v) {
        Integer integer = (Integer) v.getTag();
        switch (integer) {
            case 0:
                Toast.makeText(MainActivity.this, "点击简单列表", Toast.LENGTH_SHORT).show();
                startActivity(CommonActivity.class);
                break;
            case 1:
                Toast.makeText(MainActivity.this, "点击多种样式", Toast.LENGTH_SHORT).show();
                startActivity(TestActivity.class);
                break;
            case 2:
                startActivity(PersonModelActivity.class);
                break;
            case 3:
                startActivity(SimpleActivity.class);
                break;
            case 4:
                startActivity(MixActivity.class);
                break;
        }
    }

    private void startActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
