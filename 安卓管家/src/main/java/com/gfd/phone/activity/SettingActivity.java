package com.gfd.phone.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.utils.SPreUtils;

/**
 * 设置界面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private CheckBox mCbBoot;
    private CheckBox mCbNotifition;
    private CheckBox mCbPush;
    private ImageView mImgHelp;
    private ImageView mImgIdea;
    private ImageView mImgMe;
    private ImageView mImgShape;
    private ImageView mImgVersion;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("设置");
        boolean boot = SPreUtils.getBoolean("boot", false);
        mCbBoot.setChecked(boot);
        boolean notifation = SPreUtils.getBoolean("notifation", false);
        mCbNotifition.setChecked(notifation);

    }

    @Override
    protected void initView() {
        mCbBoot = getViewById(R.id.cb_setting_boot);
        mCbNotifition = getViewById(R.id.cb_setting_notification);
        mCbPush = getViewById(R.id.cb_setting_push);
        mImgHelp = getViewById(R.id.img_setting_help);
        mImgIdea = getViewById(R.id.img_setting_idea);
        mImgMe = getViewById(R.id.img_setting_me);
        mImgShape = getViewById(R.id.img_setting_share);
        mImgVersion = getViewById(R.id.img_setting_version);

    }

    @Override
    protected void setListaner() {
        mImgHelp.setOnClickListener(this);
        mImgMe.setOnClickListener(this);
        mImgIdea.setOnClickListener(this);
        mImgShape.setOnClickListener(this);
        mImgVersion.setOnClickListener(this);
        mCbBoot.setOnCheckedChangeListener(this);
        mCbPush.setOnCheckedChangeListener(this);
        mCbNotifition.setOnCheckedChangeListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_setting_help://帮助说明
                Intent intent = new Intent(SettingActivity.this,GuideActivity.class);
                intent.putExtra("help",true);
                startActivity(intent);
                break;
            case R.id.img_setting_idea://意见反馈
                break;
            case R.id.img_setting_share://好友分享
                break;
            case R.id.img_setting_version://版本更新
                break;
            case R.id.img_setting_me://关于我们
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_setting_boot://开机启动
                SPreUtils.saveBoolean("boot", isChecked);
                break;
            case R.id.cb_setting_notification://消息通知
                SPreUtils.saveBoolean("notifation", isChecked);
                if (isChecked) {//显示
                    showNotifation();
                } else {//关闭通知
                    NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.cancel(0);
                }
                break;
            case R.id.cb_setting_push://消息推送
                break;
        }
    }

    /**
     * 显示通知
     */
    private void showNotifation() {
        //设置通知的一些属性
        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("安卓管家")
                        .setContentText("点击清理");
        //设置通知的点击事件（点击之后跳转到哪里）
        Intent resultIntent = new Intent(this, HomeActivity.class);
        //扩进程启动
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(HomeActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        //通过通知管理者显示通知
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());//显示通知
    }
}
