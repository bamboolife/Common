package com.bamboo.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

import androidx.annotation.NonNull;

/**
 * 项目名称：CommonLibrary
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-01-19 20:07
 * 描述：
 */
public class NetWorkUtils {

    /**
     * 网络是否已连接
     *
     * @return true:已连接 false:未连接
     */
    public static boolean isConnected(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET);
                }
            } else {
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * Wifi是否已连接
     *
     * @return true:已连接 false:未连接
     */
    public static boolean isWifiConnected(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
                }
            } else {
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return false;
    }

    /**
     * 是否为流量
     * @param context
     * @return
     */
    public static boolean isMobileData(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                NetworkCapabilities networkCapabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                }
            } else {
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        return false;
    }

//    public static NetworkType getNetworkType(Context context) {
//        Network
//        NetworkType netType = NetworkType.NETWORK_NO;
//        NetworkInfo info = getActiveNetworkInfo(context);
//        if (info != null && info.isAvailable()) {
//
//            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
//                netType = NetworkType.NETWORK_WIFI;
//            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
//                switch (info.getSubtype()) {
//
//                    case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
//                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
//                    case TelephonyManager.NETWORK_TYPE_UMTS:
//                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
//                    case TelephonyManager.NETWORK_TYPE_HSDPA:
//                    case TelephonyManager.NETWORK_TYPE_HSUPA:
//                    case TelephonyManager.NETWORK_TYPE_HSPA:
//                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
//                    case TelephonyManager.NETWORK_TYPE_EHRPD:
//                    case TelephonyManager.NETWORK_TYPE_HSPAP:
//                        netType = NetworkType.NETWORK_3G;
//                        break;
//
//                    case TelephonyManager.NETWORK_TYPE_LTE:
//                    case TelephonyManager.NETWORK_TYPE_IWLAN:
//                        netType = NetworkType.NETWORK_4G;
//                        break;
//
//                    case TelephonyManager.NETWORK_TYPE_GSM:
//                    case TelephonyManager.NETWORK_TYPE_GPRS:
//                    case TelephonyManager.NETWORK_TYPE_CDMA:
//                    case TelephonyManager.NETWORK_TYPE_EDGE:
//                    case TelephonyManager.NETWORK_TYPE_1xRTT:
//                    case TelephonyManager.NETWORK_TYPE_IDEN:
//                        netType = NetworkType.NETWORK_2G;
//                        break;
//                    default:
//                        String subtypeName = info.getSubtypeName();
//                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
//                                || subtypeName.equalsIgnoreCase("WCDMA")
//                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
//                            netType = NetworkType.NETWORK_3G;
//                        } else {
//                            netType = NetworkType.NETWORK_UNKNOWN;
//                        }
//                        break;
//                }
//            } else {
//                netType = NetworkType.NETWORK_UNKNOWN;
//            }
//        }
//        return netType;
//    }

}
