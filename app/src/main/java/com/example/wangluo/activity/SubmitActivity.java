package com.example.wangluo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wangluo.R;
import com.example.wangluo.adapter.SubMitAdapter;
import com.example.wangluo.bean.CartBean;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.db.manager.AddressDictManager;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import java.util.List;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {

    private ImageView iv_tuichu;
    private TextView tv_shipUserName;
    private TextView tv_shipAddress;
    private TextView tv_shipUserMobile;
    private ImageView iv_jojn;
    private TextView tv_money;
    private Button tv_commit;
    private PopupWindow popupWindow;
    private AddressDictManager addressDictManager;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private List<CartBean> cartbeans;
    private RecyclerView rv;
    private List<CartBean> cartbean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        initView();
    }

    private void initView() {
        cartbean = (List<CartBean>) getIntent().getSerializableExtra("cartbean");
        Log.e("cartbean", "initView: "+cartbean.toString() );
        iv_tuichu = (ImageView) findViewById(R.id.iv_tuichu);
        tv_shipUserName = (TextView) findViewById(R.id.tv_shipUserName);
        tv_shipAddress = (TextView) findViewById(R.id.tv_shipAddress);
        tv_shipUserMobile = (TextView) findViewById(R.id.tv_shipUserMobile);
        iv_jojn = (ImageView) findViewById(R.id.iv_jojn);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_commit = (Button) findViewById(R.id.tv_commit);
        rv = findViewById(R.id.rv);
        iv_jojn.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
        AddressSelector selector = new AddressSelector(this);
        tv_shipAddress.setOnClickListener(this);
        addressDictManager = selector.getAddressDictManager();
        selector.setTextSize(13);
        rv.setLayoutManager(new LinearLayoutManager(this));
        SubMitAdapter subMitAdapter = new SubMitAdapter(this, R.layout.layout_submit_item, cartbean);
        rv.setAdapter(subMitAdapter);
        subMitAdapter.notifyDataSetChanged();

        //设置指针颜色
        selector.setIndicatorBackgroundColor(android.R.color.holo_orange_light);
        //设置字体获得焦点的颜色
        selector.setTextSelectedColor(android.R.color.holo_orange_light);
        //设置字体没有获得焦点的颜色
        selector.setTextUnSelectedColor(android.R.color.holo_blue_light);

        //选择监听，用户选取中和选取完毕之后调用此方法
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override

            public void onAddressSelected(Province province, City city, County county, Street street) {

                String address = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                        (street == null ? "" : street.name);
//                mSelectorContent.setVisibility(View.VISIBLE);
                tv_shipAddress.setText(address);
//                mViewcontent.removeAllViews();
            }
        });

//        如将此处的注释放开，即可全屏显示一次
//        View view = selector.getView();
//        mViewcontent.addView(view);
//        mSelectorContent.setVisibility(View.GONE);
    }
    private BottomDialog dialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commit:
                Intent intent = new Intent(SubmitActivity.this, DemoActivity.class);
                intent.putExtra("info","成功");
                startActivity(intent);
                break;
            case R.id.iv_jojn:
                if (dialog != null) {
                    dialog.show();
                } else {
                    dialog = new BottomDialog(this);
                    //地址选取监听
                    dialog.setOnAddressSelectedListener(this);
                    //Dialog监听
                    dialog.setDialogDismisListener(this);
                    //设置字体的大小
                    dialog.setTextSize(14);
                    //设置指示器的颜色
                    dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);
                    //设置字体获得焦点的颜色
                    dialog.setTextSelectedColor(android.R.color.holo_orange_light);
                    //设置字体没有获得焦点的颜色
                    dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);
                    dialog.show();
                }
                break;
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);

        String address = (province == null ? "" : province.name) + " " + (city == null ? "" : city.name) + " " + (county == null ? "" : county.name) + " " + (street == null ? "" : street.name);

        tv_shipAddress.setText("地址 :" + " " + address);

        if (dialog != null) {
            dialog.dismiss();
        }
//        getSelectedArea();
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    /**
     * 根据code 来显示选择过的地区
     */

    private void getSelectedArea() {
        String province = addressDictManager.getProvince(provinceCode);
        String city = addressDictManager.getCity(cityCode);
        String county = addressDictManager.getCounty(countyCode);
        String street = addressDictManager.getStreet(streetCode);
    }
}