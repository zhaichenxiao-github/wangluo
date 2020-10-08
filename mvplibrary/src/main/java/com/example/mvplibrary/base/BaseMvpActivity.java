package com.example.mvplibrary.base;

import com.example.mvplibrary.presenter.BasePresenter;
import com.example.mvplibrary.view.BaseView;

public abstract class BaseMvpActivity<V extends BaseView ,P extends BasePresenter<V>> extends BaseActivity {
    public P mPresenter;

    @Override
    protected void initEvent() {
        mPresenter=initPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.onDestoryView();
            mPresenter=null;
        }
    }
}
