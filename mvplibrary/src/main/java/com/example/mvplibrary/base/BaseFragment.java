package com.example.mvplibrary.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;


import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends RxFragment {
    private Activity activity;
    private Context context;
    private View rootView;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView==null){
            rootView=inflater.inflate(initLayoutId(), null);
        }
        unbinder= ButterKnife.bind(this,rootView);
        initData();
        return rootView;
    }

    protected abstract void initData();

    protected abstract int initLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView!=null){
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        if (unbinder!=null){
            unbinder.unbind();
        }
    }
}
