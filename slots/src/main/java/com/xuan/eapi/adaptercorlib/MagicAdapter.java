package com.xuan.eapi.adaptercorlib;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.helper.event.InjectCallback;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :
 * 1.三种组件模式
 * a.组件依赖页面，组件只展示UI，组件本身没有生命周期，组件的事件交给页面处理，然后刷新数据
 * b.组件自身维护一套体系，自身内部逻辑闭合，只依赖于model，组件需要感知生命周期，处理生命周期相关的操作。依赖于ViewHolder携带。
 * c.在b的基础上，组件内部需要对修改关闭，对拓展开放，组件自身实现细粒度的MVP。依赖于Model携带，不仅仅ViewHolder需要感知生命周期，model也需要感知生命周期。
 */

public class MagicAdapter extends RecyclerView.Adapter<Component> {
    private SlotContext slotContext;

    public MagicAdapter(SlotContext slotContext) {
        this.slotContext = slotContext;
    }

    @Override
    public Component onCreateViewHolder(ViewGroup parent, int viewType) {
        Component component = slotContext.createComponent(viewType, parent);
        //注入监听器
        if (InjectCallback.class.isAssignableFrom(component.getClass())) {
            ((InjectCallback) component).injectCallback(slotContext.obtainEventCenter());
        }
        component.itemView.setOnClickListener(slotContext.obtainEventCenter());
        return component;
    }

    @Override
    public void onBindViewHolder(Component holder, int position) {
        Object item = slotContext.getItem(position);
        holder.onBind(position, item);
    }

    @Override
    public int getItemCount() {
        return slotContext.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return slotContext.getItemType(position);
    }
}
