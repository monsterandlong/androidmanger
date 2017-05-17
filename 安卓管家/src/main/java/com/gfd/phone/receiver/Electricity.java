package com.gfd.phone.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import static com.gfd.phone.activity.PhonemgrActivity.mProgress;
import static com.gfd.phone.activity.PhonemgrActivity.mRela;
import static com.gfd.phone.activity.PhonemgrActivity.mTextdian;

/**
 * Created by 张志龙 on 2017/4/26.
 */

public class Electricity extends BroadcastReceiver {

    public static int nowelec;
    public static int totalelec;
    public  int percent;
    private int mtemperature;


    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BATTERY_CHANGED)){
            nowelec = intent.getExtras().getInt("level");
            totalelec = intent.getExtras().getInt("scale");
            mtemperature = intent.getIntExtra("temperature",0);
            Log.e("aa","aa1="+nowelec);
            Log.e("aa","aa2="+totalelec);
            percent = nowelec * 100 / totalelec;
            Log.e("aa","aa3="+percent);
            mTextdian.setText(percent+"%");
            mProgress.setProgress(percent);
            mRela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    String[] s={"当前电量为"+nowelec,"电池温度"+mtemperature};
                    builder.setTitle("电池信息").setItems(s,null);
                    builder.create().show();
                }
            });
        }
    }

}