package com.study.xuan.emvp.model;

import com.study.xuan.emvp.ComponentId;
import com.study.xuan.emvp.R;
import com.xuan.annotation.BindType;

/**
 * Author : xuan.
 * Date : 2018/5/17.
 * Description :the description of this file
 */
@BindType(ComponentId.USER_INFO_LAYOUT)
public class UserInfo {
    public int imgUrl = R.drawable.ic_launcher_foreground;
    public String name = "名字";
}