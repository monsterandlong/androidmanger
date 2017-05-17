package com.gfd.phone.activity;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.adapter.RocketAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.RunInfo;
import com.gfd.phone.utils.MemoryInfoManager;
import com.gfd.phone.utils.Program;

import java.util.ArrayList;
import java.util.List;


/**
 * 手机加速界面
 */
public class RocketActivity extends BaseActivity implements View.OnClickListener {


    private TextView mTvModel;
    private ProgressBar mProgressMemory;
    private Button mBtnClear;
    private ListView mListApps;
    private ProgressBar mProgress;
    private Button mBtnState;
    private List<RunInfo> data;
    private RocketAdapter adapter;
    private List<RunInfo> allUser;
    public static CheckBox mCbAllSelect;




    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("手机加速");
        mTvModel.setText(Build.MODEL);
        long avaiable = MemoryInfoManager.getPhoneAvailableMemoeySize();
        long total = MemoryInfoManager.getPhoneTotalMemoeySize();
        double rote = (total - avaiable) * 1.0 / total;
        mProgressMemory.setProgress((int) (rote * 100));
        mBtnState.setText("只显示系统进程");
        mBtnState.setTag(true);
        new Thread() {
            @Override
            public void run() {
                allUser = Program.getUser();
                RocketActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new RocketAdapter(allUser);
                        mListApps.setAdapter(adapter);
                    }
                });
            }
        }.start();

    }

    @Override
    protected void initView() {
        mTvModel = getViewById(R.id.tv_rocket_model);
        mProgressMemory = getViewById(R.id.pb_rocket_rote);
        mBtnClear = getViewById(R.id.bt_rocket_clear);
        mListApps = getViewById(R.id.list_rocket_appinfo);
        mProgress = getViewById(R.id.pb_rocket_clear);
        mBtnState = getViewById(R.id.bt_rocket_state);
        mCbAllSelect = getViewById(R.id.cb_rocket_allselct);


    }

    @Override
    protected void setListaner() {
        mBtnClear.setOnClickListener(this);
        mBtnState.setOnClickListener(this);
        //全选选择监听
       mCbAllSelect.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               for (RunInfo runInfo : allUser) {
                   runInfo.setIsselect(mCbAllSelect.isChecked());
               }
               adapter.notifyDataSetChanged();
           }
       });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rocket;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_rocket_clear://一键清理
                List<RunInfo> runInfoList=new ArrayList<>();
                for (RunInfo info : allUser) {
                    if (info.isselect()){
                        Program.killApp(info.getPackname());
                    }else{
                        runInfoList.add(info);
                    }
                }
                allUser.clear();
                allUser.addAll(runInfoList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.bt_rocket_state://进程切换
                final boolean isUserApp = (boolean) mBtnState.getTag();
                new Thread() {
                    @Override
                    public void run() {
                        if (isUserApp) {//显示当前用户信息
                            mBtnState.setTag(false);
                            data = Program.getAllUser();
                            Log.e("aa", "size" + data.size());
                        } else {//显示系统信息
                            mBtnState.setTag(true);
                            data = Program.getUser();
                        }
                        //只能在主线程更新UI布局
                        RocketActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                boolean tag = (boolean) mBtnState.getTag();
                                String msg = (tag == true) ? "只显示用户进程" : "只显示系统进程";
                                mBtnState.setText(msg);
                                allUser.clear();
                                allUser.addAll(data);
                                adapter.notifyDataSetChanged();

                            }
                        });
                    }
                }.start();
                break;
        }
    }

}
