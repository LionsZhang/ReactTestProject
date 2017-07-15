package app.reactproject.com.utill;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.view.inputmethod.InputMethodManager;

import com.example.administrator.lmw.LmwApp;

import java.util.List;


public class APPUtil {

    /**
     * 获取当前的应用名称
     *
     * @param context
     * @return
     */
    public static String getAppName(Context context) {
        String appName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            appName = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return appName;

    }

    /**
     * 创建桌面快捷方式
     *
     * @param context
     */
    public static void createShortCut(Context context) {
        // 发送广播的意图，要创建快捷图标了
        Intent intent = new Intent();
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        // 快捷方式 要包含3个重要的信息 1，名称 2.图标 3.干什么事情
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, APPUtil.getAppName(context));
        //	intent.putExtra( Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource( context.getResources(), R.drawable.ic_launcher ) );
        // 桌面点击图标对应的意图。
        Intent shortcutIntent = new Intent();
        shortcutIntent.setAction(Intent.ACTION_MAIN);
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClassName(context, context.getClass().getName());
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        context.sendBroadcast(intent);
    }

    /**
     * 获取版本号
     *
     * @param isV true V1.0.0 false 1.0.0
     * @return 当前应用的版本号
     * <p>
     * String
     * @auther lion
     */
    public static String getVersion(boolean isV) {
        try {
            PackageManager manager = LmwApp.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(LmwApp.getInstance().getPackageName(), 0);
            String version = info.versionName;
            if (isV) {
                version = String.format("%s%s", "V", version);
            }
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "can not get app version";
        }
    }

    /**
     * 获取渠道
     *
     * @return
     */
    public static String getChannel(Context context) {
        ApplicationInfo appInfo;
        String channel = "";
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Object msg = appInfo.metaData.get("UMENG_CHANNEL");
            channel = msg != null ? msg.toString() : "lmw";
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            channel = "lmw";
        }
        return channel;
    }

    /**
     * 跳转到应用市场
     *
     * @param context
     */
    public static void goAppStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id="
                            + context.getPackageName())));
        }
    }

    /**
     * 判断是否安装了某个应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstallPackage(Context context, String packageName) {
        boolean checkResult = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            checkResult = packageInfo != null;
        } catch (Exception e) {
            checkResult = false;
        }
        return checkResult;
    }


    /**
     * 隐藏键盘
     *
     * @param ac
     */
    public static void hideInputMethod(Activity ac) {
        ((InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(ac.getCurrentFocus().getWindowToken(), 0);
    }

    /**
     * 跳转到外部浏览器
     *
     * @param context
     * @param url
     */
    public static void goWebByUrl(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * 判断APP是否前台进程
     */
    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            if (runningProcesses != null) {
                for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                    //前台程序
                    if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                        for (String activeProcess : processInfo.pkgList) {
                            if (activeProcess.equals(context.getPackageName())) {
                                isInBackground = false;
                            }
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            if (taskInfo != null) {
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                if (componentInfo.getPackageName().equals(context.getPackageName())) {
                    isInBackground = false;
                }
            }
        }

        return isInBackground;
    }

}
