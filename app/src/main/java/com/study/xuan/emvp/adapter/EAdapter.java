package com.study.xuan.emvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.study.xuan.emvp.factory.ComponentFactory;
import com.study.xuan.emvp.factory.IComponentFactory;
import com.study.xuan.emvp.manager.IModerBinder;
import com.study.xuan.emvp.manager.ModelManager;
import com.study.xuan.emvp.vh.Component;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class EAdapter extends RecyclerView.Adapter<Component> {
    private IModerBinder moderBinder;

    public EAdapter(List<Object> data) {
        this(new ModelManager(data));
    }

    public EAdapter(IModerBinder moderBinder) {
        this.moderBinder = moderBinder;
    }

    @Override
    public Component onCreateViewHolder(ViewGroup parent, int viewType) {
        IComponentFactory factory = new ComponentFactory();
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
