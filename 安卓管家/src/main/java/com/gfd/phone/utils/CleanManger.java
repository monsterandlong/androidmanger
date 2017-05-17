package com.gfd.phone.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.gfd.phone.app.App;
import com.gfd.phone.entity.CleanInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张志龙 on 2017/5/4.
 */

public class CleanManger  {
    private static final String DB_PATH = "data/data/com.gfd.phone/databases";
    private static final String DB_NAME = "clearpath.db";

    /** 只执行一次 */
    static {
        copyDb();
    }

    /**
     * 复制数据库到应用存储路径
     */
    private static void copyDb() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = App.appContext.getAssets().open("db/clearpath.db");
            File path = new File(DB_PATH);
            if (!path.exists()) path.mkdirs();
            fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) fos.close();
                if (is != null) is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取所有的服务类型的名字
     *
     * @return
     */
    public static List<CleanInfo> getAllInfo() {
        List<CleanInfo> CleanInfos = new ArrayList<>();
        //获取数据库
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + "/" + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from softdetail;", null);
        while (cursor.moveToNext()) {
            String filepath= cursor.getString(cursor.getColumnIndex("filepath"));
            String name = cursor.getString(cursor.getColumnIndex("softEnglishname"));
            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + filepath);
            if (file.exists()) {
                long size=file.length();
                CleanInfo CleanInfo = new CleanInfo(name, file,size);
                //添加到集合中
                CleanInfos.add(CleanInfo);
            }
        }
        cursor.close();
        return CleanInfos;
    }



}
