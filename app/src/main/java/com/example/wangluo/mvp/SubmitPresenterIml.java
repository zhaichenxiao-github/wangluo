package com.example.wangluo.mvp;

import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.wangluo.bean.SubParams;
import com.example.wangluo.bean.SubmitBean;

import java.util.List;

public class SubmitPresenterIml extends BasePresenter<SubmitConcast.SubmitView> implements ModelSubCallBack<List<SubmitBean>>,SubmitConcast.SubmitPresenter {
    @Override
    public void OnSuccessSub(List<SubmitBean> list) {
        mView.OnSuccessSub(list);
    }

    @Override
    public void OnError(String msg, int code) {

    }

    @Override
    public void getSub(SubParams subParams) {
        ModelFractory.createModel(SubmitModelIml.class).OnSuccessSub(subParams,this, getLifecycle());
    }
}
