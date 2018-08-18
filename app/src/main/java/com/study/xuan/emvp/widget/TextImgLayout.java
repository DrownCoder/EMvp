package com.study.xuan.emvp.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.model.ImageInfo;
import com.xuan.annotation.ComponentType;
import com.xuan.component.R;
import com.xuan.eapi.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/5/23.
 * Description :the description of this file
 */
@ComponentType(value = ComponentId.TEXT_IMG)
public class TextImgLayout extends LinearLayout implements IComponentBind<ImageInfo> {
    private View root;
    private TextView tvInfo;

    public TextImgLayout(Context context) {
        this(context, null);
    }

    public TextImgLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImgLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root = LayoutInflater.from(context).inflate(R.layout.text_img_layout, this);
        initView();
    }

    private void initView() {
        //tvInfo = root.findViewById(R.id.tv_pro);
    }

    @Override
    public void bind(ImageInfo item) {
        //tvInfo.setText(item.promInfo.desc);
    }

    @Override
    public void unBind() {

    }
}
