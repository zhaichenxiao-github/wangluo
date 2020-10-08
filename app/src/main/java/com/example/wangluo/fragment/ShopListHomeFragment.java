package com.example.wangluo.fragment;


import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.wangluo.R;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeIdBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.mvp.CategoryConstacts;
import com.example.wangluo.mvp.CategoryPresenterIml;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopListHomeFragment extends BaseMvpFragment<CategoryConstacts.CategoryTabsView, CategoryPresenterIml> implements CategoryConstacts.CategoryTabsView {
    @BindView(R.id.iv_goodsDetailOne)
    ImageView ivGoodsDetailOne;
    @BindView(R.id.iv_goodsDetailTwo)
    ImageView ivGoodsDetailTwo;
    private int id;

    public ShopListHomeFragment(int id) {
        this.id = id;
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_shop_list_home, container, false);
//        initView(view);
//        return view;
//    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_shop_list_home;
    }


    @Override
    protected void initGetData() {
        ShopHomeIdBean shopHomeIdBean = new ShopHomeIdBean();
        shopHomeIdBean.setGoodsId(id);
        mPresenter.getShopHome(shopHomeIdBean);
    }

    @Override
    protected CategoryPresenterIml initPresenter() {
        return new CategoryPresenterIml();
    }

    @Override
    public void showShopHomeList(ShopHomeList shopHomeList) {
        String goodsDetailOne = shopHomeList.getGoodsDetailOne();
        String goodsDetailTwo = shopHomeList.getGoodsDetailTwo();
        Glide.with(getActivity()).load(goodsDetailOne).into(ivGoodsDetailOne);
        Glide.with(getActivity()).load(goodsDetailTwo).into(ivGoodsDetailTwo);
    }

    @Override
    public void showShopList(List<ShopBean> shopBean) {

    }

    @Override
    public void showCategoryTabs(List<CateGoryDemo> tabs) {

    }

    @Override
    public void showCategoryList(List<CateGoryListBean> lists) {

    }

    @Override
    public void showLogin(String loginBean) {

    }


    @Override
    public void onError(String msg, int code) {

    }
}
