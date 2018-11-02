package com.study.xuan.emvp.activity.person;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

@ComponentType(
        value = 1,
        layout = R.layout.person_item_layout,
        attach = PersonModel.class
)
public class PersonVH extends Component<PersonModel> {
    private TextView tvName;

    public PersonVH(Context context, View itemView) {
        super(context, itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
        //tvName.findViewById(R.id.tv_name);
        tvName.setText(item.name);
    }
}