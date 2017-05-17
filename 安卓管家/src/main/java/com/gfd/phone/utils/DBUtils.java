package com.gfd.phone.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gfd.phone.app.App;
import com.gfd.phone.entity.Tel;
import com.gfd.phone.entity.TelInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DBUtils {

    private static final String DB_PATH = "data/data/com.gfd.phone/databases";
    private static final String DB_NAME = "commonnum.db";

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
            is = App.appContext.getAssets().open("db/commonnum.db");
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
    public static List<Tel> getTelServiceName() {
        List<Tel> tels = new ArrayList<>();
        //获取数据库
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + "/" + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from classlist;", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int id = cursor.getInt(cursor.getColumnIndex("idx"));
            Tel tel = new Tel(name, id);
            //添加到集合中
            tels.add(tel);
        }
        cursor.close();
        return tels;
    }

    /**
     * 获取指定服务的电话信息
     * @param idx
     * @return
     */
    public static List<TelInfo> getServiceTelInfos(int idx) {
        List<TelInfo> telInfos = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + "/" + DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from table" + idx + ";", null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            TelInfo telInfo = new TelInfo(name, number);
            telInfos.add(telInfo);
        }
        cursor.close();
        return telInfos;
    }

}
