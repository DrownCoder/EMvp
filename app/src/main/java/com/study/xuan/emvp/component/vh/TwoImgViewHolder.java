package com.study.xuan.emvp.component.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.component.widget.IComponentBind;
import com.study.xuan.emvp.model.TwoImg;
import com.xuan.annotation.ComponentType;

/**
 * Author : xuan.
 * Date : 2018/5/22.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.IMAGE_TWO_VH,
        layout = R.layout.two_img
)
public class TwoImgViewHolder extends RecyclerView.ViewHolder implements IComponentBind<TwoImg> {
    public TwoImgViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(TwoImg item) {

    }

    @Override
    public void unBind() {

    }
}
