package app.reactproject.com.utill;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class NetUtil {


    public static final int STATE_INVALIDE_NETWORK = -1; // 无网络
    public static final int STATE_UNKNOWN_NEWORK = 0; // 未知网络
    public static final int STATE_MOBILE_NETWORK = 1; // 移动网络
    public static final int STATE_WIFI_NETWORK = 2; // wifi网络
    public static final int STATE_ETHERNET_NETWORK = 3; // 以太网
    public static final int STATE_2G_NETWORK = 4;
    public static final int STATE_3G_NETWORK = 5;
    public static final int STATE_4G_NETWORK = 6;

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return true：网络可用 false：网络不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null) {
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return true：网络可用 false：网络不可用
     */
    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
    /**
     * 检查网络是否可用
     *
     * @param context
     * @return true：网络可用 false：网络不可用
     */
    public static boolean hasNet(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isAvailable());

    }

    /**
     * 获取网络状态
     *
     * @param context
     * @return
     */
    public static int getNetWorkState(Context context) {

        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (null == networkInfo) {
            return STATE_INVALIDE_NETWORK;
        }

        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {

            return STATE_MOBILE_NETWORK;

        } else if (nType == ConnectivityManager.TYPE_WIFI) {

            return STATE_WIFI_NETWORK;

        } else if (nType == ConnectivityManager.TYPE_ETHERNET) {
            return STATE_ETHERNET_NETWORK;
        }

        return STATE_UNKNOWN_NEWORK;

    }

    /**
     * 得到当前的手机网络类型
     *
     * @param context
     * @return
     */
    public static int getCurrentNetType(Context context) {
        int type = STATE_INVALIDE_NETWORK;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = STATE_INVALIDE_NETWORK;
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = STATE_WIFI_NETWORK;
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = STATE_2G_NETWORK;
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = STATE_3G_NETWORK;
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = STATE_4G_NETWORK;
            }
        }
        return type;
    }

    /**
     * 得到当前的手机网络类型
     *
     * @param context
     * @return
     */
    public static String getCurrentNetName(Context context) {
        String type = "";
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            type = "";
        } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
            type = "WIFI";
        } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
            int subType = info.getSubtype();
            if (subType == TelephonyManager.NETWORK_TYPE_CDMA
                    || subType == TelephonyManager.NETWORK_TYPE_GPRS
                    || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
                type = "2G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                    || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                    || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
                type = "3G";
            } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
                type = "4G";
            }
        }
        return type;
    }

    /**
     * 获取运营商名字
     *
     * @param context
     * @return
     */
    public static String getOperatorName(Context context) {
        String ProvidersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (TextUtils.isEmpty(IMSI)) {
            return ProvidersName;
        }
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "电信";
        } else {
            ProvidersName = "其他";
        }
        return ProvidersName;

    }

}
