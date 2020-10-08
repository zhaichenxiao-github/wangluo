package com.example.wangluo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wangluo.adapter.VpAdapter;
import com.example.wangluo.fragment.CartFragment;
import com.example.wangluo.fragment.CategoryFragment;
import com.example.wangluo.fragment.HomeFragment;
import com.example.wangluo.fragment.MsgFragment;
import com.example.wangluo.fragment.UserFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.vp)
    ViewPager vp;

    private int lastSelectPosition;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ShopApplication.getRefWatcher().watch(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //单例测试leakcanry
//        SingLeton singLeton = SingLeton.newInstance(this);
        initView();
    }


    private void initView() {
//
//        bvb.setMode(BottomNavigationBar.MODE_FIXED);
//        bvb.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
//        bvb.setBarBackgroundColor(R.color.colorPrimary);
//
//        bvb.setActiveColor(R.color.colorBlack)
//                .setInActiveColor(R.color.colorPrimaryDark);
//        bvb.addItem(new BottomNavigationItem(R.drawable.btn_nav_home_press,"主页" ))
//                .addItem(new BottomNavigationItem(R.drawable.btn_nav_category_press, "分类"))
//                .addItem(new BottomNavigationItem(R.drawable.btn_nav_cart_press, "购物车"))
//                .addItem(new BottomNavigationItem(R.drawable.btn_nav_msg_press,"消息"))
//                .addItem(new BottomNavigationItem(R.drawable.btn_nav_user_press, "我的"))
//                .setFirstSelectedPosition(0)//设置第一个为选中状态
//                .initialise();//必须调用该方法才会生效
//        bvb.setTabSelectedListener(this);
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new CategoryFragment());
        list.add(new CartFragment());
        list.add(new MsgFragment());
        list.add(new UserFragment());
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), list);
        vp.setAdapter(vpAdapter);
        tabs.setupWithViewPager(vp);
        tabs.getTabAt(0).setCustomView(getCustonView(R.drawable.select_tab_home, "首页"));
        tabs.getTabAt(1).setCustomView(getCustonView(R.drawable.select_tab_category, "分类"));
        tabs.getTabAt(2).setCustomView(getCustonView(R.drawable.select_tab_cart, "购物车"));
        tabs.getTabAt(3).setCustomView(getCustonView(R.drawable.select_tab_msg, "消息"));
        tabs.getTabAt(4).setCustomView(getCustonView(R.drawable.select_tab_user, "我的"));
    }
    public View getCustonView(int img,String st){
        View view = LayoutInflater.from(this).inflate(R.layout.layout_tablayout, null);
        ImageView iv = view.findViewById(R.id.iv);
        TextView tv = view.findViewById(R.id.tv);
        iv.setImageResource(img);
        tv.setText(st);
        return view;
    }


}
