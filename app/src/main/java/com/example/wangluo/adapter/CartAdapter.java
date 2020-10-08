package com.example.wangluo.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wangluo.R;
import com.example.wangluo.bean.CartBean;
import com.example.wangluo.event.UpdateTalPrice;
import com.example.wangluo.fragment.CartFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import ren.qinc.numberbutton.NumberButton;

public class CartAdapter extends BaseQuickAdapter<CartBean, BaseViewHolder> {

    private Context context;
    public CartAdapter(Context context,int layoutResId, @Nullable List<CartBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CartBean item) {
        TextView tv_goodsDefaultPrice = helper.getView(R.id.tv_goodsDefaultPrice);
        TextView tv_goodsDefaultSku = helper.getView(R.id.tv_goodsDefaultSku);
        TextView tv_goodsDesc = helper.getView(R.id.tv_goodsDesc);
        CheckBox cb_select = helper.getView(R.id.cb_select);
        ImageView iv_goodsDefaultIcon = helper.getView(R.id.iv_goodsDefaultIcon);
        NumberButton number_button = helper.getView(R.id.number_button);
        tv_goodsDefaultPrice.setText(item.getGoodsPrice());
        tv_goodsDefaultSku.setText(item.getGoodsSku());
        tv_goodsDesc.setText(item.getGoodsDesc());
        Glide.with(context).load(item.getGoodsIcon()).into(iv_goodsDefaultIcon);
        if (item.isCheck()){
            cb_select.setChecked(true);
        }else{
            cb_select.setChecked(false);
        }
        cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    item.setCheck(true);
                }else{
                    item.setCheck(false);
                }
                if (!CartFragment.isCheck){
                    EventBus.getDefault().postSticky(new UpdateTalPrice());
                }
            }
        });
        EditText text_count = number_button.findViewById(R.id.text_count);
        if (text_count.getTag() instanceof TextWatcher){
            text_count.removeTextChangedListener((TextWatcher) text_count.getTag());
        }
        text_count.setText(item.getGoodsCount()+"");
        Log.e("text_count", "convert: "+item.getGoodsCount());
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                item.setGoodsCount(Integer.parseInt(s.toString()));
                if (!CartFragment.isCheck){
                    EventBus.getDefault().postSticky(new UpdateTalPrice());
                }
            }
        };
        text_count.addTextChangedListener(textWatcher);
        text_count.setTag(textWatcher);

    }
}
