package com.zjh.directorysp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SPSaveFile {
    // 保存多个姓名和电话到 data.xml 文件中
    public static boolean saveUserInfo(Context context, Map<String, String> userInfo) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        for (String key : userInfo.keySet()) {
            edit.putString(key, userInfo.get(key));
        }
        edit.commit();
        return true;
    }

    // 从 data.xml 文件中获取存储的多个 QQ 账号和密码
    public static Map<String, String> getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Map<String, String> userMap = (Map<String, String>) sp.getAll();
        if (userMap == null) {
            userMap = new HashMap<String, String>();
        }
        for (String key : userMap.keySet()) {
            userMap.put(key, sp.getString(key, null));
        }
        return userMap;
    }

    // 增加、删除、查询修改 data.xml 文件中的个人信息
    public static void updateUserInfo(Context context, Map<String, String> userInfo) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        for (String key : userInfo.keySet()) {
            edit.putString(key, userInfo.get(key));
        }
        edit.commit();
    }

    public static void removeUserInfo(Context context, String name) {
        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        Map<String, String> userMap = (Map<String, String>) sp.getAll();
        for (String key : userMap.keySet()) {
            if (key.equals(name)) {
                userMap.remove(key);
            }
        }
        for (String key : userMap.keySet()) {
            edit.putString(key, userMap.get(key));
        }
        edit.commit();
    }
}
