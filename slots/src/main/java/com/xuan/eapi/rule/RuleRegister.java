package com.xuan.eapi.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xuan.
 * Date : 2018/10/30.
 * Description :注册规则
 */
public class RuleRegister implements IRuleRegister {
    private List<Class<?>> rules;
    @Override
    public void registerRule(Class<?> clazz) {
        if (obtainRules() == null) {
            rules = new ArrayList<>();
        }
        obtainRules().add(clazz);
    }

    @Override
    public List<Class<?>> obtainRules() {
        return rules;
    }
}
