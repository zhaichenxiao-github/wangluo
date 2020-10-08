package com.example.mvplibrary.model;

import java.util.HashMap;
import java.util.Map;

public class ModelFractory {
    //存储model类的集合
    private static Map<String, BaseModel> modelMap=new HashMap<>();
    public static <M extends BaseModel> M createModel(Class<M> mClass){
        if (modelMap.get(mClass)!=null){
            return (M) modelMap.get(mClass);
        }
        M m=null;
        try {
            //通过反射来创建对象，但是你的子类必须有无参构造方法
            m= mClass.newInstance();
            if (m!=null){
                modelMap.put(mClass.getName(), m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
}