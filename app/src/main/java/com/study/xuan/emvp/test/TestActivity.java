package com.study.xuan.emvp.test;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.model.ImageInfo;
import com.study.xuan.emvp.model.ImageModel;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.presenter.MainLogic;
import com.study.xuan.emvp.presenter.OtherLogic;
import com.study.xuan.emvp.widget.TextImgLayout;
import com.xuan.eapi.adapter.MagicAdapter;
import com.xuan.eapi.context.SlotContext;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private DragRecyclerView dragRcy;
    private List<Object> mData;
    private LinearSmoothScroller SmoothScroller;
    public static boolean canScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mData = new ArrayList<>();
        mData.add(new ImageModel());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new ImageInfo(ComponentId.TEXT_IMG));
        mData.add(new ImageInfo(ComponentId.WEBVIEW));
        /*mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new Product(true));
        mData.add(new SingleImg());
        mData.add(new ImageInfo(ComponentId.IMAGE_TWO_VH));
        mData.add(new UserInfo());
        UserInfo userInfo = new UserInfo(true);
        userInfo.injectPresenter(new CommunityLogic(this));
        mData.add(userInfo);
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());*/
        mData.add(new ImageInfo(ComponentId.TEXT_IMG));
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        mData.add(new UserInfo());
        //mData.add(new ImageInfo(ComponentId.TEXT_IMG));
        final SlotContext tookContext = new SlotContext(this, mData);
        dragRcy = findViewById(R.id.drag_rcy);
        //final TestLayoutManager layoutManager = new TestLayoutManager(this);
        dragRcy.setLayoutManager(new LinearLayoutManager(this));
        //tookContext.registerLogic(new MainLogic(this));
        //tookContext.registerLogic(new OtherLogic(this));
        dragRcy.setAdapter(new MagicAdapter(tookContext));
        dragRcy.setLockClass(TextImgLayout.class);
        dragRcy.addLockIndex(14);
        dragRcy.addLockIndex(27);
        //new TestSnapHelper().attachToRecyclerView(dragRcy);
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        if (context == null || context.getResources() == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
