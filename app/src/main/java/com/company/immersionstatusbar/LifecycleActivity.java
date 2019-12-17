package com.company.immersionstatusbar;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "clz";
    private LifecycleRegistry mLifecycleRegistry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        Lifecycle.State currentState = mLifecycleRegistry.getCurrentState();
        Log.i(TAG, "onCreate: " + currentState);
//        getLifecycle().addObserver(new MyObserver());
    }

//    public class MyObserver implements LifecycleObserver {
//        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//        public void connectListener() {
//            Log.i(TAG, "connectListener: " + "onResume");
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//        public void disConnectListener() {
//            Log.i(TAG, "disConnectListener: " + "onPause");
//        }
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: " + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: " + "onPause");
    }

    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
