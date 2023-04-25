package com.zjh.directoryfile;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class SaveFile {
    public static boolean saveUserInfo(Context context, Map<String, String> userInfo) {
        try {
            FileOutputStream fos = context.openFileOutput("data.txt", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userInfo);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Map<String, String> getUserInfo(Context context) {
        try {
            InputStream inputStream = context.openFileInput("data.txt");
            ObjectInputStream oin = new ObjectInputStream(inputStream);
            Map<String, String> userInfo = (Map<String, String>) oin.readObject();
            oin.close();
            inputStream.close();
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

