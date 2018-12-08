package com.study.xuan.emvp.activity.product;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.study.xuan.emvp.R;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;

/**
 * Author : xuan.
 * Date : 2018/11/10.
 * Description :the description of this file
 */
@ComponentType(value = Product.PRODUCT,
        layout = R.layout.single_product,
        attach = Product.class)
public class ProductVH extends Component<Product> {
    private TextView tvName, tvPrice;

    public ProductVH(Context context, View root) {
        super(context, root);
        tvName = root.findViewById(R.id.tv_p_name);
        tvPrice = root.findViewById(R.id.tv_p_price);
    }

    @Override
    public void onBind(int pos, final Product item) {
        tvName.setText(item.pName);
        tvPrice.setText(item.price);
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.onDoEvent(0, item);
            }
        });
    }
}
