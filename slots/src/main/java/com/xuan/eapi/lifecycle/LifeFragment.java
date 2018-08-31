package com.xuan.eapi.lifecycle;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author : xuan.
 * Date : 2018/8/23.
 * Description :the description of this file
 */

public class LifeFragment extends Fragment implements InjectLifeListener {
    private ILifeCycle lifeCycle;
    public static LifeFragment newInstance() {
        LifeFragment fragment = new LifeFragment();
        return fragment;
    }

    @Override
    public void setLifeCycle(ILifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (lifeCycle != null) {
            lifeCycle.onCreate();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        if (lifeCycle != null) {
            lifeCycle.onDestroy();
        }
        super.onDestroy();
    }
}
