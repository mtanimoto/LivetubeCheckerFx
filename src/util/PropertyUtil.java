package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class PropertyUtil {

    public static String loadPropertyValue(String path, String key) {
        try {
            File file = FileUtils.getFile(path);
            if (file.exists()) {
                Properties prop = new Properties();
                prop.load(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                return prop.getProperty(key);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static List<String> loadSettingFile(String path) {
        try{
            File file = FileUtils.getFile(path);
            return file.exists() ? FileUtils.readLines(file, "UTF-8") : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readssFile(String path) {
        return null;
    }

    public static boolean write(String path, String key, String value) {
        try {
            File file = FileUtils.getFile(path);
            if (file.exists()) {
                Properties prop = new Properties();
                FileOutputStream fos = new FileOutputStream(new File(path));

                prop.setProperty(key, value);
                prop.store(fos, "settting file"); // 書き込み
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
