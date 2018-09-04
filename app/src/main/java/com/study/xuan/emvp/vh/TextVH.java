package com.study.xuan.emvp.vh;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.study.xuan.emvp.model.Text;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.helper.event.InjectCallback;

/**
 * Author : xuan.
 * Date : 2018/9/1.
 * Description :一行文字
 */

@ComponentType(
        value = ComponentId.SINGLE_TEXT,
        layout = R.layout.single_text
)
public class TextVH extends Component<Text> implements InjectCallback {
    private TextView tv;
    private View.OnClickListener onClickListener;

    public TextVH(Context context, View itemView) {
        super(context, itemView);
        tv = (TextView) itemView;
    }

    @Override
    public void onBind(int pos, Text item) {
        tv.setText(item.title);
        tv.setTag(item.eventId);
        tv.setOnClickListener(onClickListener);
    }

    @Override
    public void onUnBind() {

    }

    @Override
    public void injectCallback(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
