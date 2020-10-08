package com.example.wangluo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httplibrary.SpUtils;
import com.example.wangluo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetUpActivity extends AppCompatActivity {

    @BindView(R.id.iv_login_tuichu)
    ImageView ivLoginTuichu;
    @BindView(R.id.rll_login)
    RelativeLayout rllLogin;
    @BindView(R.id.rl_yonghu)
    RelativeLayout rlYonghu;
    @BindView(R.id.rl_yijian)
    RelativeLayout rlYijian;
    @BindView(R.id.rl_guanyu)
    RelativeLayout rlGuanyu;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.tv_yh)
    TextView tvYh;
    @BindView(R.id.tv_yj)
    TextView tvYj;
    @BindView(R.id.tv_gy)
    TextView tvGy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        String tv_yh = tvYh.getText().toString();
        String tv_gy = tvGy.getText().toString();
        String tv_yj = tvYj.getText().toString();
        rlYonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetUpActivity.this,tv_yh,Toast.LENGTH_SHORT ).show();
            }
        });
        rlGuanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetUpActivity.this,tv_gy,Toast.LENGTH_SHORT ).show();
            }
        });
        rlYijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SetUpActivity.this,tv_yj,Toast.LENGTH_SHORT ).show();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivLoginTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtils.remove(SetUpActivity.this, "share_data", "token");
                finish();
            }
        });
    }
}
