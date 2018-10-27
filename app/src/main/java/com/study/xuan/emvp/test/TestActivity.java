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
import com.study.xuan.emvp.model.Text;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.presenter.ImgLogic;
import com.study.xuan.emvp.presenter.OtherLogic;
import com.study.xuan.emvp.widget.TextImgLayout;
import com.xuan.eapi.adaptercorlib.MagicAdapter;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private DragRecyclerView dragRcy;
    private List<Text> mData;
    private LinearSmoothScroller SmoothScroller;
    public static boolean canScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Text text = new Text("第"+i+"个");
            mData.add(text);
        }
        final SlotContext<Text> tookContext = new SlotContext<>(this, mData);
        tookContext.attach(Text.class);
        dragRcy = findViewById(R.id.drag_rcy);
        tookContext.bind(dragRcy);
        //final TestLayoutManager layoutManager = new TestLayoutManager(this);
        dragRcy.setLayoutManager(new LinearLayoutManager(this));
        //tookContext.registerLogic(new MainLogic(this));
        //tookContext.registerLogic(new OtherLogic(this));
        dragRcy.setAdapter(new MagicAdapter(tookContext));
        //dragRcy.setLockClass(TextImgLayout.class);
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
