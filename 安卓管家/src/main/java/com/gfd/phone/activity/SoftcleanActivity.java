package com.gfd.phone.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.gfd.phone.R;
import com.gfd.phone.adapter.SoftAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.RunInfo;
import com.gfd.phone.utils.Program;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;

public class SoftcleanActivity extends BaseActivity {

    public SoftAdapter softAdapter;
    public ListView mList;
    public List<RunInfo> datas=new ArrayList<>();
    public List<RunInfo> appInfo;
    public static CheckBox mCbAllselect;
    public AppRecevise recevise;


    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        softAdapter = new SoftAdapter(datas);
        mList.setAdapter(softAdapter);
        Intent intent=getIntent();
        int id=intent.getIntExtra("id",-1);
        switch (id){
            case R.id.tv_soft_all:
                setTitle("所有软件");
                appInfo = Program.getAppInfo();
                break;
            case R.id.tv_soft_user:
                setTitle("用户软件");
                appInfo = Program.getAppUser();
                break;
            case R.id.tv_soft_system:
                setTitle("系统软件");
                appInfo = Program.getAppSystem();
                break;
        }
        softAdapter.refresh(appInfo);
    }

    @Override
    protected void initView() {
        mList = getViewById(R.id.list_soft);
        mCbAllselect = getViewById(R.id.cb_soft_seclect);
        //广播注册
        recevise = new AppRecevise();
        IntentFilter filter=new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        filter.addDataScheme("package");
        registerReceiver(recevise,filter);
    }
    @Override
    protected void setListaner() {
        mCbAllselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (RunInfo data : appInfo) {
                    data.setIsselect(mCbAllselect.isChecked());
                }
                softAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recevise);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_softclean;
    }


    public void bt_soft_all(View view){
        for (RunInfo data : datas) {
            if (data.isselect()){
                Intent intent=new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+data.getPackname()));
                startActivity(intent);
            }
        }

    }
    //卸载成功
    private class AppRecevise extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (id) {
                case R.id.tv_soft_all:
                    appInfo = Program.getAppInfo();
                    break;
                case R.id.tv_soft_user:
                    appInfo = Program.getAppUser();
                    break;
                case R.id.tv_soft_system:
                    appInfo = Program.getAppSystem();
                    break;
            }
            softAdapter.refresh(appInfo);
            mCbAllselect.setChecked(false);
        }
    }

}

