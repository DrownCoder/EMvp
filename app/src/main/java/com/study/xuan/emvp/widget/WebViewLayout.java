package com.study.xuan.emvp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.ComponentType;
import com.xuan.eapi.component.Component;
import com.xuan.eapi.component.IComponentBind;

/**
 * Author : xuan.
 * Date : 2018/6/14.
 * Description :the description of this file
 */
@ComponentType(ComponentId.WEBVIEW)
public class WebViewLayout extends WebView implements IComponentBind {
    public WebViewLayout(Context context) {
        this(context, null);
    }

    public WebViewLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        loadUrl("http://www.dangdang.com/");
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return true;
    }

    @Override
    public void onBind(int pos, Object item) {

    }

    @Override
    public void onUnBind() {

    }
}
