package com.example.wangluo.mvp;

import com.example.mvplibrary.model.BaseModelCallBack;
import com.example.mvplibrary.model.ModelFractory;
import com.example.mvplibrary.presenter.BasePresenter;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryIdBean;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.LoginIdBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeIdBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.bean.ShopIdBean;

import java.util.List;

public class CategoryPresenterIml extends BasePresenter<CategoryConstacts.CategoryTabsView> implements CategoryConstacts.Categorypresenter, BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean>, ShopHomeList, LoginBean>{
    @Override
    public void onSuccess(List<CateGoryDemo> strings) {
        mView.showCategoryTabs(strings);
    }

    @Override
    public void onSuccessList(List<CateGoryListBean> cateGoryListBeans) {
        mView.showCategoryList(cateGoryListBeans);
    }

    @Override
    public void onSuccessShopList(List<ShopBean> shopBeans) {
        mView.showShopList(shopBeans);
    }

    @Override
    public void onSuccessShopHomeList(ShopHomeList shopHomeList) {
        mView.showShopHomeList(shopHomeList);
    }

    @Override
    public void onSuccessLogin(LoginBean loginBean) {
        if (loginBean!=null){
            mView.showLogin("登陆成功");
        }
    }


    @Override
    public void onFail(String msg, int code) {
        mView.onError(msg, code);
    }

    @Override
    public void cancle() {

    }


    @Override
    public void getLoginId(LoginIdBean loginIdBean) {
        ModelFractory.createModel(CategoryModelIml.class).showLogin(loginIdBean, this, getLifecycle());
    }

    @Override
    public void getShopHome(ShopHomeIdBean shopHomeIdBean) {
        ModelFractory.createModel(CategoryModelIml.class).showShopHomeList(shopHomeIdBean, this, getLifecycle());
    }

    @Override
    public void getShopList(ShopIdBean shopIdBean) {
        ModelFractory.createModel(CategoryModelIml.class).showShopList(shopIdBean, this, getLifecycle());
    }

    @Override
    public void getCategoryTab(CateGoryIdBean cateGoryIdBean) {
        ModelFractory.createModel(CategoryModelIml.class).showCategoryTab(cateGoryIdBean, this, getLifecycle());
    }

    @Override
    public void getCategoryList(CateGoryIdBean goryIdBean) {
        ModelFractory.createModel(CategoryModelIml.class).showCategoryList(goryIdBean, this, getLifecycle());
    }
}
