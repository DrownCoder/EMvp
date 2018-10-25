package com.xuan.eapi.helper;

import com.xuan.eapi.rule.IComponentRule;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :获取注解的映射表
 */

public class Slots {
    public static volatile Slots instance;
    private IComponentRule componentRule;
    //private IPresenterRule presenterRule;

    public static Slots getInstance() {
        if (instance == null) {
            synchronized (Slots.class) {
                if (instance == null) {
                    instance = new Slots();
                }
            }
        }
        return instance;
    }

    private Slots() {
        initRule();
    }

    private void initRule() {
        try {
            Class componentRule = Class.forName("com.xuan.eapi.rule.ComponentRule");
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
