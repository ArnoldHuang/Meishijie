package com.qf.fragment;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.base.BaseFragment;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;
import com.qf.util.UniversalUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 推荐页菜单导航ViewPager中的Fragment
 */
@ContentView(R.layout.fragment_recomment_nav)
public class RecommentNavFragment extends BaseFragment<RecmentEntity.SanCan>{

    //菜单大图控件
    @ViewInject(R.id.iv_pic)
    private ImageView iv;

    //菜单标题
    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    //菜单简要说明
    @ViewInject(R.id.tv_content)
    private TextView tv_cont;

    private static final String DATA_KEY = "sancan";

    /**
     * 设置数据的方法
     * @param sanCans
     * @return
     */
    @Override
    public Bundle setDatas(RecmentEntity.SanCan sanCans) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_KEY, sanCans);
        return bundle;
    }

    /**
     * 获得数据的方法
     * @param bundle
     */
    @Override
    public void getDatas(Bundle bundle) {
        RecmentEntity.SanCan sancan = (RecmentEntity.SanCan) bundle.getSerializable(DATA_KEY);
        if(sancan != null){
            //获得参数后设置到控件上
            UniversalUtil.displayImage(sancan.getTitlepic(), iv, null);
            tv_title.setText(sancan.getTitle());
            tv_cont.setText(sancan.getDescr());
        }
    }
}
