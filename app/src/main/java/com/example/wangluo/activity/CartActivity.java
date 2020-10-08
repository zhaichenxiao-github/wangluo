package com.example.wangluo.activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wangluo.R;
import com.example.wangluo.fragment.CartFragment;

public class CartActivity extends AppCompatActivity {

    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl, new CartFragment());
        fragmentTransaction.commit();
    }
}
