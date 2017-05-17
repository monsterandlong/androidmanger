package com.gfd.phone.entity;

import android.graphics.drawable.Drawable;
import android.text.format.Formatter;

import com.gfd.phone.app.App;

/**
 * Created by 张志龙 on 2017/4/21.
 */

public class RunInfo {
    String appname;
    String packname;
     String versionName;
    Drawable icon;
    long usemomory;
    boolean isselect;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public void isselect(boolean select){
      this.isselect=select;
    }

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }

    public RunInfo(String appname, Drawable icon, String packname, long usemomory){
      this.appname=appname;
        this.icon=icon;
        this.packname=appname;
        this.usemomory=usemomory;
    }
    public RunInfo(String appname, Drawable icon, String packname, String versionName){
        this.appname=appname;
        this.icon=icon;
        this.packname=packname;
        this.versionName = versionName;

    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname) {
        this.packname = packname;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getUsemomory() {
        String size=Formatter.formatFileSize(App.appContext,usemomory);
        return size;
    }

    public void setUsemomory(long usemomory) {
        this.usemomory = usemomory;
    }
}
