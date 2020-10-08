package com.example.wangluo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.wangluo.R;

import java.util.Map;

public class DemoActivity extends AppCompatActivity {

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Map <String,String> result = (Map<String, String>) msg.obj;
            Toast.makeText(DemoActivity.this, result.toString(),
                    Toast.LENGTH_LONG).show();
        };
    };
    private int SDK_PAY_FLAG=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        final String orderInfo = "info";   // 订单信息
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(DemoActivity.this);
                Map<String,String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}