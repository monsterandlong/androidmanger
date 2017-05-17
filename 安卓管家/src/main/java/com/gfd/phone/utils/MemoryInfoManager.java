package com.gfd.phone.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.gfd.phone.app.App;

import java.util.Map;


public class MemoryInfoManager {

    /**
     * 获取手机可用内存
     * @return bit
     */
    public static long getPhoneAvailableMemoeySize(){
        ActivityManager am = (ActivityManager) App.appContext.getSystemService(Context.ACTIVITY_SERVICE);
        //获取内存信息
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;//bit
    }

    /**
     * 获取手机总的内存大小
     * @return bit
     */
    public static long getPhoneTotalMemoeySize(){
        ActivityManager am = (ActivityManager) App.appContext.getSystemService(Context.ACTIVITY_SERVICE);
        //获取内存信息
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem;//bit
    }
    //手机的可用内置控件
    public static long getPhoneAvailable(String Path){
        StatFs statFs=new StatFs(Path);
        long availablesize = statFs.getAvailableBlocksLong();
        long availablecount = statFs.getBlockSizeLong();
        return availablesize*availablecount;
    }
    public static long getPhoneTotal(String Path){
        StatFs statFs=new StatFs(Path);
        long totalsize = statFs.getBlockCountLong();
        long totalcount = statFs.getBlockSizeLong();
        return totalsize*totalcount;
    }

    public static long rootavailable(){
        long rootsize = getPhoneAvailable(Environment.getRootDirectory().getAbsolutePath());
        long downsize = getPhoneAvailable(Environment.getDownloadCacheDirectory().getAbsolutePath());
        long externsize = getPhoneAvailable(Environment.getExternalStorageDirectory().getAbsolutePath());
        return rootsize+downsize+externsize;
    }
    public static long rootlTotal(){
        long Totalsize = getPhoneTotal(Environment.getRootDirectory().getAbsolutePath());
        long downsize = getPhoneTotal(Environment.getDownloadCacheDirectory().getAbsolutePath());
        long extervsize = getPhoneTotal(Environment.getExternalStorageDirectory().getAbsolutePath());
        return Totalsize+downsize+extervsize;
    }
    public static long sdavaiable(){
        Map<String, String> getenv = System.getenv();
        if (getenv.containsKey("SECOND_EXTERNAL")){
            String[] s=getenv.get("SECOND_EXTERNAL").split(":");
            long sdavailable = getPhoneAvailable(s[0]);
            return sdavailable;
        }
       return 0;
    }
    public static long sdtatal(){
        Map<String, String> getenv = System.getenv();
        if (getenv.containsKey("SECOND_EXTERNAL")){
            String[] s=getenv.get("SECOND_EXTERNAL").split(":");
            long sdtotal = getPhoneTotal(s[0]);
            return sdtotal;
        }
        return 0;
    }
}
