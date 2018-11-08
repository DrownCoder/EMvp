package com.study.xuan.emvp.model;

import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/9/1.
 * Description :the description of this file
 */
@BindType(0)
public class Text {
    public String title;
    public int eventId;

    public Text(String item,int id) {
        title = item;
        this.eventId = id;
    }

    public Text(String title) {
        this.title = title;
    }
}
