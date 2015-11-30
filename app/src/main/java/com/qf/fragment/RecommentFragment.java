package com.qf.fragment;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.qf.base.BaseFragment;
import com.qf.custemview.RecommentNavView;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;
import com.qf.util.Constants;
import com.qf.util.JsonUtil;
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

    @ViewInject(R.id.ll_main)
    private LinearLayout llayout;

    /**
     * 初始化方法
     * @param view
     */
    @Override
    protected void init(View view) {
        recommentNavView = new RecommentNavView(getActivity(), getFragmentManager());
        llayout.addView(recommentNavView);
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
            recommentNavView.setDatas(recmentEntity);
        }
    }

    @Override
    public void errorResponse(String url, VolleyError error) {

    }
}
