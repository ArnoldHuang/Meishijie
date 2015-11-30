package com.qf.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.qf.meishijie.R;

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

        init();
        loadDatas();
    }

    /**
     * 初始化
     */
    public void init(){

    }

    /**
     * 加载数据
     */
    public void loadDatas(){

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in_rigth, R.anim.activity_out);
    }

    /**
     * fragment管理
     */
    public void fragmentManager(int fl_resid, Class fclass){
        fragmentManager(fl_resid, 0, 0, fclass);
    }

    /**
     * 包含动画的fragment管理
     * @param fl_resid
     * @param fclass
     * @param inanim
     * @param outanim
     */
    public void fragmentManager(int fl_resid, int inanim, int outanim, Class fclass){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(inanim != 0 || outanim != 0){
            fragmentTransaction.setCustomAnimations(inanim, outanim);
        }
        if(fragmentMap.containsKey(fclass.getName())){
            BaseFragment fragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentMap.get(fclass.getName()));
            if(showFragment != null && fragment.getClass() != showFragment.getClass()){
                fragmentTransaction.hide(showFragment);
            }
            fragmentTransaction.show(fragment);
            showFragment = fragment;
        } else {
            BaseFragment baseFragment = BaseFragment.getInstance(fclass);
            fragmentTransaction.add(fl_resid, baseFragment, baseFragment.getFTag());
            fragmentMap.put(fclass.getName(), baseFragment.getFTag());
        }
        fragmentTransaction.commit();

    }
}
