package com.bamboo.common.utils;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * log工具类
 */
public class LogUtil {

    private static final String TAG = "LogUtil";

    private final static boolean isLog = true;

    public  void d(String TAG, String msg) {
        if (isLog) {
            Log.d(TAG, msg);
//            saveLog2File(msg);
        }
    }

    public static void w(String TAG, String msg) {
        if (isLog) {
            Log.w(TAG, msg);
//            saveLog2File(msg);
        }
    }

    public static void i(String TAG, String msg) {
        if (isLog) {
            Log.w(TAG, msg);
//            saveLog2File(msg);
        }
    }

    public static void v(String TAG, String msg) {
        if (isLog) {
            Log.w(TAG, msg);
//            saveLog2File(msg);
        }
    }

    public static void e(String TAG, String msg) {
        if (isLog) {
            Log.e(TAG, msg);
//            saveLog2File(msg);
        }
    }

    private static void saveLog2File(final String log) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String d = sdf.format(new Date());
                String result = d + ": " + log;
                StringBuffer sb = new StringBuffer();
                sb.append(result);
                try {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    long timetamp = System.currentTimeMillis();
                    String time = formatter.format(new Date());
                    String fileName = time + ".log";
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String path = Environment.getExternalStorageDirectory().toString() + File.separator + "GCS" + File.separator + "LOG";
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(new File(path + File.separator + fileName), true);
                        OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
                        osw.write(sb.toString());
                        osw.write("\n");
                        osw.flush();
                        osw.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.e(TAG, "an error occured while writing file ...");
                }
                return null;
            }
        }.execute();
    }
}
