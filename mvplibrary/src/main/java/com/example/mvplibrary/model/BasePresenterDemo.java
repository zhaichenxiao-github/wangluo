package com.example.mvplibrary.model;//package com.example.mvplibrary.model;
//
//import com.http.httplibrary.callback.load.example.mvplibrary.BaseModle;
//import com.http.httplibrary.callback.load.example.mvplibrary.BaseView;
//
//import java.lang.ref.WeakReference;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Proxy;
//import java.lang.reflect.Type;
//
///**
// * 项目名：AndroidAptDemo
// * 包名：  com.liangxq.mvplibrary.mvpone
// * 文件名：BasePresenter
// * 创建者：liangxq
// * 创建时间：2020/3/10  18:07
// * 描述：TODO
// */
//public class BasePresenter<V extends BaseView, M extends BaseModle> {
//    private V mProxyView;
//    private M modle;
//    private WeakReference<V> mWeakReference;
//
//    public void attach(final V view) {
//        mWeakReference = new WeakReference<V>(view);
//        //动态代理来处理同意来处理view为null的判断
//        mProxyView = (V) Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (mWeakReference == null || mWeakReference.get() == null) {
//                    return null;
//                }
//                Object invoke = method.invoke(mWeakReference.get(), args);
//                return invoke;
//            }
//        });
//        initModle();
//    }
//
//    public void detach() {
//        if (mWeakReference != null) {
//            mWeakReference.clear();
//            mWeakReference = null;
//        }
//        if (modle != null) {
//            modle = null;
//        }
//    }
//
//    public V getView() {
//        return mProxyView;
//    }
//
//    //动态的创建modle
//    public void initModle() {
//        try {
//            Type type = this.getClass().getGenericSuperclass();
//            ParameterizedType parameterizedType = (ParameterizedType) type;
//            Class<M> mClass = (Class<M>) parameterizedType.getActualTypeArguments()[c];
//            modle = mClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public M getModle() {
//        return modle;
//    }
//}
