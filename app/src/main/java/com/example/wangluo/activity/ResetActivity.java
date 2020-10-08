package com.example.wangluo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.httplibrary.utils.JsonUtils;
import com.example.wangluo.R;
import com.example.wangluo.bean.CateGoryResponse;

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

public class ResetActivity extends AppCompatActivity {

    @BindView(R.id.iv_login_tuichu)
    ImageView ivLoginTuichu;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_pwd)
    EditText etUserPwd;
    @BindView(R.id.btn_ok)
    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        ivLoginTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = getIntent().getStringExtra("username");
                String userPwd_One = etUserName.getText().toString();
                String userPwd_Two = etUserPwd.getText().toString();
                ResetIdBean resetIdBean = new ResetIdBean();
                resetIdBean.setMobile(username);
                resetIdBean.setPwd(userPwd_One);
                OkHttpClient build = new OkHttpClient.Builder().build();
                MediaType parse = MediaType.parse("application/json;charset=utf-8");
                RequestBody requestBody = RequestBody.create(parse, JsonUtils.classToJson(resetIdBean));
                Request request = new Request.Builder()
                        .url("http://169.254.189.205:8080/kotlin/userCenter/resetPwd")
                        .post(requestBody)
                        .build();
                build.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("e", "onFailure: "+e.getMessage() );
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String json = response.body().string();
                        CateGoryResponse cateGoryResponse = JsonUtils.jsonToClassId(json, CateGoryResponse.class);
                        int status = cateGoryResponse.getStatus();
                        String message = cateGoryResponse.getMessage();
                        if (status==0){
                            Log.e("tag", "onResponse: "+message );
                            Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
                            startActivity(intent);

                        }
                    }
                });
            }
        });
    }
}
