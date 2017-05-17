package com.gfd.phone.entity;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Created by 张志龙 on 2017/4/27.
 */

public class FileInfo {
    private String tybeName;
    private String fileSize;
    private File name;
    public boolean select;
    public String time;

    public String getTime() {
        long time=name.lastModified();
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return date.format(time);
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public FileInfo(File name, String tybeName, String fileSize) {
        this.tybeName = tybeName;
        this.fileSize = fileSize;
        this.name=name;
    }

    public String getTybeName() {
        return tybeName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public File getName() {
        return name;
    }

}
