package com.example.httplibrary.client;

import com.example.httplibrary.callback.BaseObserver;
import com.example.httplibrary.exceptiopn.ExceptionEngine;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目名：Shopping
 * 包名：  com.example.httplibrary.client
 * 文件名：HttpObserable
 * 创建者：liangxq
 * 创建时间：2020/8/4  10:31
 * 描述：TODO
 */
public class HttpObserable {
    LifecycleProvider lifecycleProvider;
    //绑定Activity具体的生命周的
    ActivityEvent activityEvent;
    //绑定Fragment的具体的生命周期的
    FragmentEvent fragmentEvent;
    Observable observable;
    BaseObserver baseObserver;

    public HttpObserable(Buidler buidler) {
        this.lifecycleProvider=buidler.lifecycleProvider;
        this.activityEvent=buidler.activityEvent;
        this.fragmentEvent=buidler.fragmentEvent;
        this.observable=buidler.observable;
        this.baseObserver=buidler.baseObserver;
    }

    /**
     * 对初始化返回值JsonElement 进行转换操作。
     * @return
     */
    public Observable map(){
        return observable.map(new Function<JsonElement,Object>() {
            @Override
            public Object apply(JsonElement o) throws Exception {
                return new Gson().toJson(o);
            }
        });
    };


    /*onErrorResumeNext*/
    //错误信息的分类回调
    private Observable onErrorResumeNext() {
        return bindlifecycle().onErrorResumeNext(new Function<Throwable, ObservableSource>() {
            @Override
            public ObservableSource apply(Throwable throwable) throws Exception {
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });
    }

    //监听取消订阅的操作
    /*doOnDispose*/
    private Observable doOnDispose() {
        if (baseObserver != null) {
            return onErrorResumeNext().doOnDispose(new Action() {
                @Override
                public void run() throws Exception {
                    baseObserver.canclend();
                }
            });
        }
        return onErrorResumeNext();
    }

    //Rxjava的生命周期的绑定
    private Observable bindlifecycle() {
        Observable observable = map();
        if (lifecycleProvider != null) {
            if (activityEvent != null || fragmentEvent != null) {
                //两个同时存在,以 activity 为准
                if (activityEvent != null && fragmentEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(activityEvent));
                }
                if (activityEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(activityEvent));
                }
                if (fragmentEvent != null) {
                    return map().compose(lifecycleProvider.bindUntilEvent(fragmentEvent));
                }
            } else {
                return map().compose(lifecycleProvider.bindToLifecycle());
            }
        }
        return observable;
    }

    //设置线程切换
    public Observable observer(){
        return doOnDispose().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
//
//    public static Buidler createBuilder(Observable observable){
//        return new Buidler(observable);
//    }

    public static final class Buidler{
        LifecycleProvider lifecycleProvider;
        //绑定Activity具体的生命周的
        ActivityEvent activityEvent;
        //绑定Fragment的具体的生命周期的
        FragmentEvent fragmentEvent;
        Observable observable;
        BaseObserver baseObserver;

        public Buidler setLifecycleProvider(LifecycleProvider lifecycleProvider) {
            this.lifecycleProvider = lifecycleProvider;
            return this;
        }

        public Buidler setActivityEvent(ActivityEvent activityEvent) {
            this.activityEvent = activityEvent;
            return this;
        }

        public Buidler setFragmentEvent(FragmentEvent fragmentEvent) {
            this.fragmentEvent = fragmentEvent;
            return this;
        }

        public Buidler setBaseObserver(BaseObserver baseObserver) {
            this.baseObserver = baseObserver;
            return this;
        }

        public Buidler(Observable observable) {
            this.observable = observable;
        }

        public HttpObserable build(){
            return new HttpObserable(this);
        }
    }
}
