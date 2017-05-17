package com.gfd.phone.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by 张志龙 on 2017/4/27.
 */

public class FileShowInfo {
     String name;
     Drawable ioce;
     String date;
     String size;
    boolean Select;
    public void SetSelect(boolean select){
        this.Select=select;
    }
    public boolean isSelect() {
        return Select;
    }

    public void setSelect(boolean select) {
        Select = select;
    }


    public FileShowInfo(String name, Drawable ioce, String date, String size) {
        this.name = name;
        this.ioce = ioce;
        this.date = date;
        this.size = size;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIoce() {
        return ioce;
    }

    public void setIoce(Drawable ioce) {
        this.ioce = ioce;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


}
