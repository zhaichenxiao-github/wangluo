package com.example.wangluo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.httplibrary.SpUtils;
import com.example.wangluo.R;
import com.example.wangluo.activity.LoginActivity;
import com.example.wangluo.activity.SetUpActivity;
import com.example.wangluo.app.ShopApplication;

import butterknife.BindView;

/**
 * p
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    @BindView(R.id.shouhuo)
    LinearLayout shouhuo;
    @BindView(R.id.fenxiang)
    LinearLayout fenxiang;
    LinearLayout shezhi;
    private TextView tv_login;
    private String user;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                boolean contains = SpUtils.contains(ShopApplication.getApp(), "share_data", "");
//                if (contains){
//                    tv_login.setText(user);
//                }else {
//                    tv_login.setText("登陆/注册");
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivityForResult(intent, 100);
//                }
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    private void initView(View view) {
        tv_login = view.findViewById(R.id.tv_login);
        shezhi = view.findViewById(R.id.shezhi);
        shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 400) {
            user = data.getStringExtra("user");
        }
    }
}
