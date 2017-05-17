package com.gfd.phone.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.format.Formatter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.receiver.Electricity;
import com.gfd.phone.utils.MemoryInfoManager;
import com.gfd.phone.utils.PhoneUtils;

/**
 * 手机检测界面
 */
public class PhonemgrActivity extends BaseActivity {


    public static ProgressBar mProgress;
    private TextView mTextName;
    private TextView mTextVersion;
    private TextView mTotalCpu;
    private TextView mAvailableCpu;
    private TextView mTotalMemory;
    private TextView mAvailableMemory;
    private TextView mRootFBL;
    private TextView mCameraFBL;
    private TextView mParentRoot;
    private TextView mIsroot;
    public static TextView mTextdian;
    private Electricity electricity;
    public static RelativeLayout mRela;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
      setTitle("手机检测");
        electricity = new Electricity();
        IntentFilter intentFilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(electricity,intentFilter);


        mTextName.setText("设备名称："+ Build.MODEL);
        mTextVersion.setText("版本型号:"+Build.VERSION.RELEASE);
        long totalMemory=MemoryInfoManager.getPhoneTotalMemoeySize();
        long avaiableMemory=MemoryInfoManager.getPhoneAvailableMemoeySize();
        String Memory=Formatter.formatFileSize(this,totalMemory);
        String MemoryAvail=Formatter.formatFileSize(this,avaiableMemory);
        mTotalMemory.setText("全部的运行内存:"+Memory );
        mAvailableMemory.setText("剩余的运行内存:"+MemoryAvail);
        mTotalCpu.setText("CPU的名称"+ PhoneUtils.getCpuName());
        mAvailableCpu.setText("CPU的数量:"+PhoneUtils.getCpuCount());
        mParentRoot.setText("基带版本："+Build.RADIO);
        String selectRoot="";
        if (PhoneUtils.isRoot()){
            selectRoot="是";
        }else {
            selectRoot="否";
        }
        mIsroot.setText("是否Root："+selectRoot);
        mRootFBL.setText("手机分辨率："+PhoneUtils.getRootFBL(PhonemgrActivity.this));
        mCameraFBL.setText("相机分辨率:"+PhoneUtils.getMaxPhotoSize());

    }

    @Override
    protected void initView() {
        mProgress = getViewById(R.id.pb_phonemgr_elsctricity);  //电池电量
        mTextName = getViewById(R.id.tv_phone_name);  //设备名称
        mTextVersion = getViewById(R.id.tv_phone_banben);  //版本信息
        mTotalCpu = getViewById(R.id.tv_phone_totalcpu);
        mAvailableCpu = getViewById(R.id.tv_phone_availableCPU);
        mTotalMemory = getViewById(R.id.tv_phone_totalmemory);
        mAvailableMemory = getViewById(R.id.tv_phone_availableMemory);//分辨率
        mRootFBL = getViewById(R.id.tv_phone_rootFBL);//相机分辨率
        mCameraFBL = getViewById(R.id.tv_phone_camera);
        mParentRoot = getViewById(R.id.tv_phone_parentroot);
        mIsroot = getViewById(R.id.tv_phone_isroot);
        mTextdian = getViewById(R.id.tv_phone_dinachi);
        mRela = getViewById(R.id.rela_phone);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_phonemgr;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(electricity);
    }

}
