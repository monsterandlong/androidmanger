package com.gfd.phone.activity;

import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gfd.phone.R;
import com.gfd.phone.adapter.FileShowAdapter;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.FileInfo;

import java.util.List;

public class FileShowActivity extends BaseActivity {


    private TextView mTvCount;
    private TextView mTvSize;
    private ListView mList;
    private Button mBtUnistall;
    public List<FileInfo> fileInfos;
    public FileShowAdapter adapter;
    private int a;
    private String size;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    //重新基类，为了刷新文件的界面
    protected void initActionBar(boolean showActionBar) {
        super.initActionBar(showActionBar);
        ImageView imageView= getViewById(R.id.img_root_title);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(FilemgrActivity.class,true);
            }
        });
    }

    @Override
    protected void initData() {
        a = getIntent().getIntExtra("id", -1);
        switch (a) {
            case R.id.rela_fileshow_all:
                setTitle("全部");
                fileInfos = FilemgrActivity.allList;
                size = FilemgrActivity.all;
                break;
            case R.id.rela_fileshow_word:
                fileInfos = FilemgrActivity.wordList;
                size = FilemgrActivity.word;
                setTitle("文档");
                break;
            case R.id.rela_fileshow_voide:
                fileInfos = FilemgrActivity.vodieList;
                size = FilemgrActivity.vodie;
                adapter = new FileShowAdapter(FilemgrActivity.vodieList);
                mTvCount.setText(FilemgrActivity.vodieList.size() + "");
                mTvSize.setText(FilemgrActivity.vodie);

                setTitle("视频");
                break;
            case R.id.rela_fileshow_andio:
                fileInfos = FilemgrActivity.andioList;
                size = FilemgrActivity.audio;
                setTitle("音频");
                break;
            case R.id.rela_fileshow_zip:
                fileInfos = FilemgrActivity.zipList;
                size = FilemgrActivity.zip;
                setTitle("压缩包");
                break;
            case R.id.rela_fileshow_apk:
                fileInfos = FilemgrActivity.apkList;
                size = FilemgrActivity.apk;
                setTitle("程序包");
                break;
            case R.id.rela_fileshow_pic:
                fileInfos = FilemgrActivity.imgList;
                size = FilemgrActivity.img;
                setTitle("图片");
                break;
        }
        adapter = new FileShowAdapter(fileInfos);
        mList.setAdapter(adapter);
        mTvCount.setText(fileInfos.size() + "");
        mTvSize.setText(size);

    }

    @Override
    protected void initView() {
        mTvCount = getViewById(R.id.tv_fileshow_count);
        mTvSize = getViewById(R.id.tv_fileshow_size);
        mList = getViewById(R.id.list_fileshow);
        mBtUnistall = getViewById(R.id.bt_fileshow_unitstall);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_show;
    }

    public void deleFile(View view) {
        for (int i = 0; i < fileInfos.size(); i++) {
            FileInfo fileInfo = fileInfos.get(i);
            if (fileInfo.isSelect()) {
                if (fileInfo.getName().delete()) {
                    fileInfos.remove(fileInfo);
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
        adapter.notifyDataSetChanged();
        long size = 0;
        for (FileInfo fileInfo : fileInfos) {
            size += fileInfo.getName().length();
        }
        mTvCount.setText(fileInfos.size() + "");
        mTvSize.setText(Formatter.formatFileSize(this, size));
    }

}