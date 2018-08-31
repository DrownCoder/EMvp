package com.xuan.eapi.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Author : xuan.
 * Date : 2018/8/23.
 * Description :the description of this file
 */

public class LifeV4Fragment extends Fragment implements InjectLifeListener {
    private ILifeCycle lifeCycle;

    @Override
    public void setLifeCycle(ILifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public static LifeV4Fragment newInstance() {
        LifeV4Fragment fragment = new LifeV4Fragment();
        return fragment;
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
