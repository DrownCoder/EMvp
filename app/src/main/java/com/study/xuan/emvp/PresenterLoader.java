package com.study.xuan.emvp;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

public class PresenterLoader extends Loader<IBasePresenter> {
 
    private final PresenterFactory<IBasePresenter> factory = null;
    private IBasePresenter presenter;

    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don'IBasePresenter use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public PresenterLoader(Context context) {
        super(context);
    }

    // 省略构造方法
 
    @Override
    protected void onStartLoading() {
 
        // 如果已经有Presenter实例那就直接返回
        if (presenter != null) {
            deliverResult(presenter);
            return;
            }
 
        // 如果没有
        forceLoad();
    }
 
    @Override
    protected void onForceLoad() {
        // 通过工厂来实例化Presenter
        //presenter = factory.create();
 
        // 返回Presenter
        Log.i("---------", "new Presenter");
        presenter = new Presenter();
        deliverResult(presenter);
    }
 
    @Override
    protected void onReset() {
        presenter.onDestroy();
        presenter = null;
    }
}