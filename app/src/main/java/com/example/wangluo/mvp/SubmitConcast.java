package com.example.wangluo.mvp;

import com.example.mvplibrary.model.BaseModel;
import com.example.mvplibrary.view.BaseView;
import com.example.wangluo.bean.SubParams;
import com.example.wangluo.bean.SubmitBean;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.List;

public interface SubmitConcast {
    interface SubmitView extends BaseView{
        void  OnSuccessSub(List<SubmitBean>list);
    }
    interface SubmitPresenter{
        void getSub(SubParams subParams);
    }
    interface SubModel extends BaseModel{
        void OnSuccessSub(SubParams subParams, ModelSubCallBack<List<SubmitBean>> back, LifecycleProvider lifecycleProvider);
    }
}
