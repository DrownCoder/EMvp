package com.study.xuan.emvp.test;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.study.xuan.emvp.vh.TwoImgViewHolder;
import com.study.xuan.emvp.widget.TextImgLayout;

/**
 * Author : xuan.
 * Date : 2018/6/13.
 * Description :the description of this file
 */

public class TestSnap extends LinearSnapHelper {
    public static final String TAG = "xxxxx";
    public boolean isSecond = true;

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        /*if (targetView.getClass() == TextImgLayout.class) {
            Log.i("xxxxxx", "xxxxx");
            return super.calculateDistanceToFinalSnap(layoutManager, targetView);
        }
        return new int[2];*/
        return super.calculateDistanceToFinalSnap(layoutManager, targetView);
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        Log.i(TAG, "findSnapView");
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int result = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        Log.i(TAG, "findTargetSnapPosition");
        if (result > 12) {
            return 12;
        }


        Log.i(TAG, "result:" + result);
        return result;
    }
}
