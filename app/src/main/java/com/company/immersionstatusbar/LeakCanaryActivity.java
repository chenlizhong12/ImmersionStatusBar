package com.company.immersionstatusbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.InvocationTargetException;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 用于对于检测内存泄漏，使用Square公司的LeakCanary,如果检测到内存泄漏，将会有通知，里面有详细的描述，根据描述就可以找到内存泄漏的地方。
 */
public class LeakCanaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        ClassLoader loader = MainActivity.class.getClassLoader();
        while (loader != null) {
            Log.i("clz", "onCreate: " + loader);
            loader = loader.getParent();
        }
        try {
            BangScreenActivity.class.getDeclaredMethod("show").invoke(",");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        findViewById(R.id.bt_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeakCanaryActivity.this, BangScreenActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LeakThread leakThread = new LeakThread();
        leakThread.start();
    }

    static class LeakThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(6 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("clz", "onDestroy: " + "销毁");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);//1用于监测是否有内存泄漏
        refWatcher.watch(this);
    }
}

