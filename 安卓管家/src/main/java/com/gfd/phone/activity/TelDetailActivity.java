package com.gfd.phone.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gfd.phone.R;
import com.gfd.phone.adapter.TelDetailAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.TelInfo;
import com.gfd.phone.utils.DBUtils;

import java.util.List;

public class TelDetailActivity extends BaseActivity {


    private ListView mList;
    private List<TelInfo> serviceTelInfos;
    private Intent intent;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        //获取上一页面传递过来的数据
        Intent intent = getIntent();
        int idx = intent.getIntExtra("idx", -1);
        String name = intent.getStringExtra("name");
        //设置标题
        setTitle(name);
        serviceTelInfos = DBUtils.getServiceTelInfos(idx);
        mList.setAdapter(new TelDetailAdapter(serviceTelInfos));

    }

    @Override
    protected void initView() {
        mList = getViewById(R.id.list_teldetail);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teldetail;
    }

    @Override
    protected void setListaner() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取要拨打的电话
                String telNum = serviceTelInfos.get(position).getTelNum();
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telNum));
                if(Build.VERSION.SDK_INT >= 23){//6.0+
                    //1.上下文
                    if (ActivityCompat.checkSelfPermission(TelDetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        //没有授予去权限，需要请求权限
                        ActivityCompat.requestPermissions(TelDetailActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
                        return;
                    } else {
                        startActivity(intent);
                    }
                }else{
                    startActivity(intent);
                }
            }

        });
    }

    /**
     * 请求权限结果回调
     *
     * @param requestCode  :权限请求码，区分到底是哪个请求的
     * @param permissions  ：请求的权限
     * @param grantResults ：请求结果集
     */
    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//用户没有同意权限
            Toast.makeText(this, "没有相应的权限，功能无法使用", Toast.LENGTH_SHORT).show();
        } else {//用户同意了
            startActivity(intent);
        }
    }
}
