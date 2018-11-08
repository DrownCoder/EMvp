package com.study.xuan.emvp.activity.common;

import android.widget.Toast;

import com.xuan.eapi.context.SlotContext;
import com.xuan.eapi.viewmodel.IPresent;

/**
 * Author : xuan.
 * Date : 2018/11/8.
 * Description :the description of this file
 */
public class CommonLogic implements IPresent {
    private SlotContext slotContext;

    public CommonLogic(SlotContext context) {
        this.slotContext = context;
    }

    public void pageTransfer() {
        Toast.makeText(slotContext.getContext(), "跳转页面", Toast.LENGTH_SHORT).show();
    }
}
