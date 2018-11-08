package com.study.xuan.emvp.vh;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.study.xuan.emvp.presenter.Contract;
import com.xuan.annotation.ComponentType;
import com.xuan.annotation.ILogic;
import com.xuan.component.R;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.viewmodel.IPresenterBind;

/**
 * Author : xuan.
 * Date : 2018/5/18.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.IMAGE_VH,
        layout = R.layout.single_img
)
@ILogic(Contract.ImagePresenter.class)
public class ImageViewHolder extends RecyclerView.ViewHolder implements IComponentBind<CommonModel>,IPresenterBind<Contract.ImagePresenter> {
    private ImageView iv;
    private Contract.ImagePresenter presenter;
    public ImageViewHolder(View itemView) {
        super(itemView);
        iv = (ImageView) itemView;
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onImgClick();
            }
        });
    }

    @Override
    public void onBind(int pos, CommonModel item) {
        iv.setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void injectPresenter(Contract.ImagePresenter imagePresenter) {
        this.presenter = imagePresenter;
    }
}
