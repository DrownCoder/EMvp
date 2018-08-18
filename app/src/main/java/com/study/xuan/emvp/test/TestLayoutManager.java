package com.study.xuan.emvp.test;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;

/**
 * Author : xuan.
 * Date : 2018/6/14.
 * Description :the description of this file
 */

public class TestLayoutManager extends LinearLayoutManager {
    public boolean canScroll = true;

    public TestLayoutManager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll;
    }

    //重点方法
    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state,
                                       int position) {

        //也就是说重点在于重写 SmoothScroller,而滑动的调用为 startSmoothScroll()
        RecyclerView.SmoothScroller smoothScroller = new TopSnappedSmoothScroller(recyclerView.getContext());
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }

    private class TopSnappedSmoothScroller extends LinearSmoothScroller {
        private boolean isToEnd;

        public void setToEnd(boolean toEnd) {
            isToEnd = toEnd;
        }

        public TopSnappedSmoothScroller(Context context) {
            super(context);
        }

        @Override
        public PointF computeScrollVectorForPosition(int targetPosition) {
            return TestLayoutManager.this
                    .computeScrollVectorForPosition(targetPosition);
        }

        @Override
        protected int getVerticalSnapPreference() {
            //将指定的 item 滑动至与屏幕的顶端对齐
            return isToEnd ? SNAP_TO_START : SNAP_TO_END;
        }
    }
}
