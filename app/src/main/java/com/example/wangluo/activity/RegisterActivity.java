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
import com.example.wangluo.bean.RegisterIdBean;

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

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.et_user_pwd_two)
    EditText etUserPwdTwo;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_yanzheng)
    Button btnYanzheng;
    @BindView(R.id.iv_pass)
    ImageView ivPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_userName = etUserName.getText().toString();
                String et_verify = etVerify.getText().toString();
                String et_userPwd = etUserPwd.getText().toString();
                String et_userPwdTwo = etUserPwdTwo.getText().toString();
                RegisterIdBean registerIdBean = new RegisterIdBean();
                registerIdBean.setMobile(et_userName);
                registerIdBean.setVerifyCode(et_verify);
                registerIdBean.setPwd(et_userPwdTwo);
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                MediaType parse = MediaType.parse("application/json;charset=utf-8");
                RequestBody requestBody = RequestBody.create(parse, JsonUtils.classToJson(registerIdBean));
                Request build = new Request.Builder()
                        .post(requestBody)
                        .url("http://169.254.189.205:8080/kotlin/userCenter/register")
                        .build();
                builder.build().newCall(build).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("tag", "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        CateGoryResponse json = JsonUtils.jsonToClassId(string, CateGoryResponse.class);
                        int status = json.getStatus();
                        if (status == 0) {
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                                }
//                            });
                            Intent intent = new Intent();
                            intent.putExtra("user", et_userName);
                            intent.putExtra("pwd", et_userPwdTwo);
                            setResult(300, intent);
                            finish();

                        }
                    }
                });

            }
        });
    }
}
