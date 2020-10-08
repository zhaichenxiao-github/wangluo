package com.example.wangluo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httplibrary.utils.JsonUtils;
import com.example.wangluo.R;
import com.example.wangluo.bean.CateGoryResponse;
import com.example.wangluo.bean.ForgetPwdIdBean;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ForGetActivity extends AppCompatActivity {

    @BindView(R.id.iv_login_tuichu)
    ImageView ivLoginTuichu;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.btn_yanzheng)
    Button btnYanzheng;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_get);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                String userPwd = etUserPwd.getText().toString();
                ForgetPwdIdBean forgetPwdIdBean = new ForgetPwdIdBean();
                forgetPwdIdBean.setMobile(userName);
                forgetPwdIdBean.setVerifyCode(userPwd);
                OkHttpClient build = new OkHttpClient.Builder().build();
                MediaType parse = MediaType.parse("application/json;charset=utf-8");
                RequestBody requestBody = RequestBody.create(parse, JsonUtils.classToJson(forgetPwdIdBean));
                Request request = new Request.Builder()
                        .url("http://169.254.189.205:8080/kotlin/userCenter/forgetPwd")
                        .post(requestBody)
                        .build();
                build.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("call", "onFailure: ."+e.getMessage() );
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        CateGoryResponse cateGoryResponse = JsonUtils.jsonToClassId(string, CateGoryResponse.class);
                        int status = cateGoryResponse.getStatus();
                        String message = cateGoryResponse.getMessage();
                        if (status==0){
                            Log.e("message", "onResponse: "+message );
                            Intent intent = new Intent(ForGetActivity.this, ResetActivity.class);
                            intent.putExtra("username", userName);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}
