package com.study.xuan.emvp.model;

import com.study.xuan.emvp.ComponentId;
import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/9/1.
 * Description :the description of this file
 */
@BindType(ComponentId.SINGLE_TEXT)
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
