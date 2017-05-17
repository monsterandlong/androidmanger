package com.gfd.phone.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gfd.phone.R;


public abstract class BaseActivity extends AppCompatActivity {

    /**
     * ActionBar上的标题
     */
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        super.setContentView(R.layout.layout_root);
        setContentView(getContentView()); //当没有是（）里为getLayoutId
        initActionBar(isShowActionBar());
        initView();
        initData();
        setListaner();
    }
    //***************可以没有***************************
    /**
     * 初始化Actionbar
     *
     * @param showActionBar :是否显示ActionBar
     */
    protected void initActionBar(boolean showActionBar) {
        RelativeLayout mActionBar = getViewById(R.id.relay_root_actionbar);
        if (showActionBar) {
            mTitle = getViewById(R.id.tv_root_title);
            ImageView image = getViewById(R.id.img_root_title);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();//返回上一界面
                }
            });
        } else {//不显示
            mActionBar.setVisibility(View.GONE);
        }

    }

    //***************可以没有***************************
    /**
     * 是否显示ActionBar
     *
     * @return true:表示显示
     */
    protected abstract boolean isShowActionBar();
    //***************可以也没有***************************
    private View getContentView() {
        LinearLayout mRoot = getViewById(R.id.lin_root);
        //将子类的布局解析成View
        View subView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        //设置大小
        subView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mRoot.addView(subView);
        return mRoot;
    }

    protected void init() {

    }

    /**
     * 设置监听
     */
    protected void setListaner() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 设置布局ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 以Toast的方式显示文本信息
     *
     * @param text
     */
    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 启动一个activty
     *
     * @param cls
     */
    protected void openActivity(Class<? extends BaseActivity> cls) {
        startActivity(new Intent(this, cls));
    }

    /**
     * 启动activity
     *
     * @param cls
     * @param isFinish :是否关闭当前的Activity
     */
    protected void openActivity(Class<? extends BaseActivity> cls, boolean isFinish) {
        startActivity(new Intent(this, cls));
        if (isFinish) finish();
    }

    /**
     * 通过ViewID找View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T getViewById(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }
    //***************可以没有***************************
    /**
     * 设置标题
     *
     * @param text
     */
    protected void setTitle(String text) {
        mTitle.setText(text);
    }


}
