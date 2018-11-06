package com.study.xuan.emvp.activity.person;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

@ComponentType(
        value = PersonId.COMPONENT,
        view = FrameLayout.class,
        attach = PersonModel.class
)
public class PersonBacVH extends Component<PersonModel> {
    private FrameLayout fgBac;
    private ImageView ivBac;
    private TextView tvTips;

    public PersonBacVH(Context context, View itemView) {
        super(context, itemView);
        fgBac = (FrameLayout) itemView;
        ivBac = new ImageView(context);
        ivBac.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ivBac.setBackgroundResource(R.drawable.ic_launcher_background);

        tvTips = new TextView(context);
        tvTips.setText("继承Component");
        tvTips.setTextColor(Color.WHITE);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        fgBac.addView(ivBac);
        fgBac.addView(tvTips,params);
        fgBac.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public void onBind(int pos, PersonModel item) {
    }
}