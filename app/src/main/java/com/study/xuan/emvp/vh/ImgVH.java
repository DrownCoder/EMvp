package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.model.ImageModel;
import com.study.xuan.emvp.model.Text;
import com.study.xuan.emvp.presenter.IImgShow;
import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ILogic;
import com.xuan.eapi.IPresenterBind;
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
@ILogic(IImgShow.class)
public class ImgVH extends Component<ImageModel> implements IPresenterBind<IImgShow> {
    private ImageView iv;
    private IImgShow show;
    public ImgVH(Context context, View itemView) {
        super(context, itemView);
        iv = (ImageView) itemView;
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    @Override
    public void onBind(int pos, ImageModel item) {
        iv.setImageResource(R.drawable.ic_launcher_background);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.show();
            }
        });
    }

    @Override
    public void injectPresenter(IImgShow iImgShow) {
        this.show = iImgShow;
    }
}
