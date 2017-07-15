package app.reactproject.com.utill;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by lion on 2017/3/23.
 */

public class FileUtil {

    /**
     * 获取全路径中的文件拓展名
     *
     * @param file 文件
     * @return 文件拓展名
     */
    public static String getFileExtension(File file) {
        if (file == null) return null;
        return getFileExtension(file.getPath());
    }
    /**
     * 获取全路径中的文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }

    /**
     * 通过Url获取文件名称
     *
     * @param url
     * @return
     */
    public static String getFileNameByUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
    /**
     * 创建文件目录
     *
     * @param path 目录
     * @param dir 目录
     * @return
     */
    public static File getExternalCacheDir(Context context, String path, String dir) {
        File dataDir = new File(Environment.getExternalStorageDirectory(), path);
        File appCacheDir = new File(dataDir, dir);
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                ALLog.w("Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                ALLog.i("Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }
}
