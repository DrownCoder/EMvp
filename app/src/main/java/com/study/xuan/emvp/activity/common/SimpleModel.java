package com.study.xuan.emvp.activity.common;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/11/9.
 * Description :the description of this file
 */
@BindType(ComponentId.SIMPLE)
public class SimpleModel {
    public String name;

    public SimpleModel(String name) {
        this.name = name;
    }
}
