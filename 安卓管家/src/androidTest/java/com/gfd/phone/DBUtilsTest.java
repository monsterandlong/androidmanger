package com.gfd.phone;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.gfd.phone.entity.Tel;
import com.gfd.phone.utils.DBUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * 数据库工具类测试类
 */
@RunWith(AndroidJUnit4.class)
public class DBUtilsTest {

    @Test
    public void query_test() {
        final List<Tel> telServiceName = DBUtils.getTelServiceName();
        Log.e("db", "size = " + telServiceName.size());
    }

}
