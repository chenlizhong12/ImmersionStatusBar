package com.company.immersionstatusbar;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        initEvent();
        refWatcher= setupLeakCanary();
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

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication leakApplication = (MyApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }
}
