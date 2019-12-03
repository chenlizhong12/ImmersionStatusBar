package com.company.immersionstatusbar;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {
    public static final int MSG = 1000;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new Messenger(mHandler).getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    Log.i("clz", "收到客户端消息" + msg.getData().get("msg"));
                    Messenger messenger = msg.replyTo;
                    Message message = Message.obtain(null, MSG);
                    Bundle bundle = new Bundle();
                    bundle.putString("reporterDate", "这里是服务器，收到消息了");
                    message.setData(bundle);
                    try {
                        messenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };
}
