package com.gfd.phone.activity;

import android.content.Intent;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.utils.MemoryInfoManager;
import com.gfd.phone.view.CleaeArcView;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * 软件管理界面
 */
public class SoftmgrActivity extends BaseActivity implements View.OnClickListener{

    private CleaeArcView rootView;
    private CleaeArcView sdView;
    private ProgressBar rootProgress;
    private ProgressBar sdProgress;
    private TextView rootText;
    private TextView sdText;
    private TextView tvall;
    private TextView tvuser;
    private TextView tvsystem;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("软件管理");
        long rootavailable = MemoryInfoManager.rootavailable();
        long rootlTotal = MemoryInfoManager.rootlTotal();
        long sdtotal = MemoryInfoManager.sdtatal();
        long sdavaiable = MemoryInfoManager.sdavaiable();
        int rootendsweep= (int) (rootlTotal*1.0f/(rootlTotal+sdtotal)*360);
        int sdendsweep= (int) (sdtotal*1.0f/(rootlTotal+sdtotal)*360);
        rootView.setSweepAngle(-rootendsweep);
        rootView.setColor("#3852d2");
        sdView.setSweepAngle(sdendsweep);
        sdView.setColor("#e43b5a");
        int rootpro= (int) ((rootlTotal-rootavailable)*1.0f/rootlTotal*100);
        String roottv = Formatter.formatFileSize(this, (rootlTotal - rootavailable));
        String roottv2 = Formatter.formatFileSize(this, rootlTotal);
        int sdpro= (int) ((sdtotal-sdavaiable)*1.0f/sdtotal*100);
        String sdtv = Formatter.formatFileSize(this, (sdtotal - sdavaiable));
        String sdtv2 = Formatter.formatFileSize(this, sdtotal);
        rootProgress.setProgress(rootpro);
        sdProgress.setProgress(sdpro);
        rootText.setText("内置空间"+roottv+"/"+roottv2);
        sdText.setText("外部空间"+sdtv+"/"+sdtv2);
    }

    @Override
    protected void initView() {
        rootView = getViewById(R.id.myview_softmgr_root);
        sdView = getViewById(R.id.myview_softmgr_sd);
        rootProgress = getViewById(R.id.pb_softmgr_root);
        sdProgress = getViewById(R.id.pb_softmgr_sd);
        rootText = getViewById(R.id.tv_soft_root);
        sdText = getViewById(R.id.tv_soft_sd);
        tvall = getViewById(R.id.tv_soft_all);
        tvuser = getViewById(R.id.tv_soft_user);
        tvsystem = getViewById(R.id.tv_soft_system);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_softmgr;
    }

    @Override
    protected void setListaner() {
        tvall.setOnClickListener(SoftmgrActivity.this);
        tvuser.setOnClickListener(SoftmgrActivity.this);
        tvsystem.setOnClickListener(SoftmgrActivity.this);
    }

    @Override
    public void onClick(View v) {
                Intent intent=new Intent(SoftmgrActivity.this,SoftcleanActivity.class);
                intent.putExtra("id",v.getId());
                startActivity(intent);
    }
}


