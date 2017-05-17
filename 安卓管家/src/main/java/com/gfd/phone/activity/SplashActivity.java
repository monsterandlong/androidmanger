package com.gfd.phone.activity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 应用的闪屏页面
 */
public class SplashActivity extends BaseActivity {

    /**
     * 定时的TextView
     */
    private TextView mTvTimer;
    private int time = 6;
    private Timer timer;

    @Override
    protected void initData() {
        timer = new Timer(false);
        timer.schedule(new TimerTask() {
            //此方法是在子线程中执行的
            @Override
            public void run() {
                //更新时间
                SplashActivity.this.runOnUiThread(new TimerTask() {
                    @Override
                    public void run() {
                        String timeStr = getString(R.string.splash_tv_timer);
                        timeStr = String.format(timeStr,--time);//   %s秒跳转
                        mTvTimer.setText(timeStr);
                        if(time == 0){
                            openActivity(HomeActivity.class,true);
                            timer.cancel();//取消定时任务
                        }
                    }
                });
            }
        }, 0, 1000);//1s后开始执行，然后没隔1秒就会循环调用

    }

    @Override
    protected void initView() {
        mTvTimer = getViewById(R.id.tv_splash_timer);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setListaner() {
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();//取消定时
                openActivity(HomeActivity.class,true);
            }
        });
    }

    @Override
    protected boolean isShowActionBar() {
        return false;
    }

    @Override
    protected void init() {
        //去掉标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
