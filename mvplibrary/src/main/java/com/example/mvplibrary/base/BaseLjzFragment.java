package com.example.mvplibrary.base;

import com.example.mvplibrary.presenter.BasePresenter;
import com.example.mvplibrary.view.BaseView;

public abstract class BaseLjzFragment<V extends BaseView,P extends BasePresenter<V>> extends BaseFragment {

    boolean mIsPrepare=false;//初始化视图
    boolean mIsVisible=false;  //不可见
    boolean mIsFirstLoad=true;  //第一次加载

}
