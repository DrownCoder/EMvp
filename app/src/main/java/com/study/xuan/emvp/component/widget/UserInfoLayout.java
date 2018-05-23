package com.study.xuan.emvp.component.widget;

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
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.component.IComponentBind;
import com.study.xuan.emvp.component.IPresenterBind;
import com.study.xuan.emvp.model.UserInfo;
import com.study.xuan.emvp.presenter.IUserInfoPresenter;
import com.xuan.annotation.ComponentType;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.USER_INFO_LAYOUT,
        type = ComponentType.Support.View)
public class UserInfoLayout extends FrameLayout implements IComponentBind<UserInfo>,IPresenterBind<IUserInfoPresenter<UserInfo>> {
    private ImageView ivImg;
    private TextView tvText;
    private View root;
    private UserInfo info;
    private IUserInfoPresenter<UserInfo> presenter;

    public UserInfoLayout(@NonNull Context context) {
        this(context, null);
    }

    public UserInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
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
                presenter.onTextClick(info);
            }
        });
    }

    private void initView() {
        ivImg = root.findViewById(R.id.iv_img);
        tvText = root.findViewById(R.id.tv_text);
    }

    @Override
    public void bind(UserInfo item) {
        info = item;
        //ivImg.setImageResource(R.drawable.ic_launcher_foreground);
        tvText.setText(item.name);
    }

    @Override
    public void unBind() {

    }

    @Override
    public void setPresenter(IUserInfoPresenter<UserInfo> userInfoIUserInfoPresenter) {
        presenter = userInfoIUserInfoPresenter;
    }
}
