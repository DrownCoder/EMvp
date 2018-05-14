package com.study.xuan.emvp;

public interface PresenterFactory<T extends IBasePresenter> {
      T create(Object presenter);
  }