package com.study.xuan.emvp.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Author : xuan.
 * Date : 2018/6/15.
 * Description :the description of this file
 */

public class TestSnapHelper extends LinearSnapHelper {
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
        if (manager.findLastVisibleItemPosition() == 14 || manager.findFirstVisibleItemPosition() == 14) {
            return super.findSnapView(layoutManager);
        }else{
            return null;
        }
    }
}
