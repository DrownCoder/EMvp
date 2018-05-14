package com.study.xuan.emvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.study.xuan.emvp.factory.EVHFactory;
import com.study.xuan.emvp.factory.IVHFactory;
import com.study.xuan.emvp.model.Floor;
import com.study.xuan.emvp.vh.EViewHolder;

import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public class EAdapter<T extends Floor> extends RecyclerView.Adapter<EViewHolder> {
    private List<T> mData;

    @Override
    public EViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        IVHFactory factory = new EVHFactory();
        return factory.createViewHolder(viewType);
    }

    @Override
    public void onBindViewHolder(EViewHolder holder, int position) {
        holder.onBind(position, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).type;
    }
}
