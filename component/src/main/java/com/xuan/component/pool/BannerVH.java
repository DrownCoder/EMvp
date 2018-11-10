package com.xuan.component.pool;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.xuan.annotation.ComponentType;
import com.xuan.component.ComponetId;
import com.xuan.component.model.Banner;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponetId.BANNER,
        view = ImageView.class
)
public class BannerVH extends Component<Banner> {
    public BannerVH(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBind(int pos, Banner item) {

    }
}
