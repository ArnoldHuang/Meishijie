package com.qf.custemview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.base.BaseFragment;
import com.qf.fragment.RecommentNavFragment;
import com.qf.meishijie.R;
import com.qf.model.RecmentEntity;
import com.qf.util.L;
import com.qf.util.UniversalUtil;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.Calendar;
import java.util.List;

/**
 * 推荐页头部菜单缩略图控件封装
 */
public class RecommentNavView extends LinearLayout implements ViewPager.OnPageChangeListener {

    //头部时间段控件
    @ViewInject(R.id.ll_timeid)
    private LinearLayout ll_time;

    //菜单大图ViewPager
    @ViewInject(R.id.vp_id)
    private ViewPager vp_id;
    private MyAdapter myAdapter;

    //缩略图外部滑动控件
    @ViewInject(R.id.hsv_id)
    private HorizontalScrollView horizontalScrollView;

    //缩略图摆放控件
    @ViewInject(R.id.ll_id)
    private LinearLayout ll_id;

    private FragmentManager fm;

    private int[][] times = {{0, 10},{10, 14},{14, 16},{16, 20},{20, 24}};

    /**
     * 数据总量参数 -- 用于计算哪些菜谱属于哪个时间段
     */
    private int datasCount;
    private int tabCount;
    private int events = 3;//每个时间段默认包含3个菜谱

    /**
     * 构造方法
     * @param context
     * @param fm
     */
    public RecommentNavView(Context context, FragmentManager fm) {
        super(context);
        init();
        this.fm = fm;
    }

    /**
     * 初始化方法
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custems_recoment_image, this, true);
        x.view().inject(this);

        vp_id.addOnPageChangeListener(this);
    }

    /**
     * 设置数据源 -- 外部调用该方法来设置控件的数据
     */
    public void setDatas(RecmentEntity recmentEntity){
        if(recmentEntity != null) {
            //获得头部时间段数据
            List<RecmentEntity.TabTitle> tabTitles = recmentEntity.getSan_can_titles();
            tabCount = tabTitles.size();
            //获得菜单缩略图数据
            List<RecmentEntity.SanCan> sanCans = recmentEntity.getSan_can();
            datasCount = sanCans.size();

            events = datasCount/tabCount;


            /**
             * 设置菜单大图和缩略图数据
             */
            myAdapter = new MyAdapter(fm, sanCans);
            vp_id.setAdapter(myAdapter);
            //设置缩略图
            paresPics(sanCans);

            /**
             * 设置时间段数据 -- 第二个参数 缩略图的总数量
             */
            paresTime(tabTitles);
        }
    }

    /**
     * 设置时间段方法
     * @param tabTitles
     */
    int index = 0;
    private void paresTime(List<RecmentEntity.TabTitle> tabTitles){
        if(tabTitles != null && tabTitles.size() > 0){
            //获取当前事件
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);//获取当前时间 -- 小时

            for(int i = 0; i < tabTitles.size(); i++){
                if(i < times.length){
                    if (times[i][0] <= hour && hour < times[i][1]) {
                        index = i;
                    }
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                TimerTab tt = new TimerTab(getContext(), tabTitles.get(i), i);
                ll_time.addView(tt, layoutParams);
            }

            /**
             * 控件加载完成后执行该方法 -- 根据时间选择当前菜谱
             */
            this.post(new Runnable() {
                @Override
                public void run() {
                    selectTimerByPosition(index);
                    vp_id.setCurrentItem(index*events);
                }
            });
        }
    }


