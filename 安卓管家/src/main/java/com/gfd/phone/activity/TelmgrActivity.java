package com.gfd.phone.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gfd.phone.R;
import com.gfd.phone.adapter.TelMgrAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.Tel;
import com.gfd.phone.utils.DBUtils;

import java.util.List;

/**
 * 通讯大全界面
 */
public class TelmgrActivity extends BaseActivity {


    private GridView mGridTel;
    private List<Tel> telNames;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("通讯大全");
        //设置适配器
        telNames = DBUtils.getTelServiceName();
        mGridTel.setAdapter(new TelMgrAdapter(telNames));

    }

    @Override
    protected void initView() {
        mGridTel = getViewById(R.id.grid_telmgr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_telmgr;
    }

    @Override
    protected void setListaner() {
        mGridTel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TelmgrActivity.this, TelDetailActivity.class);
                int idx = telNames.get(position).getId();//获取点击的item的对应的数据id
                String  name = telNames.get(position).getName();//获取点击的item的对应的数据id
                intent.putExtra("idx", idx);
                intent.putExtra("name", name);
                startActivity(intent);

            }
        });

    }
}
