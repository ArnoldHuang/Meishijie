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
 * Fragement基类 -- 泛型K 为需要传递到该fragment中的类型
 */
public class BaseFragment<K> extends Fragment{
    private boolean injected = false;

    /**
     * 静态工厂方法
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T extends BaseFragment> T getInstance(Class<T> tClass){
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 初始化执行方法
     * @param view
     */
    protected void init(View view){

    }

    /**
     * 加载数据 -- 如果需要加载数据，重写该方法
     */
    public void loadDatas(){

    }

    /**
     * 需要传递参数时调用该方法
     * @param ks
     * @return
     */
    public BaseFragment bindDatas(K... ks){
        Bundle bundle = setDatas(ks);
        this.setArguments(bundle);
        return this;
    }

    /**
     * 传递给Fragment参数的方法 -- 由子类重写
     * @return
     */
    public Bundle setDatas(K... ks){
        return null;
    }

    /**
     * 获得数据方法 -- 由子类重写
     * @param bundle
     */
    public void getDatas(Bundle bundle){

    }


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
    public void fragmentManager(int fl_resid, Class fclass){
        Activity activity = getActivity();
        if(activity instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) activity;
            baseActivity.fragmentManager(fl_resid, fclass);
        }
    }
}
