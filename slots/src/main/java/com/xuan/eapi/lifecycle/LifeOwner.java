package com.xuan.eapi.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.xuan.eapi.LogUtil;
import com.xuan.eapi.Utils.Util;

import java.util.ArrayList;

/**
 * Author : xuan.
 * Date : 2018/8/23.
 * Description :生命周期观察者
 */

public class LifeOwner implements ILifeRegistor {
    private static final String FRAGMENT_TAG = "SLOT_LIFE_FRAGMENT_OWNER";
    private Context context;
    private ArrayList<ILifeCycle> lifes;

    public static LifeOwner init(Context context) {
        return new LifeOwner(context);
    }

    private LifeOwner(Context context) {
        this.context = context;
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            inject(context);
        } else {
            LogUtil.Error("You must init this in the UI Thread!");
        }
    }

    private void inject(Context context) {
        if (context instanceof FragmentActivity) {
            injectFragment((FragmentActivity) context);
        } else if (context instanceof Activity) {
            injectFragment((Activity) context);
        } else if (context instanceof ContextWrapper) {
            inject(((ContextWrapper) context).getBaseContext());
        }
    }

    private void injectFragment(Activity activity) {
        android.app.FragmentManager fm = activity.getFragmentManager();
        createLifeFragment(fm);
    }

    private void createLifeFragment(android.app.FragmentManager fm) {
        android.app.Fragment fragment = fm.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = LifeFragment.newInstance();
            fm.beginTransaction().add(fragment, FRAGMENT_TAG).commitAllowingStateLoss();
        }
    }

    private void injectFragment(FragmentActivity activity) {
        FragmentManager fm = activity.getSupportFragmentManager();
        createLifeFragment(fm);
    }

    /**
     * 创建生命周期监听Fragment
     */
    private void createLifeFragment(FragmentManager fm) {
        Fragment fragment = fm.findFragmentByTag(FRAGMENT_TAG);
        if (fragment == null) {
            fragment = LifeV4Fragment.newInstance();
            fm.beginTransaction().add(fragment, FRAGMENT_TAG).commitAllowingStateLoss();
        }
    }

    @Override
    public void pushLife(ILifeCycle lifeCycle) {
        if (lifes == null) {
            lifes = new ArrayList<>();
        }
        lifes.add(lifeCycle);
    }
}
