package com.xuan.eapi.helper;

import android.support.v4.util.LruCache;

import com.xuan.eapi.rule.IComponentRule;

import java.lang.reflect.Constructor;

/**
 * Author : xuan.
 * Date : 2018/5/31.
 * Description :获取注解的映射表
 */

public class SlotsMap {
    private static final int CONSTRUCTOR_CACHE = 10;
    public static volatile SlotsMap instance;
    private IComponentRule componentRule;
    private static final LruCache<Class<?>, Constructor> constructorMap =
            new LruCache<Class<?>,Constructor>(CONSTRUCTOR_CACHE);
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

    public Constructor obtainConstructor(Class<?> clazz) {
        return  constructorMap.get(clazz);
    }

    public void cacheConstructor(Class<?> clazz, Constructor constructor) {
        constructorMap.put(clazz, constructor);
    }

    public IComponentRule obtainRule() {
        return componentRule;
    }
}
