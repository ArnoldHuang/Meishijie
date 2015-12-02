package com.qf.custemview;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qf.meishijie.R;

/**
 * 推荐页 商城栏目 封装控件
 */
public class RecommentShopView extends FrameLayout{

    public RecommentShopView(Context context, FragmentManager fm) {
        super(context);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custems_reshop, this, true);
    }


}
