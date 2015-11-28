package com.qf.base;

import android.app.Application;

import com.qf.util.ShareUtil;
import com.qf.util.UniversalUtil;
import com.qf.util.VolleyUtil;

import org.xutils.x;


/**
 * 自定义Application
 */
public class AppContext extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化第三方库
         */
        VolleyUtil.initVolley(this);
        UniversalUtil.initUniversal(this);
        ShareUtil.init(this);
        x.Ext.init(this);
    }
}
