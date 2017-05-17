package com.gfd.phone.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;

import com.gfd.phone.R;
import com.gfd.phone.adapter.GuidePagerAdapter;
import com.gfd.phone.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 新手指导页面
 */
public class GuideActivity extends BaseActivity {

    private ViewPager mPager;
    private GuidePagerAdapter adapter;
    private boolean isHelpOpen;

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
        //如果应用程序是第一次启动，就设置布局，否则直接跳转到闪屏页
        SharedPreferences sp = getSharedPreferences("appconfig", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        final Intent intent = getIntent();
        if (intent != null) {
            isHelpOpen = intent.getBooleanExtra("help", false);
        }
        if (!isHelpOpen) {//不是设置界面中的帮助说明启动的
            if (isFirst) {//是第一启动
                //记录启动的状态
                sp.edit().putBoolean("isFirst", false).commit();
            } else {//不是第一次启动
                openActivity(SplashActivity.class, true);
            }
        }

    }

    @Override
    protected void setListaner() {
        //设置滑动监听
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private Timer timer;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == adapter.getCount() - 1) {//是最后一个页面
                    //改参数表示是否是守护线程
                    timer = new Timer(false);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (isHelpOpen) {//是设置页面中的帮助说明启动的
                                finish();
                            } else {
                                openActivity(SplashActivity.class, true);
                            }

                        }
                    }, 2000);
                } else {
                    if (timer != null)
                        timer.cancel();//取消定时任务
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        //设置适配器
        adapter = new GuidePagerAdapter();
        mPager.setAdapter(adapter);
    }


    @Override
    protected void initView() {
        mPager = getViewById(R.id.pager_guide);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

}
