package com.qf.custemview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qf.meishijie.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 导航图标控件 -- 就是配合viewpager的那个小点点，带动画效果
 */
public class NavView extends FrameLayout{

    private int checked;//被选中的图片资源ID
    private int unchecked;//未被选中的图片资源ID
    private int count;//原点总数量

    @ViewInject(R.id.ll_checked)
    private LinearLayout ll_checked;

    @ViewInject(R.id.ll_unchecked)
    private LinearLayout ll_unchecked;

    private ImageView civ;
    private LinearLayout.LayoutParams layoutParams;
    private LinearLayout.LayoutParams unlayoutParams;

    public NavView(Context context) {
        super(context);
        init();
    }

    public NavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paresAttr(attrs);
        init();
    }

    /**
     * 解析自定义属性
     * @param attrs
     */
    private void paresAttr(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.NavView);
        checked = typedArray.getResourceId(R.styleable.NavView_checked, 0);
        unchecked = typedArray.getResourceId(R.styleable.NavView_unchecked, 0);
        count = typedArray.getInt(R.styleable.NavView_count, 0);
    }

    /**
     * 初始化方法
     */
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custems_navview, this, true);
        x.view().inject(this);
        layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.nav_img_wh), getResources().getDimensionPixelOffset(R.dimen.nav_img_wh));
        unlayoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelOffset(R.dimen.nav_img_wh), getResources().getDimensionPixelOffset(R.dimen.nav_img_wh));

        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ll_checked.removeAllViews();
        ll_unchecked.removeAllViews();

        if(count != 0){
            civ = new ImageView(getContext());
            civ.setImageResource(checked);
            civ.setPadding(4, 4, 4, 4);
            civ.setLayoutParams(layoutParams);
            ll_checked.addView(civ);

            for(int i = 0; i < count; i++){
                ImageView iv = new ImageView(getContext());
                iv.setImageResource(unchecked);
                iv.setPadding(4, 4, 4, 4);
                iv.setLayoutParams(unlayoutParams);
                ll_unchecked.addView(iv);
            }
        }
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public void setUnchecked(int unchecked) {
        this.unchecked = unchecked;
    }

    public void setCount(int count) {
        this.count = count;
        initView();
    }

    /**
     * 修改被选中下标 -- 带动画
     * @param position
     * @param positionOffset
     */
    public void selectAnimation(int position, float positionOffset){
        if(civ != null){
            layoutParams.leftMargin = (int) (civ.getWidth() * (position + positionOffset));
            civ.setLayoutParams(layoutParams);
        }
    }
}
