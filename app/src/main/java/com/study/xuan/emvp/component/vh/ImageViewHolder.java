package com.study.xuan.emvp.component.vh;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.component.widget.IComponentBind;
import com.study.xuan.emvp.model.SingleImg;
import com.xuan.annotation.ComponentType;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.IMAGE_VH,
        layout = R.layout.single_img
)
public class ImageViewHolder extends RecyclerView.ViewHolder implements IComponentBind<SingleImg> {
    private ImageView iv;
    public ImageViewHolder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView;
    }

    @Override
    public void bind(SingleImg item) {
        //iv.setImageResource(item.imgUrl);
        iv.setBackgroundColor(Color.BLUE);
    }

    @Override
    public void unBind() {

    }
}
