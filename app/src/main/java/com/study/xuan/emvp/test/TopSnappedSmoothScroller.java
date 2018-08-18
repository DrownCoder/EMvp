package com.study.xuan.emvp.test;

import android.content.Context;
import android.support.v7.widget.LinearSmoothScroller;

public class TopSnappedSmoothScroller extends LinearSmoothScroller {
        private boolean isToEnd;

        public void setToEnd(boolean toEnd) {
            isToEnd = toEnd;
        }

        public TopSnappedSmoothScroller(Context context) {
            super(context);
        }

        @Override
        protected int getVerticalSnapPreference() {
            //将指定的 item 滑动至与屏幕的顶端对齐
            return isToEnd ? SNAP_TO_START : SNAP_TO_END;
        }
}