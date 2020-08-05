package com.example.mymvplibrary.modle;

import java.util.HashMap;

/**
 * @date：2020/8/5
 * @describe：工厂模式管理员工
 * @author：FanYaJun
 */
public class ModleFractory {
    //存储modle的类的集合
    private static HashMap<String,BaseModel> maps=new HashMap<>();

    public static <M extends BaseModel> M createModel(Class<M> mClass){
        if(maps.get(mClass)!=null){

            return (M) maps.get(mClass);
        }
        M mModel=null;
        try {
            mModel= mClass.newInstance();
            if(mModel!=null){
                maps.put(mClass.getName(),mModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mModel;
    }
}
