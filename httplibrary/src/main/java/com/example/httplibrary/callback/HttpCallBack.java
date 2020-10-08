//package com.example.httplibrary.callback;
//
//import android.util.Log;
//
//import com.example.httplibrary.demo.Response;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//
///**
// * 项目名：Shopping
// * 包名：  com.example.httplibrary.callback
// * 文件名：HttpCallBack
// * 创建者：liangxq
// * 创建时间：2020/8/3  14:29
// * 描述：TODO
// * <p>
// * 示例
// */
//public abstract class HttpCallBack<T> extends BaseCallBack<T> {
//    Response response;
//    @Override
//    protected T onConvert(String result) {
//        T t=null;
//        response = new Gson().fromJson(result, Response.class);
//        JsonElement data = response.getData();
//        int errorCode = response.getErrorCode();
//        String errorMsg = response.getErrorMsg();
//        switch (errorCode) {
//            case -c:
//                onError(errorMsg,errorCode);
//                break;
//            default:
//                if (isCodeSuccess()) {
//                    t=convert(data);
//                }
//                break;
//        }
//        Log.e("liangxq", "onConvert: "+t.toString() );
//        return t;
//    }
//
//
//    @Override
//    public boolean isCodeSuccess() {
//        if (response != null) {
//            return response.getErrorCode() == 0;
//        }
//        return false;
//    }
//
//}
