package com.qf.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.x;

import java.lang.reflect.Method;

/**
 * Fragement基类
 */
public abstract class BaseFragment extends Fragment{
    private boolean injected = false;

    /**
     * 静态工厂方法
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends BaseFragment> T getInstance(Class<T> tClass, Object... obj){
        T t = null;
        try {
            t = tClass.newInstance();
            if(obj != null && obj.length > 0){
                Bundle bundle = t.setDatas(obj);
                t.setArguments(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 传递给Fragment参数的方法 -- 由子类重写
     * @return
     */
    public abstract Bundle setDatas(Object... obj);

    /**
     * 获得数据方法 -- 由子类重写
     * @param bundle
     */
    public abstract void getDatas(Bundle bundle);

    /**
     * 加载数据 -- 如果需要加载数据，重写该方法
     */
    public void loadDatas(){

    }

    /**
     * 初始化执行方法
     * @param view
     */
    protected abstract void init(View view);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        init(view);
        loadDatas();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDatas(getArguments());
    }


    /**
     * 获得当前fragment对象的tag值
     * @return
     */
    public String getFTag(){
        return "" + this.hashCode();
    }


    /**
     * fragment管理方法
     */
    public void fragmentManager(int fl_resid, Class fclass, Object... params){
        Activity activity = getActivity();
        if(activity instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) activity;
            baseActivity.fragmentManager(fl_resid, fclass, params);
        }
    }
}
