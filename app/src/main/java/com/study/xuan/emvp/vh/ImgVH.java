package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.model.ImageModel;
import com.study.xuan.emvp.model.Text;
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
public class ImgVH extends Component<ImageModel> {
    private ImageView iv;
    public ImgVH(Context context, View itemView) {
        super(context, itemView);
        iv = (ImageView) itemView;
    }

    @Override
    public void onBind(int pos, ImageModel item) {
        iv.setImageResource(R.drawable.ic_launcher_background);
    }
}
