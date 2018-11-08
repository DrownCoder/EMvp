package com.study.xuan.emvp.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/6/14.
 * Description :the description of this file
 */
@ComponentType(ComponentId.DIVIDER)
public class DividerView extends View implements IComponentBind {
    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DividerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20));
        setBackgroundColor(Color.GRAY);
    }

    @Override
    public void onBind(int pos, Object item) {

    }

    @Override
    public void onUnBind() {

    }
}
