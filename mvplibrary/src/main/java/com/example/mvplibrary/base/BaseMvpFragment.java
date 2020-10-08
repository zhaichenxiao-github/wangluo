package com.example.mvplibrary.base;

import com.example.mvplibrary.presenter.BasePresenter;
import com.example.mvplibrary.view.BaseView;

public abstract class BaseMvpFragment<V extends BaseView,P extends BasePresenter<V>> extends BaseFragment {

    public P mPresenter;
    @Override
    public void initData() {
        mPresenter=initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
            initGetData();
        }
    }

    protected abstract void initGetData();


    protected abstract P initPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter!=null){
            mPresenter.onDestoryView();
            mPresenter=null;
        }
    }
}
