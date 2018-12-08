package com.study.xuan.emvp.activity.mix;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.study.xuan.emvp.activity.common.CommonLogic;
import com.study.xuan.emvp.activity.common.CommonModel;
import com.study.xuan.emvp.activity.person.PersonModel;
import com.study.xuan.emvp.activity.person.PersonModelActivity;
import com.study.xuan.emvp.activity.common.userinfo.CommunityLogic;
import com.study.xuan.emvp.activity.product.Product;
import com.study.xuan.emvp.activity.product.ProductEventDriver;
import com.xuan.eapi.component.IComponentBind;
import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.context.ToolKitBuilder;
import com.xuan.eapi.factory.custom.CustomFactory;
import com.xuan.eapi.helper.binder.ModelBinder;
import com.xuan.eapi.helper.strategy.IMixStrategy;

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
        ProductEventDriver productEventDriver = new ProductEventDriver(this, null);
        for (int i = 1; i < 100; i++) {
            PersonModel model = new PersonModel();
            if (i < 5) {
                model.type = i;
            } else if (i <= 8) {
                Product product = new Product();
                product.injectDriver(productEventDriver);
                product.pName = "第" + i + "个" + "商品";
                product.price = "¥" + i * 100;
                model.product = product;
            } else {
                model.model = new CommonModel();
                model.model.pos = i;
            }
            mData.add(model);
        }
        SlotContext<PersonModel> slotContext = new ToolKitBuilder<PersonModel>(this)
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
                            return obj.model.handlerType() + 6;
                        }
                        if (obj.product != null) {
                            return Product.PRODUCT + 5;
                        }
                        return obj.type;
                    }
                }).setMixStrategy(new IMixStrategy<PersonModel>() {
                    @Override
                    public int getComponentId(int type) {
                        if (type == 5) {
                            return Product.PRODUCT;
                        }
                        if (type > 6) {
                            type -= 6;
                        }
                        return type;
                    }

                    @Override
                    public Class<?> attachClass(int type) {
                        if (type == 5) {
                            return Product.class;
                        }
                        if (type <= 4) {
                            return PersonModel.class;
                        }
                        return null;
                    }

                    @Override
                    public Object getBindItem(int pos, PersonModel model) {
                        if (model.model != null) {
                            return model.model;
                        }
                        if (model.product != null) {
                            return model.product;
                        }
                        return model;
                    }
                })
                .attachRule(Product.class)
                .build();
        slotContext.attachRule(PersonModel.class).registerLogic(new CommunityLogic(this))
                .registerLogic(new CommonLogic(slotContext)).bind(mRcy);
    }
}
