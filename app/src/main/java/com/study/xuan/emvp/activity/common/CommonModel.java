package com.study.xuan.emvp.activity.common;

import com.study.xuan.emvp.ComponentId;
import com.xuan.eapi.helper.binder.HandlerType;

/**
 * Author : xuan.
 * Date : 2018/11/8.
 * Description :the description of this file
 */
public class CommonModel implements HandlerType {
    public int pos;
    public String tips;
    public String eventId;

    @Override
    public int handlerType() {
        if (pos > 8) {
            pos = pos % 8;
        }
        switch (pos) {
            case 1:
                return ComponentId.VRCY;
            case 3:
                return ComponentId.DIVIDER;
            case 4:
                return ComponentId.WEBVIEW;
            case 5:
                return ComponentId.TEXT_IMG;
            case 6:
                return ComponentId.IMAGE_TWO_VH;
            case 7:
                return ComponentId.IMAGE_VH;
            case 8:
                return ComponentId.USER_INFO_LAYOUT;
        }
        return ComponentId.VRCY;
    }
}
