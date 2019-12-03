package com.company.immersionstatusbar;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * ┏┓　   ┏┓
 * ┏┛┻━━━━━┛┻━┓
 * ┃　　　　   ┃
 * ┃　━　━　   ┃
 * ████━████   ┃
 * ┃　　　　   ┃
 * ┃　 ┻　    ┃
 * ┗━┓      ┏━┛
 * 　┃      ┃
 * 　┃ 0BUG ┗━━━┓
 * 　┃0Error     ┣┓
 * 　┃0Warning   ┏┛
 * 　┗┓┓┏━┳┓┏┛ ━
 * 　　┃┫┫ ┃┫┫
 * 　　┗┻┛ ┗┻┛
 * Created by clz on 2019/11/29
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initEvent();
    }

    private void initEvent() {
        int pid = Process.myPid();
        Log.i("clz", "onCreate: " + "进程被创建了" + pid);
        String progressName = "";
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appinfo : activityManager.getRunningAppProcesses()) {
            if (appinfo.pid == pid) {
                progressName = appinfo.processName;
            }
        }
        if ("com.company.immersionstatusbar".equals(progressName)) {
            Log.i("clz", "processName=" + progressName + "-----work");
        } else {
            Log.i("clz", "processName=" + progressName + "-----work");
        }
    }
}
