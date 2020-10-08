package com.example.wangluo.mvp;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.view.BaseView;
import com.example.wangluo.bean.CartBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CartConstacts {
    interface CartView extends BaseView{
        void OnSuccessCart(List<CartBean> list);

    }
    interface CartPresenter{
        void getCart();
    }
    interface CartModel extends BaseModel{
        void OnSuccessCart(ModelCartBack<List<CartBean>> back, LifecycleProvider lifecycleProvider);
    }
}
