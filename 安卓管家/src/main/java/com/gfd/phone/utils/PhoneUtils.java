package com.gfd.phone.utils;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by 张志龙 on 2017/4/26.
 */

public class PhoneUtils {

    //获取CPU的名称
    public static String getCpuName(){
        try {
            FileReader fr=new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text=br.readLine();
            String[] array=text.split(":\\s+",2);
            return array[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static int getCpuCount() {
        class CpuFilter implements FileFilter {
            public boolean accept(File pathname) {
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new CpuFilter());
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
    public static String getRootFBL(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String rootFBL=metrics.widthPixels+ "*" + metrics.heightPixels;
        return rootFBL;
    }
    public static boolean isRoot() {
        boolean bool = false;

        try {
            if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
                bool = false;
            } else {
                bool = true;
            }
        } catch (Exception e) {

        }
        return bool;
    }
    public static String getMaxPhotoSize() {
        String maxSize = "";
        Camera camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> pictureSizes = parameters.getSupportedPictureSizes();
        maxSize=pictureSizes.get(0).width+"*"+pictureSizes.get(0).height;
        return maxSize;
    }

}
