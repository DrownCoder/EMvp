package com.study.xuan.emvp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.model.IUserInfo;
import com.xuan.annotation.ILogic;
import com.xuan.eapi.imodel.PostEvent;
import com.study.xuan.emvp.presenter.IUserInfoPresenter;
import com.xuan.annotation.ComponentType;
import com.xuan.component.R;
import com.xuan.eapi.IComponentBind;
import com.xuan.eapi.IPresenterBind;
import com.xuan.eapi.LogUtil;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */
@ComponentType(value = ComponentId.USER_INFO_LAYOUT)
@ILogic(IUserInfoPresenter.class)
public class UserInfoLayout extends FrameLayout implements IComponentBind<IUserInfo>, IPresenterBind<IUserInfoPresenter<IUserInfo>> {
    private ImageView ivImg;
    private TextView tvText;
    private View root;
    private IUserInfo info;
    private IUserInfoPresenter<IUserInfo> presenter;

    public UserInfoLayout(@NonNull Context context) {
        this(context, null);
    }

    public UserInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        root = LayoutInflater.from(context).inflate(R.layout.user_info_layout, this);
        initView();
        initEvent();
    }

    private void initEvent() {
        tvText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PostEvent.class.isAssignableFrom(info.getClass())) {
                    PostEvent post = (PostEvent) info;
                    if (post.interceptEvent()) {
                        if (IUserInfoPresenter.class.isAssignableFrom(post.postPresenter().getClass())) {
                            IUserInfoPresenter presenter = (IUserInfoPresenter) post.postPresenter();
                            presenter.onTextClick(post);
                        } else {
                            LogUtil.Error("the presenter must implement the IUserInfoPresenter");
                        }
                        return;
                    }
                }

                presenter.onTextClick(info);
            }
        });
    }

    private void initView() {
        ivImg = root.findViewById(R.id.iv_img);
        tvText = root.findViewById(R.id.tv_text);
    }

    @Override
    public void bind(IUserInfo item) {
        info = item;
        //ivImg.setImageResource(R.drawable.ic_launcher_foreground);
        tvText.setText(item.getText());
    }

    @Override
    public void unBind() {

    }

    @Override
    public void injectPresenter(IUserInfoPresenter<IUserInfo> userInfoIUserInfoPresenter) {
        presenter = userInfoIUserInfoPresenter;
    }
}
