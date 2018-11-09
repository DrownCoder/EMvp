package com.study.xuan.emvp.activity.mix;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.study.xuan.emvp.activity.person.PersonModel;
import com.study.xuan.emvp.activity.person.PersonModelActivity;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;
import com.xuan.eapi.factory.custom.CustomFactory;
import com.xuan.eapi.helper.binder.IMapAttach;
import com.xuan.eapi.helper.binder.ModelBinder;
import com.xuan.eapi.helper.manager.IModelManger;

import java.util.ArrayList;
import java.util.List;

public class MixActivity extends AppCompatActivity {
    private RecyclerView mRcy;
    private List<PersonModel> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix);
        mRcy = findViewById(R.id.rcy);
        mRcy.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            PersonModel model = new PersonModel();
            if (i < 5) {
                model.type = i;
            } else {
                model.model = new CommonModel();
                model.model.pos = i;
            }
            mData.add(model);
        }
        SlotContext<PersonModel> slotContext = new ToolKitBuilder<PersonModel>(this)
                .attachRule(PersonModel.class)
                .setData(mData)
                .setComponentFactory(new CustomFactory() {
                    @Override
                    protected IComponentBind createViewHolder(Context context, ViewGroup parent,
                                                              int type) {
                        if (type == 3) {
                            return new PersonModelActivity.InnerVH(new TextView(MixActivity.this));
                        }
                        return super.createViewHolder(context, parent, type);
                    }
                })
                .setModerBinder(new ModelBinder<PersonModel>() {
                    @Override
                    protected int bindItemType(int pos, PersonModel obj) {
                        if (obj.model != null) {
                            return obj.model.handlerType() + 4;
                        }
                        return obj.type;
                    }
                }).setMapAttach(new IMapAttach() {
                    @Override
                    public Class<?> attachClass(int type) {
                        if (type <= 4) {
                            return PersonModel.class;
                        }
                        return null;
                    }

                    @Override
                    public int getComponentType(int type) {
                        if (type > 4) {
                            type -= 4;
                        }
                        return type;
                    }
                })
                .setModelManger(new IModelManger<PersonModel>() {
                    @Override
                    public Object getBindItem(int pos, PersonModel model) {
                        if (pos >= 4) {
                            return model.model;
                        }
                        return model;
                    }
                })
                .build();
        slotContext.bind(mRcy);
    }
}
