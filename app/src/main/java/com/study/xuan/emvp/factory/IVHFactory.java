package com.study.xuan.emvp.factory;

import com.study.xuan.emvp.vh.EViewHolder;

/**
 * Author : xuan.
 * Date : 2018/5/14.
 * Description :the description of this file
 */

public interface IVHFactory {
    EViewHolder createViewHolder(int type);
}
