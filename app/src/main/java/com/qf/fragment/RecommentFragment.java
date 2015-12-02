package com.qf.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.qf.base.BaseFragment;
import com.qf.custemview.RecommentNavView;
import com.qf.custemview.RecommentTop2View;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;
import com.qf.util.Constants;
import com.qf.util.JsonUtil;
import com.qf.util.L;
import com.qf.util.UniversalUtil;
import com.qf.util.VolleyUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 推荐页fargment
 */
@ContentView(R.layout.fragment_recoment)
public class RecommentFragment extends BaseFragment implements VolleyUtil.OnRequest {

    /**
     * 推荐页数据实体类
     */
    private RecmentEntity recmentEntity;

    /**
     * 缩略图控件
     */
    private RecommentNavView recommentNavView;

    /**
     * 推荐食材 达人 菜单 模块
     */
    private RecommentTop2View recommentTop2View;

    /**
     * 广告栏目
     */
    private ImageView gliv;

    @ViewInject(R.id.ll_main)
    private LinearLayout llayout;

    /**
     * 初始化方法
     * @param view
     */
    @Override
    protected void init(View view) {
        //初始化缩略图封装组件
        recommentNavView = new RecommentNavView(getActivity(), getFragmentManager());
        llayout.addView(recommentNavView);

        //初始化推荐模块封装组件
        recommentTop2View = new RecommentTop2View(getActivity(), getFragmentManager());
        llayout.addView(recommentTop2View);

        //初始化广告ImageView
        gliv = new ImageView(getContext());
        gliv.setScaleType(ImageView.ScaleType.FIT_XY);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        llayout.addView(gliv, layoutParams);
    }

    /**
     * 加载数据
     */
    @Override
    public void loadDatas() {
        //请求主页数据
        VolleyUtil.requestString(Constants.URL.RECOMENT_MAIN_URL, this);
    }

    /**
     * 数据加载完成
     * @param url
     * @param response
     */
    @Override
    public void response(String url, String response) {
        if(response != null){
            recmentEntity = JsonUtil.getRDataByJSON(response);
            if(recmentEntity != null) {
                //设置头部的菜谱缩略图数据
                recommentNavView.setDatas(recmentEntity);
                //推荐达人的控件
                recommentTop2View.setDatas(recmentEntity);
                //设置广告的控件
                UniversalUtil.displayImage(recmentEntity.getTop4().getPhoto(), gliv, null);
            }
         }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
