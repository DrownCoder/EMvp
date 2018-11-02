package com.study.xuan.emvp.activity.person;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

@ComponentType(
        value = 2,
        view = ImageView.class,
        attach = PersonModel.class
)
public class PersonBacVH extends Component<PersonModel> {
    private ImageView ivBac;

    public PersonBacVH(Context context, View itemView) {
        super(context, itemView);
        ivBac = (ImageView) itemView;
        ivBac.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        ivBac.setBackgroundResource(R.drawable.ic_launcher_background);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
    }
}