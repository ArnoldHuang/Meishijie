package com.qf.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * Activity基类
 */
public class BaseActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;
    private Map<String, String> fragmentMap = new HashMap<>();
    private BaseFragment showFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        fragmentManager = getSupportFragmentManager();
    }

    /**
     * fragment管理方法
     */
    public void fragmentManager(int fl_resid, Class fclass, Object... params){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(fragmentMap.containsKey(fclass.getName())){
            BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentMap.get(fclass.getName()));
            if(showFragment != null && fragment.getClass() != showFragment.getClass()){
                fragmentTransaction.hide(showFragment);
            }
            fragmentTransaction.show(fragment);
            showFragment = fragment;
        } else {
            BaseFragment baseFragment = BaseFragment.getInstance(fclass, params);
            fragmentTransaction.add(fl_resid, baseFragment, baseFragment.getFTag());
            fragmentMap.put(fclass.getName(), baseFragment.getFTag());
        }
        fragmentTransaction.commit();
    }
}
