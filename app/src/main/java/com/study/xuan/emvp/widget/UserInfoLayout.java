package com.study.xuan.emvp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.model.HandlerType;
import com.xuan.annotation.ComponentType;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */
@ComponentType(type = ComponentId.USER_INFO_LAYOUT)
public class UserInfoLayout extends FrameLayout implements IWidget<HandlerType> {
    public UserInfoLayout(@NonNull Context context) {
        this(context, null);
    }

    public UserInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public UserInfoLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.user_info_layout, this, false);
    }

    @Override
    public void bind(HandlerType item) {

    }
}
