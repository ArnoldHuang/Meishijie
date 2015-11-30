package com.qf.meishijie;

import android.os.SystemClock;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qf.base.BaseActivity;
import com.qf.fragment.RecommentFragment;
import com.qf.util.UniversalUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    public static final int EXIT_TIME_GAP = 2000;

    @ViewInject(R.id.rb_recomment)
    private RadioButton rb_recom;


    /**
     * 初始化方法
     */
    @Override
    public void init() {
        rb_recom.performClick();
    }

    /**
     * RadioButton导航选择事件
     * @param group
     * @param checkedId
     */
    @Event(value = R.id.rg_tab, type = RadioGroup.OnCheckedChangeListener.class)
    private void onCheckedChangeEvent(RadioGroup group, int checkedId){
        switch (checkedId){
            case R.id.rb_recomment:
                fragmentManager(R.id.fl_frament_main, RecommentFragment.class);
                break;
            case R.id.rb_discover:
                break;
            case R.id.rb_shop:
                break;
            case R.id.rb_topic:
                break;
            case R.id.rb_wode:
                break;
        }
    }

    /**
     * 实现按两次back键退出程序的功能
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void goBack() {
        if (SystemClock.uptimeMillis() - mExitTime > EXIT_TIME_GAP) {
            Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
            mExitTime = SystemClock.uptimeMillis();
        } else {
            MainActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 退出APP时，清理图片缓存
         */
        UniversalUtil.clearCache();
    }
}
