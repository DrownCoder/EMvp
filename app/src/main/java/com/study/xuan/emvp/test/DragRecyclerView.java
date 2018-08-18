package com.study.xuan.emvp.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/6/13.
 * Description :竖向滑动的RecyclerView，仿照Viewpager实现，可以中断滑动
 */

public class DragRecyclerView extends RecyclerView {
    private static String TAG = "DragRecyclerView";
    private boolean onDrag;
    private boolean isUpScroll;
    private boolean isBalance;
    private int lastVisiblePos;
    private int lastCompletelyVisiblePos;
    private int firstCompletelyVisiblePos;
    private int firstVisiblePos;
    private LinearLayoutManager layoutManager;
    private TopSnappedSmoothScroller smoothScroller;
    int[] location = new int[2];
    private int[] itemLoc = new int[2];
    private int screenHeight;
    private AlignRunnable alignRunnable;
    private Runnable pageRunnable;
    //当前页
    private int page = 0;
    //定位class,区分两个页面的
    private Class<?> lockClass;
    //定位组件的高度
    private int lockHeight = 300;
    private List<Integer> lockIndex;


    public DragRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public DragRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        screenHeight = getDisplayHeight(context);
        lockIndex = new ArrayList<>();
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        layoutManager = (LinearLayoutManager) layout;
        smoothScroller = new TopSnappedSmoothScroller(getContext());
        super.setLayoutManager(layout);
    }

    /**
     * 页面与页面之间用于区分的楼层样式
     */
    public void setLockClass(Class<?> lockClass) {
        this.lockClass = lockClass;
    }

    /**
     * 区分页面楼层样式的index
     */
    public void addLockIndex(int index) {
        lockIndex.add(index);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        getLocationOnScreen(location);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            if (page == 2) {
                if (getChildAt(0) instanceof WebView) {
                    return false;
                }
            }
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                onDrag = true;
                break;
            case MotionEvent.ACTION_UP:
                onDrag = false;
                Log(isUpScroll ? "向上滑动" : "向下滑动");
                checkBalance(isUpScroll);
                break;
        }
        return super.onTouchEvent(e);
    }

    private void checkBalance(boolean isUp) {
        if (isBalance) {
            return;
        }
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getClass() == lockClass) {
                if (!isUp) {
                    if (checkIndexInValid(true)) {
                        return;
                    }
                    smoothScroller.setToEnd(true);
                    smoothScroller.setTargetPosition(lockIndex.get(page) + 1);
                    stopScroll();
                    post(obtainPageRunnable());
                    page++;
                    Log("到第二屏");
                } else {
                    if (checkIndexInValid(false)) {
                        return;
                    }
                    smoothScroller.setToEnd(false);
                    smoothScroller.setTargetPosition(lockIndex.get(page - 1) - 1);
                    stopScroll();
                    post(obtainPageRunnable());
                    page--;
                    Log("到第一屏幕");
                }

            }
        }
    }

    private boolean checkIndexInValid(boolean isDown) {
        if (isDown) {
            return page > lockIndex.size() - 1 || page < 0;
        } else {
            return page - 1 < 0 || page - 1 > lockIndex.size() - 1;
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        if (dy > 0) {
            isUpScroll = false;
        } else {
            isUpScroll = true;
        }
        if (onDrag) {
            return;
        }
        if (dy > 0) {
            //往下滑
            if (checkIndexInValid(true)) {
                return;
            }
            lastCompletelyVisiblePos = layoutManager.findLastCompletelyVisibleItemPosition();
            lastVisiblePos = layoutManager.findLastVisibleItemPosition();
            if (lastCompletelyVisiblePos == lockIndex.get(page) - 1) {
                stopScroll();
                if (lastVisiblePos == lockIndex.get(page)) {
                    View view = layoutManager.getChildAt(getChildCount() - 1);
                    if (view != null) {
                        view.getLocationOnScreen(itemLoc);
                        int scrollPost = -(screenHeight - itemLoc[1]);
                        if (Math.abs(scrollPost) > lockHeight) {
                            return;
                        }
                        isBalance = true;
                        post(obtainAlignRunnable(scrollPost));
                        isBalance = false;
                        Log("第一屏幕到底对齐" + -(screenHeight - itemLoc[1]));
                    }
                }
            }
        } else {
            //往上滑
            if (checkIndexInValid(false)) {
                return;
            }
            firstCompletelyVisiblePos = layoutManager.findFirstCompletelyVisibleItemPosition();
            firstVisiblePos = layoutManager.findFirstVisibleItemPosition();
            if (firstCompletelyVisiblePos == lockIndex.get(page - 1) + 1) {
                stopScroll();
                if (firstVisiblePos == lockIndex.get(page - 1)) {
                    View view = layoutManager.getChildAt(1);
                    if (view != null) {
                        view.getLocationOnScreen(itemLoc);
                        int y = itemLoc[1] - location[1];
                        if (Math.abs(y) > lockHeight) {
                            return;
                        }
                        isBalance = true;
                        post(obtainAlignRunnable(y));
                        isBalance = false;
                        Log("第二屏幕到头对齐" + -y);
                    }
                }
            }
        }
        super.onScrolled(dx, dy);
    }


    /**
     * 翻页使用的
     */
    private Runnable obtainPageRunnable() {
        if (pageRunnable == null) {
            pageRunnable = new Runnable() {
                @Override
                public void run() {
                    layoutManager.startSmoothScroll(smoothScroller);
                }
            };
        }
        return pageRunnable;
    }

    /**
     * 对齐使用的
     */
    private Runnable obtainAlignRunnable(final int offset) {
        if (alignRunnable == null) {
            alignRunnable = new AlignRunnable();
        }
        alignRunnable.setOffset(offset);
        return alignRunnable;
    }

    private class AlignRunnable implements Runnable {
        private int offset;

        public void setOffset(int offset) {
            this.offset = offset;
        }

        @Override
        public void run() {
            smoothScrollBy(0, offset);
        }
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDisplayHeight(Context context) {
        if (context == null || context.getResources() == null) {
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void Log(String log) {
        Log.i(TAG, log);
    }
}
