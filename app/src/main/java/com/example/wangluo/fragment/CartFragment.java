package com.example.wangluo.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvplibrary.base.BaseMvpFragment;
import com.example.wangluo.R;
import com.example.wangluo.activity.SubmitActivity;
import com.example.wangluo.adapter.CartAdapter;
import com.example.wangluo.bean.CartBean;
import com.example.wangluo.event.UpdateTalPrice;
import com.example.wangluo.mvp.CartConstacts;
import com.example.wangluo.mvp.CartPresenterIml;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseMvpFragment<CartConstacts.CartView, CartPresenterIml> implements CartConstacts.CartView, View.OnClickListener {


    @BindView(R.id.iv_tuichu)
    ImageView ivTuichu;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.rl_gouwu)
    RelativeLayout rlGouwu;
    @BindView(R.id.rtn_whole)
    CheckBox rtnWhole;
    @BindView(R.id.tv_prices)
    TextView tvPrices;
    @BindView(R.id.btn_check_out)
    Button btnCheckOut;

    //定义编辑和完成的标识
    public static boolean isCheck=false;

    private boolean isCheckSingle=true;
    List<CartBean> cartBeans=new ArrayList<>();
    List<CartBean> list=new ArrayList<>();
    private CartAdapter cartAdapter;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    protected int initLayoutId() {
        return R.layout.fragment_cart;
    }


    @Override
    protected void initGetData() {
        EventBus.getDefault().register(this);
        mPresenter.getCart();
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));
        cartAdapter = new CartAdapter(getActivity(), R.layout.layout_cart_item, this.cartBeans);
        rv.setAdapter(cartAdapter);
        ivTuichu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        tvPrices.setVisibility(View.VISIBLE);
        tvPrices.setText("￥0.0");
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isCheck){
                    tvEdit.setText("完成");
                    tvPrices.setVisibility(View.GONE);
                    isCheck=true;
                    btnCheckOut.setText("删除");
                }else{
                    tvPrices.setVisibility(View.VISIBLE);
                    tvPrices.setText("￥0.00");
                    tvEdit.setText("编辑");
                    btnCheckOut.setText("去结算");
                    isCheck=false;
                    int totalCount=0;
                    for (CartBean cartBean : cartBeans) {
                        if (cartBean.isCheck()){
                            //价格
                            totalCount+=Integer.parseInt(cartBean.getGoodsPrice())*cartBean.getGoodsCount();
                        }
                    }
                    tvPrices.setText("合计:￥"+totalCount);
                }
            }
        });

        rtnWhole.setChecked(false);
        rtnWhole.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    for (int i = 0; i < cartBeans.size(); i++) {
                        cartBeans.get(i).setCheck(true);
                    }
                }else{
                    if (isCheckSingle){
                        for (int i = 0; i < cartBeans.size(); i++) {
                            cartBeans.get(i).setCheck(false);
                        }
                    }
                }
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //刷新操作
                        cartAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
        btnCheckOut.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setTotalPrice(UpdateTalPrice updateTalPrice){
        int totalPrice=0;
        int total=0;
        for (CartBean cartBean : cartBeans) {
            if (cartBean.isCheck()){
                total+=1;
                totalPrice+=Integer.parseInt(cartBean.getGoodsPrice())*cartBean.getGoodsCount();
            }
        }
        if (total==cartBeans.size()){
            isCheckSingle=true;
            rtnWhole.setChecked(true);
        }else{
            isCheckSingle=false;
            rtnWhole.setChecked(false);
        }
        tvPrices.setText("合计￥"+totalPrice);
    }

    @Override
    protected CartPresenterIml initPresenter() {
        return new CartPresenterIml();
    }

    @Override
    public void OnSuccessCart(List<CartBean> list) {
        this.cartBeans=list;
        cartAdapter.setNewData(cartBeans);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i < cartBeans.size(); i++) {
            if (cartBeans.get(i).isCheck()==true){
                list.add(cartBeans.get(i));
            }else if (cartBeans.get(i).isCheck()==false){
                list.clear();
            }
        }
        Log.e("cartbean", "initGetData: "+list.toString() );
        String s = btnCheckOut.getText().toString();
        if (s.equals("去结算")){
            Intent intent = new Intent(getActivity(), SubmitActivity.class);
            intent.putExtra("cartbean", (Serializable) list);
//            intent.putExtra("cartbeans", (Parcelable) this.cartBeans);
            startActivity(intent);
        }else if (s.equals("删除")){

        }
    }
}