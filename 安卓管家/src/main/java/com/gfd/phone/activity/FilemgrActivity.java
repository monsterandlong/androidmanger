package com.gfd.phone.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gfd.phone.R;
import com.gfd.phone.base.BaseActivity;
import com.gfd.phone.entity.FileInfo;
import com.gfd.phone.utils.FileManger;
import com.gfd.phone.utils.FileTypeUtil;

import java.util.List;

/**
 * 文件管理界面
 */
public class FilemgrActivity extends BaseActivity implements View.OnClickListener {


    private TextView mSize;
    private TextView mAll;
    private TextView mWord;
    private TextView mVoide;
    private TextView mAudio;
    private TextView mPicture;
    private TextView mZip;
    private TextView mApp;
    public static String all;
    public static String word;
    public static String vodie;
    public static String audio;
    public static String img;
    public static String zip;
    public static String apk;
    Handler handler = new Handler() {



        @Override
        public void handleMessage(Message msg) {
            all = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getAllSzie());
            word = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getWordSzie());
            vodie = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getvodieSzie());
            audio = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getandioSzie());
            img = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getimgSzie());
            zip = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getzipSzie());
            apk = Formatter.formatFileSize(FilemgrActivity.this, fileManger.getapkSSzie());
            switch (msg.what) {
                case 0:
                    String tybeName = (String) msg.obj;
                    mAll.setText(all);
                    mSize.setText(Formatter.formatFileSize(FilemgrActivity.this, fileManger.getAllSzie()));
                    switch (tybeName) {
                        case FileTypeUtil.TYPE_TXT:
                            mWord.setText(word);
                            break;
                        case FileTypeUtil.TYPE_VIDEO:
                            mVoide.setText(vodie);
                            break;
                        case FileTypeUtil.TYPE_AUDIO:
                            mAudio.setText(audio);
                            break;
                        case FileTypeUtil.TYPE_IMAGE:
                            mPicture.setText(img);
                            break;
                        case FileTypeUtil.TYPE_ZIP:
                            mZip.setText(zip);
                            break;
                        case FileTypeUtil.TYPE_APK:
                            mApp.setText(apk);
                            break;
                    }
                    break;
                case 1:
                    mAll.setText(all);
                    mSize.setText(all);
                    mWord.setText(word);
                    mVoide.setText(vodie);
                    mAudio.setText(audio);
                    mPicture.setText(img);
                    mZip.setText(zip);
                    mApp.setText(apk);
                    break;

            }
        }
    };
    private FileManger fileManger;
    private RelativeLayout mRLall;
    private RelativeLayout mRLword;
    private RelativeLayout mRLvodie;
    private RelativeLayout mRLandio;
    private RelativeLayout mRLimg;
    private RelativeLayout mRLzip;
    private RelativeLayout mRLapk;
    public static List<FileInfo> apkList;
    public static List<FileInfo> imgList;
    public static List<FileInfo> zipList;
    public static List<FileInfo> andioList;
    public static List<FileInfo> vodieList;
    public static List<FileInfo> wordList;
    public static List<FileInfo> allList;

    @Override
    protected boolean isShowActionBar() {
        return true;
    }

    @Override
    protected void initData() {
        setTitle("文件清理");

        fileManger = new FileManger();
        allList = fileManger.getallList();
        wordList = fileManger.getwordList();
        vodieList = fileManger.getvodieList();
        andioList = fileManger.getandioList();
        zipList = fileManger.getzipList();
        imgList = fileManger.getimgList();
        apkList = fileManger.getapklList();

        FileManger.searchONclick fileListener = new FileManger.searchONclick() {

            @Override
            public void searchTybe(String tybeaName) {
                Message msg = handler.obtainMessage();
                msg.obj = tybeaName;
                msg.what = 0;
                handler.sendMessage(msg);
            }

            @Override
            public void searchEnd() {
                Message msg = handler.obtainMessage();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        fileManger.setSearchListener(fileListener);
        new Thread() {
            @Override
            public void run() {
                super.run();
                fileManger.search();
            }
        }.start();

    }


    @Override
    protected void initView() {
        mSize = getViewById(R.id.tv_file_size);
        mAll = getViewById(R.id.tv_file_all);
        mWord = getViewById(R.id.tv_file_word);
        mVoide = getViewById(R.id.tv_file_voide);
        mAudio = getViewById(R.id.tv_file_audio);
        mPicture = getViewById(R.id.tv_file_pic);
        mZip = getViewById(R.id.tv_file_yasuo);
        mApp = getViewById(R.id.tv_file_app);
        mRLall = getViewById(R.id.rela_fileshow_all);
        mRLword = getViewById(R.id.rela_fileshow_word);
        mRLvodie = getViewById(R.id.rela_fileshow_voide);
        mRLandio = getViewById(R.id.rela_fileshow_andio);
        mRLimg = getViewById(R.id.rela_fileshow_pic);
        mRLzip = getViewById(R.id.rela_fileshow_zip);
        mRLapk = getViewById(R.id.rela_fileshow_apk);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_filemgr;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(FilemgrActivity.this,FileShowActivity.class);
        intent.putExtra("id", v.getId());
        startActivity(intent);
        finish();
    }

    @Override
    protected void setListaner() {
        super.setListaner();
        mRLall.setOnClickListener(this);
        mRLword.setOnClickListener(this);
        mRLvodie.setOnClickListener(this);
        mRLandio.setOnClickListener(this);
        mRLzip.setOnClickListener(this);
        mRLapk.setOnClickListener(this);
        mRLimg.setOnClickListener(this);

    }

}
