package com.example.wangluo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wangluo.R;
import com.example.wangluo.bean.DiscountBean;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.ArrayList;

public class DisCountAdapter extends RecyclerView.Adapter<DisCountAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DiscountBean> list;


    public DisCountAdapter(Context context, ArrayList<DiscountBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, null);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiscountBean discountBean = list.get(position);
        Glide.with(context).load(discountBean.getImage()).into(holder.iv);
        holder.tv_price.setText(discountBean.getPrice());
        holder.tv_price_two.setText(discountBean.getOriginal_price());
        holder.tv_price_two.setTextSize(8f);
        holder.tv_price_two.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleTagImageView iv;
        private TextView tv_price;
        private TextView tv_price_two;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_price_two=itemView.findViewById(R.id.tv_price_two);
        }
    }
}