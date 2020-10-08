package com.example.mvplibrary.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

abstract public class BaseAdapter<DATA> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<DATA> datas=new ArrayList<>();
    protected Context context;
    protected LayoutInflater layoutInflater;
    private int itemLayoutId;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseAdapter(List<DATA> datas, Context context, int itemLayoutId) {
        this.datas = datas;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
        this.itemLayoutId = itemLayoutId;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(itemLayoutId, parent, false);
        return new BaseViewHolder(view, context);
    }

    public void setNewData(@Nullable List<DATA> data){
        this.datas=data;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
            }
        });
        bindData(holder, datas.get(position));
    }

    protected abstract void bindData(BaseViewHolder holder,DATA data);
    @Override
    public int getItemCount() {
        return datas.size();
    }
    interface OnItemClickListener{
        void onClick(int position);
    }
}
