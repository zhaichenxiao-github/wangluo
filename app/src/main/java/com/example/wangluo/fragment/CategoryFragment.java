package com.example.wangluo.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.httplibrary.client.HttpClient;
import com.example.httplibrary.utils.JsonUtils;
import com.example.httplibrary.utils.LogUtils;
import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.wangluo.R;
import com.example.wangluo.activity.ShopListActivity;
import com.example.wangluo.adapter.CategoryAdapter;
import com.example.wangluo.adapter.CategoryListAdapter;
import com.example.wangluo.bean.CateGoryCallback;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryIdBean;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.bean.ShopIdBean;
import com.example.wangluo.mvp.CategoryConstacts;
import com.example.wangluo.mvp.CategoryPresenterIml;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.FactoryConfigurationError;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseMvpFragment<CategoryConstacts.CategoryTabsView, CategoryPresenterIml> implements CategoryConstacts.CategoryTabsView {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rv_two)
    RecyclerView rvTwo;
    private CategoryAdapter categoryAdapter;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<CateGoryDemo> list;
    private CateGoryIdBean cateGoryIdBean;
    private CateGoryDemo cateGoryDemo;


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initGetData() {
        cateGoryIdBean = new CateGoryIdBean();
        cateGoryIdBean.setParentId(0);
        mPresenter.getCategoryTab(cateGoryIdBean);
        cateGoryIdBean.setParentId(1);
        mPresenter.getCategoryList(cateGoryIdBean);
    }

    @Override
    protected CategoryPresenterIml initPresenter() {
        return new CategoryPresenterIml();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.fragment_category;
    }




    @Override
    public void showShopHomeList(ShopHomeList shopHomeList) {

    }

    @Override
    public void showShopList(List<ShopBean> shopBean) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void showCategoryTabs(List<CateGoryDemo> tabs) {
        Log.e("44444444", "showCategoryTabs: "+tabs.toString() );
        list = new ArrayList<>();
        list.addAll(tabs);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(tabs);
        rv.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
        categoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                cateGoryIdBean.setParentId(position+1);
                mPresenter.getCategoryList(cateGoryIdBean);
                categoryAdapter.ischeck.set(position, true);
                if (categoryAdapter.isLayoutPosition!=-1){
                    categoryAdapter.ischeck.set(categoryAdapter.isLayoutPosition, false);
                    categoryAdapter.notifyItemChanged(categoryAdapter.isLayoutPosition);
                }
                categoryAdapter.notifyItemChanged(position);
                int parentId = cateGoryIdBean.getParentId();
//                if (position>=2){
//                    rvTwo.setVisibility(View.GONE);
//                }
            }
        });
    }

    @Override
    public void showCategoryList(List<CateGoryListBean> lists) {
//        Log.e("6666", "showCategoryList: "+lists.toString() );
        rvTwo.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(getActivity(),R.layout.layout_multiitem,lists);
        rvTwo.setAdapter(categoryListAdapter);
        categoryListAdapter.notifyDataSetChanged();
        categoryListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), ShopListActivity.class);
                CateGoryListBean cateGoryListBean = lists.get(position);
                int id = cateGoryListBean.getId();
                intent.putExtra("id", id);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void showLogin(String loginBean) {

    }


    @Override
    public void onError(String msg, int code) {
        LogUtils.e(msg);
    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_category, container, false);
//        initView(view);
//        return view;
//    }

}