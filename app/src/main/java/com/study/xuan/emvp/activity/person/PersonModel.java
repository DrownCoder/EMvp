package com.study.xuan.emvp.activity.person;

import com.xuan.annotation.BindType;
import com.xuan.eapi.helper.binder.HandlerType;

/**
 * Author : xuan.
 * Date : 2018/10/30.
 * Description :the description of this file
 */
@BindType(1)
public class PersonModel implements HandlerType {
    public String name;
    public int type;

    @Override
    public int handlerType() {
        return type;
    }
}
