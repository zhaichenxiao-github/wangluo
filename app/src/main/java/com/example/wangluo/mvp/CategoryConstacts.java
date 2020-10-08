package com.example.wangluo.mvp;

import androidx.appcompat.view.menu.ShowableListMenu;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.model.BaseModelCallBack;
import com.example.mvplibrary.view.BaseView;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryIdBean;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.LoginIdBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeIdBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.bean.ShopIdBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface CategoryConstacts {
    interface CategoryTabsView extends BaseView{
        void showShopHomeList(ShopHomeList shopHomeList);
        void showShopList(List<ShopBean> shopBean);
        void showCategoryTabs(List<CateGoryDemo> tabs);
        void showCategoryList(List<CateGoryListBean> lists);
        void showLogin(String loginBean);
        void onError(String msg,int code);
    }

    interface Categorypresenter{
        void getLoginId(LoginIdBean loginIdBean);
        void getShopHome(ShopHomeIdBean shopHomeIdBean);
        void getShopList(ShopIdBean shopIdBean);
        void getCategoryTab(CateGoryIdBean cateGoryIdBean);
        void getCategoryList(CateGoryIdBean goryIdBean);
    }
    interface CategoryModel extends BaseModel{
        void showLogin(LoginIdBean loginIdBean,BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> beanBaseModelCallBack,LifecycleProvider lifecycleProvider);
        void showShopHomeList(ShopHomeIdBean shopHomeIdBean,BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> baseModelCallBack,LifecycleProvider lifecycleProvider);
        void showShopList(ShopIdBean shopIdBean,BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> baseModelCallBack,LifecycleProvider lifecycleProvider);
        void showCategoryTab(CateGoryIdBean cateGoryIdBean, BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean> ,ShopHomeList,LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider);
        void showCategoryList(CateGoryIdBean cateGoryIdBean, BaseModelCallBack<List<CateGoryDemo>,List<CateGoryListBean>,List<ShopBean>,ShopHomeList,LoginBean> baseModelCallBack, LifecycleProvider lifecycleProvider);
        void onError(String msg,int code);
    }
}
