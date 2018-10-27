package com.xuan.eapi.helper;

import com.xuan.annotation.ViewInfo;
import com.xuan.eapi.rule.IComponentRule;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :获取注解的映射表
 */

public class SlotsMap {
    public static volatile SlotsMap instance;
    private IComponentRule componentRule;
    //private IPresenterRule presenterRule;

    public static SlotsMap getInstance() {
        if (instance == null) {
            synchronized (SlotsMap.class) {
                if (instance == null) {
                    instance = new SlotsMap();
                }
            }
        }
        return instance;
    }

    private SlotsMap() {
        initRule();
    }

    private void initRule() {
        try {
            //Class componentRule = Class.forName("com.xuan.eapi.rule.ComponentRule");
            Class componentRule = Class.forName("com.study.xuan.emvp.TempRule");
            //Class presenterRule = Class.forName("com.xuan.eapi.rule.PresenterRule");
            this.componentRule = (IComponentRule) componentRule.newInstance();
            //this.presenterRule = (IPresenterRule) presenterRule.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public IComponentRule obtainRule() {
        return componentRule;
    }
}
