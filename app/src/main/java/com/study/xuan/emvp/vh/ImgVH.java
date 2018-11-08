package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/9/6.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.VRCY,
        view = ImageView.class
)
public class ImgVH extends Component<CommonModel> {
    private ImageView iv;
    public ImgVH(Context context, View itemView) {
        super(context, itemView);
        iv = (ImageView) itemView;
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void onBind(int pos, CommonModel item) {
        iv.setImageResource(R.drawable.ic_launcher_background);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
