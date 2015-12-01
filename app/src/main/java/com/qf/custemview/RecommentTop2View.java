package com.qf.custemview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qf.base.BaseFragment;
import com.qf.fragment.RecommentTop2Fragment;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 推荐页中部 今日食材 推荐达人 控件封装
 */
public class RecommentTop2View extends FrameLayout{

    @ViewInject(R.id.vp_top2)
    private ViewPager vp;
    private TopAdapter topAdapter;
    private FragmentManager fm;

    public RecommentTop2View(Context context, FragmentManager fm) {
        super(context);
        this.fm = fm;
        init();
    }

    /**
     * 初始化布局
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custems_retop2, this, true);
        x.view().inject(this);
    }

    /**
     * 设置数据源--供外界调用
     * @param
     */
    public void setDatas(RecmentEntity data){
        topAdapter = new TopAdapter(fm, data.getTop2());
        vp.setAdapter(topAdapter);
    }

    /**
     * ViewPager适配器
     */
    class TopAdapter extends FragmentStatePagerAdapter{

        private List<RecmentEntity.Top2> datas;

        public TopAdapter(FragmentManager fm, List<RecmentEntity.Top2> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return BaseFragment.getInstance(RecommentTop2Fragment.class).bindDatas(datas.get(position));
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
