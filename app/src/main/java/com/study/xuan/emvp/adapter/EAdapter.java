package com.study.xuan.emvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.study.xuan.emvp.factory.ComponentFactory;
import com.study.xuan.emvp.factory.IComponentFactory;
import com.study.xuan.emvp.manager.IModerBinder;
import com.study.xuan.emvp.manager.ModelManager;
import com.study.xuan.emvp.component.Component;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class EAdapter extends RecyclerView.Adapter<Component> {
    private IModerBinder moderBinder;
    private Context mContext;

    public EAdapter(Context context, List<Object> data) {
        this(context, new ModelManager(data));
    }

    public EAdapter(Context context, IModerBinder moderBinder) {
        this.mContext = context;
        this.moderBinder = moderBinder;
    }

    @Override
    public Component onCreateViewHolder(ViewGroup parent, int viewType) {
        IComponentFactory factory = new ComponentFactory(mContext, parent);
        return factory.createViewHolder(viewType);
    }

    @Override
    public void onBindViewHolder(Component holder, int position) {
        holder.onBind(position, moderBinder.getItem(position));
    }

    @Override
    public int getItemCount() {
        return moderBinder.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return moderBinder.getItemType(position);
    }
}
