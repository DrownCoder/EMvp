package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/11/6.
 * Description :the description of this file
 */
@ComponentType(
        value = ComponentId.SIMPLE,
        view = TextView.class
)
public class SimpleVH extends Component {
    public SimpleVH(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void onBind(int pos, Object item) {

    }
}
