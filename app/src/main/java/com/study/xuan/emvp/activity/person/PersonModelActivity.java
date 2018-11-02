package com.study.xuan.emvp.activity.person;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            model.type = new Random().nextInt(5);
            if (model.type == 0) {
                model.type = 1;
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
            value = 3,
            view = TextView.class,
            attach = PersonModel.class
    )
    public static class InnerVH extends RecyclerView.ViewHolder implements IComponentBind<PersonModel> {
        private TextView tv;
        public InnerVH(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }

        @Override
        public void onBind(int pos, PersonModel item) {
            tv.setText("这是内部类的ViewHolder");
        }

        @Override
        public void onUnBind() {

        }
    }

}
