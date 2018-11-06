package com.study.xuan.emvp.activity.person;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/11/2.
 * Description :the description of this file
 */
@ComponentType(
        value = PersonId.CUSTOM,
        attach = PersonModel.class
)
public class CustomView extends LinearLayout implements IComponentBind<PersonModel> {
    public CustomView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.cutom_view_vh, this, true);
        setBackgroundColor(Color.BLACK);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
    }

    @Override
    public void onUnBind() {

    }
}
