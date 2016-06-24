package com.quick.charles.quick_imageloader.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.io.Closeable;
import java.util.List;

/**
 * Created by charles on 2016/6/19.
 */
public class MyUtils {
    public static String getProcessName(Context cxt,int pid){
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if(runningApps==null){
            return  null;
        }
        for(ActivityManager.RunningAppProcessInfo procInfo:runningApps){
            if(procInfo.pid == pid){
                return  procInfo.processName;
            }
        }
        return  null;
    }

    public static void close(Closeable closeable){
        try {
            if(closeable!=null){
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DisplayMetrics getScreenMetrics(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return  dm;
    }

    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public static void executeInThread(Runnable runnable) {
        new Thread(runnable).start();
    }
}
