package com.xuan.eapi;

import com.xuan.eapi.rule.IComponentRule;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :the description of this file
 */

public class Slots {
    public static volatile Slots instance;
    private IComponentRule componentRule;

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
            Class rule = Class.forName("com.xuan.eapi.rule.ComponentRule");
            componentRule = (IComponentRule) rule.newInstance();
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
