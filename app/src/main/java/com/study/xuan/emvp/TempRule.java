package com.study.xuan.emvp;

import android.util.SparseArray;
import java.util.HashMap;
import java.util.Map;
import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.rule.IComponentRule;

public class TempRule implements IComponentRule {
    public static final SparseArray<ViewInfo> WIDGET_TYPE;

    public static final Map<Class<?>, SparseArray<ViewInfo>> ATTACH_TYPE;

    public static final Map<Class<?>, Integer> MODEL_TYPE;

    static {
        WIDGET_TYPE = new SparseArray<>();
        MODEL_TYPE = new HashMap();
        ATTACH_TYPE = new HashMap<>();

        putWidget(2,new ViewInfo(2,
                com.study.xuan.emvp.vh.ImageViewHolder.class,2131296304,1, com.study.xuan.emvp.presenter.Contract.ImagePresenter.class));
        putWidget(8,new ViewInfo(8,
                com.study.xuan.emvp.vh.ImgVH.class,-1,2,android.widget.ImageView.class, com.study.xuan.emvp.presenter.IImgShow.class));
        putWidget(3,new ViewInfo(3,
                com.study.xuan.emvp.vh.TwoImgViewHolder.class,2131296309,1, com.study.xuan.emvp.presenter.Contract.TwoImgPresenter.class));
        //putWidget(7,new ViewInfo(7, com.study.xuan.emvp.vh.TextVH.class,2131296305,2, null,com.study.xuan.emvp.model.Text.class));
        attachWidget(com.study.xuan.emvp.model.Text.class,7,new ViewInfo(7, com.study.xuan.emvp.vh.TextVH.class,2131296305,2, null,com.study.xuan.emvp.model.Text.class));
        putWidget(4,new ViewInfo(4,
                com.study.xuan.emvp.widget.TextImgLayout.class,0, null));
        putWidget(6,new ViewInfo(6,
                com.study.xuan.emvp.widget.DividerView.class,0, null));
        putWidget(9,new ViewInfo(9,
                com.study.xuan.emvp.widget.DividerView.InnerView.class,0, null));
        putWidget(5,new ViewInfo(5,
                com.study.xuan.emvp.widget.WebViewLayout.class,0, null));
        putWidget(1,new ViewInfo(1,
                com.study.xuan.emvp.widget.UserInfoLayout.class,0, com.study.xuan.emvp.presenter.IUserInfoPresenter.class));
        putModel(com.study.xuan.emvp.model.SingleImg.class,2);
        putModel(com.study.xuan.emvp.model.Text.class,7);
        putModel(com.study.xuan.emvp.model.Product.class,1);
        putModel(com.study.xuan.emvp.model.ImageModel.class,8);
        putModel(com.study.xuan.emvp.model.UserInfo.class,1);
    }

    private static void putWidget(int id,ViewInfo info) {
        WIDGET_TYPE.put(id, info);
    }

    private static void putModel(Class<?> clazz, int id) {
        MODEL_TYPE.put(clazz, id);;
    }

    private static void attachWidget(Class<?> clazz,int id,ViewInfo info){
        SparseArray attachArray = ATTACH_TYPE.get(clazz);
        if (attachArray == null) {
            attachArray = new SparseArray();
            ATTACH_TYPE.put(clazz, attachArray);
        }
        attachArray.put(id, info);
    }

    @Override
    public ViewInfo obtainViewInfo(int id) {
        return WIDGET_TYPE.get(id);
    }

    @Override
    public int obtainComponentId(Class<?> clazz) {
        return MODEL_TYPE.get(clazz);
    }

    @Override
    public ViewInfo obtainAttachViewInfo(Class<?> clazz, int id) {
        return ATTACH_TYPE.get(clazz).get(id);
    }

}
