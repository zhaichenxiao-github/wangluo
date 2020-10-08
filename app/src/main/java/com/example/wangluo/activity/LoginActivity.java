package com.example.wangluo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.httplibrary.HttpGlobalConfig;
import com.example.httplibrary.SpUtils;
import com.example.mvplibrary.base.BaseMvpActivity;
import com.example.wangluo.R;
import com.example.wangluo.app.ShopApplication;
import com.example.wangluo.bean.CateGoryDemo;
import com.example.wangluo.bean.CateGoryListBean;
import com.example.wangluo.bean.LoginBean;
import com.example.wangluo.bean.LoginIdBean;
import com.example.wangluo.bean.ShopBean;
import com.example.wangluo.bean.ShopHomeList;
import com.example.wangluo.mvp.CategoryConstacts;
import com.example.wangluo.mvp.CategoryPresenterIml;
import com.example.wangluo.utils.Concasts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class LoginActivity extends BaseMvpActivity<CategoryConstacts.CategoryTabsView, CategoryPresenterIml> implements CategoryConstacts.CategoryTabsView {

    @BindView(R.id.iv_login_tuichu)
    ImageView ivLoginTuichu;
    @BindView(R.id.tv_regirst)
    TextView tvRegirst;
    @BindView(R.id.iv_user_name)
    ImageView ivUserName;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.iv_user_pwd)
    ImageView ivUserPwd;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_wangji)
    TextView tvWangji;
    @BindView(R.id.btn_yanzheng)
    Button btnYanzheng;
    private LoginIdBean loginIdBean;
    private String userName;
    private String userPwd;
    private String user;
    private String user1;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        ButterKnife.bind(this);
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_login;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getString(List<String> strings) {
        Log.e("aaaa", "getString: " + strings);
    }

    @Override
    protected void initData() {
        btnYanzheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btnYanzheng.setClickable(false);
                        btnYanzheng.setText(millisUntilFinished/1000 + "s");
                    }

                    @Override
                    public void onFinish() {
                        btnYanzheng.setClickable(true);
                        btnYanzheng.setText("获取验证码");
                    }
                }.start();
            }
        });
        ivLoginTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvRegirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 200);
            }
        });

        tvWangji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForGetActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = etUserName.getText().toString();
                userPwd = etUserPwd.getText().toString();
                if (userName != null && userPwd != null) {
                    loginIdBean = new LoginIdBean();
                    loginIdBean.setPushId(HttpGlobalConfig.getInsance().getAppkey(Concasts.JPUSHREGISTID).toString());
                    loginIdBean.setPwd(userPwd);
                    loginIdBean.setMobile(userName);
                    mPresenter.getLoginId(loginIdBean);
                }
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
        Log.e("showLogin", "showLogin: "+loginBean );
        String string = etUserName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("user", string);
        setResult(400,intent);
        finish();
    }


    @Override
    public void onError(String msg, int code) {
        Log.e("msg", "onError: " + msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
            user1 = data.getStringExtra("user");
            String pwd = data.getStringExtra("pwd");
            etUserName.setText(user1, null);
            etUserPwd.setText(pwd, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
