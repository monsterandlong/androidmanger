package com.gfd.phone.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.utils.MemoryInfoManager;
import com.gfd.phone.view.CleaeArcView;

/**
 * 应用程序的主页面
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mImageLeft;
    private ImageView mImageRight;
    private ImageView mImageClear;
    private CleaeArcView mClearSpeed;
    private TextView mTvRote;

    @Override
    protected boolean isShowActionBar() {
        return false;
    }

    @Override
    protected void initData() {
        long phoneTotalMemoeySize = MemoryInfoManager.getPhoneTotalMemoeySize();
        long phoneAvailableMemoeySize = MemoryInfoManager.getPhoneAvailableMemoeySize();
        float rote = (phoneTotalMemoeySize - phoneAvailableMemoeySize ) * 1.0f / phoneTotalMemoeySize;
        int startAngle = (int) (rote * 360);
        //设置加速球的初始值
        mClearSpeed.setStartAngle(startAngle);
        mClearSpeed.setColor("#e7cf30");
        int r = (int) (rote * 100);
        mTvRote.setText(r + "");

    }

    @Override
    protected void initView() {
        mImageLeft = getViewById(R.id.img_home_title_left);
        mImageRight = getViewById(R.id.img_home_title_right);
        mImageClear = getViewById(R.id.img_home_speed);
        mClearSpeed = getViewById(R.id.clear_home_speed);
        mTvRote = getViewById(R.id.tv_home_percent);
    }

    @Override
    protected void setListaner() {
        mImageLeft.setOnClickListener(this);
        mImageRight.setOnClickListener(this);
        mImageClear.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    /**
     * 功能item点击监听
     */
    public void item_click(View view) {
        switch (view.getId()) {
            case R.id.tv_home_item_rocket://手机加速
                openActivity(RocketActivity.class);
                break;
            case R.id.tv_home_item_softmgr://软件管理
                openActivity(SoftmgrActivity.class);
                break;
            case R.id.tv_home_item_phonemgr://手机检测
                openActivity(PhonemgrActivity.class);
                break;
            case R.id.tv_home_item_telmgr://通讯大全
                openActivity(TelmgrActivity.class);
                break;
            case R.id.tv_home_item_filemgr://文件管理
                openActivity(FilemgrActivity.class);
                break;
            case R.id.tv_home_item_sdclean://垃圾清理
                openActivity(ClearActivity.class);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_home_title_left) {//关于界面
            openActivity(AboutActivity.class);
        } else if (v.getId() == R.id.img_home_title_right) {//设置界面
            openActivity(SettingActivity.class);
        } else if (v.getId() == R.id.img_home_speed) {//加速求
            long phoneTotalMemoeySize = MemoryInfoManager.getPhoneTotalMemoeySize();
            long phoneAvailableMemoeySize = MemoryInfoManager.getPhoneAvailableMemoeySize();
            float rote = (phoneTotalMemoeySize - phoneAvailableMemoeySize ) * 1.0f / phoneTotalMemoeySize * 0.8f;
            int startAngle = (int) (rote * 360);
            mClearSpeed.setSweepAngle(startAngle);
            int r = (int) (rote * 100);
            mTvRote.setText(r + "");
        }
    }
}
