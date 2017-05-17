package com.gfd.phone.utils;

import android.os.Environment;

import com.gfd.phone.entity.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张志龙 on 2017/4/27.
 */

public class FileManger {
    public  List<FileInfo> allList = new ArrayList<>();
    public  List<FileInfo> wordList = new ArrayList<>();
    public  List<FileInfo> vodieList = new ArrayList<>();
    public  List<FileInfo> andioList = new ArrayList<>();
    public  List<FileInfo> imgList = new ArrayList<>();
    public  List<FileInfo> zipList = new ArrayList<>();
    public  List<FileInfo> apkList = new ArrayList<>();

    public  long wordSize;
    public  long vodieSize;
    public  long andioSize;
    public  long imgSize;
    public  long zipSize;
    public  long apkSize;
    long allSize;
    public  long getAllSzie(){return allSize;}
    public  long getWordSzie(){return wordSize;}
    public  long getvodieSzie(){return vodieSize;}
    public  long getandioSzie(){return andioSize;}
    public  long getimgSzie(){return imgSize;}
    public  long getzipSzie(){return zipSize;}
    public  long getapkSSzie(){return apkSize;}
    public  List<FileInfo> getallList(){return allList;}
    public  List<FileInfo> getwordList(){return wordList;}
    public  List<FileInfo> getvodieList(){return vodieList;}
    public  List<FileInfo> getandioList(){return andioList;}
    public  List<FileInfo> getimgList(){return imgList;}
    public  List<FileInfo> getzipList(){return zipList;}
    public  List<FileInfo> getapklList(){return apkList;}


    private searchONclick fileListener;
    private boolean isStopSearch;

    public void setStopSearch(boolean isStopSearch) {
        this.isStopSearch = isStopSearch;
    }

    public void search() {
        String rootpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(rootpath);
        search(file, false);

    }

    public void search(File file, boolean isFileRun) {
        if (isStopSearch) {
            if (fileListener != null && isFileRun) fileListener.searchEnd();
            return;
        }
        if (file != null || file.exists() || file.canRead()) {

            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File file1 : files) {
                    search(file1, false);
                }
            } else {
                String name = null;
                name = file.getName();
                String[] data = FileTypeUtil.getFileIconAndTypeName(file);
                String ioceNaem = data[0];
                String tybeName = data[1];
                FileInfo fileInfo = new FileInfo(file, data[0], data[1]);
                allList.add(fileInfo);
                allSize += file.length();
                switch (data[1]) {
                    case FileTypeUtil.TYPE_TXT:
                        wordList.add(fileInfo);
                        wordSize += file.length();
                        break;
                    case FileTypeUtil.TYPE_VIDEO:
                        vodieList.add(fileInfo);
                        vodieSize += file.length();
                        break;
                    case FileTypeUtil.TYPE_AUDIO:
                        andioList.add(fileInfo);
                        andioSize += file.length();
                        break;
                    case FileTypeUtil.TYPE_IMAGE:
                        imgList.add(fileInfo);
                        imgSize += file.length();
                        break;
                    case FileTypeUtil.TYPE_ZIP:
                        zipList.add(fileInfo);
                        zipSize += file.length();
                        break;
                    case FileTypeUtil.TYPE_APK:
                        apkList.add(fileInfo);
                        apkSize += file.length();
                        break;
                }
                if (fileListener != null) fileListener.searchEnd();
                return;
            }

        }


    }


    public interface searchONclick {
        void searchTybe(String tybeaName);

        void searchEnd();
    }

    public void setSearchListener(searchONclick fileListener) {

        this.fileListener = fileListener;
    }
}
