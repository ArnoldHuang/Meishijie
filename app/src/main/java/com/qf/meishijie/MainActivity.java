package com.qf.meishijie;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.qf.base.BaseActivity;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private long mExitTime = 0;
    public static final int EXIT_TIME_GAP = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
