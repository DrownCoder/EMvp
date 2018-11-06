package com.study.xuan.emvp.activity.person;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.IComponentBind;

@ComponentType(
        value = PersonId.VIEWHOLDER,
        layout = R.layout.person_item_layout,
        attach = PersonModel.class
)
public class PersonVH extends RecyclerView.ViewHolder implements IComponentBind<PersonModel> {
    private TextView tvName;

    public PersonVH(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }

    @Override
    public void onBind(int pos, PersonModel item) {
        //tvName.findViewById(R.id.tv_name);
        tvName.setText(item.name);
    }

    @Override
    public void onUnBind() {

    }
}