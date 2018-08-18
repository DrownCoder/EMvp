package com.xuan.eapi.helper.event;

import android.view.View;

/**
 * Author : xuan.
 * Date : 2018/8/18.
 * Description :注入事件中心
 */

public interface InjectCallback {
    void injectCallback(View.OnClickListener onClickListener);
}
