package com.company.immersionstatusbar;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

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
 * Created by clz on 2019/12/13
 */
public class MyObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void connectListener() {

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void disConnectListener() {

    }

}
