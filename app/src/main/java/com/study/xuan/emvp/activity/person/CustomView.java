package com.study.xuan.emvp.activity.person;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/11/2.
 * Description :the description of this file
 */
@ComponentType(
        value = 4,
        attach = PersonModel.class
)
public class CustomView extends ImageView implements IComponentBind<PersonModel> {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
        setImageResource(R.drawable.ic_launcher_foreground);
    }

    @Override
    public void onUnBind() {

    }
}
