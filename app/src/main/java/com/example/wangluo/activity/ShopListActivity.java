package com.example.wangluo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvplibrary.base.BaseMvpActivity;
import com.example.wangluo.R;
import com.example.wangluo.adapter.ShopListAdapter;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.bean.ShopIdBean;
import com.example.wangluo.mvp.CategoryConstacts;
import com.example.wangluo.mvp.CategoryPresenterIml;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListActivity extends BaseMvpActivity<CategoryConstacts.CategoryTabsView, CategoryPresenterIml> implements CategoryConstacts.CategoryTabsView, OnRefreshLoadMoreListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.sml)
    SmartRefreshLayout sml;
    @BindView(R.id.iv_tuichu)
    ImageView ivTuichu;
    private ArrayList<ShopBean> list = new ArrayList<>();
    private ShopListAdapter shopListAdapter;
    private ArrayList<ShopBean> shopBeans;
    private ShopIdBean shopIdBean;
    private int id;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_list);
//    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_shop_list;
    }

    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        shopIdBean = new ShopIdBean();
        shopIdBean.setCategoryId(id);
        shopIdBean.setPageNo(1);
        mPresenter.getShopList(shopIdBean);
        sml.setOnRefreshLoadMoreListener(this);
        ivTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected CategoryPresenterIml initPresenter() {
        return new CategoryPresenterIml();
    }

    @Override
    public void showShopHomeList(ShopHomeList shopHomeList) {

    }

    @Override
    public void showShopList(List<ShopBean> shops) {
        list.addAll(shops);
        Log.e("shop", "showShopList: " + shops.toString());
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        shopListAdapter = new ShopListAdapter(this, R.layout.layout_shop_list, list);
        rv.setAdapter(shopListAdapter);
        shopListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ShopListActivity.this, ShopListHomeActivity.class);
                ShopBean shopBean = list.get(position);
                int categoryId = shopBean.getId();
                intent.putExtra("id" , categoryId);
                startActivity(intent);
            }
        });
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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//        initData();
        if (list.size() > 0) {
            list.clear();
            shopIdBean.setPageNo(1);
            mPresenter.getShopList(shopIdBean);
            shopIdBean.setPageNo(2);
            mPresenter.getShopList(shopIdBean);
//            shopListAdapter.notifyDataSetChanged();
        }
        sml.finishLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//        page++;
//        initData();
        sml.finishRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
}
