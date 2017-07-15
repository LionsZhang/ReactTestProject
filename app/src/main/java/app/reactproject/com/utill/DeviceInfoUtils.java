package app.reactproject.com.utill;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.Locale;
import java.util.UUID;

/**
 * 提供设备信息的工具类
 *
 * @author lion
 */
public class DeviceInfoUtils {

    /**
     * 获取手机设备名
     *
     * @return
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取系统sdk版本号
     *
     * @return
     */
    public static String getSystemSdkVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取系统当前语言
     *
     * @return
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 获取手机imsi
     * 国际移动用户识别码
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }

    /**
     * 获取AndroidId
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID); // 获取AndroidId
    }

    /**
     * 获取wifi物理地址
     *
     * @param context
     * @return
     */
    public static String getWifiMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String wifiMac = info.getMacAddress();
        return wifiMac;
    }

    /**
     * 获取设备的唯一标识
     *
     * @param context
     * @return 唯一标识字符串
     */
    public static String getDeviceUUID(Context context) {
        String uuid = "";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            uuid = getIMEI(context);
            if (TextUtils.isEmpty(uuid)) {
                uuid = getWifiMac(context);
            }
            if (TextUtils.isEmpty(uuid)) {
                uuid = getAndroidId(context);
            }
            if (TextUtils.isEmpty(uuid)) {
                uuid = autoGenerateUUID(context);
            }
        } else
            uuid = autoGenerateUUID(context);
        return uuid;
    }

    /**
     * 获取一个uuid
     *
     * @param context
     * @return
     */
    public static String autoGenerateUUID(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("cacheMap", Context.MODE_PRIVATE);
        String uuid = preferences.getString("uuid", "");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            preferences.edit().putString("uuid", uuid);
        }
        return uuid;
    }

}
