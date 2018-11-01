package com.study.xuan.emvp.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;

public class PersonModelActivity extends AppCompatActivity {
    private RecyclerView mRcy;
    private List<PersonModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_model);
        mRcy = findViewById(R.id.rcy_user);
        mData = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            PersonModel model = new PersonModel();
            model.name = "名字" + i;
            mData.add(model);
        }
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        SlotContext slotContext =
                new ToolKitBuilder<PersonModel>(this)
                        .setData(mData)
                        .attachRule(PersonModel.class).build();
        slotContext.bind(mRcy);
    }

    @ComponentType(
            value = 1,
            layout = R.layout.person_item_layout,
            attach = PersonModel.class
    )
    public static class PersonVH extends Component<PersonModel> {
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

}
