package com.gfd.phone.entity;

import java.io.File;

/**
 * Created by 张志龙 on 2017/5/4.
 */

public class CleanInfo {
    public String name;
    private File path;
    private long size;
    public boolean select;

    public CleanInfo() {

    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public CleanInfo(String name, File path, long size) {
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
