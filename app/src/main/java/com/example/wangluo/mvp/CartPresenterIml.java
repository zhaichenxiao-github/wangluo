package com.example.wangluo.mvp;

import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.wangluo.bean.CartBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public class CartPresenterIml extends BasePresenter<CartConstacts.CartView> implements ModelCartBack<List<CartBean>>,CartConstacts.CartPresenter {
    @Override
    public void getCart() {
        ModelFractory.createModel(CartModelIml.class).OnSuccessCart(this, getLifecycle());
    }

    @Override
    public void OnSuccessCart(List<CartBean> list) {
        mView.OnSuccessCart(list);
    }

    @Override
    public void OnError() {

    }
}
