package com.study.xuan.emvp.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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
 * Date : 2018/5/22.
 * Description :一行两图组件
 */
@ComponentType(
        value = ComponentId.IMAGE_TWO_VH,
        layout = R.layout.two_img
)
@ILogic(Contract.TwoImgPresenter.class)
public class TwoImgViewHolder extends RecyclerView.ViewHolder implements IComponentBind<CommonModel>,IPresenterBind<Contract.TwoImgPresenter> {
    private Contract.TwoImgPresenter presenter;
    public TwoImgViewHolder(View itemView) {
        super(itemView);
        itemView.findViewById(R.id.iv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLeftClick();
            }
        });

        itemView.findViewById(R.id.iv_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRightClick();
            }
        });
    }

    @Override
    public void onBind(int pos, CommonModel item) {

    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void injectPresenter(Contract.TwoImgPresenter twoImgPresenter) {
        presenter = twoImgPresenter;
    }
}
