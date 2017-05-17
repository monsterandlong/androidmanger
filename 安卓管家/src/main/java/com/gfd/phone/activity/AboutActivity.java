package com.gfd.phone.activity;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;

/**
 * 关于界面
 */
public class AboutActivity extends BaseActivity {


    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("关于我们");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }
}
