package com.gfd.phone.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Debug;

import com.gfd.phone.app.App;
import com.gfd.phone.entity.RunInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张志龙 on 2017/4/21.
 */

public class Program  {

    public static List<RunInfo> runInfoList;
    public static List<RunInfo> allUser;
    public static List<RunInfo> user;
    private static PackageInfo packageInfo;
    private static List<RunInfo> allAppInfo;
    private static List<RunInfo> allAppuser;
    private static List<RunInfo> allAppsystem;

    public static List<RunInfo> getAllinfo(){
        ActivityManager am= (ActivityManager) App.appContext.getSystemService(Context.ACTIVITY_SERVICE);
        PackageManager pm= App.appContext.getPackageManager();
        List<ActivityManager.RunningAppProcessInfo> allappinfo = am.getRunningAppProcesses();
        runInfoList = new ArrayList<>();
        allUser= new ArrayList<>();
        user = new ArrayList<>();

            for (ActivityManager.RunningAppProcessInfo appProcessInfo : allappinfo) {
                try{
                //每个进程代表的ID
                int id=appProcessInfo.pid;
                //进程的包名
                String packName=appProcessInfo.processName;
                //进程的级别
                int importance=appProcessInfo.importance;
                //判断是本APP时不显示
                if (packName.equals(App.appContext.getPackageName())){
                    continue;
                }
                if (importance>=ActivityManager.RunningAppProcessInfo.IMPORTANCE_SERVICE){
                    //得到内存信息
                    Debug.MemoryInfo[] memoryInfo = am.getProcessMemoryInfo(new int[]{id});
                    //得到内存的大小
                    long size=memoryInfo[0].getTotalPrivateDirty()*1024;
                    //APP的信息
                    ApplicationInfo appinfo = pm.getApplicationInfo(packName, 0);
                    //APP的图标
                    Drawable icon = appinfo.loadIcon(pm);
                    //APP的名字
                    String appName = appinfo.loadLabel(pm).toString();
                    RunInfo runInfo=new RunInfo(appName,icon,packName,size);
                    runInfoList.add(runInfo);
                    if ((appinfo.flags & appinfo.FLAG_SYSTEM)!=0){
                        allUser.add(runInfo);
                    }else {
                         user.add(runInfo);
                    }
                }
            }catch (Exception e){
            e.printStackTrace();
        }
            }

        return runInfoList;
    }
   public static List<RunInfo> getAllUser(){
       getAllinfo();
    return allUser;
   }
   public static  List<RunInfo> getUser(){
      getAllinfo();
    return user;
   }
   //删除软件的代码
   public static void killApp(String name){
       ActivityManager am= (ActivityManager) App.appContext.getSystemService(Context.ACTIVITY_SERVICE);
       am.killBackgroundProcesses(name);
   }

   public static List<RunInfo> getAppInfo(){
       allAppInfo = new ArrayList<>();
       allAppuser = new ArrayList<>();
       allAppsystem = new ArrayList<>();
       PackageManager pm=App.appContext.getPackageManager();
       List<ApplicationInfo> inAlldAppInfo = pm.getInstalledApplications(PackageManager.GET_ACTIVITIES
               | PackageManager.MATCH_UNINSTALLED_PACKAGES);
       for (ApplicationInfo applicationInfo : inAlldAppInfo) {
           Drawable icon = applicationInfo.loadIcon(pm);
           String name = applicationInfo.loadLabel(pm).toString();
           String packName = applicationInfo.packageName;
           try {
               packageInfo = pm.getPackageInfo(packName, 0);
           } catch (PackageManager.NameNotFoundException e) {
               e.printStackTrace();
           }
           String versionName = packageInfo.versionName;
           RunInfo runInfo=new RunInfo(name,icon,packName,versionName);
           allAppInfo.add(runInfo);
           if ((applicationInfo.flags&applicationInfo.FLAG_SYSTEM)!=0){
              allAppuser.add(runInfo);
           }else {
              allAppsystem.add(runInfo);
           }
       }
       return allAppInfo;
   }
   public static List<RunInfo> getAppSystem(){
       getAppInfo();
       return allAppuser;
   }
    public static List<RunInfo> getAppUser(){
        getAppInfo();
        return allAppsystem;
    }
}
