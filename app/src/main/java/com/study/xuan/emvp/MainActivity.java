package com.study.xuan.emvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.study.xuan.emvp.model.ImageInfo;
import com.study.xuan.emvp.model.Product;
import com.study.xuan.emvp.model.SingleImg;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.presenter.CommunityPresenter;
import com.study.xuan.emvp.presenter.MainPresenter;
import com.study.xuan.emvp.presenter.OtherPresenter;
import com.study.xuan.emvp.widget.UserInfoLayout;
import com.xuan.eapi.BasePresenter;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.adapter.EAdapter;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.context.ToolKitBuilder;
import com.xuan.eapi.context.ToolKitContext;
import com.xuan.eapi.helper.binder.ModelBinder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    protected BasePresenter mPresenter;
    RecyclerView mRcy;
    private List<Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mData.add(new UserInfo());
        mData.add(new Product(true));
        mData.add(new SingleImg());
        mData.add(new ImageInfo(ComponentId.IMAGE_TWO_VH));
        mData.add(new UserInfo());
        UserInfo userInfo = new UserInfo(true);
        userInfo.injectPresenter(new CommunityPresenter(this));
        mData.add(userInfo);
        mData.add(new ImageInfo(ComponentId.TEXT_IMG));
        //ToolKitContext toolKitContext = new ToolKitContext(this, mData);
        ToolKitContext toolKitContext = ToolKitBuilder.init(this, mData).setModerBinder(new ModelBinder() {
            @Override
            public IComponentBind createComponent(int viewId) {
                switch (viewId) {
                    case ComponentId.USER_INFO_LAYOUT:
                        return new UserInfoLayout(MainActivity.this);
                }
                return null;
            }
        }).build();
        //ToolKitContext toolKitContext = new ToolKitContext(this, mData);
       /* List<Integer> pid = new ArrayList<>();
        pid.add(PresenterId.COMMUNITY_PRESENTER);
        toolKitContext.prepareLogic(pid);*/
        toolKitContext.registerLogic(new MainPresenter(this));
        toolKitContext.registerLogic(new OtherPresenter(this));
        mRcy.setAdapter(new EAdapter(toolKitContext));
    }
}
