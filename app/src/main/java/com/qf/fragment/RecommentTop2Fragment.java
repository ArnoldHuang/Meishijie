package com.qf.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.base.AbsBaseAdapter;
import com.qf.base.BaseFragment;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;
import com.qf.util.UniversalUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * 推荐食材 推荐达人 那个栏目对应的Fragment
 */
@ContentView(R.layout.fragment_recomment_top2)
public class RecommentTop2Fragment extends BaseFragment<RecmentEntity.Top2>{

    /***
     * GridView显示的列数
     */
    private int colums = 3;

    @ViewInject(R.id.tv_top2_title)
    private TextView tv_title;

    @ViewInject(R.id.gv_top2_cont)
    private GridView gv_cont;
    private AbsBaseAdapter adapter;

    @Override
    protected void init(View view) {
        gv_cont.setNumColumns(colums);
    }

    @Override
    public Bundle setDatas(RecmentEntity.Top2 top2s) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", top2s);
        return bundle;
    }

    @Override
    public void getDatas(Bundle bundle) {
        RecmentEntity.Top2 top2 = (RecmentEntity.Top2) bundle.getSerializable("datas");
        tv_title.setText(top2.getTitle());

        switch (top2.getType()){
            case 1:
                //食材推荐
                adapter = new AbsBaseAdapter<RecmentEntity.Top2.TopObj>(getContext(), R.layout.item_top2_sc){
                    @Override
                    public void bindDatas(AbsBaseAdapter<RecmentEntity.Top2.TopObj>.ViewHolder viewHolder, RecmentEntity.Top2.TopObj data) {
                        //图标
                        ImageView iv_sc = (ImageView) viewHolder.getView(R.id.iv_sc_pic);
                        UniversalUtil.displayImage(data.getImage(), iv_sc, null);

                        //名称
                        TextView tv_title = (TextView) viewHolder.getView(R.id.tv_sc_name);
                        tv_title.setText(data.getTitle());

                        //热能
                        ImageView iv_rl = (ImageView) viewHolder.getView(R.id.iv_sc_rl_pic);
                        TextView tv_rl = (TextView) viewHolder.getView(R.id.tv_sc_rl);
                        tv_rl.setText(data.getHeat_word());
                        switch (data.getHeat_level()){
                            case 1://低热能
                                iv_rl.setImageResource(R.drawable.yyxx_icon_low);
                                tv_rl.setTextColor(Color.GREEN);
                                break;
                            case 2://中热能
                                iv_rl.setImageResource(R.drawable.yyxx_icon_mid);
                                tv_rl.setTextColor(Color.YELLOW);
                                break;
                            case 3://高热能
                                iv_rl.setImageResource(R.drawable.yyxx_icon_high);
                                tv_rl.setTextColor(Color.RED);
                                break;
                        }

                        //功效
                        TextView tv_gx = (TextView) viewHolder.getView(R.id.tv_sc_gx);
                        tv_gx.setText(data.getGongxiao());
                    }
                };
                break;
            case 2:
                //达人推荐
                adapter = new AbsBaseAdapter<RecmentEntity.Top2.TopObj>(getContext(), R.layout.item_top2_dr){
                    @Override
                    public void bindDatas(AbsBaseAdapter<RecmentEntity.Top2.TopObj>.ViewHolder viewHolder, RecmentEntity.Top2.TopObj data) {
                        //图标
                        ImageView iv_dr = (ImageView) viewHolder.getView(R.id.iv_dr_pic);
                        UniversalUtil.displayImage(data.getAvatar(), iv_dr, null);

                        //名称
                        TextView dr_title = (TextView) viewHolder.getView(R.id.tv_dr_name);
                        dr_title.setText(data.getUser_name());

                        //菜谱
                        TextView tv_cp = (TextView) viewHolder.getView(R.id.tv_dr_cp);
                        tv_cp.setText(data.getDescr());

                        //等级
                        TextView tv_level = (TextView) viewHolder.getView(R.id.tv_dr_level);
                        tv_level.setText(data.getLevel());
                    }
                };
                break;
            case 3:
                //菜单推荐
                adapter = new AbsBaseAdapter<RecmentEntity.Top2.TopObj>(getContext(), R.layout.item_top2_menu){
                    @Override
                    public void bindDatas(AbsBaseAdapter<RecmentEntity.Top2.TopObj>.ViewHolder viewHolder, RecmentEntity.Top2.TopObj data) {
                        //大图
                        ImageView iv_menu1 = (ImageView) viewHolder.getView(R.id.iv_menu_pic);
                        UniversalUtil.displayImage(data.getRecipes().get(0), iv_menu1, null);

                        //小图1
                        ImageView iv_menu2 = (ImageView) viewHolder.getView(R.id.iv_menu_pic2);
                        UniversalUtil.displayImage(data.getRecipes().get(1), iv_menu2, null);

                        //小图2
                        ImageView iv_menu3 = (ImageView) viewHolder.getView(R.id.iv_menu_pic3);
                        UniversalUtil.displayImage(data.getRecipes().get(2), iv_menu3, null);

                        //名称
                        TextView tv_menu = (TextView) viewHolder.getView(R.id.tv_menu_name);
                        tv_menu.setText(data.getTitle());

                        //简介
                        TextView tv_descr = (TextView) viewHolder.getView(R.id.tv_menu_descr);
                        tv_descr.setText(data.getDescr());
                    }
                };
                break;
        }
        adapter.setDatas(top2.getObj());
        gv_cont.setAdapter(adapter);
    }
}
