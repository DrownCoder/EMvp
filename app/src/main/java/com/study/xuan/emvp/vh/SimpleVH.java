package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.SimpleModel;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/11/6.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.SIMPLE,
        layout = R.layout.single_text
)
public class SimpleVH extends Component<SimpleModel> {
    private TextView tvList;
    public SimpleVH(Context context, View itemView) {
        super(context, itemView);
        tvList = itemView.findViewById(R.id.tv_simple);
    }

    @Override
    public void onBind(int pos, SimpleModel item) {
        tvList.setText(item.name);
    }
}
