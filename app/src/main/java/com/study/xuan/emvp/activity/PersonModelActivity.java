package com.study.xuan.emvp.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            if (i % 2 == 0) {
                model.type = 1;
            }else{
                model.type = 2;
            }
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

    @ComponentType(
            value = 2,
            view = ImageView.class,
            attach = PersonModel.class
    )
    public static class PersonBacVH extends Component<PersonModel> {
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

}
