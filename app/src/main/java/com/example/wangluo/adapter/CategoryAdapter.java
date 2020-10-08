package com.example.wangluo.adapter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wangluo.R;
import com.example.wangluo.bean.CateGoryDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import retrofit2.http.POST;

public class CategoryAdapter extends BaseQuickAdapter<CateGoryDemo, BaseViewHolder> {


    private int layoutPosition;
    public int isLayoutPosition=-1;
    public List<Boolean> ischeck=new ArrayList<>();

    public CategoryAdapter( @Nullable List<CateGoryDemo> data) {
        super(R.layout.layout_item_category, data);
        if (data.size()>0){
            for (int i = 0; i < data.size(); i++) {
                ischeck.add(i,false);
            }
        }
    }

//    @Override
//    public void setNewData(@Nullable List<CateGoryDemo> data) {
//        super.setNewData(data);
//        if (data.size()>0){
//            for (int i = 0; i < data.size(); i++) {
//                ischeck.add(false);
//            }
//        }
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CateGoryDemo item) {
        helper.setText(R.id.tv_categoryName, item.getCategoryName());
        TextView tv_categoryname = helper.getView(R.id.tv_categoryName);
        layoutPosition = helper.getLayoutPosition();
        if (ischeck.get(layoutPosition)){
            isLayoutPosition=layoutPosition;
            tv_categoryname.setSelected(true);
        }else{
            tv_categoryname.setSelected(false);
        }

        helper.addOnClickListener(layoutPosition);
    }
}
