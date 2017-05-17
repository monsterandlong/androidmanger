package com.gfd.phone.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gfd.phone.activity.GuideActivity;
import com.gfd.phone.utils.SPreUtils;


public class BootReceiver extends BroadcastReceiver{

    /** 当接受的广播发送的时候 就会自动回调 */
    @Override
    public void onReceive(Context context, Intent intent) {
        boolean boot = SPreUtils.getBoolean("boot", false);
        if(boot){
            //启动应用
            Intent intent1  = new Intent(context, GuideActivity.class);
            //开始新的栈来管理启动Activity，因为当前环境非Content，是不存在栈的
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent1);
        }

    }
}
