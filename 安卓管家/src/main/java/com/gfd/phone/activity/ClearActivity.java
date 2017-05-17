package com.gfd.phone.activity;

import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.adapter.CleanAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.CleanInfo;
import com.gfd.phone.utils.CleanManger;

import java.util.List;

/**
 * 垃圾清理界面
 */
public class ClearActivity extends BaseActivity {


    private ListView mList;
    public static CheckBox mCbAll;
    private List<CleanInfo> cleanInfoList;
    private Button mBtClean;
    private CleanAdapter adapter;
    private TextView mtv;
    CleanInfo cleanInfo=new CleanInfo();

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("垃圾清理");
        cleanInfoList = CleanManger.getAllInfo();
        adapter = new CleanAdapter(cleanInfoList);
        mList.setAdapter(adapter);
        mtv.setText(Formatter.formatFileSize(this,cleanInfo.getSize()));
    }

    @Override
    protected void initView() {
        mList = getViewById(R.id.list_clean);
        mCbAll = getViewById(R.id.cb_clean);
        mBtClean = getViewById(R.id.bt_clean);
        mtv = getViewById(R.id.tv_clean);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_clear;
    }

    @Override
    protected void setListaner() {
       mCbAll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               for (CleanInfo cleanInfo : cleanInfoList) {
                   cleanInfo.setSelect(mCbAll.isChecked());
               }
               adapter.notifyDataSetChanged();
           }
       });
    }
    public void bt_clean(View view){
        for (int i = 0; i < cleanInfoList.size(); i++) {
            CleanInfo cleanInfo=cleanInfoList.get(i);
            if (cleanInfo.isSelect()){
                cleanInfoList.remove(cleanInfo);
            }
        }
        adapter.notifyDataSetChanged();
        long size=0;
        for (CleanInfo cleanInfo : cleanInfoList) {
            size+=cleanInfo.getPath().length();
        }
        mtv.setText(Formatter.formatFileSize(this,size));
    }
}