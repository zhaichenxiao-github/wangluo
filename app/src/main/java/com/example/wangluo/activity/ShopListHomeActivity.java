package com.example.wangluo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wangluo.R;
import com.example.wangluo.fragment.ShopListHomeFragment;
import com.example.wangluo.fragment.ShopListshopFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListHomeActivity extends AppCompatActivity {

    @BindView(R.id.iv_tuichu)
    ImageView ivTuichu;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    private ArrayList<Fragment> fragments;
    private ShopListshopFragment shopListshopFragment;
    private ShopListHomeFragment shopListHomeFragment;
    private ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        int id = getIntent().getIntExtra("id", 0);
        fragments = new ArrayList<>();
        shopListshopFragment = new ShopListshopFragment(id);
        shopListHomeFragment = new ShopListHomeFragment(id);
        fragments.add(shopListshopFragment);
        fragments.add(shopListHomeFragment);
        titles = new ArrayList<>();
        titles.add("商品");
        titles.add("详情");
        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        });
        tabs.setupWithViewPager(vp);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (null != customView && customView instanceof TextView) {
                    ((TextView) customView).setTextColor(ContextCompat.getColor(ShopListHomeActivity.this, R.color.colorWhite));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (null != customView && customView instanceof TextView) {
                    ((TextView) customView).setTextColor(ContextCompat.getColor(ShopListHomeActivity.this, R.color.colorBlack));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