    /**
     * 设置缩略图
     */
    private void paresPics(List<RecmentEntity.SanCan> sanCans){
        if(sanCans != null && sanCans.size() > 0){
            //循环获得每一个菜单的数据
            for(int i = 0; i < sanCans.size(); i++){
                final int index = i;
                //根据数据手动创建ImageView(缩略图)对象
                ImageView giv = new ImageView(getContext());
                if (i == 0) {
                    //默认选中第一个菜单
                    giv.setTag("checked");
                } else {
                    //未被选中的菜单 缩略图添加一个灰色遮障
                    giv.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                }
                //设置缩略图点击事件，控制大图ViewPager的显示
                giv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_id.setCurrentItem(index);
                    }
                });
                //设置缩略图的宽高
                LinearLayout.LayoutParams layoutParams = new LayoutParams((int)getResources().getDimension(R.dimen.image_m_wh), (int)getResources().getDimension(R.dimen.image_m_wh));
                giv.setLayoutParams(layoutParams);
                //调用universal进行图片下载
                UniversalUtil.displayImage(sanCans.get(i).getTitlepic_m(), giv, null);
                //将完成的缩略图添加进线性布局中
                ll_id.addView(giv);
            }
        }
    }

    /**
     * ViewPager控件的监听方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        scrollPics(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 滑动ViewPager 更新缩略图方法
     */
    private void scrollPics(int index){
        ImageView giv = (ImageView) ll_id.findViewWithTag("checked");
        giv.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        giv.setTag(null);

        giv = (ImageView) ll_id.getChildAt(index);
        giv.clearColorFilter();
        giv.setTag("checked");

        /**
         * 这段代码是将被选中的缩略图移动到屏幕中间
         */
        int leftx = giv.getLeft();
        //获得屏幕的宽度
        int offset = leftx-(getResources().getDisplayMetrics().widthPixels/2) + giv.getWidth()/2;
        horizontalScrollView.smoothScrollTo(offset, 0);

        /**
         * 设置当前菜谱所属时间段
         */
        int timeposition = index/events;
        selectTimerByPosition(timeposition);
    }


    /**
     * 菜单大图的ViewPager所需要的适配器
     */
    class MyAdapter extends FragmentStatePagerAdapter {

        private List<RecmentEntity.SanCan> datas;

        public MyAdapter(FragmentManager fm, List<RecmentEntity.SanCan> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            RecmentEntity.SanCan sanCan = datas.get(position);
            return BaseFragment.getInstance(RecommentNavFragment.class).bindDatas(sanCan);
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }

    /**
     * 时间段标签封装类 -- 上午 中午 下午 那个标签
     */
    public class TimerTab extends LinearLayout implements OnClickListener {
        private RecmentEntity.TabTitle tabTitle;
        private LinearLayout.LayoutParams layoutParams;

        private ImageView iv;
        private TextView tv;
        private int[] positions;//该时间段 所包含的菜谱下标集合
        private int position;//当前组件所属的下标

        public TimerTab(Context context, RecmentEntity.TabTitle tabTitle, int position) {
            super(context);
            this.tabTitle = tabTitle;

            this.position = position;

            positions = new int[events];
            for(int i = 0; i < positions.length; i++){
                positions[i] = (position * events) + i;//计算所包含的菜谱下标
            }

            initView();
        }


        /**
         * 初始化
         */
        private void initView() {
            this.setOrientation(HORIZONTAL);
            this.setGravity(Gravity.CENTER);
            this.setOnClickListener(this);

            //设置tab的图片
            layoutParams = new LinearLayout.LayoutParams((int)getResources().getDimension(R.dimen.tab_img_wh), (int)getResources().getDimension(R.dimen.tab_img_wh));
            iv = new ImageView(getContext());
            iv.setLayoutParams(layoutParams);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setVisibility(GONE);
            UniversalUtil.displayImage(tabTitle.getTitlepic(), iv, null);
            this.addView(iv);


            //设置tab的文本
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv = new TextView(getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(layoutParams);
            tv.setText(tabTitle.getTitle());
            tv.setSingleLine();
            tv.setTextSize(getResources().getDimension(R.dimen.tab_text_size));
            tv.setTextColor(Color.parseColor("#55888888"));
            this.addView(tv);
        }

        /**
         * 设置为选中
         */
        private void checked(){
            if(this.getTag() == null) {
                iv.setVisibility(VISIBLE);
                tv.setTextColor(Color.parseColor("#ffff0000"));
                this.setTag("checked");
            }
        }

        /**
         * 设置为未选中
         */
        private void unchecked(){
            if("checked".equals(this.getTag())) {
                iv.setVisibility(GONE);
                tv.setTextColor(Color.parseColor("#55888888"));
                this.setTag(null);
            }
        }

        @Override
        public void onClick(View v) {
            if(getTag() == null || !getTag().equals("checked")) {
                selectTimerByPosition(position);
                vp_id.setCurrentItem(position * events);
            }
        }
    }

    /**
     * 根据tab下标选中该tab
     * @param position
     */
    private void selectTimerByPosition(int position){
        TimerTab tt = (TimerTab) ll_time.findViewWithTag("checked");
        if(tt != null) {
            tt.unchecked();
        }

        tt = (TimerTab) ll_time.getChildAt(position);
        tt.checked();
    }
}
